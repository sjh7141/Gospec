<template>
      <v-card 
        color="grey lighten-4"
      >
        <v-toolbar
          :color="selectedEvent.color"
          dark
        >
          <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
          <v-spacer></v-spacer>
        <div  v-if="likestate==false">
        <Like @state-change="onStateChange" :contestNo="contestNo" :selectedEvent="selectedEvent"/>
        </div>
        <div v-else>
        <DisLike @state-change="onStateChange" :contestNo="contestNo" :selectedEvent="selectedEvent"/>
        </div>
        </v-toolbar>
        <v-card-text>
          <div class="content">{{selectedEvent.details}}</div> 

        </v-card-text>
        <v-btn>
          <router-link :to="{ path: '/contest/' + selectedEvent.contestNo}">공모전 자세히 보기</router-link>
        </v-btn>
        <v-btn>
          <router-link :to="{ path: '/contest/' + selectedEvent.contestNo +'/teams'}">공모전 팀찾기</router-link>
        </v-btn>
        <v-card-actions>
          <v-btn
            text
            color="secondary"
            @click="onDialog"
          >
            Cancel
          </v-btn>
        </v-card-actions>
        </v-card>
        

</template>

<script>
import Like from '../common/Like.vue'
import DisLike from '../common/DisLike.vue'

export default {
    components: {
      Like,
      DisLike  
    },
    props: {
    contestNo:null,
    likestate:null,
    selectedEvent:{},
    dialog:{
        type:Boolean
    },
    color:null,
      },
    data: () => ({
      like: 0,
      likestate: '',
      selectedEvent:{},
      selectedOpen: false,
      dialog:false,
      }),
      methods: {
        onDialog() {
          this.$emit('dialog-change', false)
        },
        onStateChange(likestate) {
          this.likestate = likestate
          
          console.log(likestate)
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