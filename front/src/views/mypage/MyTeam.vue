<template>
<div class="myTeam">
    <div class="_title_wrapper">
        <h3 class="_title">
            {{mode.C}} 관리
            <button class="switch" @click="switchMode()">{{ mode.TEXT }}</button>
        </h3>
    </div>

    <div v-if="mode == MODE.APPLY">
        <my-team-each v-for="(eachT, idx) in teamList.filter(x=>x.username != this.userName)" :key="'a'+idx" 
        :eachTeam="eachT" :isExpand="isExpand(eachT)"
        @setSelected="setExpandedPost" @refreshList="loadMyTeam"/>
    </div>

    <div v-else>
        <my-team-each v-for="(eachT, idx) in teamList.filter(x=>x.username == this.userName)" :key="idx" 
            :eachTeam="eachT" :isExpand="isExpand(eachT)"
            @setSelected="setExpandedPost" @refreshList="loadMyTeam"/>
    </div>
</div>
</template>

<script>
import axios from 'axios'
import MyTeamEach from '@/components/team/MyTeamEach.vue'

const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/';
// const URL = 'http://localhost:8181/api/teams/';

const MODE = {APPLY:{C:"지원한 팀", TEXT:"내가 쓴 팀 보기"}, LEADER:{C:"내가 쓴 팀", TEXT:"지원한 팀 보기"}};

export default {
    name: 'myteam',
    components: {MyTeamEach,},
    data() {return {
        teamList: [],
        expandedPost: 0,
        mode: MODE.APPLY,    //초기값은 내가 지원한 팀 관리
        MODE: MODE, //이부분은 template에서 인용할때 필요한 듯
    }},
    created() {this.loadMyTeam();},
    methods: {
        loadMyTeam() {
            const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
            axios.get(URL, config)
                .then(response => {
                    // console.dir(response);
                    this.teamList = response.data;
                })
                .catch(error => console.log(error));
        },
        isExpand({postNo}) {
            return this.expandedPost == postNo;
        },
        setExpandedPost(payload) {
            this.expandedPost = (this.expandedPost == payload) ? 0 : payload;
        },
        switchMode() {
            this.mode = (this.mode == MODE.APPLY) ? MODE.LEADER : MODE.APPLY;
        },
    },
    computed: {
        userName() {
            let ca = this.$cookies.get('auth-token');
            let base64Url = ca.split('.')[1];
            let decodedValue = JSON.parse(window.atob(base64Url));
            return decodedValue.sub;
        },
    },
}
</script>

<style scoped>
._title_wrapper {
    width: 80%;
    margin: auto auto;
    margin-bottom: 50px;
}
._title {
    text-align: left;
    margin-left: 50px;
    margin-right: 50px;
    padding: 20px;
    padding-left: 50px;
    border-left: 30px solid lightgray;
    border-bottom: 1px solid lightgray;
    line-height: 37.6px;
}
.switch {
    float: right;
    background-color: #ff5252;
    border-radius: 10px;
    padding: 5px;
    min-width: 50px;
    font-size: 16px;
    color: white;
}
.switch:hover {
    background-color: #ff5252cc;
}
.myTeam{
    margin-bottom: 60px;
}
</style>