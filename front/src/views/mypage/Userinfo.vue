<template>
  <div>
      <h6>개인정보 수정을 위해 비밀번호를 작성해주세요.</h6>
      <input v-model='password' type="password"><br>
      <button class='btn btn-primary' @click="passwordCheck">확인</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      password: '',
      username: '',
    }
  },
  methods: {
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    },
    passwordCheck() {
      const loginData = {
        username: this.username,
        password: this.password
      }
      axios.post('http://localhost:8181/login', loginData)
      .then(
      )
      .catch(
      )
    }
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style>

</style>