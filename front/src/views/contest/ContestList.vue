<template>
  <div>
    This is contest list
    <prime-classify></prime-classify>
    <secondary-classify></secondary-classify>

    <ul>
      <li><router-link to='/contest/1234'>공모전게시물예제1 (글번호:1234)</router-link></li>
      <li><router-link to='/contest/5678'>공모전게시물예제2 (글번호:5678)</router-link></li>
    </ul>

    <router-link to='/contest/write'>새공모전올리기</router-link>
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
