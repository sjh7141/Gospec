<template>
    <div class="middle">
        <ul class="horizontal">
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id}">상세설명</router-link></li>
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/myteam'}">내팀관리</router-link></li>
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams'}">팀찾기</router-link></li>
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/applicants'}">팀원찾기</router-link></li>
        </ul>
    </div>
</template>

<script> 
    export default {
        name: '',
        components: { 

        },
        computed:{
        },
        watch: {
        },
        created() {
        },
        methods : {
        },
        data: function() {
           return {
               
           }
        },
    }
</script>

<style scoped>
    .middle {
        display: block;
        margin: auto;
    }

    .horizontal li {
        display: inline-block;
        margin: 10px;
    }
</style>>