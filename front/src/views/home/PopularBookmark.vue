<template>

<div class="contestcard">
  <v-row dense
  >
  <v-col v-for="each in bcontest" v-bind:key="each.contestNo">
  <v-hover v-slot:default="{ hover }">
    <v-card
      
      max-width="300"
    >
  
      <router-link :to="{ path: '/contest/' + each.contestNo}">
      <v-img
        :aspect-ratio="16/9"
        class="white--text align-end"
        height="300px"
        :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + each.imgSrc"
      >
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out black v-card--reveal"
            style="height: 100%; font-size:20px; color:white;"
            v-text="each.title"
          >
          </div>
        </v-expand-transition>
      </v-img>
      </router-link>


      <div class="ddayhome"><dday :data='each'/></div>
      <v-card-text class="text--primary">
        {{each.totalReward}} 
      <br/> 
        {{each.startDate + ' ~ ' +each.endDate}}
      </v-card-text>

    <v-card-actions>
      <LikeState2 class='_inlineBlock' :selectedEvent="each"/>

      <v-btn
        text        
      >
      <router-link :to="{ path: '/contest/' + each.contestNo}" style ="text-decoration: none; color:black;">
      
      자세히 보기
      
      </router-link>
      </v-btn>
      
    </v-card-actions>
    </v-card>
    </v-hover>
  </v-col>
</v-row>
</div>
</template>

<script>
import Dday from '@/components/contest/Dday.vue'
import LikeState2 from '@/components/common/LikeState2.vue'
  export default {
    components:{
      LikeState2,
      Dday
    },
    props:{
      bcontest: {
        type:Array
      }
    },
    data() {
      return {
      }
    }
  }

</script>
<style>
.card{
  display: inline-block;

}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
.ddayhome {
  display: inline-block;
  font-size: 20px;
}

</style>
