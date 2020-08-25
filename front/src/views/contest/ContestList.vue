<template>
<div>
  <prime-classify/>

  <div class="popular">
    <h4 class="popular_title">인기 공모전</h4>
    <PopularContest :contest.sync ="topContestList"/>
  </div>

  <secondary-classify/>

  <table id='list' class='centered'>
    <tr>
      <th class="tb-th-1">글번호</th>
      <th class="tb-th-2">공모전명</th>
      <th class="tb-th-3">주최사</th>
      <th class="tb-th-4">현재현황</th>
      <th class="tb-th-5">조회수</th>
    </tr>
    <tr v-for="eachC in contestList" v-bind:key="eachC.contestNo">
      <td>{{ eachC.contestNo }}</td>
      <td class='right'>
        <LikeState2 class='_inlineBlock' :selectedEvent="eachC"/>
        <router-link :to="'/contest/' + eachC.contestNo" @click.native='addViewCount(eachC)'>
          {{ eachC.title }}
        </router-link>
      </td>
      <td>{{ maxLengthFilter(eachC.host) }}</td>
      <td><dday :data='eachC'/></td>
      <td>{{ eachC.viewCount }}</td>
    </tr>
  </table>

  <pagination/>
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
  created() {
    this.$store.dispatch('getContestList');
    this.$store.dispatch('getTopContestList');
  },
  methods: {
    maxLengthFilter(string, baseLength = 15) {
      return string.length > baseLength ? `${string.substr(0, baseLength)}...` : string;
    },
    addViewCount({contestNo}) {
      axios.patch(URL_PART + contestNo)
        .then(res => { if(res.status == 200) '' })  //조회수 증가요청 성공
        .catch(error => console.log(error))
    },
  },
  computed: {
    contestList() { return this.$store.state.ContestList.list; },
    topContestList() { return this.$store.state.ContestList.topList; },
  },
}
</script>

<style scoped>
#list {
  margin-top: 20px;
  min-width: 90%;
}

#list th {
  background-color: white;
  border: thin solid rgba(0,0,0,.12);
  
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

.popular_title {
  width: 90%;
  margin: 5px auto 10px auto;
  text-align: left;
  border-left: 10px solid #ff5252;
  padding: 5px 30px;
}
.popular {
  width: 90%;
  margin: 20px auto;
  background-color: #eeeeee;
  border-radius: 40px;
  padding: 10px;
}

.tb-th-1 {width: 60px;}
.tb-th-2 {min-width: 295px;}
.tb-th-3 {width: 220px;}
.tb-th-4 {width: 76px;}
.tb-th-5 {width: 55px;}
a{
  color : black !important;
}
</style>