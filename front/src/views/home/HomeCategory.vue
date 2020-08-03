<template>
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-list>
      <v-list-item-group v-model="model">
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
export default {
    methods : {
    },
    data() {
    return {
        items: [
        {
          icon: 'mdi-inbox',
          text: 'all',
        },
        {
          icon: 'mdi-star',
          text: '기획-아이디어',
        },
        {
          icon: 'mdi-send',
          text: '광고-마케팅',
        },
        {
          icon: 'mdi-email-open',
          text: '논문-리포트',
        },
        {
          icon: 'mdi-email-open',
          text: '영상-UCC-사진',
        },
        {
          icon: 'mdi-email-open',
          text: '디자인-캐릭터-웹툰',
        },
        {
          icon: 'mdi-email-open',
          text: '웹-모바일-플래시',
        },
        {
          icon: 'mdi-email-open',
          text: '게임-소프트웨어',
        },
        {
          icon: 'mdi-email-open',
          text: '과학-공학',
        },
        {
          icon: 'mdi-email-open',
          text: '문학-글-시나리오',
        },
        {
          icon: 'mdi-email-open',
          text: '건축-건설-인테리어',
        },
        {
          icon: 'mdi-email-open',
          text: '네이밍-슬로건',
        },
        {
          icon: 'mdi-email-open',
          text: '예체능-미술-음악',
        },
        {
          icon: 'mdi-email-open',
          text: '대외활동-서포터즈',
        },
        {
          icon: 'mdi-email-open',
          text: '봉사활동',
        },
        {
          icon: 'mdi-email-open',
          text: '취업-창업',
        },
        {
          icon: 'mdi-email-open',
          text: '해외',
        },
        {
          icon: 'mdi-email-open',
          text: '기타',
        },
      ],
      model: 1,
    }
},

}
</script>

<style>

</style>