<template>
<div class="middle">
    <ul class="horizontal">
        <li :class="{selected: modeNow == mode.CODE}"
            v-for="(mode, idx) in MODES" :key="idx"
            @click="pageChange(mode.CODE)">
            {{ mode.TEXT }}
        </li>
    </ul>
</div>
</template>

<script> 
export default {
    name: 'modeNavBar',
    data() {
        return {
            MODES: {
                ALL: {TEXT: '전체',CODE: 'all'},
                NEW: {TEXT: '신규', CODE: 'new'},
                SOON: {TEXT: '마감임박', CODE: 'soon'},
                ING: {TEXT: '접수중', CODE: 'ing'},
                FUTURE: {TEXT: '접수예정', CODE: 'future'},
                END: {TEXT: '마감', CODE: 'end'},
            }
        }
    },
    methods : {
        pageChange(code) {
            this.$store.commit('setMode', code);
            this.$store.commit('setPage', 1);
            this.$store.dispatch('getContestList');
        }
    },
    computed:{
        modeNow() {
            return this.$store.state.ContestList.params.mode;
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

.selected {
    background-color: #dddddd;
}
</style>>