<template>
  <v-row justify="center" style="display:inline-block; margin: 10px;">
    <v-avatar class='mx-auto' size='60'
      color="primary"
      dark
      @click.stop="dialog = true">
      <v-img :src=userData.profileImg></v-img>
    </v-avatar>

    <v-dialog
      v-model="dialog"
      max-width='1000'
    >
        <v-card>
            <div class='col-12 text-right'>
        <i type='button' class="fas fa-times text-right"
        @click="dialog = false" style='font-size:20px;'></i>
            </div>
          <ProfileModalDetail :userData='userData' />
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
