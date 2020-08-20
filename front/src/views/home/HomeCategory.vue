<template>
<div class="home">
  <v-card
    class="category mx-auto"
    max-width="400"
    
  >
    <v-list>
      <v-list-item-group v-model="model">
        <v-list-item
          v-for="(item,i) in items"
          :key="i"
          :item="item"
          @click ="getfield(item.text,item.fieldtype,item.icons,item.color)"
          @change ="getContest(); getBookmark();"
          color="error"
        >
          <v-list-item-content>
            <v-list-item-title v-text="item.fieldtype"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
<div class="contest">
  <div class="home_title" style="margin-right:100px">
    <v-btn :color="color" fab="fab" x-small="x-small">
    <v-icon>{{ icons }}</v-icon> 
  </v-btn>
  {{ fieldtype }} 인기 공모전
   </div>
  <PopularContest :contest ="contest"/>
  </div>
<div class="study">
  <div class="home_title" style="margin-right:100px">
  <v-btn :color="color" fab="fab" x-small="x-small">
    <v-icon>{{ icons }}</v-icon> 
  </v-btn>
  Gospec 회원픽
  </div>
  <PopularBookmark :bcontest ="bcontest"/></div>
</div>

</template>

<script>
import PopularContest from './PopularContest.vue'
import PopularBookmark from './PopularBookmark.vue'
import axios from 'axios'
export default {
  components: {
    PopularContest,
    PopularBookmark,
  },
  created() {
    this.getContest()
    this.getBookmark()

  },
  methods: {
    getfield(field,fieldtype,icons,color) {
      this.field = field
      this.fieldtype = fieldtype
      this.icons = icons
      this.color = color
      console.log(fieldtype)
    },
    getContest() {
    console.log(this.field)
    axios.get("http://i3a202.p.ssafy.io:8181/api/contest/field/top/"+this.field)
    .then(response => {
      this.contest = response.data
      console.log("getContest")
      })
    .catch(error => { console.log(error) })
    },
    getBookmark() {
      console.log(this.field)
      axios.get("http://i3a202.p.ssafy.io:8181/api/contest/field/top-bookmark/"+this.field)
      .then(response => {
        this.bcontest = response.data
        console.log("고스펙회원픽")
        })
      .catch(error => { console.log(error) })
    },
    },

              
    data() {
    return {
        contest:[],
        bcontest:[],
        item:'',
        field:'all',
        fieldtype: '모든',
        icons: "fas fa-arrow-right", 
        color: "error",
        items:[
        {
          text: 'all',
          fieldtype: '모든',
          icons: "fas fa-arrow-right", 
          color: 'error',
          
        },
        {
          text: '기획-아이디어',
          fieldtype: '기획/아이디어',
          icons: "far fa-lightbulb interest", 
          color:'pink lighten-2',
        },
        {
          text: '광고-마케팅',
          fieldtype: '광고/마케팅',
          icons: "fas fa-bullhorn ml-2 interest",
          color:'purple lighten-2',
        },
        {
          text: '논문-리포트',
          fieldtype: '논문/리포트',
          icons:"fas fa-scroll interest", 
          color:'blue lighten-4',
        },
        {
          text: '영상-UCC-사진',
          fieldtype: '영상/UCC/사진',
          icons: "fas fa-video interest", 
          color:'indigo darken-1',
        },
        {
          text: '디자인-캐릭터-웹툰',
          fieldtype: '디자인/캐릭터/웹툰',
          icons: "fas fa-palette interest", 
          color:'cyan',
        },
        {
          text: '웹-모바일-플래시',
          fieldtype: '웹/모바일/플래시',
          icons:"fas fa-mobile-alt interest", 
          color:'teal lighten-1',
        },
        {
          text: '게임-소프트웨어',
          fieldtype: '게임/소프트웨어',
          icons:"fas fa-gamepad interest", 
          color:'light-blue lighten-1',
        },
        {
          text: '과학-공학',
          fieldtype: '과학/공학',
          icons:"fas fa-flask interest", 
          color:'lime lighten-3',

        },
        {
          text: '문학-글-시나리오',
          fieldtype: '문학/글/시나리오',
          icons:"fas fa-book-open interest", 
          color:'green lighten-2',
        },
        {
          text: '건축-건설-인테리어',
          fieldtype: '건축/건설/인테리어',
          icons:"fas fa-building interest", 
          color:'lime accent-2',
        },
        {
          text: '네이밍-슬로건',
          fieldtype: '네이밍/슬로건',
          icons:"fas fa-quote-left interest", 
          color:'orange lighten-2',
        },
        {
          text: '예체능-미술-음악',
          fieldtype: '예체능/미술/음악',
          icons:"fas fa-music interest",
           color:'yellow lighten-2',
        },
        {
          text: '대외활동-서포터즈',
          fieldtype: '대외활동/서포터즈',
          icons:"fas fa-hands-helping interest",
          color:'amber darken-3',
        },
        {
          text: '봉사활동',
          fieldtype: '봉사활동',
          icons:"fas fa-people-carry interest",
          color:'brown darken-1',
        },
        {
          text: '취업-창업',
          fieldtype: '취업/창업',
          icons:"fas fa-id-card-alt interest",
          color:'blue-grey darken-1',
        },
        {
          text: '해외',
          fieldtype: '해외',
          icons:"fas fa-plane-departure interest",
          color:'grey lighten-2',
        },
        {
          text: '기타',
          fieldtype: '기타',
          icons:"fas fa-ellipsis-h interest",
          color:'grey lighten-1'
        },
      ],

      model: 0,
    }
},

}
</script>

<style>
.home {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  grid-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
  margin-bottom: 50px;
}
.category {
  grid-column: 1;
  grid-row: 1/5;

}
.contest {
  grid-column: 2/7;
  grid-row: 1/3;
  font-size: 30px;
  color:black;
  margin-bottom: 50px;
}
.study {
  grid-column: 2/7;
  grid-row: 3/5;
  font-size: 30px;
  color:black;

}
.home_title {
  margin-bottom: 30px;
}
</style>
