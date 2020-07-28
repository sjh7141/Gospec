<template>
  <div>
    <v-card-text class='p-0'>
      <v-chip-group
        v-model="amenities"
        column
        multiple
      >
        <v-chip v-for='interest in interests' :key='interest.id' filter outlined>{{ interest }}</v-chip>
      </v-chip-group>
    </v-card-text>
  </div>
</template>

<script>
export default {
  data() {
    return{
      interests: ['기획/아이디어', '광고/마케팅', '논문/리포트', '영상/UCC/사진', '디자인/캐릭터/웹툰', '웹/모바일/플래시', '게임/소프트웨어', '과학/공학'],
      amenities: null,
    }
  },
  methods: {
  }
}
</script>

<style>

</style>