<template>
<div v-if ="likeState==true">
<v-btn id="dislike" icon color='#FDD835'>
    <v-icon @click='clickDisLike()'>mdi-star</v-icon>
</v-btn>
</div>
<div v-else>
<v-btn id="like" icon color="dark">
    <v-icon @click='clickLike()'>mdi-star</v-icon>
</v-btn>
</div>
</template>

<script>
import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/message'

export default {
    props: ['likeState', 'mailNo'],
    methods: {
      clickLike() {
        this.likeState = true;
        this.changeState();
      },
      clickDisLike() {
        this.likeState = false;
        this.changeState();
      },
      changeState(){
            const config = {
              headers: {
              Authorization: this.$cookies.get("auth-token")
              },
            }
            let data = {no : this.mailNo,
                        state : this.likeState,
                      };
            axios.patch(URL+'/important-box', data ,config)
                .then(({ data }) => {
                    if(!data){
                      let msg = '보관함 이동시 문제가 발생했습니다.';
                      alert(msg);
                    }
                    this.checkedNo = [];
                    this.$store.dispatch('getReceiveMessages');
                    this.$store.dispatch('getImportantMessages');
                    this.$store.dispatch('getAllMessages');
                })
      },

    }

}
</script>

<style>

</style>