<template>
<div class="middle" style="width: 90%;">
    <div class="horizontal">
        <v-card outlined>
            <v-tabs color="error" center-active centered>
                <v-tab v-for="(mode, idx) in MODES" :key="idx"
                    @click="pageChange(mode.CODE)">
                    {{ mode.TEXT }}
                </v-tab>
            </v-tabs>
        </v-card>
    </div>
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
    },
}
</script>

<style scoped>
.middle {
    display: inline-block;
    margin: auto;
}
</style>>