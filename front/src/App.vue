<template>
  <div id="app">
    <main-header @checkLogin='checkLogin'></main-header>
    <v-app>
      <router-view name="a"></router-view>
      <div class='container p-0' style='margin-top: 100px'>
        <router-view />
      </div>
    </v-app>
  </div>
</template>
  
<script>
import MainHeader from './components/common/Header.vue'
import Stomp from 'stompjs'
import SockJS from 'sockjs-client'
const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
  components: {
    MainHeader,
  },
  data() {
    return {
      isLoggedIn: '',
    }
  },
  created(){ 
    
  },
  methods: {
    checkLogin(data){
      this.isLoggedIn = data
    },
    logout(data) {
      this.isLoggedIn = data 
      this.$router.push('/home')
    },
  },
  mounted() {
    this.isLoggedIn = this.$cookies.isKey('auth-token');
    //새로고침시 재연결
    let ca = this.$cookies.get("auth-token")
    this.$store.dispatch('getIsLogin', false);
    this.$store.commit('setMessageColor', false);
    if(ca != null){
          let base64Url = ca.split('.')[1]
          let decodedValue = JSON.parse(window.atob(base64Url))
          let username = decodedValue.sub
          this.$store.socket = new SockJS(API_URL+"/socket");
          this.$store.client = Stomp.over(this.$store.socket)
          this.$store.client.connect({}, () => {
            this.$store.client.subscribe("/topic/"+username, res => {
              let flag = (res.body==0)?false:true;
              this.$store.commit('setMessageColor', flag);
              if(flag){
                  this.$store.dispatch('getReceiveMessages');
                  this.$store.dispatch('getAllMessages');
              }
            })
          })
          this.$store.dispatch('getIsLogin', true);
          this.$store.commit('setUsername', username);
          this.$store.dispatch('getMessageColor', username)        
    }
    
  },
  watch: {
  }
}
</script>

<style>
@font-face { 
  font-family: 'NanumSquareRound'; 
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); 
  font-weight: 1000; 
  font-style: normal; }
@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);

#app {
  font-family: NanumSquareRound, Avenir, Helvetica, Arial, sans-serif;
  font-weight: 700;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
