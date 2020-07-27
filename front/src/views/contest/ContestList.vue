<template>
  <div>
    <prime-classify></prime-classify>
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

const API_URL = "http://localhost:8181/api/contest/field/all/1"

export default {
  name: 'contest list',
  components: {
    PrimeClassify,
    SecondaryClassify
  },
  data() {
    return {
      contestList: [],
    }
  },
  created() {
    axios.get(API_URL)
      .then( response => {
        console.dir(response.data.list);
        this.contestList = response.data.list;
      })
      .catch(error => { console.log(error) })
  },
}
</script>
