<template>
  <div>
    This is contest each <br>
    공모전 글 고유번호: {{ $route.params.contest_id }}<br>
    
    <contest-nav-bar></contest-nav-bar>

    [여기서도 중첩라우팅 적용]<br>
    <router-view></router-view>

    <hr>
    <ul>
      <li><router-link :to="{path: '/contest/' + $route.params.contest_id + '/update'}">공모전 수정</router-link></li>
      <li><router-link to='/contest'>공모전 목록</router-link></li>
    </ul>
    <!--
    <a @click.prevent=route_modify>라우트이벤트방식 수정</a>
    <button @click=route_modify>라우트이벤트방식 수정2</button>
    -->
  </div>
</template>

<script>
import ContestNavBar from '../../components/common/ContestNavBar.vue'

export default {
  name: '',
  components: {
      ContestNavBar,
  },
  methods: {
      /*
      route_modify() {
          this.$router.push({ path: this.$route.params.contest_id + '/update'});
      },
      */
  }
}
</script>
