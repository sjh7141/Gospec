<template>
<div>
    <h3>팀원 추천</h3>
    <div class="loginInfo" v-show="!isLoggedIn">로그인이 필요한 서비스입니다.</div>
    <div class='mx-auto' style='margin-top: 80px'>
       <applicant-each v-for="(each, idx) in recomList" :key="idx" :data="each"/>
    </div>
    <hr style="margin: 20px;">
    <h3>이 공모전에 관심있는 회원</h3>
    <div class="loginInfo" v-show="!isLoggedIn">로그인이 필요한 서비스입니다.</div>
    <div class='mx-auto' style='margin-top: 80px'>
    <applicant-each v-for="(each, idx) in bookmarkedList" :key="idx" :data="each"/>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import ApplicantEach from '@/components/team/ApplicantEach.vue'

const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/recommand'
const URL_BOOK = 'http://i3a202.p.ssafy.io:8181/api/users/bookmark-user/'
// const URL = 'http://localhost:8181/api/teams/recommand'
// const URL_BOOK = 'http://localhost:8181/api/users/bookmark-user/'

export default {
    components: {
        ApplicantEach,
    },
    data() {return {
        recomList : [],
        bookmarkedList: [],
    }},
    created() {
        this.loadList();
    },
    methods: {
        loadList() {
            const config = { headers : { Authorization : this.$cookies.get('auth-token') }};

            axios.get(URL, config)
                .then(response => {
                    // console.dir(response);
                    this.recomList = response.data.user;
                })
                .catch(error => console.log(error))

            axios.get(URL_BOOK + this.$route.params.contest_id, config)
                .then(response => {
                    // console.dir(response);
                    this.bookmarkedList = response.data;
                }).catch(error => console.log(error));
        },
    },
    computed: {
        isLoggedIn() { return this.$cookies.isKey('auth-token'); },
    },
}
</script>

<style scoped>
.loginInfo {
    min-height: 200px;
    line-height: 200px;
    color: gray;
}
</style>