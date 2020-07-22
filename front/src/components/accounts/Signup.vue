<template>
<div>
  <div v-if='!completeSignup'>
    <!-- 닉네임 -->
    <div>
      <label for="validationServer01">닉네임</label>
      <div v-if="checkNickname">
        <input v-model='nickname' type="text" class="form-control is-valid" id="validationServer01" required>
        <div class="valid-feedback">
          {{ message.nickname }}
        </div>
      </div>
      <div v-else>
        <input v-model='nickname' type="text" class="form-control is-invalid" id="validationServer01" required>
        <div class="invalid-feedback">
          {{ message.nickname }}
        </div>
      </div>
    </div>

    <!-- 이메일 -->
    <div>
      <label for="validationServer02">이메일</label>
      <div v-if="checkEmail">
        <input v-model='email' type="text" class="form-control is-valid" id="validationServer02" required>
        <div class="valid-feedback">
          OK!
        </div>
        <button @click="emailCertification" v-if="!clickEmailCertification" class='btn btn-primary'>이메일 인증</button>
        <div v-if='clickEmailCertification'>
          <div v-if="!certificationNumberCheck">
            <p>인증번호가 발송되었습니다.</p>
            <label for="certification">인증 번호를 입력하세요</label>
            <input v-model='certificationNumber' id="certification" type="text">
            <button @click="certification">인증번호 확인</button>
          </div>
          <div v-else>
            <fieldset disabled>
              <input v-model='certificationNumber' id="certification" type="text">
            </fieldset>
            <button v-if='certificationNumberCheck'>인증완료</button>
          </div>
          <p v-if="certificationFail" style='color: red;'>인증번호를 다시 한 번 확인해주세요.</p>
        </div>
      </div>
      <div v-else>
        <input v-model='email' type="text" class="form-control is-invalid" id="validationServer02" required>
        <div class="invalid-feedback">
          이메일 형식에 맞게 작성해주세요.
        </div>
        <button class='btn btn-secondary'>이메일 인증</button>
      </div>
    </div>

    <!-- 패스워드 -->
    <div>
      <label for="validationServer02">비밀번호</label>
      <div v-if="checkPassword">
        <input v-model='password' type="password" class="form-control is-valid" id="validationServer03" required>
        <div class="valid-feedback">
        </div>
      </div>
      <div v-else>
        <input v-model='password' type="password" class="form-control is-invalid" id="validationServer03" required>
        <div class="invalid-feedback">
          영문,숫자 포함 8 자리이상이어야 합니다.
        </div>
      </div>
    </div>

    <!-- 패스워드확인 -->
    <div>
      <label for="validationServer02">비밀번호확인</label>
      <div v-if="checkPasswordConfirm">
        <input v-model='passwordConfirm' type="password" class="form-control is-valid" id="validationServer04" required>
        <div class="valid-feedback">
        </div>
      </div>
      <div v-else>
        <input v-model='passwordConfirm' type="password" class="form-control is-invalid" id="validationServer04" required>
        <div class="invalid-feedback">
          비밀번호가 일치하지 않습니다.
        </div>
      </div>
    </div>
    <button v-if="checkEmail && checkNickname && checkPassword && checkPasswordConfirm && certificationNumberCheck" @click="signup" class="btn btn-primary">회원가입</button>
    <button v-else class="btn btn-secondary">회원가입</button>
  </div>

  <div v-else>
    <CompleteSignup />
  </div>
</div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import CompleteSignup from '../accounts/CompleteSignup.vue'

export default {
  data: () => {
    return {
      nickname: '',
      email: "",
      password: "",
      passwordConfirm: "",
      checkNickname: false,
      checkEmail: false,
      checkPassword:false,
      checkPasswordConfirm:false,
      passwordSchema: new PV(),
      completeSignup: false,
      message: {
        nickname: ''
      },
      clickEmailCertification: false,
      certificationNumber: '',
      certificationNumberConfirm: '',
      certificationFail: false,
      certificationNumberCheck: false,
    };
  },
  components: {
    CompleteSignup,
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
      if (this.email.length >= 0 && EmailValidator.validate(this.email))
        this.checkEmail = true
      else this.checkEmail = false

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
      {
        this.checkPassword = false
      }
      else {
        this.checkPassword = true
      }

      if (
        this.nickname.length >= 3
      ) {
        this.checkNickname = true
        this.message.nickname = ''
      }
      else {
        this.checkNickname = false
        this.message.nickname = '3자 이상 작성하세요'
        }

      
      if (
        this.password == this.passwordConfirm && this.passwordConfirm.length > 0
      )
        this.checkPasswordConfirm = true
      else this.checkPasswordConfirm = false

    },
    signup () {
      this.$emit('submit-signup-data', this.signupData)
      console.log(this.signupData)
      this.completeSignup = true
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
  },
  computed: {
    signupData() {
      return {
        nickname: this.nickname,
        email: this.email,
        password: this.password,
        passwordConfirm: this.passwordConfirm,
      }
    }
  }

}
</script>