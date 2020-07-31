<template>
  <div class='container'>
    <p>회원정보수정</p>
    <div class='d-flex row'>
      <!-- 선택창 -->
      <div class="col-3 border border-secondary">
        <p @click='clickInfoUpdateBtn'>회원정보수정</p>
        <p @click='clickDeleteBtn'>회원탈퇴</p>
      </div>
      <!-- 라벨 -->
      <div class='col-9 border border-secondary'>
        <UserInfoUpdate v-if="state == 'infoUpdate'" />
        <DeleteUser v-if="state == 'delete'" />
      </div>
    </div>
  </div>
</template>

<script>
import DeleteUser from '../accounts/DeleteUser.vue'
import UserInfoUpdate from '../accounts/UserInfoUpdate.vue'

export default {
  data() {
    return {
      state: 'infoUpdate',
      username: '',
      }
  },
  components: {
    DeleteUser,
    UserInfoUpdate,
  },
  methods: {
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    },
    clickDeleteBtn() {
      this.state = 'delete'
    },
    clickInfoUpdateBtn() {
      this.state = 'infoUpdate'
    },
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style>

</style>