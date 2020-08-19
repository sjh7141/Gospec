<template>
<div>
    <aside id="sidebar" class="nano">
    <v-navigation-drawer permanent >
      <v-list>
        <v-list-item>
          <v-list-item-avatar>
              <v-icon large color="error">mdi-email</v-icon>
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
        :min-height="630" 
      >
        <v-list-item-group v-model="item" color="primary">
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            color="#ff5252"
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
        <div id="box">
            <div class="face">
                <div class="eye"></div>
                <div class="eye right"></div>
                <div class="mouth happy"></div>
            </div>
            <div class="shadow scale"></div>
        </div>
    </aside>
    <div id="right">
        <header class="header">
          <!-- <div class="search-box">
            <input placeholder="Search..."><span class="icon glyphicon glyphicon-search"></span>
          </div> -->
          <h2 class="page-title"><a class="sidebar-toggle-btn trigger-toggle-sidebar"><span class="line"></span><span class="line"></span><span class="line"></span><span class="line line-angle1"></span><span class="line line-angle2"></span></a>{{typeString}}</h2>
        </header>
        <div style='text-align: right; margin: 10px 0;'>
          <template v-if="isTable">
            <v-btn small color="primary" @click="moveToMail" v-if="typeMail == 4">복구</v-btn> &nbsp;
            <v-btn small color="error" @click="deleteMessage" >삭제</v-btn>
          </template>
        </div>
        

        <div style='text-align: left; margin:5px 0px' v-if="isTable">
          <v-data-table
            v-if="typeMail==1"
            v-model="checkedNo"
            :headers="header"
            :items="receiveMessages"
          >
            <template v-slot:body="props">
            <tbody>
              <tr v-for="item in props.items" v-bind:key="item.no" :class="[item.reading? readClass : '', unreadClass]">
                <td>
                <v-checkbox v-model="checkedNo" :key="item.no" :value="item.no" style="margin:0px; padding:0px" color="#ff5252"
                            hide-details />
                </td>
                <td><like-state :likeState="item.important" :mailNo="item.no"/></td>
                <td>{{item.sender}}</td>
                <td><a @click.prevent="openMessage(item.no)">{{checkLength(item.contents)}}</a></td>
                <td>{{item.registTime}}</td>
              </tr> 
              <tr v-if="props.items.length==0">
                <td></td>
                <td></td>
                <td></td>
                <td>받은 쪽지함이 비었습니다.</td>
                <td></td>
              </tr>
              <tr v-for="(value,key) in length()" :key="key">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            </template>
          </v-data-table>

          <v-data-table
            v-if="typeMail==5"
            v-model="checkedNo"
            :headers="headers"
            :items="sendMessages"
          >
            <template v-slot:body="props">
            <tbody>
              <tr v-for="item in props.items" v-bind:key="item.no" :class="[item.reading? readClass : '', unreadClass]">
                <td>
                <v-checkbox v-model="checkedNo" :key="item.no" :value="item.no" style="margin:0px; padding:0px" color="#ff5252"
                            hide-details />
                </td>
                <td>{{item.receiver}}</td>
                <td class="contents"><a @click.prevent="openMessage(item.no)">{{checkLength(item.contents)}}</a></td>
                <td>{{item.registTime}}</td>
              </tr>
              <tr v-if="props.items.length==0">
                <td></td>
                <td></td>
                <td>보낸 쪽지함이 비었습니다.</td>
                <td></td>
              </tr>
              <tr v-for="(value,key) in length()" :key="key">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            </template>
          </v-data-table>

          <v-data-table
            v-if="typeMail==2"
            v-model="checkedNo"
            :headers="header"
            :items="allMessages"
          >
            <template v-slot:body="props">
            <tbody>
              <tr v-for="item in props.items" v-bind:key="item.no" :class="[item.reading? readClass : '', unreadClass]" >
                <td>
                <v-checkbox v-model="checkedNo" :key="item.no" :value="item.no" style="margin:0px; padding:0px" color="#ff5252"
                            hide-details />
                </td>
                <td><like-state :likeState="item.important" :mailNo="item.no"/></td>
                <td >{{item.sender}}</td>
                <td><a @click.prevent="openMessage(item.no)">{{checkLength(item.contents)}}</a></td>
                <td >{{item.registTime}}</td>
              </tr>
              <tr v-if="props.items.length==0">
                <td></td>
                <td></td>
                <td></td>
                <td>전체 쪽지함이 비었습니다.</td>
                <td></td>
              </tr>
             <tr v-for="(value,key) in length()" :key="key">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            </template>
          </v-data-table>

          <v-data-table
            v-if="typeMail==3"
            v-model="checkedNo"
            :headers="header"
            :items="importantMessages"
          >
            <template v-slot:body="props">
            <tbody>
              <tr v-for="item in props.items" v-bind:key="item.no" :class="[item.reading? readClass : '', unreadClass]">
                <td>
                <v-checkbox v-model="checkedNo" :key="item.no" :value="item.no" style="margin:0px; padding:0px" color="#ff5252"
                            hide-details />
                </td>
                <td><like-state :likeState="item.important" :mailNo="item.no"/></td>
                <td>{{item.sender}}</td>
                <td><a @click.prevent="openMessage(item.no)">{{checkLength(item.contents)}}</a></td>
                <td>{{item.registTime}}</td>
              </tr>
              <tr v-if="props.items.length==0">
                <td></td>
                <td></td>
                <td></td>
                <td>보관함이 비었습니다.</td>
                <td></td>
              </tr>
              <tr v-for="(value,key) in length()" :key="key">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            </template>
          </v-data-table>

          <v-data-table
            v-if="typeMail==4"
            v-model="checkedNo"
            :headers="header"
            :items="deleteMessages"
          >
            <template v-slot:body="props">
            <tbody>
              <tr v-for="item in props.items" v-bind:key="item.no">
                <td>
                <v-checkbox v-model="checkedNo" :key="item.no" :value="item.no" style="margin:0px; padding:0px" color="#ff5252"
                            hide-details />
                </td>
                <td></td>
                <td>{{item.sender}}</td>
                <td>{{checkLength(item.contents)}}</td>
                <td>{{item.registTime}}</td>
              </tr>
              <tr v-if="props.items.length==0">
                <td></td>
                <td></td>
                <td></td>
                <td>휴지통이 비었습니다.</td>
                <td></td>
              </tr>
              <tr v-for="(value,key) in length()" :key="key">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
              </tr>
            </tbody>
            </template>
          </v-data-table>
        </div>
        <div v-if="!isTable" style='text-align: left; margin:5px 0px'>
          <detail-message :messageNo="mailNo" :type="typeMail" @childs-event="closeMessage"></detail-message>
        </div>
    </div>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import axios from 'axios'
import DetailMessage from '@/components/message/ReadMessage.vue';
import LikeState from '@/components/message/MessageLikeState.vue';

const URL = 'http://i3a202.p.ssafy.io:8181/api/message'

export default {
    components: {
      DetailMessage,
      LikeState,
    },
    data(){
        return{
            typeString : '받은 쪽지함',
            typeMail : 1,
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
            header: [
              {text: '', value:'box', width:'5%'},
              {text: '', value:'star', width:'5%'},
              {text: '보낸사람', value: 'sender', width:'20%'},
              {text: '내용', value: 'contents', width: '50%'},
              {text: '보낸시간', value: 'registTime', width: '20%'},
            ],
            headers: [
              {text: '', value:'box'},
              {text: '받는사람', value: 'sender', width:'25%',},
              {text: '내용', value: 'contents', width: '55%'},
              {text: '보낸시간', value: 'registTime', width: '20%'},
            ],
            readClass : 'read',
            unreadClass : 'unread',
            isTable : true,
            mailNo : 0,
            length : ()=>{
              let list = [];
              let cur = 0;
              if(this.typeMail==1) cur = this.receiveMessages.length;
              else if(this.typeMail==2) cur = this.allMessages.length;
              else if(this.typeMail==3) cur = this.importantMessages.length;
              else if(this.typeMail==4) cur = this.deleteMessages.length;
              else cur = this.sendMessages.length;
              let size = (10-cur>0)?10-cur:0;
              if(size == 10) size--;
              for(let i=0; i<size; i++){
                list.push(i);
              }
              return list;
            },

        }
    },
    computed:{
        ...mapGetters(['receiveMessages', 'sendMessages', 'allMessages', 'importantMessages', 'deleteMessages']),
    },
    created(){
        this.checkusername();
        this.$store.dispatch('getReceiveMessages');
        this.$store.dispatch('getSendMessages');
        this.$store.dispatch('getAllMessages');
        this.$store.dispatch('getImportantMessages');
        this.$store.dispatch('getDeleteMessages');
    },
    methods: {
        deleteMessage(){
            if(this.checkedNo.length == 0){
                alert('삭제할 메세지를 선택해주세요.')
                return;
            }
            if(this.typeMail >= 4){
              if(window.confirm("쪽지를 지우면 지워진 쪽지는 복구할 수 없습니다.\n 삭제하시겠습니까?")) {
                  this.delete();
              }
            }else {
              if(window.confirm("휴지통으로 선택한 쪽지들이 이동됩니다.")) {
                  this.moveToDelete();
              }
            }
        },
        delete(){
            var checkedNo = this.checkedNo;
            const config = {
                headers: {
                Authorization: this.$cookies.get("auth-token")
                },
            }
            if(this.typeMail == 4){
                config.data = {no : checkedNo};
                axios.delete(URL+'/receiver', config)
                    .then(({ data }) => {
                        if(!data){
                          let msg = '삭제 처리시 문제가 발생했습니다.';
                          alert(msg);
                        }
                        this.checkedNo = [];
                        this.$store.dispatch('getDeleteMessages');
                    })
                    .catch(() => {
                        alert('삭제 처리시 에러가 발생했습니다.');
                    });
            }else if(this.typeMail == 5){
                config.data = {no : checkedNo};
                axios.delete(URL+'/sender', config)
                    .then(({ data }) => {
                        if(!data){
                          let msg = '삭제 처리시 문제가 발생했습니다.';
                          alert(msg);
                        }
                        this.checkedNo = [];
                        this.$store.dispatch('getSendMessages');
                    })
                    .catch(() => {
                        alert('삭제 처리시 에러가 발생했습니다.');
                    });
            }
        },
        moveToDelete(){
           var checkedNo = this.checkedNo;
            const config = {
                headers: {
                Authorization: this.$cookies.get("auth-token")
                },
            }
            let data = {no : checkedNo,
                        state : true,
            };
            axios.patch(URL+'/trash-can', data ,config)
                .then(({ data }) => {
                    if(!data){
                      let msg = '휴지통 이동시 문제가 발생했습니다.';
                      alert(msg);
                    }
                    this.checkedNo = [];
                    if(this.typeMail == 1) this.$store.dispatch('getReceiveMessages');
                    else if(this.typeMail == 3)  this.$store.dispatch('getImportantMessages');
                    else if(this.typeMail == 2){
                      this.$store.dispatch('getReceiveMessages');
                      this.$store.dispatch('getImportantMessages');
                    }
                    this.$store.dispatch('getAllMessages');
                    this.$store.dispatch('getDeleteMessages');
                })
                .catch(() => {
                    alert('휴지통 이동시 에러가 발생했습니다.');
                });
            
        },
        checkusername() {
          var ca = this.$cookies.get("auth-token")
          if(ca != null){
            var base64Url = ca.split('.')[1]
            var decodedValue = JSON.parse(window.atob(base64Url))
            this.username = decodedValue.sub
            this.nickname = this._utf8_decode(decodedValue.nickname)
          }else{
              alert("로그인이 필요한 서비스입니다.");
              this.$router.push('/home');
          }
        },
        change(name){
            this.checkedNo = [];
            this.typeString = name;
            this.isTable = true;
            if(name == '받은 쪽지함') this.typeMail = 1;
            else if(name == '전체 쪽지함') this.typeMail = 2;
            else if(name == '보관함') this.typeMail = 3;
            else if(name == '휴지통') this.typeMail = 4;
            else if(name == '보낸 쪽지함') this.typeMail = 5;
        },
        openMessage(no){
          this.isTable = false;
          this.mailNo = no;
          if(this.typeMail == 5) this.$store.dispatch('getSendMessage', no);
          else this.$store.dispatch('getReceiveMessage', no);
        },
        closeMessage(isDelete){
          this.isTable = true;
          if(this.typeMail == 5){
               this.$store.dispatch('getSendMessages');
          }else{
              if(this.typeMail == 1) this.$store.dispatch('getReceiveMessages');
              else if(this.typeMail == 3) this.$store.dispatch('getImportantMessages');
              else if(this.typeMail == 2){
                this.$store.dispatch('getReceiveMessages');
                this.$store.dispatch('getImportantMessages');
              }
              this.$store.dispatch('getAllMessages');
          }
          if(isDelete){
              this.$store.dispatch('getDeleteMessages');
          }
        },
        
      _utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = 0
        var c2 = 0
        var c3 = 0
 
        while ( i < utftext.length ) {
 
            c = utftext.charCodeAt(i);
 
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            }
            else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }
            else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
 
        }
 
        return string;
      },
      moveToMail(){
        if(window.confirm("선택한 메일들을 복구하시겠습니까?")) {
          var checkedNo = this.checkedNo;
              const config = {
                  headers: {
                  Authorization: this.$cookies.get("auth-token")
                  },
              }
              let data = {no : checkedNo,
                          state : false,
              };
              axios.patch(URL+'/trash-can', data ,config)
                  .then(({ data }) => {
                      if(!data){
                        let msg = '메일함 이동시 문제가 발생했습니다.';
                        alert(msg);
                      }
                      this.checkedNo = [];
                      this.$store.dispatch('getReceiveMessages');
                      this.$store.dispatch('getImportantMessages');
                      this.$store.dispatch('getAllMessages');
                      this.$store.dispatch('getDeleteMessages');
                  })
                  .catch(() => {
                      alert('메일함 이동시 에러가 발생했습니다.');
                  });
        }
      },
      checkLength(contents){
        if(contents.length > 30){
          return contents.substring(0,29)+"..."
        }else{
          return contents;
        }
      },
    },
}
</script>

<style scoped> 
.button {
  border: none;
  border-radius: 2px;
  background-color: red;
}
a {
  color: #ff5252 !important;
}
.unread{
  /* color : rgb(60, 139, 217); */
  color : #ff5252;
}
.read {
  color : black !important;
}
.read a{
  color : black !important;
}
.content{
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100px;
  height: 5px;
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
  width: 350px;
  height: 300px;
  left: 75%;
  top: 68px;
  perspective: 40px;
  z-index: 0;
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
  padding : 0;
  margin : 0;
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
      height: 100%;
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
  .header {
    padding: 54.5px 0px 54px 60px;
    border-bottom: 1px solid #efefef;
    overflow: hidden;
  }
  .header .page-title {
    display: block;
    text-align: left;
  }
  .header .page-title .sidebar-toggle-btn {
    width: 0;
    margin-top: 1px;
    padding: 11px 0 0 0;
    float: left;
    position: relative;
    display: block;
    cursor: pointer;
    transition-duration: 0.3s;
    transition-delay: 0.5s;
    opacity: 0;
    margin-right: 0;
  }
  .header .page-title .sidebar-toggle-btn {
    transition-delay: 0s;
  }
  .header .page-title .sidebar-toggle-btn .line {
    height: 3px;
    display: block;
    background: #888;
    margin-bottom: 4px;
    transition-duration: 0.5s;
    transition-delay: 0.5s;
  }
  .header .page-title .sidebar-toggle-btn .line-angle1 {
    transform: rotate(-120deg);
  }
  .header .page-title .sidebar-toggle-btn .line-angle2 {
    transform: rotate(120deg);
  }
  .header .page-title .sidebar-toggle-btn .line-angle1 {
    width: 8px;
    margin: 0;
    position: absolute;
    top: 15px;
    left: -11px;
    transform: rotate(-60deg);
  }
  .header .page-title .sidebar-toggle-btn .line-angle2 {
    width: 8px;
    margin: 0;
    position: absolute;
    top: 21px;
    left: -11px;
    transform: rotate(60deg);
  }
  .header .page-title .icon {
    font-size: 15px;
    margin-left: 20px;
    position: relative;
    top: -5px;
    cursor: pointer;
  }
    .header .search-box {
    float: right;
    width: 150px;
    height: 40px;
    position: relative;
  }
  .header .search-box input,
  .header .search-box .icon {
    transition-duration: 0.3s;
  }
  .header .search-box input {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    border: 0;
    padding: 0;
    margin: 0;
    text-indent: 15px;
    height: 40px;
    z-index: 1;
    outline: none;
    color: #999;
    background: transparent;
    border: 2px solid #efefef;
    border-radius: 5px;
    transition-timing-function: cubic-bezier(0.3, 1.5, 0.6, 1);
  }
  .header .search-box input:focus {
    color: #333;
    border-color: #d6d6d6;
    width: 150%;
  }
  .header .search-box input:focus ~ .icon {
    opacity: 1;
    z-index: 2;
    color: #61c7b3;
  }
  .header .search-box .icon {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    width: 40px;
    text-align: center;
    line-height: 40px;
    cursor: pointer;
    opacity: 0.5;
  }
</style>