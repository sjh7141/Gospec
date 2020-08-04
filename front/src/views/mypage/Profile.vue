<template>
    <div>
        <v-card class='row' style='padding: 50px;' color='grey lighten-4'>
            <div class='col-12 text-right'>
                <button @click='clickEditBtn' class='btn btn-primary'>
                    <i class="fas fa-pen mr-2"></i>
                    <p v-if='!editBtn' class='d-inline'>Edit</p>
                    <p v-else class='d-inline'>OK</p>
                </button>
            </div>
            <div class='col-3'>
                <h3 class='my-4'>
                    <i class="fas fa-square mr-1" style='color: red; font-size:15px;'></i>Profile</h3>
                <v-avatar class='mx-auto' size='200'>
                        <v-img 
                        v-if="imageUrl" :src="imageUrl"></v-img>
                        <v-img
                            v-else
                            src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                      </v-avatar>
                <p style='margin-bottom: 50px; margin-top: 50px;'>{{ userData.name }} | {{ userData.nickname }}</p>
                <i class="fas fa-phone-square-alt"></i> {{ userData.phone }}<br>
                <i class="fas fa-envelope-square"></i>
                {{ userData.username }}
            </div>
            <v-card v-if='!editBtn' class='col-8 row' style='margin-left: 5%;'>
                <div class="col-6">
                    <h5 class='text-left'><i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>Career</h5>

                </div>
                <div class="col-6">
                    <h5 class='text-left'>관심사</h5>

                </div>
                <div class="col-6">
                    <h5 class='text-left'>자기소개</h5>

                </div>
                <div class="col-6">
                    <h5 class='text-left'>어학 / 자격증</h5>

                </div>
                
            </v-card>
            <v-card v-else class='col-9 row p-2'>
            </v-card>
            이메일 :{{ userData.username }}
            이름 : {{ userData.name }}
            닉네임 : {{ userData.nickname }}
            전공 : {{ userData.major }}
            자기소개 : {{ userData.selfIntroduction }}
        </v-card>
        
    </div>
</template>

<script>
import axios from 'axios'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

export default {
  components: {
  },
  data() {
      return {
          userData: null,
          editBtn: false,
      }
  },
  methods: {
      getUserInfo() {
          const config = {
              headers: {
                  Authorization: this.$cookies.get("auth-token")
              }
          }
          axios.get(API_URL + '/api/users', config)
          .then(res => {
              this.userData = res.data
              console.log(res.data)
          })
          .catch(err => console.log(err.response))
      },
      clickEditBtn() {
          this.editBtn = !this.editBtn
      }
  },
  created() {
      this.getUserInfo()
  }

}
</script>

<style scoped>
</style>