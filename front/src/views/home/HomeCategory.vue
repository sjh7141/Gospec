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
          :field="item.text"
          @click ="getfield(item.text,item.fieldtype)"
          @change ="getContest"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.fieldtype"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
<div class="contest">{{ fieldtype }} 인기 공모전<PopularContest :contest ="contest"/></div>
<div class="study">{{ fieldtype }} 인기 스터디<PopularStudy :contest ="contest"/></div>
</div>

</template>

<script>
import PopularContest from './PopularContest.vue'
import PopularStudy from './PopularStudy.vue'
import axios from 'axios'
export default {
  components: {
    PopularContest,
    PopularStudy,
  },
  mounted() {
    this.getContest()
  },
  methods: {
    getfield(field,fieldtype) {
      this.field = field
      this.fieldtype = fieldtype
      console.log(fieldtype)
    },
    getContest() {
    console.log(this.field)
    axios.get("http://i3a202.p.ssafy.io:8181/api/contest/field/"+this.field)
    .then(response => {
      this.contest = response.data
      })
    .catch(error => { console.log(error) })
      },
  },
    data() {
    return {
        contest:[],
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
      model: 1,
    }
},

}
</script>

<style>
.home {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
}
.category {
  grid-column: 1;
  grid-row: 1/5;

}
.contest {
  grid-column: 2/7;
  grid-row: 1/3;
  font-size: 30px;

}
.study {
  grid-column: 2/7;
  grid-row: 3/5;
  font-size: 30px;

}

</style>