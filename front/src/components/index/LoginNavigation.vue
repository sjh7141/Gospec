<template>
  <v-container>
    <v-navigation-drawer
      v-model="drawer"
      :color="color"
      :expand-on-hover="expandOnHover"
      :mini-variant="miniVariant"
      :right="right"
      :permanent="permanent"
      :src="bg"
      absolute
      dark
    >
        <v-list
          dense
          nav
          class="py-0"
        >
          <v-list-item two-line :class="miniVariant && 'px-0'">
            <v-list-item-avatar style='background-color: white'>
              <img v-if='userData.profileImg' :src="userData.profileImg">
              <img v-else src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png">
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>{{ userData.nickname }}</v-list-item-title>
              <v-list-item-subtitle>{{ userData.username }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item
            v-for="item in items"
            :key="item.title"
            @click='link(item.link)'
          >
      
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-list-item class="logout" @click="logout">
            <v-list-item-icon>
              <v-icon>mdi-help-box</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>로그아웃</v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-navigation-drawer>
  </v-container>
</template>

<script>
import axios from 'axios'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

  export default {
    data () {
      return {
        drawer: true,
        items: [
          { title: '프로필', icon: 'mdi-view-dashboard', link: "/mypage" },
          { title: '내정보수정', icon: 'mdi-image',link: "/mypage/userinfo" },
          { title: '팀정보', icon: 'mdi-help-box', link: "/mypage/myteam" },
        ],

        right:true,
        permanent: true,
        miniVariant: true,
        expandOnHover: true,
        nickname: '',
        userData: null,
      }
    },
    methods: {
      logout() {
        this.$cookies.remove('auth-token')
        this.$emit('logout')
      },
      checkusername() {
        var ca = this.$cookies.get("auth-token")
        var base64Url = ca.split('.')[1]
        var decodedValue = JSON.parse(window.atob(base64Url))
        this.username = decodedValue.sub
        this.nickname = this._utf8_decode(decodedValue.nickname)
        console.log(decodedValue)
      },
      link(event){
        this.$router.push(event)
      },
      _utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = 0
        var c2 = 0
        var c3 = 0
 
        while ( i < utftext.length ) {
 
            c = utftext.charCodeAt(i);
 
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
 
        }
 
        return string;
      },
      getUserInfo() {
          const config = {
              headers: {
                  Authorization: this.$cookies.get("auth-token")
              }
          }
          axios.get(API_URL + '/api/users', config)
          .then(res => {
              this.userData = res.data
          })
          .catch(err => console.log(err.response))
      },
    },
    mounted() {
      this.checkusername()
      this.getUserInfo()
    }
    
  }
</script>