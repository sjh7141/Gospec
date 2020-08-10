<template>
<div>
    <my-team-each v-for="(eachT, idx) in teamList" :key="idx" 
        :eachTeam="eachT" :isExpand="isExpand(eachT)"
        @setSelected="setExpandedPost" @refreshList="loadMyTeam"/>
</div>
</template>

<script>
import axios from 'axios'
import MyTeamEach from '@/components/team/MyTeamEach.vue'

const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/';
// const URL = 'http://localhost:8181/api/teams/';

export default {
    name: 'myteam',
    components: {MyTeamEach,},
    data() {return {
        teamList: [],
        expandedPost: 0,
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
            (this.expandedPost == payload) ? this.expandedPost = 0 : this.expandedPost = payload;
        },
    },
}
</script>