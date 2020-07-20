<template>
    <div class="middle">
        <ul class="horizontal">
            <li>전체</li>
            <li>신규</li>
            <li>마감임박</li>
            <li>접수중</li>
            <li>접수예정</li>
            <li>마감</li>
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