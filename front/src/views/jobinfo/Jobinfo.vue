<template>
    <div>
        here is Jobinfo
        <ProfileModal :username='username' />
    </div>
</template>

<script>
import ProfileModal from '../../components/accounts/ProfileModal.vue'
    export default {
        components: {
            ProfileModal,
        },
        created(){
        },
        watch: {
        },
        methods: {
        },
        data: () => {
            return {
                username: 'namdk7@naver.com'
            }
        }

    }
</script>