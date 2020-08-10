<template>
    <div>
        This is Contest Applicant List Page.
        <message-button receiver="test5"></message-button>
        <applicant-each v-for="(each, idx) in recomList" :key="idx" :data="each"/>
    </div>
</template>

<script>
import axios from 'axios'
import ApplicantEach from '@/components/team/ApplicantEach.vue'
import MessageButton from '@/components/message/MessageButton.vue';

const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/recommand'
// const URL = 'http://localhost:8181/api/teams/recommand'

export default {
    components: {
        ApplicantEach,
        MessageButton,
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