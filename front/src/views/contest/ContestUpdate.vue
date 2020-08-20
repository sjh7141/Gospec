<template>
  <div>
    This is contest update<br>
    수정할 공모전 글의 고유번호: {{ $route.params.contest_id }}<br>
    <router-link :to="{path: '/contest/' + $route.params.contest_id}">
      수정하기 (수정성공시 이동할 페이지 링크)
    </router-link>
  </div>
</template>

<script>
export default {
  name: '',
}
</script>
