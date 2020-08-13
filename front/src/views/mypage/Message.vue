<template>
<div>
    <aside id="sidebar" class="nano">
    <v-navigation-drawer permanent >
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
              <v-icon large color="teal darken-2">mdi-email</v-icon>
          </v-list-item-avatar>
        </v-list-item>

        <v-list-item >
          <v-list-item-content>
            <v-list-item-title class="title">{{nickname}}</v-list-item-title>
            <v-list-item-subtitle>{{username}}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-divider></v-divider>
      <v-list
        nav
        dense
      >
        <v-list-item-group v-model="item" color="primary">
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            @click="change(item.text)"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
        <!-- <div class="nano-content">
          <div class="logo-container"><span class="logo glyphicon glyphicon-envelope"></span>쪽지</div>
          <menu class="menu-segment">
            <ul>
              <li class="active"><a href="#">Inbox<span> (43)</span></a></li>
              <li><a href="#">Important</a></li>
              <li><a href="#">Sent</a></li>
              <li><a href="#">Drafts</a></li>
              <li><a href="#">Trash</a></li>
            </ul>
          </menu>
          <div class="separator"></div>
          <div class="menu-segment">
            <ul class="labels">
              <li class="title">Labels <span class="icon">+</span></li>
              <li><a href="#">Dribbble <span class="ball pink"></span></a></li>
              <li><a href="#">Roommates <span class="ball green"></span></a></li>
              <li><a href="#">Bills <span class="ball blue"></span></a></li>
            </ul>
          </div>
          <div class="separator"></div>
          <div class="bottom-padding"></div>
        </div> -->
    </aside>
    <div id="right">
        <!-- <h2 style='margin: 30px;'>{{typeString}}</h2> -->
        <div id="box">
            <div class="dot"></div>
            <div class="dot two"></div>
            <div class="face">
                <div class="eye"></div>
                <div class="eye right"></div>
                <div class="mouth happy"></div>
            </div>
            <div class="shadow scale"></div>
        </div>
        <div style='text-align: right; margin: 30px 10%;'>
            <v-btn small color="error" @click="deleteMessage">삭제</v-btn>
        </div>
        <div>
        <table id='list' class='centered' style="z-index: 5; position: relative;">
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
        </div>
        <pagination v-bind:type="type"></pagination>
    </div>
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
            typeString : '받은 쪽지함',
            typeElseString : '보낸',
            checkedNo:[],
            username: '',
            nickname: '',
            item: 0,
            items: [
                { text: '받은 쪽지함', icon: 'mdi-email' },
                { text: '보낸 쪽지함', icon: 'mdi-email-open' },
                { text: '전체 쪽지함', icon: 'mdi-mailbox' },
                { text: '보관함', icon: 'mdi-folder-open' },
                { text: '휴지통', icon: 'mdi-trash-can' },
            ],
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
            this.nickname = decodedValue.nickname;
          }else{
              alert("로그인이 필요한 서비스입니다.");
              this.$router.push('/home');
          }
        },
        change(name){
            console.log(name);
            this.typeString = name;
            this.changeType
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

@import url('https://fonts.googleapis.com/css?family=Lato:400,700');

html {
  display: grid;
  min-height: 100%;
}

body {
  display: grid;
  overflow: hidden;
  font-family: 'Lato', sans-serif;
  text-transform: uppercase;
  text-align: center;
}

#container {
  position: relative;
  margin: auto;
  overflow: hidden;
  width: 700px;
  height: 250px;
}

button, .dot {
  cursor: pointer;
}

#box {
  position: absolute;
  width: 30%;
  height: 40%;
  left: -2%;
  top: 10px;
  border-radius: 20px;
  box-shadow: 5px 5px 20px rgba(#CBCDD3, 10%);
  perspective: 40px;
}

.dot {
  width: 8px;
  height: 8px;
  background: #FCFCFC;
  border-radius: 50%;
  position: absolute;
  top: 4%;
  right: 6%;
}

.two {
  right: 12%;
  opacity: .5;
}

.face {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: bounce 1s ease-in infinite;
}

.face2 {
  position: absolute;
  width: 22%;
  height: 22%;
  background: #FCFCFC;
  border-radius: 50%;
  border: 1px solid #777777;
  top: 21%;
  left: 37.5%;
  z-index: 2;
  animation: roll 3s ease-in-out infinite;
}

.eye {
  position: absolute;
  width: 5px;
  height: 5px;
  background: #777777;
  border-radius: 50%;
  top: 40%;
  left: 20%;
}

.right {
  left: 68%;
}

.mouth {
  position:absolute;
  top: 43%;
  left: 41%;
  width: 7px;
  height: 7px;
  border-radius: 50%;
}

.happy {
  border: 2px solid;
  border-color: transparent #777777 #777777 transparent;
  transform: rotate(45deg);
}

.sad {
  top: 49%;
  border: 2px solid;
  border-color: #777777 transparent transparent #777777;
  transform: rotate(45deg);
}

.shadow {
  position: absolute;
  width: 21%;
  height: 3%;
  opacity: .5;
  background: #777777;
  left: 40%;
  top: 43%;
  border-radius: 50%;
  z-index: 1;
}

.scale {
  animation: scale 1s ease-in infinite;
}
.move {
  animation: move 3s ease-in-out infinite;
}


.message {
  position: absolute;
  width: 100%;
  text-align: center;
  height: 40%;
  top: 47%;
}

.button-box {
  position: absolute;
  background: #FCFCFC;
  width: 50%;
  height: 15%;
  border-radius: 20px;
  top: 73%;
  left: 25%;
  outline: 0;
  border: none;
  box-shadow: 2px 2px 10px rgba(#777777, .5);
  transition: all .5s ease-in-out;
}

@keyframes bounce {
  50% {
     transform: translateY(-10px);
  }
}

@keyframes scale {
  50% {
    transform: scale(0.9);
  }
}

@keyframes roll {
  0% {
    transform: rotate(0deg);
    left: 25%;
  }
  50% {
    left: 60%;
    transform: rotate(168deg);
  }
  100% {
    transform: rotate(0deg);
    left: 25%;
  }
}

@keyframes move {
  0% {
    left: 25%;
  }
  50% {
    left: 60%;
  }
  100% {
    left: 25%;
  }
}
  #sidebar{
      float : left;
      width : 20%;
      background: #1d2127;
      height: 100%;
      background: #1d2127;
      transition-duration: 0.3s;
  }
  #right{
      float : right;
      width: 80%;
  }
  #sidebar a {
    color: #abb4be;
  }
  #sidebar .logo-container {
    font-weight: 100;
    font-size: 40px;
    line-height: 40px;
    text-align: center;
    margin: 30px 0;
    cursor: pointer;
  }
  #sidebar .logo-container .logo {
    position: relative;
    top: -6px;
    font-size: 20px;
    margin-right: 15px;
    padding: 10px;
    border: 2px solid #61c7b3;
    border-radius: 100px;
    text-indent: 1px;
    color: #61c7b3;
  }
</style>