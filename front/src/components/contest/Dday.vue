<template>
<div id='dday'>
    <div>D {{ sign }} {{ Math.abs(gap(today, end)) }}</div>
    <div class='state' :style="{backgroundColor:state.COLOR}">{{ state.TEXT }}</div>
</div>
</template>

<script>
const MODES = {
    ALL: {TEXT: '전체',CODE: 'all', COLOR: '#ffffff',},
    NEW: {TEXT: '신규', CODE: 'new', COLOR: '#00AEFF',},
    SOON: {TEXT: '마감임박', CODE: 'soon', COLOR: '#D7031C',},
    ING: {TEXT: '접수중', CODE: 'ing', COLOR: '#D7B203',},
    FUTURE: {TEXT: '접수예정', CODE: 'future', COLOR: '#03CBD7',},
    END: {TEXT: '마감', CODE: 'end', COLOR: '#6A6A6A',},
}
export default {
    props: ['data'],
    data() { return {
        today: new Date(),
    }},
    methods: {
        gap(todayDateObj, ddayDateObj) {
            // 오늘에서 기준일을 빼야하지?
            let gapByMilSec = todayDateObj.getTime() - ddayDateObj.getTime();
            return Math.floor(gapByMilSec / (1000*60*60*24));
        }
    },
    computed: {
        end() {
            return new Date(this.data.endDate.trim());
        },
        start() {
            return new Date(this.data.startDate);
        },
        sign() {
            return this.gap(this.today, this.end) > 0 ? '+' : '-';
        },
        state() {
            if (this.today.getTime() < this.start.getTime()) {
                return MODES.FUTURE;
            } else if (this.gap(this.today, this.end) > 0) {
                return MODES.END;
            } else if ( Math.abs(this.gap(this.today, this.end)) <= 7 ) {
                return MODES.SOON;
            } else {
                return MODES.ING;
            }
        }
    }
}
</script>

<style>
.state {
    border-radius: 7px;
    color: white;
    font-size: 0.8em;
    padding: 3px;
}
</style>