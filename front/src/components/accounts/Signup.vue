<template>
<div>
  <!-- 닉네임 -->
  <div>
    <label for="validationServer01">닉네임</label>
    <div v-if="checkNickname">
        <input v-if='!possibleNickname' v-model='nickname' type="text" class="form-control is-valid" id="validationServer01" placeholder="" required>
        <fieldset v-else disabled>
          <input v-model='nickname' type="text" class="form-control is-valid" id="validationServer01" placeholder="닉네임을 입력해주세요." required>
        </fieldset>
        <button @click='doubleCheckNickname' class='btn btn-primary'>닉네임 중복확인</button>
      <div class="valid-feedback">
        {{ message.nickname }}
      </div>
    </div>
    <div v-else>
      <input v-model='nickname' type="text" :class=nicknameFormIsValid id="validationServer01" placeholder="닉네임을 입력해주세요." required>
      <button class='btn btn-secondary'>닉네임 중복확인</button>
      <div class="invalid-feedback">
        {{ message.nickname }}
      </div>
    </div>
  </div>

  <!-- 이메일 -->
  <div>
    <label for="validationServer02">이메일</label>
    <div v-if="checkEmail">
      <input v-if='!certificationNumberCheck' v-model='email' type="text" class="form-control is-valid" id="validationServer02" placeholder="이메일을 입력해주세요." required>
      <fieldset v-else disabled>
        <input v-model='email' type="text" class="form-control is-valid" id="validationServer02" placeholder="이메일을 입력해주세요." required>
      </fieldset>
      <div class="valid-feedback">
        OK!
      </div>
      <button @click='emailDuplication' v-if='!emailDuplicationCheck' class='btn btn-primary'>이메일 중복확인</button>
      <button @click="emailCertification" v-if="emailDuplicationCheck && !clickEmailCertification" class='btn btn-primary'>이메일 인증</button>
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
      <input v-model='email' type="text"  :class=emailFormIsValid id="validationServer02" placeholder="이메일을 입력해주세요." required>
      <div class="invalid-feedback">
        {{ message.email }}
      </div>
    </div>
  </div>

  <!-- 패스워드 -->
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

  <!-- 패스워드확인 -->
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

  <!-- 회원가입 정보 입력이 정확하지 않을 때 -->
  <div v-if="checkError" >
    <div v-for="errorMessage in errorMessages" :key='errorMessage'>
      {{ errorMessage }}
    </div>
  </div>
  <Terms />
  <button v-if="checkEmail && checkNickname && checkPassword && checkPasswordConfirm && certificationNumberCheck && possibleNickname" @click="signup" class="btn btn-primary">회원가입</button>
  <button v-else @click='notAllowSignup' class="btn btn-secondary">회원가입</button>
</div>
</template>

<script>
import Terms from './Terms.vue'
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from 'axios'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
  components: { Terms },
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
      message: {
        nickname: '',
        email: '',
        password: '',
        passwordconfirm: '',
      },
      clickEmailCertification: false,
      certificationNumber: '',
      certificationNumberConfirm: '',
      certificationFail: false,
      certificationNumberCheck: false,
      emailFormIsValid: 'form-control',
      passwordFormIsValid: 'form-control',
      passwordConfirmFormIsValid: 'form-control',
      nicknameFormIsValid: 'form-control',
      profileImg: null,
      selfIntroduction: null,
      name: null,
      phone: null,
      birthday: null,
      errorMessages: [],
      checkError: false,
      possibleNickname: false,
      emailDuplicationCheck: false,
      checkTerms: null,
    };
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
      this.checkFormPassword();
    },
    email() {
      this.checkFormEmail();
    },
    nickname() {
      this.checkFormNickname();
    },
    passwordConfirm() {
      this.checkFormPasswordConfirm();
    }
  },
  methods: {
    checkFormEmail() {
      if (
        this.email.length >= 0 && EmailValidator.validate(this.email)
        ) {
        this.checkEmail = true
        this.message.email = ''
        }
      else {
        this.emailFormIsValid = 'form-control is-invalid'
        this.checkEmail = false
        this.message.email = '이메일 형식으로 작성해주세요.'
      } 
    },
    changeTerms() {
      this.checkTerms = !this.checkTerms
    },

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
    
    checkFormNickname() {
      if (
        this.nickname.length >= 3
      ) {
        this.checkNickname = true
        this.message.nickname = ''
      }
      else {
        this.nicknameFormIsValid = 'form-control is-invalid'
        this.checkNickname = false
        this.message.nickname = '3자 이상 작성하세요'
        }

    },

    checkFormPasswordConfirm() {
      if (
        this.password == this.passwordConfirm && this.passwordConfirm.length > 0
      ) {
        this.checkPasswordConfirm = true 
        this.message.passwordConfirm = '' }
      else { 
        this.passwordConfirmFormIsValid = 'form-control is-invalid'
        this.checkPasswordConfirm = false
        this.message.passwordConfirm = '비밀번호가 일치하지 않습니다.' }
    },
    signup () {
      this.$emit('submit-signup-data', this.signupData)
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
    notAllowSignup() {
      this.errorMessages = []
      if (!this.checkEmail) {
        this.checkError = true
        this.errorMessages.push('이메일을 정확히 작성해주세요.')}
      if (!this.checkPassword) {
        this.checkError = true
        this.errorMessages.push('패스워드를 정확히 입력해주세요.')
      }
      if (!this.checkPasswordConfirm) {
        this.checkError = true
        this.errorMessages.push('패스워드가 일치하지 않습니다.')
      }
      if (!this.checkNickname) {
        this.checkError = true
        this.errorMessages.push('닉네임을 확인해주세요.')
      }
      if (!this.checkCertificationNumber) {
        this.checkError = true
        this.errorMessages.push('이메일 인증을 완료하세요.')
      }
      if (!this.possibleNickname) {
        this.checkError = true
        this.errorMessages.push('닉네임 중복 확인을 해주세요.')
      }
    },
    // 닉네임 중복확인
    doubleCheckNickname() {
      axios.get(API_URL + '/api/users/nickname-duplication/' + this.nickname)
      .then(res => {
        if (!res.data) {
          alert('사용 가능한 닉네임입니다.')
          this.possibleNickname = true
        }
        else {
          alert('이미 사용중인 닉네임입니다.')
        }
      })
    .catch(err => console.log(err.response.data))
    },
    emailDuplication() {
      axios.get(API_URL + '/api/users/email-duplication/' + this.email,)
      .then(res => {
        console.log(res)
        if (res.data) {
          alert('이미 사용 중인 이메일입니다.')}
        else {
          alert('사용 가능한 이메일입니다.')
          this.emailDuplicationCheck = true
      }
    })
    .catch(err => console.log(err.response.data))
  },
  },
  computed: {
    signupData() {
      return {
        nickname: this.nickname,
        username: this.email,
        password: this.password,
        profileImg: this.profileImg,
        selfIntroduction: this.selfIntroduction,
        name: this.name,
        phone: this.phone,
        birthday: this.birthday,
      }
    },
  }
}
</script>