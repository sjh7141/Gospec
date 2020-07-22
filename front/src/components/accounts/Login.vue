<template>
    <div>
        <form>
            <div class="form-group">
                <label class='d-flex justify-content-left' for="loginInputEmail1">Email address</label>
                <input
                    v-model='loginData.email'
                    type="email"
                    class="form-control"
                    id="loginInputEmail1"
                    placeholder="name@example.com">
            </div>
            <div class="form-group">
                <label class='d-flex justify-content-left' for="loginInputPassword1">Password</label>
                <input
                    v-model='loginData.password'
                    type="password"
                    class="form-control"
                    id="loginInputPassword1"
                    placeholder="영문, 숫자 포함 8자 이상 입력하세요.">
            </div>
        </form>
        <div class='d-flex justify-content-center'>
            <button @click="login" class='btn btn-primary' style="width: 18rem;">로그인</button>
        </div>
        <div class='d-flex justify-content-center'>
            <p @click="clickSignupBtn">회원가입 </p> | <p @click="clickPasswordBtn"> 비밀번호찾기</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Login',
    data() {
        return{
            modalState: '',
            loginData: {
                email: '',
                password: '',
            }
        }
    },
    methods: {
        login() {
            this.$emit('submit-login-data', this.loginData)
        },
        clickSignupBtn() {
            this.$emit('clickSignupBtn')
        },
        clickPasswordBtn() {
            this.$emit('clickPasswordBtn')
        }
    }
}
</script>

<style></style>