import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/message'
export default ({
    state: {
        socket : {},
        client : {},
        receiveMessages : [],
        sendMessages : [],
        allMessages : [],
        importantMessages : [],
        deleteMessages : [],
        message : {},
        username : '',
        isLogin : false,

        messageColor : false,
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
        allMessages(state){
            return state.allMessages;
        },
        importantMessages(state){
            return state.importantMessages;
        },
        deleteMessages(state){
            return state.deleteMessages;
        },
        message(state){
            return state.message;
        },
        username(state){
            return state.username;
        },
        isLogin(state){
            return state.isLogin;
        },
        messageColor(state){
            return state.messageColor;
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
        setAllMessages(state, payload){
            state.allMessages = payload;
        },
        setImportantMessages(state, payload){
            state.importantMessages = payload;
        },
        setDeleteMessages(state, payload){
            state.deleteMessages = payload;
        },
        setMessage(state, payload){
            state.message = payload;
        },
        setUsername(state, payload){
            state.username = payload;
        },
        setIsLogin(state, payload){
            state.isLogin = payload;
        },
        setMessageColor(state, payload){
            state.messageColor = payload;
        },
    },
    actions: {
       getReceiveMessages(context){
        axios
            .get(URL+'/receiver/1/'+context.state.username)
            .then(({data})=>{
                context.commit('setReceiveMessages', data.list);
            });
       },
       getAllMessages(context){
        axios
            .get(URL+'/receiver/2/'+context.state.username)
            .then(({data})=>{
                context.commit('setAllMessages', data.list);
            });
       },
       getImportantMessages(context){
        axios
            .get(URL+'/receiver/3/'+context.state.username)
            .then(({data})=>{
                context.commit('setImportantMessages', data.list);
            });
       },
       getDeleteMessages(context){
        axios
            .get(URL+'/receiver/4/'+context.state.username)
            .then(({data})=>{
                context.commit('setDeleteMessages', data.list);
            });
       },
       getSendMessages(context){
        axios
            .get(URL+'/sender/all/'+context.state.username)
            .then(({data})=>{
                context.commit('setSendMessages', data.list);
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
       },
       getIsLogin(context, payload){
        context.commit('setIsLogin', payload);
       },
    },
})