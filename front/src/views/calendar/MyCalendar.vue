<template>
<div>
  <v-row class="fill-height">
      <v-col>
        <v-sheet height="64" width="100%">
          <v-toolbar flat color="white">
            <v-btn outlined class="mr-4" color="grey darken-2" @click="setToday">
              Today
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>mdi-chevron-left</v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small>mdi-chevron-right</v-icon>
            </v-btn>
            <v-toolbar-title v-if="$refs.calendar">
              {{ $refs.calendar.title }}
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-menu bottom right>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  outlined
                  color="grey darken-2"
                  v-bind="attrs"
                  v-on="on"
                >
                  <span>{{ typeToLabel[type] }}</span>
                  <v-icon right>mdi-menu-down</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item @click="type = 'month'">
                  <v-list-item-title>Month</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'week'">
                  <v-list-item-title>Week</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = 'day'">
                  <v-list-item-title>Day</v-list-item-title>
                </v-list-item>
                <v-list-item @click="type = '4day'">
                  <v-list-item-title>4 days</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-toolbar>
        </v-sheet>
        <v-sheet height="750" width="100%">
          <v-calendar
            ref="calendar"
            v-model="focus"
            color="primary"
            :events="events"
            :event-color="getEventColor"
            :type="type"
            @click:event="showEvent"
            @click:more="viewDay"
            @click:date="viewDay"
            @change="updateRange"
            style= "z-index: 1; min-height: 750px;"
          ></v-calendar>
          <v-dialog 
          v-model="dialog"
          width=60%
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
          >
         <CalendarDetail @delete-change="deleteRange" @dialog-change= "onDialogChange" :likestate="likestate" :selectedEvent="selectedEvent" :color="color" :selectedElement="selectedElement" />
        </v-dialog>
        </v-sheet>
      </v-col>
    </v-row>
</div>
 
</template>

<script>
import axios from 'axios'
import CalendarDetail from './CalendarDetail.vue'
  export default {
    components: {
      CalendarDetail
    },
      props: {
        myContest: {
            type: Array,
        },

        
      },
    data() {
      return{
        likestate: {
          type:Boolean
        },
        dialog: null,
        selectedEvent: {},
        color: null,
        selectedElement: null,
        selectedOpen: false,
        focus: '',
        type: 'month',
        typeToLabel: {
          month: 'Month',
          week: 'Week',
          day: 'Day',
          '4day': '4 Days',
        },
        events: [],
      }
    },
  
    mounted () {
      this.$refs.calendar.checkChange()

    },
    created() {
      this.updateRange()

    },
    methods: {
      deleteRange(contestNo) {
        console.log("deleteRange")
        let idx = this.events.findIndex(item => item.contestNo === contestNo);
        console.log("삭제 번째 " + idx);
        this.events.splice(idx,2);
        if(this.events.length === 1){
          console.log("한개남음")
          this.events = [];
        }
        console.log("deleteRange")
      },
      onDialogChange (dialog) {
        this.dialog = dialog
      },
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      updateRange() {
        this.name = ''
        this.start = ''
        this.end = ''
        this.content =''
        this.contestNo = ''
        this.details = ''
        const events = []
        for (let i = 0; i < this.myContest.length; i++){
          events.push({
            name: this.myContest[i].title,
            start: this.myContest[i].startDate,
            end: this.myContest[i].startDate,
            details : this.myContest[i].content,
            contestNo: this.myContest[i].contestNo,
            
            // 여기 시작 색상
            color: 'black',
          })
          
          events.push({
            name: this.myContest[i].title,
            start: this.myContest[i].endDate,
            end: this.myContest[i].endDate,
            details : this.myContest[i].content,
            contestNo: this.myContest[i].contestNo,
            // 여기 끝 색상
            color: '#FF5252',
          })

          this.events = events
        }
        
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      showEvent ({ nativeEvent, event }) {
        const config = {
          headers: {
            Authorization: this.$cookies.get("auth-token"),
          }
      } 
        axios.get('http://i3a202.p.ssafy.io:8181/api/contest/check/' + event.contestNo, config)
        .then(res => {
          this.likestate = res.data
	
          })
        .catch(err => console.log(err.response))
        console.log(event)
        console.log(this.likestate)
        const open = () => {
          this.dialog=true,
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => this.selectedOpen = true, 10)
        }
        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }
        nativeEvent.stopPropagation()
      },


    },
  }
</script>

