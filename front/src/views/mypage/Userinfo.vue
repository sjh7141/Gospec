<template>
  <div>
    <ProfileImage :userData='userData'/>
      <div v-if="!isInfoChanged">
        <h6>개인정보 수정을 위해 비밀번호를 작성해주세요.</h6>
          <v-text-field
          v-model="password"
          @keypress.enter="passwordCheck"
          type="password"
          solo
          label="비밀번호를 입력해주세요."
          clearable
          class='mx-auto mt-4'
          style='width:50%'
        ></v-text-field>
        <p v-if='!checkPassword' style='color: red;'>{{ errorMessage }}</p>
        <button class='btn btn-primary' @click="passwordCheck">확인</button>
      </div>
      <div v-else>
        <UserInfoList :password='password'/>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import ProfileImage from '../../components/accounts/ProfileImage.vue'
import UserInfoList from '../../components/accounts/UserInfoList.vue'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
  data() {
    return {
      password: '',
      username: '',
      checkPassword: false,
      isInfoChanged: '',
      errorMessage: '',
      userData: null,
    }
  },
  components: {
    UserInfoList,
    ProfileImage,
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
      axios.post(API_URL + '/login', loginData)
      .then((res) =>
        this.checkThen(res)
      )
      .catch((res) =>
      this.checkcatch(res)
      )
    },
    checkThen(res) {
      if (res) {
        this.checkPassword = true
        this.isInfoChanged = true
      }
    },
    checkcatch(res) {
      if (res) {
        this.checkPassword = false
        this.isInfoChanged = false
        this.errorMessage = '비밀번호를 확인해주세요.'
      }
    },
      getUserInfo() {
          const config = {
              headers: {
                  Authorization: this.$cookies.get("auth-token")
              }
          }
          axios.get(API_URL + '/api/users', config)
          .then(res => {
              this.userData = res.data
          })
          .catch(err => console.log(err.response))
      },
  },
  created() {
    this.getUserInfo()
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style scoped>
</style>