<template>
    <div>
        <div style='text-align: right; margin: 10px 0;'>
                <v-dialog
                v-model="dialog"
                max-width="400"
                >
                    <v-card > 
                        <v-img
                        :aspect-ratio="16/9"
                        src="../../assets/messageInBottle.jpg"
                        >
                        </v-img>
                        <div style="text-align : right; margin: 10px">받는사람 : {{message.sender}}</div>
                        <v-col>
                        <v-textarea v-model="newMessage"
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
        </div>
        <v-card
            max-width="100%"
        >
            <v-img
            class="white--text align-end"
            :aspect-ratio="16/3"
            src="@/assets/sunrise.jpg"
            >
            </v-img>
            <div style="text-align : left; margin: 10px">보낸사람 : {{message.sender}}</div>
            <div style="text-align : left; margin: 10px">보낸시간 :  {{message.registTime}}</div>
            <hr>
            <v-card-text>
                <div>{{message.contents}}</div>
            </v-card-text>
            <v-row justify="end">
            <v-card-actions>
                <v-btn color="primary" text v-if="type!=5" @click.stop="validation">답장</v-btn>
                <v-btn color="red" text @click="deleteMessage">삭제</v-btn>
            </v-card-actions>
            </v-row>
        </v-card>
        <div style='text-align: right; margin: 10px 0;'>
            <v-btn small color="primary" @click="moveMessage">쪽지함 보기</v-btn> 
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/message'
export default {
    computed: {
    ...mapGetters(['message']),
    },
    data () {
      return {
        dialog: false,
        newMessage: '',
        username: '',
        flag: false,
      }
    },
    props:['messageNo', 'type'],
    created(){
        this.checkusername();
        this.$store.commit('setUsername', this.username);
        this.readMessage();
    },
    methods:{
      moveMessage(){
          this.$emit('childs-event', this.flag);
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
            if(this.type != 5){
                config.data = {no : checkedNo};
                axios.delete(URL+'/receiver', config)
                    .then(({ data }) => {
                        let msg = '삭제 처리시 문제가 발생했습니다.';
                        if (data) {
                            msg = '삭제가 완료되었습니다.';
                        }
                        alert(msg);
                        this.flag = true;
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
                        this.flag = true;
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
        readMessage(){
            var checkedNo = parseInt(this.messageNo);
            const config = {
                headers: {
                Authorization: this.$cookies.get("auth-token")
                },
            }
            if(this.type != 5){
                let data = {no : checkedNo};
                axios.patch(URL+'/receiver', data, config)
                    .then(() => {
                    })
                    .catch(() => {
                        alert('읽기 처리시 에러가 발생했습니다.');
                    });
            }else{
                let data = {no : checkedNo};
                axios.patch(URL+'/sender', data, config)
                    .then(() => {
                    })
                    .catch(() => {
                        alert('읽기 처리시 에러가 발생했습니다.');
                    });
            }
        }
  },
}
</script>

<style>

</style>