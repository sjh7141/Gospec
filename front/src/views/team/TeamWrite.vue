<template>
  <div>
    <h3 class="smallTitle">팀 찾기 게시물 등록</h3>
    <form class='_form' action="">
      <label for='_title'>제목: </label><br>
      <input class='_input' style="outline : 0;" type="text" name="title" id="_title" v-model='post.title' placeholder="제목을 입력하세요"/><br>

      <label for="_content">내용: </label><br>
      <textarea class='_input' style="outline : 0;" name="content" id="_content" cols="30" rows="10" v-model='post.content' placeholder="내용을 입력하세요"></textarea>

      <label for="_memberMax">최대인원: </label>
      <input class='_input _numInput' style="outline : 0;" type="number" name="memberMax" id="_memberMax" v-model='post.memberMax'/><br>

      <div style="text-align: center;">
        <button class='_btn' @click.prevent="$router.push('/contest/'+ $route.params.contest_id +'/teams')">팀찾기 목록</button>
        <button class='_btn' @click.prevent='registPost'>등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
const URL_PART = 'http://i3a202.p.ssafy.io:8181/api/board/teams'
// const URL_PART = 'http://localhost:8181/api/board/teams'

export default {
  name: 'teamWrite',
  data() {return {
    post: {
      title: '',
      content: '',
      memberMax: 1,
      MemberCur: 1,
      contestNo: this.$route.params.contest_id,
    },
  }},
  methods: {
    registPost() {
      //유효성 검사
      if (!this.validation()) {return;}

      const config = {
        headers : {
          Authorization : this.$cookies.get('auth-token')
        }
      }
      axios.post(URL_PART, this.post, config)
        .then(response => {
          if (response.status == 200) {
            alert('정상등록되었습니다');
            this.$router.push('/contest/' + this.$route.params.contest_id + '/teams');
          }
        })
        .catch(error => {
          // 토큰만료의 경우에도 이쪽으로 내려온다
          console.dir(error);
          alert('한 공모전 당 모집 글은 하나만 작성할 수 있습니다.');
          this.$router.push('/contest/' + this.$route.params.contest_id + '/teams');
        });
    },
    validation() {
      if (this.post.title.length <= 0) {
        alert('제목을 입력하세요');
        document.querySelector('#_title').focus();
        return false;
      } else if (this.post.content.length <= 0) {
        alert('내용을 입력하세요');
        document.querySelector('#_content').focus();
        return false;
      } else if (this.post.memberMax <= 0) {
        alert('유효하지 않은 최대인원입니다');
        document.querySelector('#_memberMax').focus();
        return false;
      }
      return true;
    },
  }
}
</script>

<style>
.smallTitle {
  border-bottom: 1px solid lightgray;
  padding-bottom: 10px;
  width: 60%;
  margin: auto;
  margin-top: 40px;
}

._form {
  width: 80%;
  margin: 10px auto;
  text-align: left;
}

._form label {
  margin-top: 30px;
}

._input {
  width: 100%;
  border-radius: 30px;
  border: 1px solid #dddddd;
  padding: 15px;
  margin: 5px;
}


._numInput {
  width: 60%;
  margin-left: 100px;
}

._btn {
  display: inline-block;
  margin: 5px 15px;
  min-width: 80px;
  padding: 7px;
  background-color: lightgray;
  border-radius: 10px;
}

._btn:hover {
  background-color: gray;
  color: white;
}
</style>