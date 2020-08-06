<template>
<div class="contestcard">
    <v-col
    v-for="concard in concards"
    :key="concard.contestNos"      
    >
    <v-card
    class="card mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="300px"
      :src="concard.imgSrc"
    >
      <v-card-title v-text="concard.title"></v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0" v-text="concard.totalReward"></v-card-subtitle>

    <v-card-text class="text--primary" v-text="concard.startDate">
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
      <router-link :to="{ path: '/contest/' + concard.contestNo }">
      자세히 보기
      </router-link>
      </v-btn>
      
    </v-card-actions>
  </v-card>
</v-col>

</div>
</template>

<script>
  export default {
    props:{
      contest: {
        type:Array
      }
    },
    methods: {
      getcard() {
        console.log(this.concards)
      }
    },
    data() {
      return{
        concards: [
          {
            title: this.contest[0].title,
            totalReward: this.contest[0].totalReward,
            startDate: this.contest[0].startDate,
            endDate: this.contest[0].endDate,
            imgSrc: '', 
          
            contestNo: this.contest[0].contestNo
          },
          {
            title: this.contest[1].title,
            totalReward: this.contest[1].totalReward,
            startDate: this.contest[1].startDate,
            endDate: this.contest[1].endDate,
            imgSrc: '',
            contestNo: this.contest[1].contestNo

          },
          {
            title: this.contest[2].title,
            totalReward: this.contest[2].totalReward,
            startDate: this.contest[2].startDate,
            endDate: this.contest[2].endDate,
            imgSrc:'', 

            contestNo: this.contest[2].contestNo
          }
        ]
      }
    }


  

  }
  

</script>
<style>
.card{
  display: inline-block;

}
</style>
