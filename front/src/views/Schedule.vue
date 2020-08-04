<template>
<div>
<div class="banner">
<Banner />
</div>
<CalendarClassify />
</div>




</template>

<script>
import CalendarClassify from '../components/common/CalendarClassify.vue'
import Banner from '../components/common/Banner.vue'


    export default {
        components: {
            CalendarClassify,
            Banner,

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
<style>
.banner{

    margin-left:50px;
}
</style>