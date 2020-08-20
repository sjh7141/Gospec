<template>
  <div>
      This is team each page<br>
      현재 게시글 번호: {{ $route.params.team_id }}<br><br>

      <ul>
        <li><router-link :to="{path: '/contest/' + $route.params.contest_id + '/teams'}">
            팀찾기 게시물 목록으로 이동
        </router-link></li>
        <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams/' + $route.params.team_id + '/update' }">
            팀찾기 게시물 수정
        </router-link></li>
      </ul>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>