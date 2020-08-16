<template>
<div class="schedule">
<div class="banner">
<Banner />
</div>
<CalendarClassify />
</div>

</template>

<script>
import CalendarClassify from './calendar/CalendarClassify.vue'
import Banner from './calendar/Banner.vue'

    export default {
        components: {
            CalendarClassify,
            Banner,

        },
        mounted(){
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
.schedule {

    margin-right: 70px;
    margin-left: 10px;
    margin-top: 80px;
}

.banner{

    margin-left:50px;
}
</style>