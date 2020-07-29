<template>
    <div class='d-flex'>
        <MypageDropdown v-if='isLoggedIn' @logout="logout" />
        <!-- Button trigger modal -->
        <p v-if='!isLoggedIn'
            @click="loginBtn"
            class='mr-4'
            data-toggle="modal"
            data-target="#accountModal">
            Login
        </p>
        <p v-if='!isLoggedIn'
        @click="signupBtn"
            data-toggle="modal"
            data-target="#accountModal">
            Signup
        </p>

        <!-- Modal -->
        <div
            class="modal fade"
            id="accountModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="accountModalLabel"
            aria-hidden="true">
            <div :class=modalSize>
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'login' || modalState == 'completeLogin'">Login</h5>
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'signup' || modalState == 'completeSignup'">Signup</h5>
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'password' || modalState == 'completePasswordChange'">비밀번호 찾기</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <Login @submit-login-data='login' @clickSignupBtn="clickSignupBtn" @clickPasswordBtn="clickPasswordBtn" v-if="modalState == 'login'"/>
                        <Signup @submit-signup-data="signup" v-if="modalState == 'signup'" />
                        <Password @completePasswordChange="completePasswordChange" v-if="modalState == 'password'" />
                        <CompleteSignup v-if="modalState == 'completeSignup'" />
                        <CompletePasswordChange @loginPage="loginBtn" v-if="modalState == 'completePasswordChange'" />
                        <CompleteLogin v-if="modalState == 'completeLogin'" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Login from '../accounts/Login.vue'
import Signup from '../accounts/Signup.vue'
import Password from '../accounts/Password.vue'
import CompleteSignup from '../accounts/CompleteSignup.vue'
import CompletePasswordChange from '../accounts/CompletePasswordChange.vue'
import CompleteLogin from '../accounts/CompleteLogin.vue'
import MypageDropdown from '../common/MypageDropdown.vue'
import axios from 'axios'

const API_URL = 'http://localhost:8181'

export default {
    name: 'Modal',
    data() {
        return {
            modalState: '',
            loginData: {
                username: null,
                password: null,
            },
            modalSize: '',
            isLoggedIn: false,
        }
    },
    components: {
        Login,
        Signup,
        Password,
        CompleteSignup,
        CompletePasswordChange,
        CompleteLogin,
        MypageDropdown,
    },
    watch: {
    },
    methods: {
        loginBtn() {
            this.modalState = 'login'
            this.modalSize = 'modal-dialog'
        },
        signupBtn() {
            this.modalState = 'signup'
            this.modalSize = 'modal-dialog modal-lg'
        },
        clickSignupBtn() {
            this.modalState = 'signup'
            this.modalSize = 'modal-dialog modal-lg'
        },
        clickPasswordBtn() {
            this.modalState = 'password'
            this.modalSize = 'modal-dialog'
        },
        signup(signupData) {
            axios.post(API_URL + '/api/users/', signupData)
            .then(res => {
                console.log(res)
                console.log('회원가입 성공')
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
                this.modalState = 'completeLogin'
                this.$emit('login', this.isLoggedIn)
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

<style></style>