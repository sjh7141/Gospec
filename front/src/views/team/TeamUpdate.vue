<template>
  <div>
      This is team update page<br>
      <router-link :to="{path: '/contest/' + $route.params.contest_id + '/teams/' + $route.params.team_id}">
        수정 성공시 게시물상세로 이동할 링크
      </router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>