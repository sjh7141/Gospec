<template>
    <div>
        <applicant-each v-for="(each, idx) in recomList" :key="idx" :data="each"/>
    </div>
</template>

<script>
import axios from 'axios'
import ApplicantEach from '@/components/team/ApplicantEach.vue'

const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/recommand'
// const URL = 'http://localhost:8181/api/teams/recommand'

export default {
    components: {
        ApplicantEach,
    },
    data() {return {
        recomList : [],
    }},
    created() {
        const config = {
            headers : {
                Authorization : this.$cookies.get('auth-token')
            }
        };

        axios.get(URL, config)
            .then(response => {
                console.dir(response);
                this.recomList = response.data.user;
            })
            .catch(error => console.log(error))
    },
}
</script>