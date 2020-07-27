<template>
<div>
  <div class="d-flex">
  <button class= 'cal-btn mr-4' @click="totalCalBtn">전체일정</button>
  <button @click="myCalBtn">내일정</button>
  </div>
  <div class="cal-body">
    <TotalCalendar :contest='contest' v-if="calState == 'total'"/>
    <MyCalendar :myContest='myContest' v-if="calState == 'my'"/>
  </div>
</div>

</template>

<script>
import TotalCalendar from '../common/TotalCalendar.vue'
import MyCalendar from '../common/MyCalendar.vue'
import axios from 'axios'
const API_URL = "http://localhost:8181/api/contest/2020-07-01/2020-07-31"
const MY_API_URL = "http://localhost:8181/api/contest/bookmark/"

export default {
  components: {
    TotalCalendar,
    MyCalendar,

   },
  data() {
    return{
      calState: '',
      contest: [],
      myContest: [],
      email:'',

    }
  },
  created() {
    this.totalCalBtn()

  },
  methods: {
    totalCalBtn() {
      
      axios.get(API_URL)
        .then(response => {
            this.contest = response.data
            this.calState = 'total'

            })
        .catch(error => { console.log(error) })
      
    },
    myCalBtn() {
      var ca = this.$cookies.get("auth-token")
      console.log(ca)
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      console.log(decodedValue)
      this.email = decodedValue['sub']
 
      axios.get(MY_API_URL + this.email + "/2020-01-31/2020-12-31")
      .then(response => {
        this.myContest = response.data
        this.calState = 'my'
        console.log(response.data)

      })
      .catch(error => { console.log(error) })
      
      
    },
  }
}
</script>

<style>

</style>