<template>
  <v-row justify="center">
    <v-icon color="yellow darken-2" @click.stop="dialog = true">mdi-email</v-icon>
    <v-dialog
      v-model="dialog"
      max-width="400"
    >
      <v-card>
        <v-card-title class="headline justify-center" >쪽지보내기</v-card-title>
        <v-col>
          <v-textarea v-model="message"
            solo
            name="input-7-4"
            label="쪽지 내용을 적어주세요."
          ></v-textarea>
        </v-col>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="sendMessage"
            class="justify-center"
          >
            전송
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import Stomp from 'stompjs'
import SockJS from 'sockjs-client'

//const API_URL = 'http://i3a202.p.ssafy.io:8181'
const API_URL = 'http://localhost:8181'
  export default {
    data () {
      return {
        dialog: false,
        message: '',
				logs: [],
        status: '',
        username: '',
      }
    },
    props: ['receiver']
    ,
    created: function(){
      this.checkusername();
      //this.connect();
    },
    methods: {
				connect() {
          this.$store.socket = new SockJS(API_URL+"/socket");
          this.$store.client = Stomp.over(this.$store.socket);

          this.$store.client.connect({}, frame => {
            this.status = 'connected';
            this.$store.client.subscribe("/topic/계정1", res => {
              console.log(res.body);
            })
            console.log(frame);
          })
				},
				disconnect() {
          this.$store.socket.close();
          this.status = "disconnected";
          this.logs = [];
				},
				sendMessage() {	
          console.log(this.$store.client);
					const msg = { 
							contents : this.message,
							sender : this.username,
							receiver : this.receiver,
							read : false
          };
          this.$store.client.send("/app/"+this.receiver, {}, JSON.stringify(msg));
          this.dialog = false;
          this.message = '';
        },
        checkusername() {
          var ca = this.$cookies.get("auth-token")
          var base64Url = ca.split('.')[1]
          var decodedValue = JSON.parse(window.atob(base64Url))
          this.username = decodedValue.sub
        },
			}
    
  }
</script>

<style>

</style>