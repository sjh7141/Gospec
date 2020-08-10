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
                    <router-link to="/"><a class="navbar-brand nav-item nav-link" href="#">GoSpec</a></router-link>
                    <router-link to="/home"><a class="nav-item nav-link" href="#">HOME</a></router-link>
                    <router-link to="/schedule"><a class="nav-item nav-link" href="#">전체일정</a></router-link>
                    <router-link to="/contest"><a class="nav-item nav-link" href="#">공모전</a></router-link>
                    <router-link to="/study"><a class="nav-item nav-link" href="/study">스터디</a></router-link>
                    <router-link to="/jobinfo"><a class="nav-item nav-link" href="/jobinfo">취업정보</a></router-link>
                </div>
                <div class="navbar-nav">
                    <p
                        class='nav-item nav-link my-auto mr-4'
                        type='button'
                        @click='clickLogin'
                        v-if='!isLoggedIn'>로그인</p>
                    <Modal :modalState='modalState' :modalTitle='modalTitle' :modalSize='modalSize' :check='check' @login='login' @closeModal='closeModal'/>
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
            logout() {
                this.isLoggedIn = false
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
            console.log("#####");
            console.log(this.$store.socket);
            console.log("#####");
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