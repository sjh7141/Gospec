<template>
  <div class="dropdown">
    <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      {{ username }}
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
      <router-link to="/mypage">마이페이지</router-link>
      <p @click="logout">Logout</p>
      <p @click="withdrawal">회원탈퇴</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      username: ''
    }
  },
  methods: {
    logout() {
      this.$cookies.remove('auth-token')
      this.$emit('logout', false)
    },
    // 회원탈퇴 업데이트 후 수정
    withdrawal() {
      const config = {
        headers: {
          Authorization: `Token ${this.$cookies.get("auth-token")}`
        }
      }
      this.$cookies.remove('auth-token')
      axios.delete('http://i3a202.p.ssafy.io:8181/api/users/', config)
    },
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    }
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style>

</style>