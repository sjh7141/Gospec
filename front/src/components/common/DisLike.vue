<template>
<v-btn icon color='#FDD835'>
    <v-icon v-on:click='clickDisLike(selectedEvent.contestNo)'>mdi-star</v-icon>
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
      clickDisLike(contestNo) {
      this.$emit('state-change',false)
      var ca = this.$cookies.get("auth-token")
      console.log(ca)
      console.log(contestNo)
      console.log('취소')

      axios.delete("http://i3a202.p.ssafy.io:8181/api/contest/bookmark",{
        headers: {
          Authorization: ca
        },
        data: {
          contestNo: contestNo
        }
      })
      .then(res => {
        console.log(res.data)
        console.log('삭제')
      })
      }
    }

}
</script>

<style>

</style>