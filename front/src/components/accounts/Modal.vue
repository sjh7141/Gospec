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
        <Login @submit-login-data='login' @clickSignupBtn="clickSignupBtn" :isLoggedIn='isLoggedIn' @clickPasswordBtn="clickPasswordBtn" v-if="modalState == 'login'"/>
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
import Stomp from 'stompjs'
import SockJS from 'sockjs-client'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
    props: {
        check: Boolean,
        modalState: String,
        modalSize: String,
        modalTitle: String,
        checkParent: String,
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
                // this.modalState = 'completeSignup'
                // this.modalSize = '850'
                this.loginData.username = signupData.user.username
                this.loginData.password = signupData.user.password
                axios.post(API_URL + '/login', this.loginData)
                .then(() => {
                    this.login(this.loginData)
                    this.$emit('signup', true)
                    if (this.checkParent){
                        this.$router.push('/home')
                    }
                    this.checkParent = false
                })
                .catch(err => console.log(err.response))
            })
            .catch(err => {
                console.log(err.response)
                alert('회원가입에 실패했습니다.')
            })
        },
        login(loginData) {
            console.log(loginData);
            axios.post(API_URL + '/login', loginData)
            .then(res => {
                this.setCookie(res.headers.authorization)
                this.$emit('login', this.isLoggedIn)
                this.show = false
                //쪽지 소켓 오픈
                this.checkusername();
                this.connect();
                this.$router.go()
            })
            .catch(err => {
                console.log(err.response)
                alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.')
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
            this.$router.push('/home')
        },
        connect() {
          this.$store.socket = new SockJS(API_URL+"/socket");
          this.$store.client = Stomp.over(this.$store.socket);

          this.$store.client.connect({}, frame => {
            this.$store.client.subscribe("/topic/"+this.username, res => {
              console.log(res.body);
            })
            console.log(frame);
          })
        },
        checkusername() {
          var ca = this.$cookies.get("auth-token")
          var base64Url = ca.split('.')[1]
          var decodedValue = JSON.parse(window.atob(base64Url))
          this.username = decodedValue.sub
        },

    },
    mounted() {
        this.isLoggedIn = this.$cookies.isKey('auth-token')
    }
}
</script>

<style>
</style>