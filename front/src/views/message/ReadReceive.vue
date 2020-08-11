<template>
  <div>
    <detail-message :messageNo="no" type="receive"></detail-message>
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
    this.$store.dispatch('getReceiveMessage', `${this.$route.query.no}`);
    this.no = this.$route.query.no;
  },
};
</script>

<style></style>
