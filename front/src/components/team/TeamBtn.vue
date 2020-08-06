<template>
    <button class='btn' :style="'background-color: ' + getStatus.COLOR" :disabled='!getStatus.BTN' @click.stop='action'>
        {{getStatus.TEXT}}
    </button>
</template>

<script>
import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/teams'
// const URL = 'http://localhost:8181/api/teams'

const STAT = {
    GUEST: {C: 'guest', BTN: true, COLOR: 'pink', TEXT: '신청',},
    LEADER: {C: 'leader', BTN: false, COLOR: 'gray', TEXT: '내게시물',},
    MEMBER: {C: 'member', BTN: true, COLOR: 'red', TEXT: '멤버탈퇴',},//#ee4a5d
    APPLICANT: {C: 'applicant', BTN: true, COLOR: 'red', TEXT: '지원취소',},
    APPLIABLE: {C: 'appliable', BTN: true, COLOR: 'pink', TEXT: '신청',},
    FULL: {C: 'full', BTN: false, COLOR: 'gray', TEXT: '마감',},
};

export default {
    props: ['team',],
    methods: {
        action() {
            let c = this.getStatus.C;
            let dto = {
                approvalFlag: false,
                leaderUsername: this.$props.team.username,
                memberUsername: this.userName,
                teamId: this.$props.team.teamId,
            };
            const config = {
                headers : {
                    Authorization : this.$cookies.get('auth-token')
                }
            };
            if (c == STAT.GUEST.C) {
                alert('로그인이 필요한 서비스입니다.');
                //로그인모달창 띄워야함!
            } else if (c == STAT.MEMBER.C) {
                if (confirm('정말 멤버에서 탈퇴하시겠습니까?')) {//탈퇴처리
                    axios.delete(URL, {headers:config.headers, data:dto})
                        .then(response => {
                            // console.dir(response);
                            if (response.data == true) {
                                this.$emit('refreshList');  //목록 새로고침
                            }
                        })
                        .catch(error => console.log(error))
                }
            } else if (c == STAT.APPLICANT.C) {
                if (confirm('정말 지원을 철회하시겠습니까?')) {//탈퇴처리
                    axios.delete(URL, {headers:config.headers, data:dto})
                        .then(response => {
                            // console.dir(response);
                            if (response.data == true) {
                                this.$emit('refreshList');  //목록 새로고침
                            }
                        })
                        .catch(error => console.log(error))
                }
            } else if (c == STAT.APPLIABLE.C) {
                axios.post(URL, dto, config)//지원처리
                    .then(response => {
                        // console.dir(response);
                        if (response.data == true) {
                            this.$emit('refreshList');  //목록 새로고침
                        }
                    })
                    .catch(error => console.log(error))
            }
        },   //end action
    },
    computed: {
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
            return this.userName == this.$props.team.username;
        },
        isInApprovalList() {
            return this.$props.team.approvalList.filter(x => x.memberUsername == this.userName).length !== 0;
        },
        isListAvailable() {
            return (1 + this.$props.team.approvalList.filter(x => x.approvalFlag).length) < this.$props.team.memberMax;
        },
        isMember() {
            return this.$props.team.approvalList.filter(x => x.memberUsername == this.userName && x.flag).length > 0;
        },
    },
}
</script>

<style>
.btn {
    border-radius: 10px;
    color: white;
}

.btn:disabled {
    color: white;
}
</style>