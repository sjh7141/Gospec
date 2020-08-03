<template>
  <v-dialog v-model="dialog"
            width=60%

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
              <Like v-if="likestate==false" :contestNo="contestNo" :selectedEvent="selectedEvent"/>
              <DisLike v-else :contestNo="contestNo" :selectedEvent="selectedEvent"/>
              </v-toolbar>
              <v-card-text>
                <div class="content">{{selectedEvent.details}}</div> 

              </v-card-text>
              <v-btn>
                <router-link :to="{ path: '/contest/' + selectedEvent.contestNo}">공모전 자세히 보기</router-link>
              </v-btn>
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
      }),
      methods: {
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