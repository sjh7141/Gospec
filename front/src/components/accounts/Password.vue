<template>
  <div>
    <!-- 첫 화면 - 이메일 작성 -->
    <div v-if='!certificationNumberCheck'>
      <p>가입시 등록하신 이메일 주소를 입력해주세요.</p>
      <p>인증 완료 시 비밀번호 변경이 가능합니다.</p>
    </div>

    <!-- 이메일 입력 확인 -->
    <div>
      <div v-if="checkEmail">
          <input v-model='email' v-if='!certificationNumberCheck' type="text" class="form-control is-valid" id="validationServer06" required>
          <div class="valid-feedback">
          </div>
        <button v-if='!clickEmailCertification' class='btn btn-primary' @click='emailCertification'>전송</button>
        <div v-if='clickEmailCertification && !certificationNumberCheck'>
          <p>인증번호가 발송되었습니다.</p>
          <label for="certification">인증 번호를 입력하세요</label>
          <input v-model='certificationNumber' id="certification" type="text" style='border: 2px solid'>
          <button @click="certification">인증번호 확인</button>
          <p v-if="certificationFail" style='color: red;'>인증번호를 다시 한 번 확인해주세요.</p>
        </div>
        <div v-if='certificationNumberCheck'>
          <fieldset disabled>
            <input v-model='email' type="text" class="form-control is-valid" id="validationServer06" required>
          </fieldset>
          <button v-if='certificationNumberCheck'>인증완료</button><br>

          <!-- 새 비밀번호 입력 확인 -->
          <div>
            <div v-if="checkPassword">
              <input v-model='password' type="password" class="form-control is-valid" id="validationServer03" placeholder="새 비밀번호" required>
              <div class="valid-feedback">
              </div>
            </div>
            <div v-else>
              <input v-model='password' type="password" class="form-control is-invalid" id="validationServer03" placeholder="새 비밀번호" required>
              <div class="invalid-feedback">
                {{ message.password }}
              </div>
            </div>
          </div>

          <!-- 새 비밀번호 입력 확인 -->
          <div>
            <label for="validationServer02">비밀번호확인</label>
            <div v-if="checkPasswordConfirm">
              <input v-model='passwordConfirm' type="password" class="form-control is-valid" id="validationServer04" placeholder="새 비밀번호 확인" required>
              <div class="valid-feedback">
              </div>
            </div>
            <div v-else>
              <input v-model='passwordConfirm' type="password" class="form-control is-invalid" id="validationServer04" placeholder="새 비밀번호 확인" required>
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
      </div>

      <!-- 이메일이 제대로 입력되지 않을 경우 -->
      <div v-else>
        <input v-model='email' type="text" class="form-control is-invalid" id="validationServer06" required>
        <div class="invalid-feedback">
          {{ message.email }}
        </div>
        <button class='btn btn-secondary'>전송</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator"
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
  },
  watch: {
    password() {
      this.checkForm();
    },
    email() {
      this.checkForm();
    },
    nickname() {
      this.checkForm();
    },
    passwordConfirm() {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if (
        this.email.length >= 0 && EmailValidator.validate(this.email)
        ) {
        this.checkEmail = true
        this.message.email = ''
        }
      else {
        this.checkEmail = false
        this.message.email = '이메일 형식으로 작성해주세요.'
      } 

      if (
        this.password.length >=  0 &&
        !this.passwordSchema.validate(this.password)
      )
      {
        this.checkPassword = false
        this.message.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
      }
      else {
        this.checkPassword = true
        this.message.password = ''
      } 
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
    emailCertification() {
      axios.get(API_URL + '/api/users/email-duplication/' + this.email)
      .then(res => {
        if (res.data) {
          axios.get(API_URL + '/api/users/email-authentication/' + this.email)
          .then(res => {
            this.certificationNumberConfirm = res.data
          })
          this.clickEmailCertification = true
        }
        else alert('가입되지 않은 이메일입니다.')
      })
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
      console.log(this.passwordData)
      axios.patch(API_URL + '/api/users/password', this.passwordData)
      .then(() => {
        this.$emit('completePasswordChange')
      })
      .catch(err => console.log(err.response))
    },
  
  },
  computed: {
    passwordData() {
      return{
        password: this.password,
        username: this.email
      }
    }
  }
}
</script>

<style>

</style>