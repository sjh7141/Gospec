<template>
  <div class='middle'>
    <ul class='horizontal'>
      <li><router-link to='/mypage/profile'>프로필</router-link></li>
      <li><router-link to='/mypage/userinfo'>내정보수정</router-link></li>
      <li><router-link to='/mypage/myteam'>팀정보</router-link></li>
    </ul>

  </div>
</template>

<script>
export default {

}
</script>

<style>
  .middle {
    display: block;
    margin: auto;
  }
  
  .horizontal li {
    display: inline-block;
    margin: 10px;
  }
</style>