<template>
<v-app>
<div class="cal">
<div class="filter">
<CalendarField />

</div>

<div class="cal-body">
  <div class="d-flex">
  <v-btn color="error" class= 'cal-btn mr-4' @click="totalCalBtn">전체일정</v-btn>
  <v-btn color="error" @click="myCalBtn">내 일정</v-btn>
  </div>
    <div class="calendar">
    <TotalCalendar :field="filed" :contest='contest' v-if="calState == 'total'"/>
    <MyCalendar :myContest='myContest' v-if="calState == 'my'"/>
    </div>
  </div>
</div>
</v-app>

</template>

<script>
import TotalCalendar from './TotalCalendar.vue'
import MyCalendar from './MyCalendar.vue'
import CalendarField from './CalendarField.vue'

import axios from 'axios'
const API_URL = "http://i3a202.p.ssafy.io:8181/api/contest/2020-01-01/2020-12-31"
const MY_API_URL = "http://i3a202.p.ssafy.io:8181/api/contest/bookmark/"

export default {
  components: {
    TotalCalendar,
    MyCalendar,
    CalendarField
   },
  data() {
    return{
      calState: '',
      contest: [],
      myContest: [],
      email:'',
      item:'',
      field:'all',
      fieldtype: '모든',
      items: [
      {
        icon: 'mdi-inbox',
        text: 'all',
        fieldtype: '모든'
      },
      {
        icon: 'mdi-star',
        text: '기획-아이디어',
        fieldtype: '기획/아이디어'
      },
      {
        icon: 'mdi-send',
        text: '광고-마케팅',
        fieldtype: '광고/마케팅'
      },
      {
        icon: 'mdi-email-open',
        text: '논문-리포트',
        fieldtype: '논문/리포트'
      },
      {
        icon: 'mdi-email-open',
        text: '영상-UCC-사진',
        fieldtype: '영상/UCC/사진'
      },
      {
        icon: 'mdi-email-open',
        text: '디자인-캐릭터-웹툰',
        fieldtype: '디자인/캐릭터/웹툰'
      },
      {
        icon: 'mdi-email-open',
        text: '웹-모바일-플래시',
        fieldtype: '웹/모바일/플래시'
      },
      {
        icon: 'mdi-email-open',
        text: '게임-소프트웨어',
        fieldtype: '게임/소프트웨어'
      },
      {
        icon: 'mdi-email-open',
        text: '과학-공학',
        fieldtype: '과학/공학'
      },
      {
        icon: 'mdi-email-open',
        text: '문학-글-시나리오',
        fieldtype: '문학/글/시나리오'
      },
      {
        icon: 'mdi-email-open',
        text: '건축-건설-인테리어',
        fieldtype: '건축/건설/인테리어'
      },
      {
        icon: 'mdi-email-open',
        text: '네이밍-슬로건',
        fieldtype: '네이밍/슬로건',
      },
      {
        icon: 'mdi-email-open',
        text: '예체능-미술-음악',
        fieldtype: '예체능/미술/음악'
      },
      {
        icon: 'mdi-email-open',
        text: '대외활동-서포터즈',
        fieldtype: '대외활동/서포터즈',
      },
      {
        icon: 'mdi-email-open',
        text: '봉사활동',
        fieldtype: '봉사활동',
      },
      {
        icon: 'mdi-email-open',
        text: '취업-창업',
        fieldtype: '취업/창업'
      },
      {
        icon: 'mdi-email-open',
        text: '해외',
        fieldtype: '해외'
      },
      {
        icon: 'mdi-email-open',
        text: '기타',
        fieldtype: '기타',
      },
    ],
    model: 0,

    }
  },
  created() {
    this.totalCalBtn()

  },
  mounted() {
    this.getfield()
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
    getfield(field,fieldtype) {
      this.field = field
      this.fieldtype = fieldtype
      console.log(fieldtype)
    },
  }
}
</script>

<style>

.filter {
  float:left;
  width:12%;
  margin-left: 10px;
  margin-right: 20px;
}


</style>