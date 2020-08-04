<template>
<v-app>
<div class="cal">
<div class="filter">
  <CalendarFeild />
</div>
<div class="cal-body">
  <div class="d-flex">
  <v-btn color="error" class= 'cal-btn mr-4' @click="totalCalBtn">전체일정</v-btn>
  <v-btn color="error" @click="myCalBtn">내 일정</v-btn>
  </div>
    <div class="calendar">
    <TotalCalendar :contest='contest' v-if="calState == 'total'"/>
    <MyCalendar :myContest='myContest' v-if="calState == 'my'"/>
    </div>
  </div>
</div>
</v-app>

</template>

<script>
import TotalCalendar from '../common/TotalCalendar.vue'
import MyCalendar from '../common/MyCalendar.vue'
import CalendarFeild from '../common/CalendarFeild.vue'
import axios from 'axios'
const API_URL = "http://i3a202.p.ssafy.io:8181/api/contest/2020-07-01/2020-07-31"
const MY_API_URL = "http://i3a202.p.ssafy.io:8181/api/contest/bookmark/"

export default {
  components: {
    TotalCalendar,
    MyCalendar,
    CalendarFeild

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

.filter {
  float:left;
  width:15%;
  margin-right: 50px;
}


</style>