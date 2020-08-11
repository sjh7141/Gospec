<template>
<div>
    <h3 style='margin: 30px;'>{{typeString}}쪽지함</h3>
    <div style='text-align: right; margin: 30px;'>
        <v-btn small color="primary" @click="changeType">{{typeElseString}}쪽지함</v-btn> &nbsp;
        <v-btn small color="error" @click="deleteMessage">삭제</v-btn>
    </div>
    <table id='list' class='centered'>
        <colgroup>
          <col :style="{ width: '5%' }" />
          <col :style="{ width: '20%' }" />
          <col :style="{ width: '50%' }" />
          <col :style="{ width: '25%' }" />
        </colgroup>
        <thead>
            <tr>
                <th>삭제</th>
                <th>보낸사람</th>
                <th>내용</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody>
            <template  v-if="isReceive">
                <tr v-for="(message, idx) in receiveMessages" :key="idx">
                    <td><input type="checkbox" :id="message.no" :value="message.no" v-model="checkedNo"></td>
                    <td>{{message.sender}}
                    <td><router-link :to="'/readReceiveMessage?no='+message.no">{{message.contents}}</router-link></td>
                    <td>{{message.registTime}}</td>
                </tr>
            </template>
            <template v-else>
                <tr v-for="(message, idx) in sendMessages" :key="idx">
                    <td><input type="checkbox" :id="message.no" :value="message.no" v-model="checkedNo"></td>
                    <td>{{message.sender}}
                    <td><router-link :to="'/readSendMessage?no='+message.no">{{message.contents}}</router-link></td>
                    <td>{{message.registTime}}</td>
                </tr>
            </template>
        </tbody>
    </table>
    <pagination v-bind:type="type"></pagination>
</div>
</template>

<script>
import Pagination from '@/components/message/Pagination.vue'
import { mapGetters } from 'vuex'
import axios from 'axios'
const URL = 'http://i3a202.p.ssafy.io:8181/api/message'

export default {
    components: {
        Pagination,
    },
    data(){
        return{
            isReceive : true,
            type : 'receive',
            typeString : '받은',
            typeElseString : '보낸',
            checkedNo:[],
            username: '',
        }
    },
    computed:{
        ...mapGetters(['receiveMessages', 'sendMessages']),
    },
    created(){
        this.checkusername();
        this.$store.commit('setMessagePage', 1);
        this.$store.commit('setUsername', this.username);
        this.$store.dispatch('getReceiveMessages');
        this.$store.dispatch('getSendMessages');
    },
    methods: {
        changeType(){
            this.isReceive = !this.isReceive;
            this.checkedNo = [];
            if(this.type == 'receive'){
                this.type = 'send';
                this.typeString = '보낸';
                this.typeElseString = '받은';
            }else{
                this.type = 'receive';
                this.typeString = '받은';
                this.typeElseString = '보낸';
            }
        },
        deleteMessage(){
            console.log("###");
            console.log(this.checkedNo);
            if(this.checkedNo.length == 0){
                alert('삭제할 메세지를 선택해주세요.')
                return;
            }
            if(window.confirm("쪽지를 삭제하시겠습니까?")) {
                this.delete();
            }
            
        },
        delete(){
            var checkedNo = this.checkedNo;
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
                    })
                    .catch(() => {
                        alert('삭제 처리시 에러가 발생했습니다.');
                    });
            }else{
                console.log(config);
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
    },
}
</script>

<style>
.button {
  border: none;
  border-radius: 2px;
  background-color: red;

}
</style>