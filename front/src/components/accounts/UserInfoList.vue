<template>
  <div class='container'>
    <div class='d-flex row'>
      <!-- 선택창 -->
      <v-card
    class="mx-auto col-3"
    tile
  >
    <v-list flat>
          <template>
            <v-list-item-content>
              <v-list-item-title class='text-left' style='padding-left: 20px'>회원정보수정</v-list-item-title>
            </v-list-item-content>
          </template>
        <!-- 개인정보수정 -->
        <v-list-item @click='clickInfoUpdateBtn'>
          <v-list-item-icon>
            <v-icon></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>
              개인정보수정
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        
        <!-- 비밀번호수정 -->
        <v-list-item @click='clickpasswordChangeBtn'>
          <v-list-item-icon>
            <v-icon></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>
              비밀번호변경
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- 회원탈퇴 -->
        <v-list-item @click='clickDeleteBtn'>
          <v-list-item-icon>
            <v-icon></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>
              회원탈퇴
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

    </v-list>
  </v-card>
      <!-- 라벨 -->
      <v-card class='col-9 p-0' style='min-height:  500px;'>
        <UserInfoUpdate v-if="state == 'infoUpdate'" />
        <DeleteUser v-if="state == 'delete'" />
        <PasswordChange v-if="state == 'passwordChange'"/>
      </v-card>
    </div>
  </div>
</template>

<script>
import DeleteUser from '../accounts/DeleteUser.vue'
import UserInfoUpdate from '../accounts/UserInfoUpdate.vue'
import PasswordChange from '../accounts/PasswordChange.vue'

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
    PasswordChange,
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
    clickpasswordChangeBtn() {
      this.state = 'passwordChange'
    }
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style scoped>
  .v-card {
  }
</style>