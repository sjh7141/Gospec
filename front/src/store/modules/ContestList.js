import axios from 'axios'

const URL = {
    DOMAIN: 'http://i3a202.p.ssafy.io:8181',
    CONTEST: 'api/contest/field',
    TOP: 'api/contest/field/top',
    contestBuild({ type, mode, page }) { return Array(this.DOMAIN, this.CONTEST, type, mode, page).join('/') },
    topBuild(type) { return Array(this.DOMAIN, this.TOP, type).join('/') },
}

export default {
    state: {
        params: {type: 'all', mode: 'all', page: 1,},
        list: [],
        topList: [],
        pagination: {},
    },
    getters: {  // store.getters.asdf로 사용(computed비슷). 첫 인자는 vuex.state. 두번째 인자는 vuex.getters

    },
    mutations: {    // commit으로 호출. 첫 인자가 vuex.state.
        setType(state, payload) {
            state.params.type = payload;
        },
        setMode(state, payload) {
            state.params.mode = payload;
        },
        setPage(state, payload) {
            state.params.page = payload;
        },
        setList(state, payload) {
            // console.log('뮤테이션의첫번째인자확인');
            // console.dir(state);
            state.list = payload;
        },
        setPagination(state, payload) {
            state.pagination = payload;
        },
        setTopList(state, payload) {
            state.topList = payload;
        },
    },
    actions: {  //비동기부분. dispatch로 호출. 첫 인자가 vuex
        getContestList(store) {
            //console.dir(store) 노가다로 유추
            let p = store.state.params;
            axios.get(URL.contestBuild(p))
                .then(response => {
                    // console.dir(response);
                        //response.data.list
                        //response.data.page
                    store.commit('setList', response.data.list);
                    store.commit('setPagination', response.data.page);
                    store.commit('setPage', response.data.page.curPage);
                })
                .catch(error => console.log(error));
        },
        getTopContestList(store) {
            let t = store.state.params.type;
            axios.get(URL.topBuild(t))
                .then(response => {
                    store.commit('setTopList', response.data);
                })
                .catch(error => console.log(error));
        },
    },
}