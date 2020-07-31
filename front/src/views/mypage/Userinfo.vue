<template>
  <div>
      <div v-if="!isInfoChanged">
        <h6>개인정보 수정을 위해 비밀번호를 작성해주세요.</h6>
        <input v-model='password' type="password"><br>
        <p v-if='!checkPassword' style='color: red;'>{{ errorMessage }}</p>
        <button class='btn btn-primary' @click="passwordCheck">확인</button>
      </div>
      <div v-else>
        <UserInfoChange />
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import UserInfoChange from '../../components/accounts/UserInfoChange.vue'

export default {
  data() {
    return {
      password: '',
      username: '',
      checkPassword: false,
      isInfoChanged: false,
      errorMessage: '',
    }
  },
  components: {
    UserInfoChange,
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
      axios.post('http://i3a202.p.ssafy.io:8181/login', loginData)
      .then(() =>
        this.checkPassword = true,
        this.isInfoChanged = true,
      )
      .catch((res) =>
        console.log(res.response),
        this.checkPassword = false,
        this.errorMessage = '비밀번호를 확인해주세요.'
        
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