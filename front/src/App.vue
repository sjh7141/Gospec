<template>
  <div id="app">
    <main-header></main-header>
    <div class='container'>
    <LoginNavigation />
    <router-view/>

    </div>
  </div>
</template>

<script>
import MainHeader from './components/common/Header.vue'
import LoginNavigation from './components/index/LoginNavigation.vue'

export default {
  components: {
    MainHeader,
    LoginNavigation

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