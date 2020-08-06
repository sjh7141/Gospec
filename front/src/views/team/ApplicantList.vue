<template>
    <div>
        <div v-for="(each, idx) in recomList" :key="idx">
            {{each.nickname}}/{{each.interestFieldList}}/{{each.username}}으로쪽지기능
        </div>
    </div>
</template>

<script>
import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/teams/recommand'
// const URL = 'http://localhost:8181/api/teams/recommand'

export default {
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