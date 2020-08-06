<template>
  <div>
      <HomeCategory />
      <img src="http://i3a202.p.ssafy.io:8181/api/file/download/co10.jpg" alt="">
  </div>
</template>

<script>
// @ is an alias to /src
import HomeCategory from './home/HomeCategory.vue'
export default {
  components:{
    HomeCategory
  },
  name: 'Home',
}
</script>

<style scoped>

</style>