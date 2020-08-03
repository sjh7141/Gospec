<template>
  <div class="home">
    <div class="category">
      카테고리
    </div>
    <div class="contest">인기 공모전<PopularContest /></div>
    <div class="study">인기 스터디<PopularStudy /></div>
    <div class="job">인기 취업정보<PopularJob /></div>
  </div>
</template>

<script>
// @ is an alias to /src
import PopularContest from './home/PopularContest.vue'
import PopularStudy from './home/PopularStudy.vue'
import PopularJob from './home/PopularJob.vue'
export default {
  components:{
    PopularContest,
    PopularStudy,
    PopularJob
  },
  name: 'Home',
}
</script>
<style>
.home {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
  margin-left: 10px;
}
.category {
  grid-column: 1;
  grid-row: 1/7;
  background-color: teal;
}
.contest {
  grid-column: 2/4;
  grid-row: 1/3;
  background-color:thistle;
}
.study {
  grid-column: 2/4;
  grid-row: 3/5;
  background-color: violet;
}
.job {
  grid-column: 2/4;
  grid-row: 5/7;
  background-color: yellow;
}
</style>