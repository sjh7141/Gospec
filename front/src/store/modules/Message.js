import axios from 'axios'
//const URL = 'http://i3a202.p.ssafy.io:8181/api/message'
const URL = 'http://localhost:8181/api/message'
export default ({
    state: {
        socket : {},
        client : {},
        receiveMessages : [],
        sendMessages : [],
        message : {},
        username : '',
        curPage : 1,
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
            return state.message
        },
        username(state){
            return state.username
        }
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
        }
    },
    actions: {
       getReceiveMessages(context){
        axios
            .get(URL+'/receiver/'+context.state.username+'/'+context.state.curPage)
            .then(({data})=>{
                context.commit('setReceiveMessages', data.list);
            });
       },
       getSendMessages(context){
        axios
            .get(URL+'/sender/'+context.state.username+'/'+context.state.curPage)
            .then(({data})=>{
                context.commit('setSendMessages', data.list);
            });
       },
       getReceiveMessage(context, payload){
           console.log("###");
           console.log(payload);
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