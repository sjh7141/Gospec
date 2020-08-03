<template>
  <div class="home">
    <div class="category">
      카테고리
    </div>
    <div class="contest">인기 공모전</div>
    <div class="study">인기 스터디</div>
    <div class="job">인기 취업정보</div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
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