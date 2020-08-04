<template>
<div>
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
          <v-list-item-content>
            <v-list-item-title v-text="item.fieldtype"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
</div>

</template>

<script>

export default {
  methods: {
    getfield(field,fieldtype) {
      this.field = field
      this.fieldtype = fieldtype
      console.log(fieldtype)
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
          text: 'all',
          fieldtype: '모든'
        },
        {
          text: '기획-아이디어',
          fieldtype: '기획/아이디어'
        },
        {
          text: '광고-마케팅',
          fieldtype: '광고/마케팅'
        },
        {
          text: '논문-리포트',
          fieldtype: '논문/리포트'
        },
        {
          text: '영상-UCC-사진',
          fieldtype: '영상/UCC/사진'
        },
        {
          text: '디자인-캐릭터-웹툰',
          fieldtype: '디자인/캐릭터/웹툰'
        },
        {
          text: '웹-모바일-플래시',
          fieldtype: '웹/모바일/플래시'
        },
        {
          text: '게임-소프트웨어',
          fieldtype: '게임/소프트웨어'
        },
        {
          text: '과학-공학',
          fieldtype: '과학/공학'
        },
        {
          text: '문학-글-시나리오',
          fieldtype: '문학/글/시나리오'
        },
        {
          text: '건축-건설-인테리어',
          fieldtype: '건축/건설/인테리어'
        },
        {
          text: '네이밍-슬로건',
          fieldtype: '네이밍/슬로건',
        },
        {
          text: '예체능-미술-음악',
          fieldtype: '예체능/미술/음악'
        },
        {
          text: '대외활동-서포터즈',
          fieldtype: '대외활동/서포터즈',
        },
        {
          text: '봉사활동',
          fieldtype: '봉사활동',
        },
        {
          text: '취업-창업',
          fieldtype: '취업/창업'
        },
        {
          text: '해외',
          fieldtype: '해외'
        },
        {
          text: '기타',
          fieldtype: '기타',
        },
      ],
      model: 0,
    }
},

}
</script>

<style>

</style>