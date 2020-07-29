<template>
<div id="header">
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: black;">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
    <a class="nav-item nav-link active text-white" href="/index">로고(인덱스)<span class="sr-only">(current)</span></a>
    <a class="nav-item nav-link text-white" href="/">HOME</a>
    <a class="nav-item nav-link text-white" href="/schedule">전체일정</a>
    <a class="nav-item nav-link text-white" href="/contest">공모전</a>
    <a class="nav-item nav-link text-white" href="/study">스터디</a>
    <a class="nav-item nav-link text-white" href="/jobinfo">전체일정</a>
    <div class="float-right">
    <a class="nav-item nav-link text-dark" href="#" ><Modal /></a>
    </div>
    </div>
</div>
</nav>
</div>

</template>

<script> 
import Modal from '../accounts/Modal.vue'

    export default {
        name: 'Header',
        components: { 
            Modal,
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
               isLoggedIn : false,
           }
        },
        mounted() {
            this.isLoggedIn = this.$cookies.isKey('auth-token')
        }
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