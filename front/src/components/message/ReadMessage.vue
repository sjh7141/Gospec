<template>
    <div>
        <div style='text-align: right; margin: 30px;'>

                <v-btn v-if="type=='receive'" small color="primary" @click.stop="validation">답장</v-btn> &nbsp;
                <v-btn small color="error" @click="deleteMessage">삭제</v-btn>
                <v-dialog
                v-model="dialog"
                max-width="400"
                >
                <v-card>
                    <v-card-title class="headline justify-center" >쪽지보내기</v-card-title>
                    <v-col>
                    <v-textarea v-model="newMessage"
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
        </div>
        <table class="table table-hover table-bordered" style="height: 600px; width: 95%;" >
            <tr style="height: 5%; text-align:left;">
                <td>보낸사람: {{message.sender}}</td>
            </tr>
            <tr style="height: 5%; text-align:left;">
                <td>보낸시간: {{message.registTime}}</td>
            </tr>
            <tr style="border-top: 1px solid black; ">
                <td style=" text-align:left;">{{message.contents}}</td>
            </tr>
        </table>
        <v-btn small color="primary" @click="moveMessage">쪽지함 이동</v-btn> &nbsp;

    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from 'axios'
//const URL = 'http://i3a202.p.ssafy.io:8181/api/message'
const URL = 'http://localhost:8181/api/message'
export default {
    computed: {
    ...mapGetters(['message']),
    },
    data () {
      return {
        dialog: false,
        newMessage: '',
        username: '',
      }
    },
    props:['messageNo', 'type'],
    created(){
        this.checkusername();
        this.$store.commit('setUsername', this.username);
    },
    methods:{
      moveMessage(){
          this.$router.push('/mypage/message');
      },
      deleteMessage(){
           if(window.confirm("쪽지를 삭제하시겠습니까?")) {
              this.delete();
            }
      },
      delete(){
            var checkedNo = [parseInt(this.messageNo)];
            const config = {
                headers: {
                Authorization: this.$cookies.get("auth-token")
                },
            }
            if(this.type == 'receive'){
                config.data = {no : checkedNo};
                axios.delete(URL+'/receiver', config)
                    .then(({ data }) => {
                        let msg = '삭제 처리시 문제가 발생했습니다.';
                        if (data) {
                            msg = '삭제가 완료되었습니다.';
                        }
                        alert(msg);
                        this.checkedNo = [];
                        this.$store.dispatch('getReceiveMessages');
                        this.moveMessage();
                    })
                    .catch(() => {
                        alert('삭제 처리시 에러가 발생했습니다.');
                    });
            }else{
                config.data = {no : checkedNo};
                axios.delete(URL+'/sender', config)
                    .then(({ data }) => {
                        let msg = '삭제 처리시 문제가 발생했습니다.';
                        if (data) {
                            msg = '삭제가 완료되었습니다.';
                        }
                        alert(msg);
                        this.checkedNo = [];
                        this.$store.dispatch('getSendMessages');
                        this.moveMessage();
                    })
                    .catch(() => {
                        alert('삭제 처리시 에러가 발생했습니다.');
                    });
            }
        },
        checkusername() {
          var ca = this.$cookies.get("auth-token")
          if(ca){
            var base64Url = ca.split('.')[1]
            var decodedValue = JSON.parse(window.atob(base64Url))
            this.username = decodedValue.sub
          }else{
              alert("로그인이 필요한 서비스입니다.");
              this.$router.push('/home');
          }
        },
         validation(){
          this.dialog = true;
        },
        sendMessage() {	
            const msg = { 
                    contents : this.newMessage,
                    sender : this.username,
                    receiver : this.message.sender,
                    reading : false
           };
          this.$store.client.send("/app/"+msg.receiver, {}, JSON.stringify(msg));
          this.dialog = false;
          this.message = '';
        },
  },
}
</script>

<style>

</style>