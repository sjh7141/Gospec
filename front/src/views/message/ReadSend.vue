<template>
  <div>
    <detail-message/>
  </div>
</template>

<script>
import DetailMessage from '@/components/message/ReadMessage.vue';
export default {
  components: {
    DetailMessage,
  },
  created() {
    this.$store.dispatch('getSendMessage', `${this.$route.query.no}`);
  },
};
</script>

<style></style>
