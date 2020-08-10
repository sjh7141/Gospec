<template>
    <div id="header">
        <nav
            class="navbar navbar-expand-lg navbar-dark"
            style="background-color: black;">
            <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup"
                aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div
                class="collapse navbar-collapse d-flex justify-content-between align-items-center"
                id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="navbar-brand nav-item nav-link" href="/index">GoSpec</a>
                    <a class="nav-item nav-link" href="/">HOME</a>
                    <a class="nav-item nav-link" href="/schedule">전체일정</a>
                    <a class="nav-item nav-link" href="/contest">공모전</a>
                    <a class="nav-item nav-link" href="/study">스터디</a>
                    <a class="nav-item nav-link" href="/jobinfo">취업정보</a>
                </div>
                <div class="navbar-nav">
                    <p
                        class='nav-item nav-link my-auto mr-4'
                        type='button'
                        @click='clickLogin'
                        v-if='!isLoggedIn'>로그인</p>
                    <Modal :modalState='modalState' :modalTitle='modalTitle' :modalSize='modalSize' :check='check' @signup='signup' @login='login' @closeModal='closeModal'/>
                </div>
            </div>
        </nav>
        <LoginNavigation @logout='logout' v-if='isLoggedIn'/>
    </div>
</template>

<script>
    import LoginNavigation from '../index/LoginNavigation.vue'
    import Modal from '../accounts/Modal.vue'

    export default {
        name: 'Header',
        components: {
            Modal,
            LoginNavigation
        },
        props: ['isHeader'],
        computed: {},
        watch: {},
        created() {},
        methods: {
            clickLogin() {
                this.check = true
                this.modalState = 'login'
            },
            closeModal() {
                this.check = false
            },
            login() {
                this.isLoggedIn = true
            },
            signup() {
                this.isLoggedIn = true
            },
            logout() {
                this.isLoggedIn = false
                this.$router.push('/home')
            }
        },
        data: function () {
            return {
                keyword: "", 
                isLoggedIn: false, 
                check: false,
                modalState: '',
                modalSize: '350',
                modalTitle: '로그인',
                }
        },
        mounted() {
            this.isLoggedIn = this
                .$cookies
                .isKey('auth-token')
        }
    }
</script>

<style scoped="scoped">
    .middle {
        display: block;
        margin: auto;
    }

    .horizontal li {
        display: inline-block;
        margin: 10px;
    }

    .nav-item.nav-link {
        color: white;
    }
</style>>