<template>
<div class='eachCtst' @click="emitSelectedPost">
    <div class="applyBtn">
        <span style='color:gray; margin-right: 40px;'>{{ 1 + T.approvalList.filter(x => x.approvalFlag).length }} / {{ T.memberMax }}</span>
        <team-btn :status="getStatus" :team="T" @refreshList="emitRefreshList"/>
    </div>

    <!-- <span>{{ T.title }}</span> -->
    <h5>{{ T.title }}</h5>

    <ProfileModal :username='T.username' />
    <span style='color:gray;'>{{ T.username }}</span>
    <message-button :receiver="T.username"/>

    <div class="container" v-show="$props.isExpand">
        <hr>
        <div class="row">

            <div id='contentArea' class="col-7" style="padding: 10px; white-space:pre-line;">
                <!-- 일단은 고정해서 개발하고 나중에 유동적으로 너비 조절하쟈...ㅠㅍㅍ -->
                <router-link :to="'/contest/'+T.contestNo">공모전 이동</router-link><br><br>
                {{ T.content }}<br><br>
                <div class='btn-wrapper'>
                    <button class='updateBtn' v-show='isLeader' @click.stop='updateMove'>수정</button>
                    <button class='delBtn' v-show='isLeader' @click.stop='deletePost'>삭제</button>
                </div>
            </div>

            <div class="col-5">
                <h5 :class="{invisible: !(isLeader || isMember)}">팀원 목록</h5>
                <div v-for="(memb, i) in T.approvalList.filter(x => x.approvalFlag)" :key="i">
                    <div :class="{invisible: !(isLeader || isMember)}">
                        <ProfileModal :username='memb.memberUsername' />
                        <span style="color: gray;">{{ memb.memberUsername }}</span>
                        <message-button :receiver="memb.memberUsername"/>
                        <button class='btn btn-danger btn-sm' :class="{invisible: !isLeader}" @click.stop="kick(memb)">강퇴</button>
                    </div>
                </div>

                <hr :class="{invisible: !isLeader}">

                <h5 :class="{invisible: !isLeader}">지원자 목록</h5>
                <div v-for="(applic, j) in T.approvalList.filter(x => !x.approvalFlag)" :key="`B${j}`"> <!--키중복경고 피하기위한 키꼼수-->
                    <div :class="{invisible: !isLeader}">
                        <ProfileModal :username='applic.memberUsername' />
                        <span style="color: gray;">{{ applic.memberUsername }}</span>
                        <message-button :receiver="applic.memberUsername"/>
                        <br>
                        <button class='btn btn-primary btn-sm' :class="{invisible: !isLeader}" @click.stop="assign(applic)">승인</button>
                        &nbsp;
                        <button class='btn btn-danger btn-sm' :class="{invisible: !isLeader}" @click.stop="reject(applic)">거절</button>
                    </div>
                </div>
            </div>

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
            if (!this.isLoggedIn) { //로그인 안했으면, 로그인해
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
            return this.userName == this.T.username;
        },
        isInApprovalList() {
            return this.T.approvalList.filter(x => x.memberUsername == this.userName).length !== 0;
        },
        isListAvailable() {
            return (1 + this.T.approvalList.filter(x => x.approvalFlag).length) < this.T.memberMax;
        },
        isMember() {
            return this.T.approvalList.filter(x => x.memberUsername == this.userName && x.approvalFlag).length > 0;
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
                    }
                }).catch(error => console.log(error));
            }
        },

    },
}
</script>

<style scoped>
.flex-container {
    display: flex;
}

.eachCtst {
    width: 80%;
    /* height: 80px; */
    margin: 5px auto;
    padding: 10px 30px;
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
    /* border-radius: 10px;
    background-color: pink;
    color: white;
    padding: 7px; */
}

.delBtn {
    background-color: red;
    color: white;
    border-radius: 10px;
    padding: 5px;
    margin: 5px;
}

.updateBtn {
    background-color: gray;
    color: white;
    border-radius: 10px;
    padding: 5px;
    margin: 5px;
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
</style>