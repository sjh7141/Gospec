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
            <v-list-item-avatar>
              <img src="https://randomuser.me/api/portraits/men/81.jpg">
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>Application</v-list-item-title>
              <v-list-item-subtitle>Subtext</v-list-item-subtitle>
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
          <v-list-item class="logout">
            <v-list-item-icon>
              <v-icon>mdi-help-box</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title @click="logout">로그아웃</v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-navigation-drawer>
  </v-container>
</template>

<script>
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
      }
    },
    methods: {
    logout() {
      this.$cookies.remove('auth-token')
      this.$emit('logout', false)
    },
    checkusername() {
      var ca = this.$cookies.get("auth-token")
      var base64Url = ca.split('.')[1]
      var decodedValue = JSON.parse(window.atob(base64Url))
      this.username = decodedValue.sub
    },
    link(event){
      this.$router.push(event)
    }
    
    },
    mounted() {
      this.checkusername()
    }
    
  }
</script>