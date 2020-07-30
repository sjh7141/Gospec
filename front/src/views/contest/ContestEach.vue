<template>
  <div>
    <div class='content-area'>
      <h3>
        {{ this.info.title }} 
        <span class='viewCount'>(조회수 : {{ this.info.viewCount }})</span>
      </h3>
      <div class='img'>
        <img :src="this.info.imgSrc" :alt="this.info.title">
      </div>
      <div class='info'>
        <ul class='info-list'>
          <li>분야: {{ this.category.join(', ') }}</li>
          <li>응모대상: {{ this.info.target }}</li>
          <li>주최/주관: {{ this.info.host }}</li>
          <li>후원/협찬: {{ this.info.sponsor }}</li>
          <li>접수기간: {{ this.info.startDate }} ~ {{ this.info.endDate }}</li>
          <li>총 상금: {{ this.info.totalReward }}</li>
          <li>1등 상금: {{ this.info.firstReward }}</li>
          <li>홈페이지: <a :href='this.info.homepage'>{{ this.info.homepage }}</a></li>
          <li>첨부파일: </li>
        </ul>
      </div>
    </div>

    <hr>

    <contest-nav-bar/>

    <router-view :content='this.info.content'></router-view>

    <hr>
    <ul>
      <li><router-link :to="{path: '/contest/' + $route.params.contest_id + '/update'}">공모전 수정</router-link></li>
      <li><router-link to='/contest'>공모전 목록</router-link></li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import ContestNavBar from '@/components/common/ContestNavBar.vue'

const API_URL_PART = 'http://localhost:8181/api/contest/'

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
.viewCount {
  font-size: 15px;
}
.img {
  border: 1px solid lightgray;
  width: 200px;
  height: 300px;
}
.info {
  text-align: left;
  margin-left: 100px;
}
</style>