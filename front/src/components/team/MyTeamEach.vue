<template>
<div class='eachCtst' @click="emitSelectedPost">
    <team-btn class="applyBtn" :status="getStatus" :team="T" @refreshList="emitRefreshList"/>
    <span>{{ T.title }}</span>
    <button class='updateBtn' v-show='isLeader' @click.stop='updateMove'>수정</button>
    <button class='delBtn' v-show='isLeader' @click.stop='deletePost'>삭제</button>
    <span style='color:gray; margin-left: 40px;'>{{ 1 + T.approvalList.filter(x => x.approvalFlag).length }} / {{ T.memberMax }}</span><br>
    <span style='color:gray;'>{{ T.username }}</span>

    <div class="container" v-show="$props.isExpand">
        <div class="row">

            <div id='contentArea' class="col-7" style="padding: 10px; white-space:pre-line;">
                <!-- 일단은 고정해서 개발하고 나중에 유동적으로 너비 조절하쟈...ㅠㅍㅍ -->
                {{ T.content }}
            </div>

            <div class="col-5">
                <div v-for="(memb, i) in T.approvalList.filter(x => x.approvalFlag)" :key="i">
                    <div :class="{invisible: !(isLeader || isMember)}">
                        {{ memb.memberUsername }} <br> [쪽지 + 프로필뷰]
                        <button class='btn btn-danger' :class="{invisible: !isLeader}" @click.stop="kick(memb)">강퇴</button>
                    </div>
                </div>

                <hr>

                <div v-for="(applic, j) in T.approvalList.filter(x => !x.approvalFlag)" :key="`B${j}`"> <!--키중복경고 피하기위한 키꼼수-->
                    <div :class="{invisible: !isLeader}">
                        {{ applic.memberUsername }} <br> [쪽지 + 프로필뷰]
                        <button class='btn btn-primary' :class="{invisible: !isLeader}" @click.stop="assign(applic)">승인</button>
                        <button class='btn btn-danger' :class="{invisible: !isLeader}" @click.stop="reject(applic)">거절</button>
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

const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
const URL_TEAM = 'http://i3a202.p.ssafy.io:8181/api/teams'

export default {
    name: 'myTeamEach',
    props: ['eachTeam', 'isExpand'],
    //emit: ['setSelected','refreshList'],
    components: {TeamBtn,},
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