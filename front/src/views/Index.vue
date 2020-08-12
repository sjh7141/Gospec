<template>
  <div>
      <MainPage />
      <MainPageInterest v-if='false' />
      <OurTeamInfo />
      <OurService />
      <Footer />
  </div>
</template>

<script>
import MainPageInterest from '../components/index/MainPageInterest.vue'
import OurService from '../components/index/OurService.vue'
import OurTeamInfo from '../components/index/OurTeamInfo.vue'
import Footer from '../components/common/Footer.vue'
import MainPage from '../components/index/MainPage.vue'

export default {
    components: {
        MainPageInterest,
        OurService,
        Footer,
        OurTeamInfo,
        MainPage
    }

}
</script>

<style>

</style>