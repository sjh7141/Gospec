<template>
  <v-row justify="center" style="display:inline-block; margin: 10px;">
    <v-avatar class='mx-auto' :size='defaultSize'
      dark
      @click.stop="dialog = true">
      <v-img v-if='userData.profileImg' :src=userData.profileImg></v-img>
      <v-img v-else src='https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png'></v-img>
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
      username: String,
      size: String,
    },
    methods: {
      getUserInfo() {
        axios.get(API_URL +'/api/users/other/' + this.username)
        .then(res => {
          this.userData = res.data
        })
      }
    },
    mounted() {
      this.getUserInfo()
    },
    computed: {
      defaultSize() {
        return this.$props.size ? this.$props.size : '100';
      },
    }
  }
</script>
