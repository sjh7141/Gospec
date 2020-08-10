<template>

<div class="contestcard">
  <v-row dense
  >
  <v-col v-for="each in contest" v-bind:key="each.contestNo">
  <v-hover v-slot:default="{ hover }">
    <v-card
      max-width="300"
    >
      <v-img
        :aspect-ratio="16/9"
        class="white--text align-end"
        height="300px"
        :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + each.imgSrc"
      >
        <v-expand-transition>
          <div
            v-if="hover"
            class="d-flex transition-fast-in-fast-out black v-card--reveal white--text"
            style="height: 100%; font-size:30px;"
            v-text="each.title"
          >
          </div>
        </v-expand-transition>
      </v-img>
      <v-card-subtitle class="pb-0" v-text="each.totalReward"></v-card-subtitle>
    <v-card-text class="text--primary" v-text="each.startDate + ' ~ ' +each.endDate">

    </v-card-text>
    <v-card-actions>
      <LikeState2 class='_inlineBlock' :selectedEvent="each"/>

      <v-btn
        color="orange"
        text
      >
      <router-link :to="{ path: '/contest/' + each.contestNo}">
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
import LikeState2 from '@/components/common/LikeState2.vue'
  export default {
    components:{
      LikeState2,
    },
    props:{
      contest: {
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
</style>
