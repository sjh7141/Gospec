<template>
    <div>
        <!-- {{isLoggedIn}} -->
        <button class='createBtn' @click='createMove'>게시물 작성하기</button>
        <div id='teamListWrapper'>
            <!-- <div class='eachCtst' v-for="each in teamList" :key="each.postNo" @click="detailMove(each.postNo)"> -->
            <div class='eachCtst' v-for="each in teamList" :key="each.postNo" @click="setSelectedPost(each.postNo)">
                <team-btn class="applyBtn" :team='each' @refreshList='reloadPage'/>
                <span>{{ each.title }}</span>
                <button class='updateBtn' v-show='userName == each.username' @click.stop='updateMove(each)'>수정</button>
                <button class='delBtn' v-show='userName == each.username' @click.stop='deletePost(each)'>삭제</button>
                <span style='color:gray; margin-left: 40px;'>{{ 1 + each.approvalList.filter(x => x.approvalFlag).length }} / {{ each.memberMax }}</span><br>
                <span style='color:gray;'>{{ each.username }}</span>
                <p id='contentArea' v-show="isDetailView(each)" style="padding: 5px; white-space:pre-line;">
                    {{ each.content }}
                </p>
            </div>
        </div>
        <button v-show="!isLastPage" @click='loadPage'>더 보기</button>
    </div>
</template>

<script>
import axios from 'axios'
import TeamBtn from '@/components/team/TeamBtn.vue'

const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
// const URL_PART = 'http://localhost:8181/api/board/teams'

export default {
    name: 'teamList',
    components: {
        TeamBtn,
    },
    data() { return {
        teamList: [],
        sPage: 0,
        pageInfo: null,
        selectedPost: 0,
    }},
    methods: {
        detailMove(page) {
            this.$router.push('/contest/' + this.ctst_id + '/teams/' + page);
        },
        createMove() {
            // 로그인 여부 체크
            if (!this.isLoggedIn) {
                // 로그인 창 뜨게 해야함
                alert('로그인!');
            } else if (this.isAlreadyWritten) {
                // 1회원이 1공모전에 글1개만 작성가능
                alert('공모전 당 하나의 구인글만 작성할 수 있습니다.');
            } else {
                this.$router.push('/contest/' + this.ctst_id + '/teams/write');
            }
        },
        updateMove(each) {
            this.$router.push('/contest/' + this.ctst_id + '/teams/' + each.postNo + '/update');
        },
        deletePost(eachPost) {
            // let payload = {
            //     "username": eachPost.username,
            //     "postNo": eachPost.postNo
            // }
            // const config = {
            //         headers : {
            //             Authorization : this.$cookies.get('auth-token')
            //         }
            //     }
            
            if (confirm('정말 삭제할거에요?')) {
                
                axios.delete(URL_PART, {
                        headers: {
                            Authorization: this.$cookies.get('auth-token')
                        },
                        data: {
                            "username": eachPost.username,
                            "postNo": eachPost.postNo
                        }
                    })
                    .then(response => {
                        console.dir(response);
                    })
                    .catch(error => console.log(error));
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
            if (this.selectedPost == no) {
                this.selectedPost = 0;
            } else {
                this.selectedPost = no;
            }
        },
        isDetailView({postNo}) {
            return this.selectedPost == postNo;
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
        isAlreadyWritten() {
            let flag = false;
            for (let each of this.teamList) {
                if (this.userName == each.username) {
                    flag = true;
                }
            }
            return flag;
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
    },
}
</script>

<style scoped>
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
</style>