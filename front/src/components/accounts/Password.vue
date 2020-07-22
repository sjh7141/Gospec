<template>
  <div>
    <div v-if='!certificationNumberCheck'>
      <p>가입시 등록하신 이메일 주소를 입력해주세요.</p>
      <p>인증 완료 시 비밀번호 변경이 가능합니다.</p>
    </div>

    <div>
      <div v-if="checkEmail">
        <div v-if='!certificationNumberCheck'>
          <input v-model='email' type="text" class="form-control is-valid" id="validationServer06" required>
          <div class="valid-feedback">
            OK!
          </div>
        </div>
        <button v-if='!clickEmailCertification' class='btn btn-primary' @click='emailCertification'>전송</button>
        <div v-if='clickEmailCertification && !certificationNumberCheck'>
          <p>인증번호가 발송되었습니다.</p>
          <label for="certification">인증 번호를 입력하세요</label>
          <input v-model='certificationNumber' id="certification" type="text">
          <button @click="certification">인증번호 확인</button>
          <p v-if="certificationFail" style='color: red;'>인증번호를 다시 한 번 확인해주세요.</p>
        </div>
        <div v-if='certificationNumberCheck'>
          <fieldset disabled>
            <input v-model='email' type="text" class="form-control is-valid" id="validationServer06" required>
          </fieldset>
          <button v-if='certificationNumberCheck'>인증완료</button>
        </div>
      </div>
      <div v-else>
        <input v-model='email' type="text" class="form-control is-invalid" id="validationServer06" required>
        <div class="invalid-feedback">
          이메일 형식에 맞게 작성해주세요.
        </div>
        <button class='btn btn-secondary'>전송</button>
      </div>
    </div>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator"

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
      }
  },
  watch: {
    email() {
      this.checkForm();
  },
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && EmailValidator.validate(this.email))
        this.checkEmail = true
      else this.checkEmail = false
    },
    emailCertification() {
      this.clickEmailCertification = true
      this.certificationNumberConfirm =  Math.floor(Math.random()*(8999)+1000)
      console.log(this.certificationNumberConfirm)
    },
    certification() {
      if (this.certificationNumber == this.certificationNumberConfirm)
        {
          this.certificationNumberCheck = true
          this.certificationFail = false
        } 
      else this.certificationFail = true
    },
  
}
}
</script>

<style>

</style>