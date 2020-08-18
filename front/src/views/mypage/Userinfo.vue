<template>
  <div>
    <ProfileImage :userData='userData'/>
      <div v-if="!isInfoChanged" class='password-check'>
        <h6>고객님의 안전한 정보보호를 위하여 비밀번호를 다시 한 번 확인합니다.</h6>
          <h6 style='margin-bottom: 40px;'> 비밀번호가 타인에게 노출되지 않도록 주의하여 주세요.</h6>
          <v-text-field
          v-model="password"
          @keypress.enter="passwordCheck"
          type="password"
          solo
          label="비밀번호를 입력해주세요."
          clearable
          class='mx-auto'
          style='width:50%; text-align:center;'
        ></v-text-field>
        <p v-if='!checkPassword' style='color: red; margin: 0;'>{{ errorMessage }}</p>
        <button class='btn btn-primary' style='margin-top: 10px;' @click="passwordCheck">확인</button>
      </div>
      <div v-else>
        <UserInfoList />
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import ProfileImage from '../../components/accounts/ProfileImage.vue'
import UserInfoList from '../../components/accounts/UserInfoList.vue'
import { mapGetters } from 'vuex'

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
    if(this.isRegist){
      this.isInfoChanged = true;
      this.checkPassword = true
      this.$store.commit('setIsRegist', false);
    }
    this.getUserInfo()
  },
  mounted() {
    this.checkusername()
  },
  computed:{
    ...mapGetters(['isRegist']),
  },
}
</script>

<style scoped>
.password-check {
  background-color: #f8f8f8;
  padding: 4rem;
  padding-bottom: 2rem;
  
}
</style>