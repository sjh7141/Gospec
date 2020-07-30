<template>
  <div>
    <prime-classify/>
    <secondary-classify/>
    <table class='centered'>
      <tr>
        <th>글번호</th>
        <th>공모전명</th>
        <th>주최사</th>
        <th>현재현황</th>
        <th>조회수</th>
      </tr>
      <tr v-for="eachC in contestList" v-bind:key="eachC.contestNo">
        <td>{{ eachC.contestNo }}</td>
        <td><router-link :to="'/contest/' + eachC.contestNo">{{ eachC.title }}</router-link></td>
        <td>{{ maxLengthFilter(eachC.host) }}</td>
        <td>{{ null }}</td>
        <td>{{ eachC.viewCount }}</td>
      </tr>
    </table>
    <pagination/>
    <!--<router-link to='/contest/write'>새공모전올리기</router-link>-->
  </div>
</template>

<script>
import PrimeClassify from '@/components/common/ContestPrimeClassify.vue'
import SecondaryClassify from '@/components/common/ContestSecondaryClassify.vue'
import Pagination from '@/components/common/Pagination.vue'

export default {
  name: 'contestList',
  components: {
    PrimeClassify,
    SecondaryClassify,
    Pagination
  },
  data() {
    return {

    }
  },
  created() {
    // this.primeFilterState = 'all';
    // console.log('asdf'+this.$store.state.ContestList.type);
    // console.log(this.$store.dispatch('getContestList'))
    this.$store.dispatch('getContestList');
  },
  methods: {
    maxLengthFilter(string, baseLength = 15) {
      return string.length > baseLength ? `${string.substr(0, baseLength)}...` : string;
    },
  },
  watch: {
  },
  computed: {
    contestList() {
      return this.$store.state.ContestList.list;
    }
  },
}
</script>

<style scoped>
.centered {
  margin: auto;
}
</style>