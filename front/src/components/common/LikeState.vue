<template>
<div v-if ="likestate==true">
<v-btn id="dislike" icon color='#FDD835'>
    <v-icon v-on:click='clickDisLike(selectedEvent.contestNo)'>mdi-star</v-icon>
</v-btn>
</div>
<div v-else>
<v-btn id="like" icon color="dark">
    <v-icon v-on:click='clickLike(selectedEvent.contestNo)'>mdi-star</v-icon>
</v-btn>
</div>
</template>

<script>
import axios from 'axios'
export default {
    props: {
      likestate:{
      type:Boolean,
      },
      contestNo:null,
      selectedEvent: {},
    },
    methods: {
      clickLike(contestNo) {
        this.likestate = true
        console.log(this.likestate)
        this.$emit('add-contest',contestNo)
        var ca = this.$cookies.get("auth-token")
        if (ca == null) {
          alert('로그인이 필요한 서비스 입니다!')
          this.likestate = false
        }
        console.log("이것은 토큰")
        console.log(ca)
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

      clickDisLike(contestNo) {
      this.likestate = false
      this.$emit('delete-contest',contestNo)
      var ca = this.$cookies.get("auth-token")
      console.log("이것은 토큰")

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