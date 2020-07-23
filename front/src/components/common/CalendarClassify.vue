<template>
<div>
  <div class="d-flex">
  <button class= 'cal-btn mr-4' @click="totalCalBtn">전체일정</button>
  <button @click="myCalBtn">내일정</button>
  </div>
  <div class="cal-body">
    <TotalCalendar :contest='contest' v-if="calState == 'total'"/>
    <MyCalendar v-if="calState == 'my'"/>
  </div>
</div>

</template>

<script>
import TotalCalendar from '../common/TotalCalendar.vue'
import MyCalendar from '../common/MyCalendar.vue'
import axios from 'axios'
const API_URL = "http://localhost:8181/api/contest/"
export default {
  components: {
    TotalCalendar,
    MyCalendar,

   },
  data() {
    return{
      calState: 'total',
      contest: [],

    }
  },
  methods: {
    totalCalBtn() {
      axios.get(API_URL)
        .then(response => {
            this.calState = 'total'
            this.contests = response.data
            })
        .catch(error => { console.log(error) })
      
    },
    myCalBtn() {
      this.calState = 'my'
    },
  }
}
</script>

<style>

</style>