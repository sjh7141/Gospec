<template>
  <div>
      This is team writing page: 글작성폼<br><br>
      <router-link :to="{path: '/contest/' + $route.params.contest_id + '/teams'}">
        등록 성공시 팀 목록으로 이동할 링크
      </router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>