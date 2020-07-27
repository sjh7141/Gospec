<template>
    <div class="user" id="login">
        here is schedule<br>
        <router-link to='/contest/1234'>공모전일정 예제 링크(글번호1234)</router-link>
        <CalendarClassify />


    </div>
</template>

<script>
import CalendarClassify from '../components/common/CalendarClassify.vue'


    export default {
        components: {
            CalendarClassify,

        },
        created(){
        },
        watch: {
        },
        methods: {
        },
        data: () => {
            return {
                email: '',
                password: '',
                
            }
        }

    }
</script>