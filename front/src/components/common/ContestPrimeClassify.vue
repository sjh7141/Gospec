<template>
    <div class="middle">
        <ul class="horizontal">
            <li @click='getContest($event)'>전체</li>
            <li @click='getContest($event)'>기획/아이디어</li>
            <li @click='getContest($event)'>광고/마케팅</li>
            <li @click='getContest($event)'>논문/리포트</li>
            <li @click='getContest($event)'>영상/UCC/사진</li>
            <li @click='getContest($event)'>디자인/캐릭터/웹툰</li>
            <li @click='getContest($event)'>웹/모바일/플래시</li>
            <li @click='getContest($event)'>게임/소프트웨어</li>
            <li @click='getContest($event)'>과학/공학</li>
            <li @click='getContest($event)'>문학/글/시나리오</li>
            <li @click='getContest($event)'>건축/건설/인테리어</li>
            <li @click='getContest($event)'>네이밍/슬로건</li>
            <li @click='getContest($event)'>예체능/미술/음악</li>
            <li @click='getContest($event)'>대외활동/서포터즈</li>
            <li @click='getContest($event)'>봉사활동</li>
            <li @click='getContest($event)'>취업/창업</li>
            <li @click='getContest($event)'>해외</li>
            <li @click='getContest($event)'>기타</li>
        </ul>
    </div>
</template>

<script> 
    export default {
        name: 'ContestPrimeClassifyNavBar',
        components: { 
        },
        computed:{
        },
        watch: {
        },
        created() {
        },
        methods : {
            getContest(event) {
                let keyword = this.keywordFilter(event.target.innerHTML);
                // console.log(keyword);
                this.$emit('setPrimeFilterKeyword', keyword);
            },
            keywordFilter(string) {
                return string == '전체' ? 'all' : string.replaceAll('/', '-');
            }
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