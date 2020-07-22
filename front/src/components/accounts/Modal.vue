<template>
    <div class='d-flex'>
        <!-- Button trigger modal -->
        <p
            @click="loginBtn"
            class='mr-4'
            data-toggle="modal"
            data-target="#accountModal">
            Login
        </p>
        <p
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
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'login'">Login</h5>
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'signup'">Signup</h5>
                        <h5 class="modal-title" id="accountModalLabel" v-if="modalState == 'password'">비밀번호 찾기</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <Login @clickSignupBtn="clickSignupBtn" @clickPasswordBtn="clickPasswordBtn" v-if="modalState == 'login'"/>
                        <Signup @submit-signup-data="signup" v-if="modalState == 'signup'" />
                        <Password v-if="modalState == 'password'" />
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
        }
    },
    components: {
        Login,
        Signup,
        Password,
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
            console.log(signupData)
        }
    },
}
</script>

<style></style>