<template>
  <v-row justify="center">
    <v-dialog
      v-model="show"
      :max-width=modalSize
    >
      <v-card color='grey lighten-3'>
      <v-card-title class='d-flex justify-content-between'>
        <div>{{ modalTitle }}</div>
        <i type='button' class="fas fa-times"
        @click="show = false" style='font-size:20px'></i>
      </v-card-title>
      <hr class='mt-0'>
      <v-card-text>
        <Login @submit-login-data='login' @clickSignupBtn="clickSignupBtn" @clickPasswordBtn="clickPasswordBtn" v-if="modalState == 'login'"/>
        <Signup @submit-signup-data="signup" v-if="modalState == 'signup'" />
        <Password @completePasswordChange="completePasswordChange" v-if="modalState == 'password'" />
        <CompleteSignup v-if="modalState == 'completeSignup'" />
        <CompletePasswordChange @loginPage="loginBtn" v-if="modalState == 'completePasswordChange'" />
      </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import Login from '../accounts/Login.vue'
import Signup from '../accounts/Signup.vue'
import Password from '../accounts/Password.vue'
import CompleteSignup from '../accounts/CompleteSignup.vue'
import CompletePasswordChange from '../accounts/CompletePasswordChange.vue'

import axios from 'axios'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
    props: {
        check: Boolean,
        modalState: String,
        modalSize: String,
        modalTitle: String,
    },
    computed: {
        show: {
            get() {
                return this.check
            },
            set(check) {
                if (!check) {
                    this.$emit('closeModal', check)
                }
            }
        }
    },
    data() {
        return {
            loginData: {
                username: null,
                password: null,
            },
            isLoggedIn: false,
            dialog: false,
        }
    },
    components: {
        Login,
        Signup,
        Password,
        CompleteSignup,
        CompletePasswordChange,
        
    },
    watch: {
    },
    methods: {
        loginBtn() {
            this.modalState = 'login'
            this.modalSize = '350'
            this.modalTitle = '로그인'
        },
        signupBtn() {
            this.modalState = 'signup'
            this.modalSize = '450'
            this.modalTitle = '회원가입'
        },
        clickSignupBtn() {
            this.modalState = 'signup'
            this.modalSize = '450'
            this.modalTitle = '회원가입'
        },
        clickPasswordBtn() {
            this.modalState = 'password'
            this.modalSize = '350'
            this.modalTitle = '비밀번호찾기'
        },
        signup(signupData) {
            axios.post(API_URL + '/api/users/', signupData)
            .then(() => {
                this.modalState = 'completeSignup'
                this.loginData.username = signupData.username
                this.loginData.password = signupData.password
                axios.post(API_URL + '/login', this.loginData)
                .then((res) => {
                    this.setCookie(res.headers.authorization)
                    this.$emit('signup', this.isLoggedIn)
                })
                .catch(err => console.log(err.response))
            })
            .catch(err => {
                console.log(err.response)
                alert('회원가입에 실패했습니다.')
            })
        },
        login(loginData) {
            axios.post(API_URL + '/login', loginData)
            .then(res => {
                this.setCookie(res.headers.authorization)
                this.$emit('login', this.isLoggedIn)
                this.show = false
            })
            .catch(err => {
                console.log(err.response)
                alert('로그인에 실패했습니다.')
            })
        },
        completePasswordChange() {
            this.modalState = 'completePasswordChange'
        },
        setCookie(token) {
            this.$cookies.set('auth-token', token)
            this.isLoggedIn = true
        },
        logout(res) {
            this.isLoggedIn = res
            this.$router.push('/')
        },

    },
    mounted() {
        this.isLoggedIn = this.$cookies.isKey('auth-token')
    }
}
</script>

<style>
</style>