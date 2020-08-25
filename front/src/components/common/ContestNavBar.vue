<template>
<div class="wrapper">
    <button class="routeBtn" :class="{active: isSelected('')}" @click="movePage('')">상세설명</button>
    <button class="routeBtn" :class="{active: isSelected('teams')}" @click="movePage('teams')">팀찾기</button>
    <button class="routeBtn" :class="{active: isSelected('applicants')}" @click="movePage('applicants')">팀원찾기</button>
</div>
</template>

<script>
export default {
    methods: {
        movePage(param) {
            this.$router.push(`/contest/${this.$route.params.contest_id}/${param}`)
        },
        isSelected(param) {
            return ( param == '' ) ? ( this.l == 3 || this.c_path == param ) : ( this.c_path == param );
        },
    },
    computed: {
        c_path() { return this.$route.path.split('/')[3] },
        l() { return this.$route.path.split('/').length },
    },
}
</script>

<style scoped>
* { all: unset; box-sizing: border-box; }

.wrapper {
    display: block;
    width: 90%;
    margin: 20px auto;
    border-radius: 30px;
    background-color: #eeeeee;
}

.routeBtn {
    padding: 15px 30px 8px 30px;
    min-width: 110px;
    margin: 0 30px;
    color: gray;
    border-bottom: 7px solid #dddddd;
    outline: 0;
}

.routeBtn:hover {
    background-color: #dddddd;
    color: black;
    border-bottom: 7px solid #ff5252;
    outline: 0;
}

.active {
    color: black;
    border-bottom: 7px solid #ff5252;
    outline: 0;
}
</style>>