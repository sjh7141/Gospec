<template>
  <div>
    <prime-classify @setPrimeFilterKeyword="setPrimeFilterKeyword"></prime-classify>
    <secondary-classify></secondary-classify>
    <table>
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
        <td>{{ eachContest.host }}</td>
        <td>{{ null }}</td>
        <td>{{ eachContest.viewCount }}</td>
      </tr>
    </table>

    <router-link to='/contest/write'>새공모전올리기</router-link>
  </div>
</template>

<script>
import axios from 'axios'
import PrimeClassify from '../../components/common/ContestPrimeClassify.vue'
import SecondaryClassify from '../../components/common/ContestSecondaryClassify.vue'

const API_URL_PART = "http://localhost:8181/api/contest/field/"

export default {
  name: 'contestList',
  components: {
    PrimeClassify,
    SecondaryClassify
  },
  data() {
    return {
      primeFilterState: '',
      contestList: [],
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
        })
        .catch( error => console.log(error) )
    }
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
