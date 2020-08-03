<template>
    <div>
        <!-- {{isLoggedIn}} -->
        <button class='createBtn' @click='createMove'>게시물 작성하기</button>
        <div id='teamListWrapper'>
            <div class='eachCtst' v-for="each in teamList" :key="each.postNo" @click="detailMove(each.postNo)">
                <div id='apply'>
                    <button>신청</button>
                </div>
                <span>{{ each.title }}</span>
                <span style='color:gray; margin-left: 40px;'>{{ each.memberCur }} / {{ each.memberMax }}</span><br>
                <span style='color:gray;'>{{ each.username }}</span>
            </div>
        </div>
        <button v-show="!isLastPage" @click='loadPage'>더 보기</button>
    </div>
</template>

<script>
import axios from 'axios'
const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
// const URL_PART = 'http://localhost:8181/api/board/teams'

export default {
    name: 'teamList',
    data() { return {
        teamList: [],
        sPage: 0,
        pageInfo: null,
    }},
    methods: {
        detailMove(page) {
            this.$router.push('/contest/' + this.ctst_id + '/teams/' + page);
        },
        createMove() {
            // 로그인 여부 체크
            if (!this.isLoggedIn) {
                // this.$emit('loginPage');
                alert('로그인!');
            } else {
                this.$router.push('/contest/' + this.ctst_id + '/teams/write');
            }
        },
        loadPage() {
            this.sPage++;
            axios.get(Array(URL_PART, this.ctst_id, this.sPage).join('/'))
                .then(response => {
                    console.dir(response.data);
                    this.teamList.push(...response.data.list);
                    this.pageInfo = response.data.page;
                })
                .catch(error => console.log(error))
        }
    },
    created() {
        this.loadPage();
    },
    computed: {
        ctst_id() {
            return this.$route.params.contest_id;
        },
        isLoggedIn() {
            return this.$cookies.isKey('auth-token');
        },
        isLastPage() {
            return this.pageInfo.totalCount <= (this.pageInfo.curPage * this.pageInfo.perPageNum);
        }
    },
}
</script>

<style scoped>
.eachCtst {
    width: 80%;
    height: 80px;
    margin: auto auto;
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

#apply {
    float: right;
    border-radius: 10px;
    background-color: pink;
    color: white;
    padding: 7px;
}
</style>