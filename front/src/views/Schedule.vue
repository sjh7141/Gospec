<template>
<div class="schedule">
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
        mounted(){
            this.styleChange()
        },
        watch: {
        },
        methods: {
            styleChange() {
                document.querySelector('#schedule').classList.remove('container')
            }
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
    margin-bottom: 50px;
    margin-right: 70px;
    margin-left: 10px;
}

.banner{

    margin-left:50px;
}
</style>