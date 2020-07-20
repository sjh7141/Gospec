<template>
  <div>
    This is contest list
    <prime-classify></prime-classify>
    <secondary-classify></secondary-classify>
    <router-link to='/contest/1234'>공모전게시물1</router-link><br>
    <router-link to='/contest/write'>새공모전올리기</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
import PrimeClassify from '../../components/common/ContestPrimeClassify.vue'
import SecondaryClassify from '../../components/common/ContestSecondaryClassify.vue'

export default {
  name: 'contest list',
  components: {
    PrimeClassify,
    SecondaryClassify
  }
}
</script>
