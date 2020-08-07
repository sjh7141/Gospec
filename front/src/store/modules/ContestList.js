import axios from 'axios'

const API_URL_PART = 'http://i3a202.p.ssafy.io:8181/api/contest/field'
const TOP_URL = 'http://i3a202.p.ssafy.io:8181/api/contest/field/top/';
const urlBuilder = (p1, p2, p3) => {
    return Array(API_URL_PART, p1, p2, p3).join('/');
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
            let fullURL = urlBuilder(p.type, p.mode, p.page);
            
            // console.log(fullURL);
            axios.get(fullURL)
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
            axios.get(TOP_URL + store.state.params.type)
                .then(response => {
                    store.commit('setTopList', response.data);
                })
                .catch(error => console.log(error));
        },
    },
}