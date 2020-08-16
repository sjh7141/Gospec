<template>
    <button class='teambtn' :style="'background-color: ' + getStatus.COLOR" :disabled='!getStatus.BTN' @click.stop='action'>
        {{getStatus.TEXT}}
    </button>
</template>

<script>
import STAT from '@/constants/TeamStatus.js'
import axios from 'axios'
import { mapGetters } from 'vuex'
const URL = 'http://i3a202.p.ssafy.io:8181/api/teams'
// const URL = 'http://localhost:8181/api/teams'

export default {
    props: ['status','team'],
    //emit: ['refreshList'],
    methods: {
        action() {
            let c = this.getStatus.C;
            let dto = {
                approvalFlag: false,
                leaderUsername: this.$props.team.username,
                memberUsername: this.username,
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
            return this.$props.status;
        },
        // isLoggedIn() {
        //     return this.$cookies.isKey('auth-token');
        // },
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
        ...mapGetters(['username']),
        // isLeader() {
        //     return this.userName == this.$props.team.username;
        // },
        // isInApprovalList() {
        //     return this.$props.team.approvalList.filter(x => x.memberUsername == this.userName).length !== 0;
        // },
        // isListAvailable() {
        //     return (1 + this.$props.team.approvalList.filter(x => x.approvalFlag).length) < this.$props.team.memberMax;
        // },
        // isMember() {
        //     return this.$props.team.approvalList.filter(x => x.memberUsername == this.userName && x.flag).length > 0;
        // },
    },
}
</script>

<style scoped>
.teambtn {
    border-radius: 10px;
    color: white;
    min-width: 85px;
    height: 35px;
}

.teambtn:disabled {
    color: lightgray;
}
</style>