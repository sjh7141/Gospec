<template>
    <div>
        <ul class='horizontal no_dot'>
            <li :class='{deactivated: !this.$props.pageData.prev}'><button @click="pageChange('prev')" :disabled='!this.$props.pageData.prev'>이전</button></li>
            <li :class='{currentPage: $props.pageData.curPage == n}' v-for='n in range($props.pageData.startPage, $props.pageData.endPage)' :key='n' @click='pageChange($event)'>{{ n }}</li>
            <li :class='{deactivated: !this.$props.pageData.next}'><button @click="pageChange('next')" :disabled='!this.$props.pageData.next'>다음</button></li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'pagination',
    props: ['pageData'],
    data() {
        return {
            
        }
    },
    created() {

    },
    methods: {
        range(start, end) {
            return Array(end - start + 1).fill().map((_, idx) => start + idx);
        },
        pageChange(event) {
            let page = null;

            if (event === 'prev') {
                page = this.$props.pageData.startPage - 1;
            } else if (event === 'next') {
                page = this.$props.pageData.endPage + 1;
            } else {
                page = event.target.innerHTML;
            }

            // alert(page);
            this.$emit('pageTo', page);
        }
    }
}
</script>

<style>
.horizontal li {
    display: inline;
}

.no_dot {
    list-style: none;
}

.deactivated {
    color: gray;
}

.currentPage {
    background-color: lightgray;
}
</style>