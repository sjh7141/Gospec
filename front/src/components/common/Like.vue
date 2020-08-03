<template>
<v-btn icon color="dark">
    <v-icon v-on:click='clickLike(selectedEvent.contestNo)'>mdi-star</v-icon>
</v-btn>

</template>

<script>
import axios from 'axios'
export default {
    props: {
        contestNo:null,
        selectedEvent: {}
    },
    methods: {
                clickLike(contestNo) {
        var ca = this.$cookies.get("auth-token")
        console.log(ca)
        console.log(contestNo)
        const data = {
          contestNo: contestNo,
        }

        const config = {
          
          headers: {
            Authorization: ca,
          }
      } 
  
      console.log('좋아요')
    
      axios.post("http://i3a202.p.ssafy.io:8181/api/contest/bookmark",data,config)
      .then(res => {
        console.log(res.data)
      })
      },
    }

}
</script>

<style>

</style>