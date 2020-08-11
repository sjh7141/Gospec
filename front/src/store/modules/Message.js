import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/message'

export default ({
    state: {
        socket : {},
        client : {},
        receiveMessages : [],
        sendMessages : [],
        message : {},
        username : '',
        messagePage : 1,
        receivePagination : {},
        sendPagination : {},
    },
    getters: {
        socket(state){
            return state.socket;
        },
        client(state){
            return state.client;
        },
        receiveMessages(state) {
            return state.receiveMessages;
        },
        sendMessages(state){
            return state.sendMessages;
        },
        message(state){
            return state.message;
        },
        username(state){
            return state.username;
        },
        receivePagination(state){
            return state.receivePagination;
        },
        sendPagination(state){
            return state.sendPagination;
        },
        messagePage(state){
            return state.messagePage;
        },
    },
    mutations: {
        setSocket(state, payload){
            state.socket = payload;
        },
        setClient(state, payload){
            state.client = payload;
        },
        setReceiveMessages(state, payload){
            state.receiveMessages = payload;
        },
        setSendMessages(state, payload){
            state.sendMessages = payload;
        },
        setMessage(state, payload){
            state.message = payload;
        },
        setUsername(state, payload){
            state.username = payload;
        },
        setReceivePagination(state, payload){
            state.receivePagination = payload;

        },
        setSendPagination(state, payload){
            state.sendPagination = payload;
        },
        setMessagePage(state, payload){
            state.messagePage = payload;
        }
    },
    actions: {
       getReceiveMessages(context){
        axios
            .get(URL+'/receiver/'+context.state.username+'/'+context.state.messagePage)
            .then(({data})=>{
                context.commit('setReceiveMessages', data.list);
                context.commit('setReceivePagination', data.page);
                context.commit('setMessagePage', data.page.curPage);
            });
       },
       getSendMessages(context){
        axios
            .get(URL+'/sender/'+context.state.username+'/'+context.state.messagePage)
            .then(({data})=>{
                context.commit('setSendMessages', data.list);
                context.commit('setSendPagination', data.page);
                context.commit('setMessagePage', data.page.curPage);
            });
       },
       getReceiveMessage(context, payload){
        axios
            .get(URL+'/receiver/'+payload)
            .then(({data})=>{
                context.commit('setMessage', data);
            });
       },
       getSendMessage(context, payload){
        axios
            .get(URL+'/sender/'+payload)
            .then(({data})=>{
                context.commit('setMessage', data);
            });
       }
    },
})