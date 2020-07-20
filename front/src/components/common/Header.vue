<template>
    <div id="header">
        <div class="middle">
            <ul class="horizontal">
                <li><router-link to="/index">로고(인덱스)</router-link></li>
                <li><router-link to="/">HOME</router-link></li>
                <li><router-link to="/schedule">전체일정</router-link></li>
                <li><router-link to="/contest">공모전</router-link></li>
                <li><router-link to="/study">스터디</router-link></li>
                <li><router-link to="/jobinfo">취업정보</router-link></li>
                <li><router-link to="/mypage">마이페이지</router-link></li>
            </ul>
        </div>
    </div>
</template>

<script> 
    export default {
        name: 'Header',
        components: { 

        },
        props: ['isHeader'],
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
               keyword : "",
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