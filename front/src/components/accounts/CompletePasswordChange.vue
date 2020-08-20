<template>
  <div>
    <h2>비밀번호 변경이 완료되었습니다.</h2>
    <button @click='loginPage'>로그인</button>
  </div>
</template>

<script>
export default {
  methods: {
    loginPage() {
      this.$emit('loginPage')
    }
  }
}
</script>

<style>

</style>