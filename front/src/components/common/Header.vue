<template>
    <div id="header">
        <nav class="navbar">
            <div class="navbar_logo">
                <i class="fas fa-external-link-square-alt"></i>
                <router-link to="/" v-if='!isLoggedIn'>GoSpec</router-link>
                <router-link to="/home" v-else>GoSpec</router-link>
            </div>
            <ul class="navbar_menu">
                <li :class="{_selected: isSelected('home')}" @click="$router.push('/home')" type='button'>
                HOME
                </li>
                <li :class="{_selected: isSelected('schedule')}" @click="$router.push('/schedule')" type='button'>
                전체일정
                </li>
                <li :class="{_selected: isSelected('contest')}" @click="$router.push('/contest')" type='button'>
                공모전
                </li>
            </ul>
            <div class="navbar_login">
                <span v-if='!isLoggedIn' @click='clickLogin' type="button">
                   로그인
                </span>
                <Modal :modalState='modalState' :modalTitle='modalTitle' :modalSize='modalSize' :check='check' @signup='signup' @login='login' @closeModal='closeModal'/>
            </div>
        </nav>
        <LoginNavigation @logout='logout' v-if='isLogin'/>
    </div>
</template>

<script>
    import LoginNavigation from '../index/LoginNavigation.vue'
    import Modal from '../accounts/Modal.vue'
    import { mapGetters } from 'vuex'
    export default {
        name: 'Header',
        components: {
            Modal,
            LoginNavigation
        },
        props: ['isHeader'],
        computed: {
            ...mapGetters(['isLogin']),
        },
        watch: {
        },
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
            },
            isSelected(path) {
                return this.$route.path.split('/')[1] == path;
            },
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
    .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: black;
        padding: 8px 12px;
        position: fixed;
        z-index: 4;
        width: 100%;
        
    }
    .navbar_logo {
        margin-left: 10px;
        font-size: 24px;
    }
    .navbar_logo i {
        color: #D32F2F;
        padding-right: 4px;
    }
    .navbar_menu {
        display: flex;
        list-style: none;
        margin-bottom: 0px;
        margin-right: 30px;
        
    }
    .navbar_menu li {
        margin: 0 10px;
        padding: 10px 12px;
        font-size: 16px;
        background-color: transparent;
        border-radius:4px;
        color: white;
    }

    .navbar_menu li:hover {
        background-color: #D32F2F;
    }
    .navbar_login {
        margin-right: 10px;
    }
    .navbar_login span {
        padding: 8px 10px;
        color:white;
        font-size: 16px;
    }
    .navbar_login :hover{
        background-color: #D32F2F;
        border-radius:4px;
    }
    a {
        text-decoration: none;
        color:white;
    }
    ._selected {
        background-color: #D32F2F !important;
    }

</style>>