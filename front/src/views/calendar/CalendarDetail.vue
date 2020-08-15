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

        <LikeState @delete-contest="OnDeleteContest" :contestNo="contestNo" :selectedEvent="selectedEvent" :likestate="likestate"/>
        
        <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
      
          <v-btn
            icon
            dark
            v-bind="attrs"
            v-on="on"
          >
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </template>
        <v-list class="detail" style="display:block margin:0px;">
          <v-list-item
          >
          <a :href= this.selectedEvent.homepage>
          공모전 사이트
          </a>
          </v-list-item>
          <v-list-item>
          <a :href="'/contest/'+selectedEvent.contestNo">공모전 자세히 보기</a>
          </v-list-item>
          <v-list-item>
          <a :href="'/contest/'+selectedEvent.contestNo+'/teams'">공모전 팀찾기</a>
        </v-list-item>
        </v-list>
      </v-menu>
        </v-toolbar>
        <v-card-text>
          <div class="content" style="color:black;">{{selectedEvent.details}}</div> 

        </v-card-text>
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
import LikeState from '../../components/common/LikeState.vue'


export default {
    components: {
      LikeState,
    },
    props: {
    contestNo:null,
    selectedEvent:{},
    dialog:{
        type:Boolean
    },
    color:null,
    likestate: {
      type:Boolean,
    }
      },
    data: () => ({
      like: 0,
      likestate: {
      type:Boolean,
    },
      selectedEvent:{},
      selectedOpen: false,
      dialog:false,
      addcontest: {}
      }),
      methods: {
        OnDeleteContest(contestNo) {
          console.log("열로옴");
        this.$emit('delete-change',contestNo)


        },
        onDialog() {
          this.$emit('dialog-change', false)
        },
      }

}
</script>

<style scoped>
.content {
    white-space: pre-line;
    text-align: left;
    margin: 30px;
}
  .detail a {
      text-decoration: none;
      color:black;
      padding: 8px 10px;
      vertical-align: center;
      display: inline;
  }


</style>