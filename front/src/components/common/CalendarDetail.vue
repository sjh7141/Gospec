<template>
  <v-dialog v-model="dialog"

            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <v-card
              color="grey lighten-4"
            >
              <v-toolbar
                :color="selectedEvent.color"
                dark
              >
                <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                <v-spacer></v-spacer>
              
                <div v-if="likestate==false">
                <v-btn icon color='dark'>
                  <v-icon @click='clickLike(selectedEvent.contestNo)'>mdi-star</v-icon>
                </v-btn>
                </div>
                <div v-else>
                <v-btn icon color='pink'>
                  <v-icon @click='clickDisLike(selectedEvent.contestNo)'>mdi-star</v-icon>
                </v-btn>
                </div>
                <v-btn icon>
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </v-toolbar>
              <v-card-text>
                <div class="content">{{selectedEvent.details}}</div> 

              </v-card-text>
              <v-card-actions>
                <v-btn
                  text
                  color="secondary"
                  @click="dialog = false"
                >
                  Cancel
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
</template>

<script>
import axios from 'axios'
export default {
    props: {
    selectedEvent:{},
    dialog:{
        type:Boolean
    },
    color:null,
      },
    data: () => ({
      like: 0,
      likestate: '',
      }),
      methods: {
        clickLike(contestNo) {
        this.starcolor = 'pink'
        var ca = this.$cookies.get("auth-token")
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
        this.like = 1
        console.log(this.like)
      })
      },
      clickDisLike(contestNo) {
      this.starcolor = 'dark'
      var ca = this.$cookies.get("auth-token")
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
        this.like = 0
        console.log(res.data)
        console.log('삭제')
        console.log(this.like)
      })
      }
      }

}
</script>

<style scoped>
.content {
    white-space: pre-line;
    text-align: left;
    margin: 30px;
}
</style>