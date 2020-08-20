<template>
  <div>
    <detail-message :messageNo="no" type="send"></detail-message>
  </div>
</template>

<script>
import DetailMessage from '@/components/message/ReadMessage.vue';

export default {
  components: {
    DetailMessage,
  },
  data(){
      return{
          no : 0,
      }
  },
  created() {
    this.$store.dispatch('getSendMessage', `${this.$route.query.no}`);
    this.no = this.$route.query.no;

  },
};
</script>

<style></style>
