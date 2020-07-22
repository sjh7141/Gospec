<template>
    <div>
        here is Mypage
        <my-page-nav-bar></my-page-nav-bar>
        <router-view></router-view>
    </div>
</template>

<script>
    import MyPageNavBar from '@/components/common/MyPageNavBar.vue'

    export default {
        components: {
            MyPageNavBar,
        },
        created(){
        },
        watch: {
        },
        methods: {
        },
        data: () => {
            return {
                
            }
        }

    }
</script>