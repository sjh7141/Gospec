<template>
   <v-row justify="center" style="display: inline-block; margin: 10px;">
    <v-icon color="yellow darken-2" @click.stop="validation">mdi-email</v-icon>
    <v-dialog
      v-model="dialog"
      max-width="400"
    >
      <v-card > 
        <v-img
        :aspect-ratio="16/9"
        src="@/assets/messageInBottle.jpg"
      >
      </v-img>
      <div style="text-align : right; margin: 10px">받는사람 : {{$props.receiver}}</div>
        <v-col>
          <v-textarea v-model="message"
            solo
            name="input-7-4"
            label="쪽지 내용을 적어주세요."
            height="250px"
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
    },
    methods: {
				sendMessage() {	
					const msg = { 
							contents : this.message,
							sender : this.username,
							receiver : this.receiver,
							reading : false
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
        validation(){
          if(this.$cookies.get("auth-token")){
            this.checkusername();
            this.dialog = true;
          }else{
            this.dialog = false;
            alert('로그인이 필요한 서비스입니다.');
          }
        },
			}
    
  }
</script>

<style>

</style>