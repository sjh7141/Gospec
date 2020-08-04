<template>
  <div>
    <!-- 프로필 사진 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">
            <img v-if='!profileImg' style='width: 50px; height: 50px;' :src=img alt="">
            <img v-else style='width: 50px; height: 50px;' :src=profileImg alt="">
          </div>
          <div class="col-9 text-left">
            <h4>{{ username }}</h4>
            <ProfileImgChange />
          </div>
        </div>

        <!-- 프로필 이름 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">이름</div>
          <div class="col-9 text-left user-input">
          <v-text-field
           style="width: 300px"
           class='m-0 p-0'
            v-model="name"
            label=""
            clearable
          ></v-text-field>
          </div>
        </div>

        <!-- 프로필 닉네임 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">닉네임</div>
          <div class="col-9 text-left user-input">
          <v-text-field
           style="width: 300px"
           class='m-0 p-0'
            v-model="nickname"
            label=""
            clearable
          ></v-text-field>
          </div>
        </div>

        
        <!-- 프로필 전화번호 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">전화번호</div>
          <div class="col-9 text-left user-input">
          <v-text-field
           style="width: 300px"
           class='m-0 p-0'
            v-model="phone"
            label=""
            clearable
          ></v-text-field>
          </div>
        </div>

        <!-- 프로필 생일 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">생일</div>
          <div class="col-9 text-left user-input">
          <div class='d-flex' style='width: 300px;'>
          <v-text-field
           class='m-0 p-0'
            v-model="birthday"
            label=""
            clearable
          ></v-text-field>
          <i class="far fa-calendar-alt ml-2" type='button' @click='clickCalendar' style='font-size:30px; display:inline;'></i>
          </div>
          </div><v-layout row wrap>
    <v-flex v-if='calendarState' xs12 sm6 class="hidden-xs-only mx-auto">
      <v-date-picker v-model="picker2" color="green lighten-1" header-color="primary" @change='birthdayData'></v-date-picker>
    </v-flex>
  </v-layout>
        </div>


        <!-- 프로필 주소 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">주소</div>
          <div class="col-9 text-left user-input">
            
          <v-text-field
          style='width: 450px'
           class='m-0 p-0'
            v-model="adress"
            label=""
            clearable
          ></v-text-field>
          </div>
        </div>
        <!-- 프로필 관심사 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">관심사</div>
          <div class="col-9 text-left">
            <UserInterest @submit-amenities='getAmenities' />
          </div>
        </div>

        <!-- 프로필 자기소개 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">자기소개</div>
          <div class="col-9 text-left py-0 px-4">
            <template>
            <v-container class='m-0 p-0' fluid>
              <v-textarea style='width: 90%'
                autocomplete=""
                label=""
              ></v-textarea>
            </v-container>
          </template>
          </div>
        </div>

        <button @click='userInfoChange'>회원정보수정</button>
        
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
      profileImg: null,
      img: 'https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png',
      picker2: null,
      calendarState: false,
      amenities: null,
    }
  },
  methods: {
    birthdayData(){
      this.birthday = this.picker2
      this.calendarState = !this.calendarState
    },
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    },
    clickCalendar() {
      this.calendarState = !this.calendarState
    },
    userInfoChange() {
      console.log(this.userInfo)
    },
    getAmenities(data) {
      this.amenities = data
    }
  },
  mounted() {
    this.checkusername()
  },
  computed: {
    userInfo() {
      return {
        username: this.username,
        name: this.name,
        nickname: this.nickname,
        selfIntroduction: this.selfIntroduction,
        phone: this.phone,
        birthday: this.birthday,
        address: this.address,
        profileImg: this.profileImg,
        amenities: this.amenities,
      }
    }
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
  .user-input {
    padding-bottom: 0;
  }
</style>