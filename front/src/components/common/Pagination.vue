<template>
<div>
    <ul class='horizontal no_dot'>
        <li :class='{deactivated: isPrev == false}'>
            <button @click="pageChange('prev')" :disabled='isPrev == false'>이전</button>
        </li>

        <li :class='{selected: pageData.curPage == n}'
            v-for='n in range(pageData.startPage, pageData.endPage)' :key='n'>
            <button @click="pageChange(n)">{{ n }}</button>
        </li>

        <li :class='{deactivated: isNext == false}'>
            <button @click="pageChange('next')" :disabled='isNext == false'>다음</button>
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

<style>
.horizontal li {
    display: inline;
    margin: 10px;
}

.no_dot {
    list-style: none;
}

.deactivated {
    color: #dddddd;
}

.selected {
    background-color: #dddddd;
}
</style>