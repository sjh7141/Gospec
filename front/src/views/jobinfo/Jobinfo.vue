<template>
    <div>
        here is Jobinfo
        <test />
    </div>
</template>

<script>
import test from '../../components/team/test.vue'
    export default {
        components: {
            test,
        },
        created(){
        },
        methods: {
        },
        data: () => {
            return {
                username: 'test'
            }
        }

    }
</script>