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
const URL_CHECK = 'http://i3a202.p.ssafy.io:8181/api/contest/check/';

export default {
    props: {
      // likestate:{
      // type:Boolean,
      // },
      contestNo:null,
      selectedEvent: {},
    },
    data() {return{
      likestate: {type:Boolean,}
    }},
    created() {
      let no = this.$props.selectedEvent.contestNo;
      const config = {headers: {Authorization: this.$cookies.get("auth-token"),}};
      axios.get(URL_CHECK + no, config)
        .then(response => {
          this.likestate = response.data;
        })
        .catch(error => console.log(error));
    },
    methods: {
      clickLike(contestNo) {
        this.likestate = true
        this.$emit('add-contest',contestNo)
        var ca = this.$cookies.get("auth-token")
        if (ca == null) {
          alert('로그인이 필요한 서비스 입니다!')
          this.likestate = false
        }
        const data = {
          contestNo: contestNo,
        }
        const config = {
          
          headers: {
            Authorization: ca,
          }
      } 

      axios.post("http://i3a202.p.ssafy.io:8181/api/contest/bookmark",data,config)
      .then(//res => {
        //console.log(res.data)
      //})
      )
      },

      clickDisLike(contestNo) {
      this.likestate = false
      this.$emit('delete-contest',contestNo)
      var ca = this.$cookies.get("auth-token")

      axios.delete("http://i3a202.p.ssafy.io:8181/api/contest/bookmark",{
        headers: {
          Authorization: ca
        },
        data: {
          contestNo: contestNo
        }
      })
      .then(//res => {
        //console.log(res.data)
      //})
      )
      }
    }

}
</script>

<style>

</style>