<template>
  <div id="app">
    <main-header></main-header>
    <Navigation />
    <router-view/>
  </div>
</template>

<script>
import MainHeader from './components/common/Header.vue'
import Navigation from './components/index/NavigationDrawers.vue'

export default {
  components: {
    MainHeader,
    Navigation

  }
}
</script>

<style>
@font-face { font-family: 'NanumSquareRound'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); font-weight: normal; font-style: normal; }

#app {
  font-family:  NanumSquareRound, Avenir, Helvetica, Arial, sans-serif;
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