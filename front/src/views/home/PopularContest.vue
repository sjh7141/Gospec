<template>
<div class="contestcard">
  <v-row dense>
    <v-col>
    <v-card
    class="card mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="300px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>{{ contest[0].title }}</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">{{ contest[0].totalReward}}</v-card-subtitle>

    <v-card-text class="text--primary">
      <div>{{ contest[0].startDate}}</div>

      <div>{{ contest[0].endDate}}</div>
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
        Explore
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
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>{{ contest[1].title }}</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">{{ contest[1].totalReward}}</v-card-subtitle>

    <v-card-text class="text--primary">
      <div>{{ contest[1].startDate}}</div>

      <div>{{ contest[1].endDate}}</div>
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
        Explore
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
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>{{ contest[2].title }}</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">{{ contest[2].totalReward}}</v-card-subtitle>

    <v-card-text class="text--primary">
      <div>{{ contest[2].startDate}}</div>

      <div>{{ contest[2].endDate}}</div>
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
        Explore
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
    methods: {
      getCard() {
        const cards = []
        for (let i = 0; i < this.contest.length; i++){
        console.log(this.contest[i].title)
        cards.push({
          title: this.contest[i].title, 
          src:'https://cdn.vuetifyjs.com/images/cards/road.jpg', 
          flex: 6 })
      }
    },
    data() {
      return {


      }

    }
  }
  }

</script>
<style>
.card{
  display: inline-block;

}
</style>