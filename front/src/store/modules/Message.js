// import Stomp from 'stompjs'
// import SockJS from 'sockjs-client'

// //const API_URL = 'http://i3a202.p.ssafy.io:8181'
// const API_URL = 'http://localhost:8181'

export default ({
    state: {
        socket : {},
        client : {},
    },
    getters: {
        socket(state){
            return state.socket;
        },
        client(state){
            return state.client;
        }
    },
    mutations: {
        setSocket(state, payload){
            state.socket = payload;
        },
        setClient(state, payload){
            state.client = payload;
        }
    },
    actions: {
        // connect(context) {
        //     context.commit('setSocket', new SockJS(API_URL+"/socket"));
        //     context.commit('setClient', Stomp.over(this.socket));
            
        //     this.client.connect({}, frame => {
        //       this.status = 'connected';
        //       this.client.subscribe("/topic/계정1", res => {
        //         console.log(res.body);
        //       })
        //       console.log(frame);
        //     })
        // },
        // disconnect() {
        //     this.socket.close();

        // },
    },
})