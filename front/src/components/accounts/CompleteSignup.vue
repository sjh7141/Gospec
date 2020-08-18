<template>
  <div class="box">
    <h3>회원가입을 축하합니다!</h3>
    <div class="content">GoSpec의 더 많은 기능들을 사용하고 싶다면 <br> 추가정보를 입력해주세요.</div>
    <v-btn color="primary" @click="moveToUserInfo">추가정보입력</v-btn><br>
    <v-btn text color="primary" @click="skip">건너뛰기</v-btn>
  </div>
</template>

<script>
export default {
  name: 'CompleteSignup',
  methods: {
        moveToUserInfo() {
          this.$emit('childs-event', true);    
        },
        skip(){
          this.$emit('childs-event', false);
        },
  },
  data(){
    return {
      content : `GoSpec의 더 많은 기능들을 사용하고 싶다면 추가정보를 입력해주세요.`,
    }
  },

}
</script>

<style scoped>
.content { 
  display: block;
  margin: 1em 1.4em;
  text-align: center;
  font-family: NanumSquareRound, Avenir, Helvetica, Arial, sans-serif;
  font-size: 17px;
  }
.box {
  height : 260px;
  margin : auto;
  padding: 35px 0 30px 0;
}
</style>