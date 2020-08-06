<template>
  <div>
    <h3>{{post.title}}</h3>
    <span>{{ post.username }}</span><br>
    <span>{{post.memberCur}} / {{post.memberMax}}</span>
    <p style='white-space: pre-line;'>{{post.content}}</p>

    <hr>
    <ul style='list-style: none;'>
      <li><router-link :to="{path: '/contest/' + $route.params.contest_id + '/teams'}">
          팀찾기 목록
      </router-link></li>
      <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams/' + $route.params.team_id + '/update' }">
          수정(본인일시)
      </router-link></li>
      <li>삭제(본인일시)</li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
// const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams/detail/'
const URL_PART = 'http://localhost:8181/api/board/teams/detail/'

export default {
  name: 'teamEach',
  data() {return {
    post: null,
  }},
  created() {
    axios.get(URL_PART + this.team_id)
      .then(response => {
        // console.dir(response);
        this.post = response.data;
      })
      .catch(error => console.log(error))
  },
  computed: {
    team_id() { //백엔드 상으로는 postNo
      return this.$route.params.team_id;
    },
    contest_id() {
      return this.$route.params.contest_id;
    },
  }
}
</script>

<style>

</style>