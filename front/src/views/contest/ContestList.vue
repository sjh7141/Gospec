<template>
  <div>
    <prime-classify/>
    <PopularContest :contest.sync ="topContestList"/>
    <secondary-classify/>
    <table id='list' class='centered'>
      <tr>
        <th>글번호</th>
        <th>공모전명</th>
        <th>주최사</th>
        <th>현재현황</th>
        <th>조회수</th>
      </tr>
      <tr v-for="eachC in contestList" v-bind:key="eachC.contestNo">
        <td>{{ eachC.contestNo }}</td>
        <td class='right'>
          <LikeState2 class='_inlineBlock' :selectedEvent="eachC"/>
          <router-link :to="'/contest/' + eachC.contestNo" @click.native='addViewCount(eachC)'>{{ eachC.title }}</router-link>
        </td>
        <td>{{ maxLengthFilter(eachC.host) }}</td>
        <td><dday :data='eachC'/></td>
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
import Dday from '@/components/contest/Dday.vue'
import LikeState2 from '@/components/common/LikeState2.vue'
import PopularContest from '@/views/home/PopularContest.vue'
import axios from 'axios'

const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/contest/'
// const URL_PART = 'http://localhost:8181/api/contest/'

export default {
  name: 'contestList',
  components: {
    PrimeClassify,
    SecondaryClassify,
    Pagination,
    Dday,
    LikeState2,
    PopularContest,
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
    this.$store.dispatch('getTopContestList');
  },
  methods: {
    maxLengthFilter(string, baseLength = 15) {
      return string.length > baseLength ? `${string.substr(0, baseLength)}...` : string;
    },
    addViewCount({contestNo}) {
      axios.patch(URL_PART + contestNo)
        .then(res => {
          console.dir(res);
        })
        .catch(error => console.log(error))
    },
  },
  watch: {
  },
  computed: {
    contestList() {
      return this.$store.state.ContestList.list;
    },
    topContestList() {
      return this.$store.state.ContestList.topList;
    }
  },
}
</script>

<style scoped>
#list {
  min-width: 80%;
}

#list th {
  background-color: #dddddd;
  border: 2px solid white;
}

#list td, th {
  border-bottom: 1px solid #dddddd;
  padding: 5px;
  margin: 5px;
}
.centered {
  margin: auto;
}
.right {
  text-align: left;
}
._inlineBlock {
  display: inline-block;
}
</style>