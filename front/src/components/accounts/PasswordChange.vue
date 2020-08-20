<template>
  <div>
    <h1>비밀번호 수정</h1>
    
          <!-- 새 비밀번호 입력 확인 -->
          
  <div>
    <label for="validationServer02">비밀번호</label>
    <div v-if="checkPassword">
      <input v-model='password' type="password" class="form-control is-valid" id="validationServer03" placeholder="비밀번호를 입력해주세요." required>
      <div class="valid-feedback">
      </div>
    </div>
    <div v-else>
      <input v-model='password' type="password"  :class=passwordFormIsValid id="validationServer03" placeholder="비밀번호를 입력해주세요." required>
      <div class="invalid-feedback">
        {{ message.password }}
      </div>
    </div>
  </div>

          <!-- 새 비밀번호 입력 확인 -->
  <div>
    <label for="validationServer02">비밀번호확인</label>
    <div v-if="checkPasswordConfirm">
      <input v-model='passwordConfirm' type="password" class="form-control is-valid" id="validationServer04" placeholder="동일한 비밀번호를 입력해주세요." required>
      <div class="valid-feedback">
      </div>
    </div>
    <div v-else>
      <input v-model='passwordConfirm' type="password"  :class=passwordConfirmFormIsValid id="validationServer04" placeholder="비밀번호를 입력해주세요." required>
      <div class="invalid-feedback">
        {{ message.passwordConfirm }}
      </div>
    </div>
  </div>


          <div>
            <div v-if="checkPassword && checkPasswordConfirm">
              <button class='btn btn-primary' @click="passwordChange">비밀번호 변경</button>
            </div>
            <div v-else>
              <button class='btn btn-secondary'>비밀번호 변경</button>
            </div>
          </div>
  </div>
</template>

<script>
import PV from "password-validator";
import axios from 'axios'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
  name: 'Password',
  data() {
    return {
      email: "",
      checkEmail: false,
      clickEmailCertification: false,
      certificationNumber: '',
      certificationNumberConfirm: '',
      certificationNumberCheck: false,
      certificationFail: false,
      passwordConfirm: '',
      password: '',
      checkPasswordConfirm: false,
      checkPassword: false,
      passwordSchema: new PV(),
      message: {
        email: '',
        password: '',
        passowrdConfirm: '',
      }
      }
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
    this.checkusername()
  },
  watch: {
    password() {
      this.checkFormPassword();
    },
    passwordConfirm() {
      this.checkFormPasswordConfirm();
    }
  },
  methods: {
    checkFormPassword() {
      if (
        this.password.length > 0 &&
        !this.passwordSchema.validate(this.password)
      )
      {
        this.passwordFormIsValid = 'form-control is-invalid'
        this.checkPassword = false
        this.message.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
      }
      else {
        this.checkPassword = true
        this.message.password = ''
      }
    },

    checkFormPasswordConfirm() {
      if (
        !this.passwordSchema.validate(this.passwordConfirm)
      ){
        this.passwordConfirmFormIsValid = 'form-control is-invalid'
        this.checkPasswordConfirm = false
        this.message.passwordConfirm = '영문,숫자 포함 8 자리이상이어야 합니다.'
      }
      else if (
        this.password == this.passwordConfirm && this.passwordConfirm.length > 0
      ) {
        this.checkPasswordConfirm = true 
        this.message.passwordConfirm = '' }
      else { 
        this.passwordConfirmFormIsValid = 'form-control is-invalid'
        this.checkPasswordConfirm = false
        this.message.passwordConfirm = '비밀번호가 일치하지 않습니다.' }
    },
    certification() {
      if (this.certificationNumber == this.certificationNumberConfirm)
        {
          this.certificationNumberCheck = true
          this.certificationFail = false
        } 
      else this.certificationFail = true
    },
    passwordChange() {
        const config = {
          headers: {
            Authorization: this.$cookies.get("auth-token"),
          }
      } 
      axios.patch(API_URL + '/api/users/password', this.passwordData, config)
      .then(() => {
        this.$router.push('/mypage')
      })
      .catch(err => console.log(err.response))
    },
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.email = decodedValue.sub
    },
  
  },
  computed: {
    passwordData() {
      return{
        password: this.password,
        username: this.email
      }
    }
  },
}
</script>

<style>

</style>