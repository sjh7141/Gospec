<template>
  <div>
      
      <MainPage />
      <OurService id='our-service' />
      <OurTeamInfo />
      <Footer id='index-footer' />
        <div class="nino-copyright">Copyright &copy; 2020
            <a @click.prevent="moveToIndex()">Gospec.com</a>
                . All Rights Reserved.
            <br/>
            GoSpec template by
            <a @click.prevent="moveToIndex()" >깜냥</a>
            
        </div>
  </div>
</template>

<script>
import OurService from '../components/index/OurService.vue'
import OurTeamInfo from '../components/index/OurTeamInfo.vue'
import Footer from '../components/common/Footer.vue'
import MainPage from '../components/index/MainPage.vue'

export default {
    components: {
        OurService,
        Footer,
        OurTeamInfo,
        MainPage
    },
    methods: {
        moveToIndex(){
            this.$router.go();
        }
    },

}
</script>

<style>
#our-service {
    position: relative;
}
#index-footer {
    bottom:0;
    width: 100%
}
.nino-copyright {
    position: absolute;
    bottom: 0;
    width: 100%;
    text-align: center;
    border-top: 1px solid #e5e5e5;
    padding: 20px 0;
    padding-top: 40px;
    background: #f8f8f8;
}
html {
    scroll-behavior: smooth;
}

</style>