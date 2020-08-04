<template>
    <div>
        <form>
            <div class="form-group">
                <label class='d-flex justify-content-left' for="loginInputEmail1">이메일</label>
                <input
                    v-model='loginData.username'
                    type="email"
                    class="form-control"
                    id="loginInputEmail1"
                    placeholder="이메일을 입력해주세요">
            </div>
            <div class="form-group">
                <label class='d-flex justify-content-left' for="loginInputPassword1">비밀번호</label>
                <input
                    @keypress.enter='login' 
                    v-model='loginData.password'
                    type="password"
                    class="form-control"
                    id="loginInputPassword1"
                    placeholder="비밀번호를 입력해주세요.">
            </div>
        </form>
        <div class='d-flex justify-content-center'>
            <button @click="login" class='btn btn-primary mb-2' style="width: 18rem;">로그인</button>
        </div>
        <div class='d-flex justify-content-center'>
            아직 계정이 없으신가요?<p @click="clickSignupBtn" class='ml-2 mb-0' type='button' style='color: #0277BD'>회원가입</p>
        </div>
        <div class='d-flex justify-content-center'>
            비밀번호가 기억나지 않으면?
        <p @click="clickPasswordBtn" class='ml-2' type='button' style='color: #0277BD;'> 비밀번호찾기</p>
        </div>
        <hr class='mt-0'>
        <p class='mb-2'>간편 로그인</p>
        <button class='btn btn-kakao'><img style='height:30px; margin-right: 30px; margin-left: 15px;' src="https://image.flaticon.com/icons/svg/2111/2111466.svg" alt="">카카오톡으로 로그인</button>
        <button class='btn btn-google mt-2'><img class='mr-3' style='height:20px' src="https://ptooff.files.wordpress.com/2017/06/google-logo.png" alt=""> 구글아이디로 로그인</button>
    </div>
</template>

<script>
export default {
    name: 'Login',
    data() {
        return{
            modalState: '',
            loginData: {
                username: '',
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
        },
        checkLogin() {
            if (this.isLoggedIn) {
                this.loginData.username = ''
                this.loginData.password = ''
            }
        }
    },
    mounted() {
        this.checkLogin()
    }
}
</script>

<style scoped>
    label {
        font-size: 1rem;
        color:#263238;
    }
    .btn-primary {
        background-color: #0277BD;
        border-color: #0277BD;
    }
    .btn-kakao {
        height: 40px;
        background-color: #FFEA00;
        width: 95%
    }
    .btn-google {
        height: 40px;
        background-color:white;
        width: 95%
    }
</style>