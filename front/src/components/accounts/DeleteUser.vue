<template>
  <div>
    <p>지금까지 모든 활동 정보들이 삭제 됩니다.</p>
    <p>정말로 탈퇴하시겠습니까?</p>
    <button @click='withdrawal' class='btn btn-danger'>탈퇴</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  methods: {
    withdrawal() {
      const config = {
        headers: {
          Authorization: this.$cookies.get("auth-token")
        }
      }
      this.$cookies.remove('auth-token')
      var result = confirm('정말로 탈퇴하시겠습니까?')
      if (result) {
        axios.delete('http://i3a202.p.ssafy.io:8181/api/users', config)
        .then(() => {
          alert('회원 탈퇴 완료');
          //소켓종료
          this.$store.socket.close();
          this.$store.commit('clearStore');
          this.$router.push('/home')
        })
        .catch(err => console.log(err.data))
      }
    },
  }
}
</script>

<style>

</style>