<template>
<div class="flex_container">
    <div>
        <!-- <hr style="margin: 20px;"> -->
        <h4 class="smallTitle">이 공모전에 관심있는 회원</h4>
        <div class='mx-auto' style='margin-top: 80px'>
            <applicant-each v-for="(each, idx) in bookmarkedList.filter( (x, idx) => idx < bookmarkItemCount)" :key="idx" :data="each"/>
        </div>
        <button @click="loadBookmarkedPage" v-show="!isLastPage">더보기</button>
    </div>

    <div>
        <h4 class="smallTitle">관심사가 비슷한 회원 추천</h4>
        <div class="loginInfo" v-show="!isLoggedIn">로그인이 필요한 서비스입니다.</div>
        <div class='mx-auto' style='margin-top: 80px'>
            <applicant-each v-for="(each, idx) in recomList" :key="idx" :data="each"/>
        </div>
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
        page: 0,
    }},
    created() {
        this.loadList();
        this.loadBookmarkedPage();
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

            axios.get(URL_BOOK + this.$route.params.contest_id)
                .then(response => {
                    // console.dir(response);
                    this.bookmarkedList = response.data;
                }).catch(error => console.log(error));
        },
        loadBookmarkedPage() {
            this.page++;
        },
    },
    computed: {
        isLoggedIn() { return this.$cookies.isKey('auth-token'); },
        isLastPage() { return this.bookmarkItemCount >= this.bookmarkedList.length },
        bookmarkItemCount() { return this.page * 5 },
    },
}
</script>

<style scoped>
.flex_container {
    display: flex;
}

.flex_container > * {
    flex: 1 1 0;
}

.loginInfo {
    min-height: 200px;
    line-height: 200px;
    color: gray;
}

.smallTitle {
    width: 80%;
    padding: 15px;
    margin: 0 auto 30px auto;
    border-bottom: 1px solid lightgray;
}
</style>