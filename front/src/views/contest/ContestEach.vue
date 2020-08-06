<template>
  <div>
    <h3>
      {{ this.info.title }} 
      <span class='viewCount'>(조회수 : {{ this.info.viewCount }})</span>
    </h3>
    <div class='content-area'>
      <div class='img'>
        <img :src="this.info.imgSrc" :alt="this.info.title">
      </div>
      <div class='_info'>
        <ul class='info-list'>
          <li><span class='label'>분야: </span>{{ this.category.join(', ') }}</li>
          <li><span class='label'>응모대상: </span>{{ this.info.target }}</li>
          <li><span class='label'>주최/주관: </span>{{ this.info.host }}</li>
          <li><span class='label'>후원/협찬: </span>{{ this.info.sponsor }}</li>
          <li><span class='label'>접수기간: </span>{{ this.info.startDate }} ~ {{ this.info.endDate }}</li>
          <li><span class='label'>총 상금: </span>{{ this.info.totalReward }}</li>
          <li><span class='label'>1등 상금: </span>{{ this.info.firstReward }}</li>
          <li><span class='label'>홈페이지: </span><a :href='this.info.homepage'>{{ this.info.homepage }}</a></li>
          <li><span class='label'>첨부파일: </span></li>
        </ul>
      </div>
    </div>

    <hr class='clear'>

    <contest-nav-bar/>

    <router-view :content='this.info.content'></router-view>

    <hr>
    <ul style='list-style:none;'>
      <!-- <li><router-link :to="{path: '/contest/' + $route.params.contest_id + '/update'}">공모전 수정</router-link></li> -->
      <li><router-link to='/contest'>공모전 목록</router-link></li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import ContestNavBar from '@/components/common/ContestNavBar.vue'

const API_URL_PART = 'http://i3a202.p.ssafy.io:8181/api/contest/'

export default {
  name: 'ContestEach',
  components: {
      ContestNavBar,
  },
  data() {
    return {
      contest_id: null,
      info: null,
      category: null,
    }
  },
  methods: {

  },
  created() {
    this.contest_id = this.$route.params.contest_id;
    axios.get(API_URL_PART + this.contest_id)
      .then(response => {
        this.info = response.data.contest;
        this.category = response.data.field;
      })
      .catch(error => console.log(error))
  }
}
</script>

<style scoped>
.content-area {
  margin: 50px auto;
}
.viewCount {
  font-size: 15px;
}
.img {
  border: 1px solid lightgray;
  width: 200px;
  height: 300px;
  float: left;
}
._info {
  text-align: left;
  float: right;
  max-width: 700px;
}
._info li {
  list-style: none;
}
.label {
  display: inline-block;
  font-weight: bold;
  width: 100px;
}
.clear {
  clear: both;
}
</style>