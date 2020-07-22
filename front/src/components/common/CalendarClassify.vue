<template>
<div>
  <div class="d-flex">
  <button class= 'cal-btn mr-4' @click="totalCalBtn">전체일정</button>
  <button @click="myCalBtn">내일정</button>
  </div>
  <div class="cal-body">
    <TotalCalendar v-if="calState == 'total'"/>
    <MyCalendar v-if="calState == 'my'"/>
  </div>
</div>

</template>

<script>
import TotalCalendar from '../common/TotalCalendar.vue'
import MyCalendar from '../common/MyCalendar.vue'

export default {
  components: {
    TotalCalendar,
    MyCalendar,

   },
  data() {
    return{
      calState: 'total',

    }
  },
  methods: {
    totalCalBtn() {
      this.calState = 'total'
    },
    myCalBtn() {
      this.calState = 'my'
    },
  }
}
</script>

<style>

</style>