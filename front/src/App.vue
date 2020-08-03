<template>
  <div id="app">
    <main-header @checkLogin='checkLogin'></main-header>
    <v-app class='container'>
      <router-view/>
    </v-app>
  </div>
</template>
  
<script>
import MainHeader from './components/common/Header.vue'

export default {
  components: {
    MainHeader,
  },
  data() {
    return {
      isLoggedIn: '',
    }
  },
  methods: {
    checkLogin(data){
      this.isLoggedIn = data
    },
    logout(data) {
      this.isLoggedIn = data 
      this.$router.push('/')
    }
  },
  mounted() {
      this.isLoggedIn = this.$cookies.isKey('auth-token')
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
