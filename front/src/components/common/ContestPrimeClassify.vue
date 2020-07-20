<template>
    <div class="middle">
        <ul class="horizontal">
            <li>전체</li>
            <li>기획/아이디어</li>
            <li>브랜드/네이밍</li>
            <li>광고/마케팅</li>
            <li>사진/영상/UCC</li>
            <li>디자인</li>
            <li>예체능</li>
            <li>문학/수기/시나리오</li>
            <li>IT/소프트웨어/게임</li>
            <li>기타</li>
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
           return {}
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