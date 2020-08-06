<template>
  <div class='p-0'>
    <!-- 프로필 사진 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">
            <v-avatar class='mx-auto' size='100'>
              <v-img 
              v-if="imageUrl" :src="imageUrl"></v-img>
              <v-img
                  v-else
                  src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
            </v-avatar>
          </div>
          <div class="col-9 text-left">
            <h4>{{ username }}</h4>
            <ProfileImgChange @submit-image-data='inputImg' :profileImg="profileImg" />
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

        <!-- 프로필 관심사 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">관심사</div>
          <div class="col-9 text-left">
            <UserInterest @submit-amenities='getinterestFieldList' :interestFieldList='interestFieldList' />
          </div>
        </div>

        <!-- 프로필 전공 -->
        <div class='row'>
          <div class="col-3 text-right profile-label">전공</div>
          <div class="col-9 text-left user-input">
            <v-text-field
            style="width: 300px"
            class='m-0 p-0'
              v-model="major"
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
            v-model="address"
            label=""
            clearable
          ></v-text-field>
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
                v-model='selfIntroduction'
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
import axios from 'axios'

const API_URL = 'http://localhost:8181'

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
      gender: null,
      major: null,
      age: null,
      imgFile: null, 
      imageUrl: null,
      authority: null,
      interestFieldList: [],
      activeRegionList: [],
      careerList: [],
      licenseList: [],

    }
  },
  methods: {
    birthdayData(){
      this.birthday = this.picker2
      this.calendarState = !this.calendarState
      var today = new Date()
      var year = today.getFullYear()
      var birthdayYear = this.birthday.split('-')[0]
      this.age = year - birthdayYear + 1
    },
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
      this.authority = decodedValue.role[0]
    },
    clickCalendar() {
      this.calendarState = !this.calendarState
    },
    getinterestFieldList(data) {
      this.interestFieldList = data
    },
    inputImg(imgData) {
      this.imgFile = imgData.imgFile, 
      this.imageUrl = imgData.imageUrl
    },
    userInfoChange() {
      const file = this.imgFile
        const config = {
          headers: {
            Authorization: this.$cookies.get("auth-token"),
            'Content-Type': 'multipart/form-data',
          }
      } 
        const config2 = {
          headers: {
            Authorization: this.$cookies.get("auth-token"),
          }
      } 
      let formData = new FormData();
      formData.append("file", file);
      if (this.imgFile) {
        axios.post(API_URL + '/api/file/upload', formData, config)
        .then((res) => {
          this.profileImg = res.data.fileDownloadUri
          console.log('check')
          axios.patch(API_URL + '/api/users', this.userInfo, config2)
          .then(() => {
            this.$router.push('/mypage')
          })
          .catch(err => console.log(err.response.data))
        })
        .catch((err) => console.log(err))
      }
      else {
          axios.patch(API_URL + '/api/users', this.userInfo, config2)
          .then(() => {
            this.$router.push('/mypage')
          })
          .catch(err => console.log(err.response.data))

      }
    },
    getUserInfo() {
        const config = {
            headers: {
                Authorization: this.$cookies.get("auth-token")
            }
        }
        axios.get(API_URL + '/api/users', config)
        .then(res => {
            this.birthday = res.data.birthday
            this.address = res.data.address
            this.gender = res.data.gender
            this.major = res.data.major
            this.name = res.data.name
            this.nickname = res.data.nickname
            this.phone = res.data.phone
            this.profileImg = res.data.profileImg
            this.selfIntroduction = res.data.selfIntroduction
            this.imageUrl = res.data.profileImg
            this.age = res.data.age,
            this.interestFieldList = res.data.interestFieldList,
            this.activeRegionList = res.data.activeRegionList,
            this.careerList = res.data.careerList,
            this.licenseList = res.data.licenseList
            console.log(res.data)
        })
        .catch(err => console.log(err.response))
    },
  },
  created() {
    this.checkusername()
    this.getUserInfo()
  },
  computed: {
    userInfo() {
      return {
        user: {
          name: this.name,
          nickname: this.nickname,
          selfIntroduction: this.selfIntroduction,
          phone: this.phone,
          birthday: this.birthday,
          address: this.address,
          profileImg: this.profileImg,
          gender: this.gender,
          major: this.major,
          age: this.age,
          username: this.username,
          authority: this.authority,
        },
        fields: this.interestFieldList,
        type: 'user',
        regions: this.activeRegionList,
        licenses: this.licenseList,
        careers: this.careerList,
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