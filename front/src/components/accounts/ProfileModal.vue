<template>
  <v-row justify="center">
    <v-avatar class='mx-auto' size='200'
      color="primary"
      dark
      @click.stop="dialog = true">
      <v-img :src=userData.profileImg></v-img>
    </v-avatar>

    <v-dialog
      v-model="dialog"
    >
      <v-card>
        <v-card>
          <ProfileModalDetail :userData='userData' />
        </v-card>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="green darken-1"
            text
            @click="dialog = false"
          >
            Disagree
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="dialog = false"
          >
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from 'axios'
import ProfileModalDetail from './ProfileModalDetail'

const API_URL = 'http://i3a202.p.ssafy.io:8181'

  export default {
    data () {
      return {
        dialog: false,
        userData: null,
      }
    },
    components: {
      ProfileModalDetail,
    },
    props: {
      username: String
    },
    methods: {
      getUserInfo() {
        axios.get(API_URL +'/api/users/other/' + this.username)
        .then(res => {
          console.log(res.data)
          this.userData = res.data
        })
      }
    },
    mounted() {
      this.getUserInfo()
    }
  }
</script>
