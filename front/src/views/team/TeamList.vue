<template>
<div>
    <button class='createBtn' @click='createMove'>게시물 작성하기</button>

    <div v-if="teamList.length == 0" class="noListInfo">
        게시물이 없습니다
    </div>
    <my-team-each v-for="(eachT, idx) in teamList" :key="idx" 
        :eachTeam="eachT" :isExpand="isDetailView(eachT)"
        @setSelected="setSelectedPost" @refreshList="reloadPage"/>

    <button v-show="!isLastPage" @click='loadPage' style="text-decoration:none; padding: 10px;">더 보기</button>
</div>
</template>

<script>
import axios from 'axios'
import MyTeamEach from '@/components/team/MyTeamEach.vue'
import { mapGetters } from 'vuex'

const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
const URL_WRITE = URL_PART + '/isWrite/';

export default {
    name: 'teamList',
    components: {MyTeamEach},
    data() { return {
        teamList: [],
        sPage: 0,
        pageInfo: {},
        selectedPost: 0,
    }},
    methods: {
        createMove() {
            // 로그인 여부 체크, 로그인 창 뜨게 할 것
            if (!this.isLogin) {alert('로그인!');return;}

            // 비동기로 서버응답받아서 체크하는 방식
            this.isWritable().then(flag => {
                if (flag == false) {
                    alert('공모전 당 하나의 구인글만 작성할 수 있습니다.');
                } else {
                    this.$router.push('/contest/' + this.ctst_id + '/teams/write');
                }
            });
        },
        loadPage() {
            this.sPage++;
            axios.get(Array(URL_PART, this.ctst_id, this.sPage).join('/'))
                .then(response => {
                    // console.dir(response.data);
                    this.teamList.push(...response.data.list);
                    this.pageInfo = response.data.page;
                })
                .catch(error => console.log(error))
        },
        reloadPage: async function() {
            this.teamList.length = 0;   //리스트 비우기
            for (let i = 1; i <= this.sPage; i++) {
                await axios.get(Array(URL_PART, this.ctst_id, i).join('/'))
                    .then(response => {
                        // console.dir(response.data);
                        this.teamList.push(...response.data.list);
                        this.pageInfo = response.data.page;
                    })
                    .catch(error => console.log(error))
            }
        },
        setSelectedPost(no) {
            this.selectedPost = (this.selectedPost == no) ? 0 : no;
        },
        isDetailView({postNo}) {
            return this.selectedPost == postNo;
        },
        isWritable: async function() {
            const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
            let res = await axios.get(URL_WRITE + this.ctst_id, config) //이러면 응답객체가 들어간다
            return res.data;    //응답객체의 data 안에 true/false가 들어있다
        },
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
        ...mapGetters(['isLogin']),

    },
}
</script>

<style scoped>
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

.noListInfo {
    min-height: 200px;
    line-height: 200px;
    color: gray;
    vertical-align: middle;
}
</style>