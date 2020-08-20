<template>
  <div>
    <h3 class="_title">
      <LikeState2 class='_inlineBlock' :selectedEvent="info"/>
      {{ this.info.title }} 
      <span class='viewCount'>(조회수 : {{ this.info.viewCount }})</span>
    </h3>
    <div class='content-area'>
      <div class='img'>
        <img class='hover' @click.stop='dialog=true' :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + info.imgSrc" :alt="this.info.title" title="크게보기">
        <v-row justify="center" style="flex:0 0 0">
          <v-dialog v-model="dialog" max-width='600'>
            <img :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + info.imgSrc" :alt="this.info.title">
          </v-dialog>
        </v-row>
      </div>

      <div class='_info'>
        <ul class='info-list'>
          <li><span class='label'>응모대상: </span>{{ this.info.target }}</li>
          <li><span class='label'>주최/주관: </span>{{ this.info.host }}</li>
          <li><span class='label'>후원/협찬: </span>{{ this.info.sponsor }}</li>
          <li><span class='label'>접수기간: </span>{{ this.info.startDate }} ~ {{ this.info.endDate }}</li>
          <li><span class='label'>총 상금: </span>{{ this.info.totalReward }}</li>
          <li><span class='label'>1등 상금: </span>{{ this.info.firstReward }}</li>
          <li><span class='label'>홈페이지: </span><a :href='this.info.homepage'>{{ this.info.homepage }}</a></li>
          <span class='label'>분야: </span><span class="cat" v-for="(cat, i) in category" :key="i">#{{cat}}</span>
          <li><span class='ddayWrapper'><dday :data="info"/></span></li>
        </ul>
      </div>
    </div>

    <contest-nav-bar/>

    <router-view :content='this.info.content' :endDate='this.info.endDate'></router-view>

    <hr>
    <button class="listBackBtn" @click="$router.push('/contest')">공모전 목록</button>
    <div style="min-height: 100px;"/>
  </div>
</template>

<script>
import axios from 'axios'
import dday from '@/components/contest/Dday.vue'
import ContestNavBar from '@/components/common/ContestNavBar.vue'
import LikeState2 from '@/components/common/LikeState2.vue'

const API_URL_PART = 'http://i3a202.p.ssafy.io:8181/api/contest/'

export default {
  name: 'ContestEach',
  components: {
      ContestNavBar,
      dday,
      LikeState2,
  },
  data() {
    return {
      contest_id: null,
      info: null,
      category: null,
      dialog: false,
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
  display: flex;
}
._title {
  padding: 10px;
  border-bottom: 1px solid lightgray;
}
.viewCount {
  font-size: 15px;
  color: gray;
}
.img {
  min-width: 100px;
  min-height: 200px;
  flex: 1 1 0;

  /* 아래 항목은 이 클래스의 하위항목 가운데정렬위한 코드 */
  display: flex;
  align-items: center;
  justify-content: center;
}
._info {
  text-align: left;
  /* float: right; */
  max-width: 700px;
  flex: 1 1 0;
}
._info li {
  list-style: none;
  padding-bottom: 5px;
}
.label {
  display: inline-block;
  font-weight: bold;
  width: 100px;
}
.clear {
  clear: both;
}
.ddayWrapper {
  display: inline-block;
  width: 60px;
  text-align: center;
  margin-top: 20px;
}

.cat {
  display: inline-block;
  border: 1px solid lightgray;
  border-radius: 20px;
  padding: 2px 6px;
  color: darkblue;
  margin-right: 5px;
}

.listBackBtn {
  border-radius: 10px;
  background-color: #ff5252;
  padding: 7px;
  color: white;
}

.listBackBtn:hover {
  background-color: #ff5252cc;
}

.hover:hover {
  opacity: 0.6;
}
</style>