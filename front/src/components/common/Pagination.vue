<template>
<div id='pagination'>
    <ul class='horizontal no_dot'>
        <li>
            <button class="pageBtn" :class='{deactivated: isPrev == false}' @click="pageChange('prev')" :disabled='isPrev == false'>이전</button>
        </li>

        <li v-for='n in range(pageData.startPage, pageData.endPage)' :key='n'>
            <button class="pageBtn numbers" :class='{selected: pageData.curPage == n}' @click="pageChange(n)">{{ n }}</button>
        </li>

        <li>
            <button class="pageBtn" :class='{deactivated: isNext == false}' @click="pageChange('next')" :disabled='isNext == false'>다음</button>
        </li>
    </ul>
</div>
</template>

<script>
export default {
    name: 'pageNavBar',
    methods: {
        range(start, end) {
            return Array(end - start + 1).fill().map((_, idx) => start + idx);
        },
        pageChange(pageNo) {
            if (pageNo == 'prev') pageNo = this.pageData.startPage - 1;
            if (pageNo == 'next') pageNo = this.pageData.endPage + 1;
            this.$store.commit('setPage', pageNo);
            this.$store.dispatch('getContestList');
        }
    },
    computed: {
        pageData() {
            return this.$store.state.ContestList.pagination;
        },
        isPrev() {
            return this.pageData.prev;
        },
        isNext() {
            return this.pageData.next;
        },
    },
}
</script>

<style scoped>
#pagination {
    margin-top: 30px;
}

.horizontal li {
    display: inline;
    margin: 10px;
}

.no_dot li {
    list-style: none;
}

.deactivated {
    color: #dddddd;
}

.selected {
    border-bottom: 2px solid #FF5252 !important;
    color: #FF5252;
}

.selected:hover {
    background-color: #FFEAEA !important;
}

.pageBtn {
    display: inline-block;
    padding: 5px 5px 3px 5px;
    border-bottom: 2px solid transparent;
    border-radius: 3px 3px 0 0;
}

.numbers:hover {
    background-color: #eeeeee;
}
</style>