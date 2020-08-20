<template>
<div class='eachCtst' :class="{expanded: isExpand}" @click="emitSelectedPost">
    <div class="applyBtn">
        <span style='color:gray; margin-right: 40px;'>{{ 1 + T.approvalList.filter(x => x.approvalFlag).length }} / {{ T.memberMax }}</span>
        <team-btn :status="getStatus" :team="T" @refreshList="emitRefreshList"/>
    </div>

    <h5 class="minititle">{{ T.title }}</h5>

    <ProfileModal :username='T.username' size="60"/>
    <span style='color:gray;'>{{ T.username }}</span>
    <message-button :receiver="T.username"/>

    <div class="container" v-show="$props.isExpand">
        <hr>
        <div class="row">

            <div id='contentArea' class="col-7" style="padding: 10px; white-space:pre-line;">
                <!-- 일단은 고정해서 개발하고 나중에 유동적으로 너비 조절하쟈...ㅠㅍㅍ -->
                <div class='btn-wrapper'>
                    <router-link :to="'/contest/'+T.contestNo" style="margin: 0 10px;text-decoration: none;">공모전 상세보기</router-link>
                    <i class="fas fa-pen _icon" v-show='isLeader' @click.stop='updateMove' type="button" title="수정"/>
                    <i class="fas fa-trash-alt _icon" v-show='isLeader' @click.stop='deletePost' type="button" title="삭제"/>
                </div>
                {{ T.content }}
            </div>

            <div class="col-5" style="padding: 20px 0 0 20px;">
                <div style="min-height: 100px;">
                    <h5 class="minititle">팀원 목록</h5>
                    <div v-if="!(isLeader||isMember)" class="restrict_msg">팀원에게 제공되는 정보입니다.</div>
                    <div v-if="(isLeader||isMember)">
                        <div v-for="(memb, i) in T.approvalList.filter(x => x.approvalFlag)" :key="i">
                            <div :class="{invisible: !(isLeader || isMember)}" class="profilewrapper">
                                <ProfileModal :username='memb.memberUsername' size="60"/>
                                <span style="color: gray;">{{ memb.memberUsername }}</span>
                                <message-button :receiver="memb.memberUsername"/>
                                <span style="flex-grow:1;text-align:right;"><i class="fas fa-times kickoutbtn" :class="{invisible: !isLeader}" @click.stop="kick(memb)" title="강퇴"/></span>
                                
                            </div>
                        </div>
                    </div>
                </div>

                <hr :class="{invisible: !isLeader}">

                <div style="min-height: 100px;">
                    <h5 :class="{invisible: !isLeader}" class="minititle">지원자 목록</h5>
                    <div v-if="isLeader">
                        <div v-for="(applic, j) in T.approvalList.filter(x => !x.approvalFlag)" :key="`B${j}`" style="margin-bottom:10px;"> <!--키중복경고 피하기위한 키꼼수-->
                            <!-- <div :class="{invisible: !isLeader}" style="display:flex; align-items:center;"> -->
                            <div :class="{invisible: !isLeader}" class="profilewrapper">
                                <ProfileModal :username='applic.memberUsername' size="60" style="flex-grow:0;"/>
                                    <span style="color: gray;">{{ applic.memberUsername }}</span>
                                    <message-button :receiver="applic.memberUsername" style="margin:0; margin-left:10px; "/>
                                    <!-- <br> -->
                                <div style="flex-grow:1;text-align:right;">
                                    <i class="fas fa-check assignicon" @click.stop="assign(applic)" title="승인"/>
                                    <i class="fas fa-times rejecticon" @click.stop="reject(applic)" title="거절"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- end 팀원 & 지원자 목록-->

        </div>
    </div>
</div>
</template>

<script>
import STAT from '@/constants/TeamStatus'
import axios from 'axios'
import TeamBtn from '@/components/team/TeamBtn.vue'
import MessageButton from '@/components/message/MessageButton.vue'
import ProfileModal from '@/components/accounts/ProfileModal.vue'
import { mapGetters } from 'vuex'

const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
const URL_TEAM = 'http://i3a202.p.ssafy.io:8181/api/teams'

export default {
    name: 'myTeamEach',
    props: ['eachTeam', 'isExpand'],
    //emit: ['setSelected','refreshList'],
    components: {TeamBtn,MessageButton,ProfileModal},
    data() {return {
        STAT: STAT,
    }},
    computed: {
        T() {return this.$props.eachTeam},  //for aliasing $props.eachTeam as T
        getStatus() {
            if (!this.isLogin) { //로그인 안했으면, 로그인해
                return STAT.GUEST;

            } else if (this.isLeader) { //리더면, 신청버튼 비활성화 또는 수정삭제
                return STAT.LEADER;

            } else if (this.isInApprovalList) { //(1)리스트에 있을 경우,
                if (this.isMember) {    //멤버라면 클릭시 탈퇴
                    return STAT.MEMBER;

                } else {    //리더승인대기중이어도 클릭시 탈퇴
                    return STAT.APPLICANT;
                }
            } else {    //(2)리스트에 없으면 신청할 건데
                if (this.isListAvailable) { //빈자리가 있으면 신청가능
                    return STAT.APPLIABLE;

                } else {    //꽉찼으면 신청불가(비활성화)
                    return STAT.FULL;
                }
            }
        },
        ...mapGetters(['isLogin', 'username']),
        isLoggedIn() {
            return this.$cookies.isKey('auth-token');
        },
        userName() {
            try {
                let ca = this.$cookies.get('auth-token');
                let base64Url = ca.split('.')[1];
                let decodedValue = JSON.parse(window.atob(base64Url));
                return decodedValue.sub;
            } catch {
                return '';
            }
        },
        isLeader() {
            return this.username == this.T.username;
        },
        isInApprovalList() {
            return this.T.approvalList.filter(x => x.memberUsername == this.username).length !== 0;
        },
        isListAvailable() {
            return (1 + this.T.approvalList.filter(x => x.approvalFlag).length) < this.T.memberMax;
        },
        isMember() {
            return this.T.approvalList.filter(x => x.memberUsername == this.username && x.approvalFlag).length > 0;
        },
    },
    methods: {
        emitSelectedPost() {this.$emit('setSelected', this.T.postNo);},
        emitRefreshList() {this.$emit('refreshList')},
        updateMove() {
            this.$router.push('/contest/' + this.T.contestNo + '/teams/' + this.T.postNo + '/update');
        },
        deletePost() {
            if (confirm('정말 삭제하시겠습니까?')) {
                axios.delete(URL_PART, {
                        headers: {
                            Authorization: this.$cookies.get('auth-token')
                        },
                        data: {
                            "username": this.T.username,
                            "postNo": this.T.postNo
                        }
                    })
                    .then(response => {
                        // console.dir(response);
                        if (response.status == 200) {
                            this.$emit('refreshList');
                        }
                    })
                    .catch(error => console.log(error));
            }
        },
        assign(applicant) {
            if (this.isListAvailable == false) {
                alert('더 이상 멤버를 추가할 수 없습니다.');
                return;
            }
            if (confirm(`정말 ${applicant.memberUsername}을(를) 멤버로 받아들이겠습니까?`)) {
                const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
                let dto = {
                    "approvalFlag": true,
                    "leaderUsername": applicant.leaderUsername,
                    "memberUsername": applicant.memberUsername,
                    "teamId": applicant.teamId
                }
                axios.patch(URL_TEAM, dto, config)
                    .then(response => {
                        // console.dir(response);
                        if (response.status == 200) {
                            this.$emit('refreshList');
                            // 알림 코드
                            this.$store.dispatch('sendSystemMessage', this.msgDtoBuilder(applicant, '회원님 팀원승인'));
                        }
                    }).catch(error => console.log(error));
            }
        },
        kick(memb) {
            if (confirm(`정말 ${memb.memberUsername}을(를) 강퇴하시겠습니까?`)) {
                const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
                axios.delete(URL_TEAM, {
                    headers: config.headers,
                    data: memb
                }).then(response => {
                    // console.dir(response);
                    if (response.status == 200) {
                        this.$emit('refreshList');
                        // 알림 코드
                        this.$store.dispatch('sendSystemMessage', this.msgDtoBuilder(memb, '회원님 팀원강퇴'));
                    }
                }).catch(error => console.log(error));
            }
        },
        reject(applicant) {
            if (confirm(`정말 ${applicant.memberUsername}의 신청을 거절하시겠습니까?`)) {
                const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
                axios.delete(URL_TEAM, {
                    headers: config.headers,
                    data: applicant
                }).then(response => {
                    // console.dir(response);
                    if (response.status == 200) {
                        this.$emit('refreshList');
                        // 알림 코드
                        this.$store.dispatch('sendSystemMessage', this.msgDtoBuilder(applicant, '회원님 승인거절'));
                    }
                }).catch(error => console.log(error));
            }
        },
        msgDtoBuilder({ memberUsername }, action) {
            return { receiver: memberUsername, title: this.T.title, contestNo: this.T.contestNo, action: action };
        },
    },
}
</script>

<style scoped>
.flex-container {
    display: flex;
}

.expanded {
    background-color: #eeeeee;
}

.eachCtst {
    width: 80%;
    margin: 5px auto;
    padding: 30px 30px 10px 30px;
    border-radius: 20px;
    border: 1px solid #eeeeee;
    text-align: left;
    vertical-align: center;
}

.eachCtst:hover {
    background-color: #eeeeee;
}

.createBtn {
    margin: 10px;
    padding: 5px 10px;
    border-radius: 10px;
    background-color: #41B883;
    color: white;
}

.createBtn:hover {
    background-color: #48A77C;
}

.applyBtn {
    float: right;
}

.invisible {
    display: none;
}

.confirmBtn {
    margin: auto auto;
    min-width: 60px;
    height: 27px;
    background-color: rgb(48, 116, 161);
}

.btn-wrapper {
    border: 1px solid lightgray;
    /* background-color: #eeeeee; */
    margin: 10px;
    border-radius: 15px;
    min-height: 50px;
    line-height: 50px;
    margin-bottom: 30px;
    padding-left: 10px;
}

._icon {
    margin: 5px 5px;
    padding: 10px;
    border: 1px solid transparent;
    border-radius: 10px;
}

._icon:hover {
    border: 1px solid lightgray;
}

.profilewrapper {
    /* display: inline-flex; */
    display: flex;
    align-items: center;
}

.profilewrapper * {
    flex-grow: 0;
    flex-shrink: 1;
}

.kickoutbtn {
    flex-grow: 1;
    text-align: right;
    padding: 5px;
}
.kickoutbtn:hover {
    color: #C82333;
}
.assignicon {
    padding: 7px;
    margin-left: 10px;
}
.assignicon:hover {
    color: #41B883;
}
.rejecticon {
    padding: 7px;
}
.rejecticon:hover {
    color: #C82333;
}
.minititle {
    border-left: 10px solid lightgray;
    margin-bottom: 15px;
    padding: 10px;
}

.asdf {
    flex: 1 1 0;
}

.qwer {
    flex: 0 1 400px;
}

.restrict_msg {
    color: gray;
    text-align: center;
    min-height: 70px;
    line-height: 70px;
    vertical-align: middle;
}
</style>