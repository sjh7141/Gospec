<template>
<div class="contestcard">
  <v-row dense
  >
    <v-col>
    <v-card
    class="card mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="300px"
      :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + contest[0].imgSrc" :alt="this.contest[0].title">
      
      <v-card-title v-text="contest[0].title"></v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0" v-text="contest[0].totalReward"></v-card-subtitle>

    <v-card-text class="text--primary" v-text="contest[0].startDate + ' ~ ' +contest[0].endDate">

    </v-card-text>

    <v-card-actions>
      <v-btn
        color="orange"
        text
      >
        Share
      </v-btn>


      <v-btn
        color="orange"
        text
      >
      <router-link :to="{ path: '/contest/' + contest[0].contestNo}">
      자세히 보기
      </router-link>
      </v-btn>
      
    </v-card-actions>
  </v-card>
  </v-col>
     <v-col>
    <v-card
    class="card mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="300px"
      :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + contest[1].imgSrc" :alt="this.contest[1].title">
    
      <v-card-title v-text="contest[1].title"></v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0" v-text="contest[1].totalReward"></v-card-subtitle>

    <v-card-text class="text--primary" v-text="contest[1].startDate + ' ~ ' +contest[1].endDate">

    </v-card-text>

    <v-card-actions>
      <v-btn
        color="orange"
        text
      >
        Share
      </v-btn>


      <v-btn
        color="orange"
        text
      >
      <router-link :to="{ path: '/contest/' + contest[1].contestNo}">
      자세히 보기
      </router-link>
      </v-btn>
      
    </v-card-actions>
  </v-card>
  </v-col>
      <v-col>
    <v-card
    class="card mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="300px"
      :src="'http://i3a202.p.ssafy.io:8181/api/file/download/' + contest[2].imgSrc" :alt="this.contest[2].title">
    
      <v-card-title v-text="contest[2].title"></v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0" v-text="contest[2].totalReward"></v-card-subtitle>

    <v-card-text class="text--primary" v-text="contest[2].startDate + ' ~ ' + contest[2].endDate">

    </v-card-text>

    <v-card-actions>
      <v-btn
        color="orange"
        text
      >
        Share
      </v-btn>


      <v-btn
        color="orange"
        text
      >
      <router-link :to="{ path: '/contest/' + contest[2].contestNo}">
      자세히 보기
      </router-link>
      </v-btn>
      
    </v-card-actions>
  </v-card>
  </v-col>

</v-row>
</div>
</template>

<script>
  export default {
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
</style>
