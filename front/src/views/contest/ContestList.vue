<template>
  <div>
    <prime-classify @setPrimeFilterKeyword="setPrimeFilterKeyword"></prime-classify>
    <secondary-classify></secondary-classify>
    <table class='centered'>
      <tr>
        <th>글번호</th>
        <th>공모전명</th>
        <th>주최사</th>
        <th>현재현황</th>
        <th>조회수</th>
      </tr>
      <tr v-for="eachContest in contestList" v-bind:key="eachContest.contestNo">
        <td>{{ eachContest.contestNo }}</td>
        <td><router-link :to="'/contest/' + eachContest.contestNo">{{ eachContest.title }}</router-link></td>
        <td>{{ maxLengthFilter(eachContest.host) }}</td>
        <td>{{ null }}</td>
        <td>{{ eachContest.viewCount }}</td>
      </tr>
    </table>
    <pagination :pageData='pageData' @pageTo='goToPage'/>
    <router-link to='/contest/write'>새공모전올리기</router-link>
  </div>
</template>

<script>
import axios from 'axios'
import PrimeClassify from '../../components/common/ContestPrimeClassify.vue'
import SecondaryClassify from '../../components/common/ContestSecondaryClassify.vue'
import Pagination from '@/components/common/Pagination.vue'

const API_URL_PART = "http://localhost:8181/api/contest/field/"

export default {
  name: 'contestList',
  components: {
    PrimeClassify,
    SecondaryClassify,
    Pagination
  },
  data() {
    return {
      primeFilterState: '',
      contestList: [],
      pageData: null,
    }
  },
  created() {
    this.primeFilterState = 'all'
  },
  methods: {
    setPrimeFilterKeyword(emittedParam) {
      this.primeFilterState = emittedParam;
    },
    getContest(url) {
      axios.get(url)
        .then( response => {
          // console.dir(response.data.list);
          this.contestList = response.data.list;
          this.pageData = response.data.page;
        })
        .catch( error => console.log(error) )
    },
    maxLengthFilter(string, baseLength = 15) {
      return string.length > baseLength ? `${string.substr(0, baseLength)}...` : string;
    },
    goToPage(page) {
      alert(`from parent : ${page}`);

      this.getContest(API_URL_PART + this.primeFilterState + '/' + page);
    },
  },
  watch: {
    primeFilterState(newState) {
      let fullURL = API_URL_PART + newState + '/1';
      // console.log(fullURL);
      this.getContest(fullURL);
    }
  }
}
</script>

<style scoped>
.centered {
  margin: auto;
}
</style>