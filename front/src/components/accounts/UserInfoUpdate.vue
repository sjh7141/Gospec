<template>
  <div>
    <!-- 프로필 사진 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">
            <img style='width: 50px; height: 50px;' :src=img alt="">
          </div>
          <div class="col-9 text-left">
            <h4>{{ username }}</h4>
            <ProfileImgChange />
          </div>
        </div>

        <!-- 프로필 이름 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">이름</div>
          <div class="col-9 text-left d-flex">
            <input type="text" v-model="name">
            <!-- <div class="btn-group btn-group-toggle" data-toggle="buttons">
              <label class="btn btn-secondary">
                <input type="radio" name="options" id="option1"> 남
              </label>
              <label class="btn btn-secondary">
                <input type="radio" name="options" id="option2"> 여
              </label>
            </div> -->
            
            <v-col
              cols="12"
              sm="6"
              class="py-2"
            >
              <v-btn-toggle
                tile
                color="deep-purple accent-3"
                group
              >
                <v-btn value="left">
                  남
                </v-btn>

                <v-btn value="right">
                  여
                </v-btn>
              </v-btn-toggle>
            </v-col>
          </div>
        </div>

        <!-- 프로필 닉네임 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">닉네임</div>
          <div class="col-9 text-left">
            <input type="text" v-model="nickname">
          </div>
        </div>

        
        <!-- 프로필 전화번호 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">전화번호</div>
          <div class="col-9 text-left">
            <input type="text" v-model="phone">
          </div>
        </div>

        <!-- 프로필 주소 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">주소</div>
          <div class="col-9 text-left">
            <input type="text" v-model="address">
          </div>
        </div>

        <!-- 프로필 생일 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">생일</div>
          <div class="col-9 text-left">
            <input type="text" v-model="birthday">
          </div>
        </div>

        <!-- 프로필 관심사 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">관심사</div>
          <div class="col-9 text-left">
            <UserInterest />
          </div>
        </div>

        <!-- 프로필 자기소개 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">자기소개</div>
          <div class="col-9 text-left">
            <textarea name="" id="" cols="50" rows="10"></textarea>
          </div>
        </div>
        
  </div>
</template>

<script>
import UserInterest from '../accounts/UserInterest.vue'
import ProfileImgChange from '../accounts/ProfileImgChange.vue'

export default {
  components: {
    ProfileImgChange,
    UserInterest,
  },
  data() {
    return{
      username: '',
      name: '',
      nickname: '',
      selfIntroduction: '',
      phone: '',
      birthday: '',
      address: '',
      img: 'https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png',
    }
  },
  methods: {
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    },
  },
  mounted() {
    this.checkusername()
  }
}
</script>

<style scoped>
  input {
    border: 1px solid;
  }
  textarea {
    border: 1px solid;
  }
</style>