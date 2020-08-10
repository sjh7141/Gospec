<template>
  <div>
    <h3>팀 찾기 게시물 수정</h3>
    <form class='_form' action="">
      <label for='_title'>제목: </label><br>
      <input class='_input' type="text" name="title" id="_title" v-model='post.title'/><br>

      <label for="_content">내용: </label><br>
      <textarea class='_input' name="content" id="_content" cols="30" rows="10" v-model='post.content'></textarea>

      <label for="_memberMax">최대인원: </label>
      <input class='_input _numInput' type="number" name="memberMax" id="_memberMax" v-model='post.memberMax'/><br>

      <button class='_btn' @click.prevent='updatePost'>수정</button>
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
      // registTime: '',
    },
  }},
  created() {
    axios.get(URL_PART + '/detail/' + this.$route.params.team_id)
      .then(response => {
        console.dir(response);
        this.post = response.data;
      })
      .catch(error => console.log(error));
  },
  methods: {
    registPost() {
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
          console.log(error);
          alert('한 공모전 당 모집 글은 하나만 작성할 수 있습니다.');
          this.$router.push('/contest/' + this.$route.params.contest_id + '/teams');
        });
    },
    updatePost() {
      const config = {
        headers : {
          Authorization : this.$cookies.get('auth-token')
        }
      }

      axios.patch(URL_PART, this.post, config)
        .then(response => {
          console.dir(response);
          if (response.status == 200) {
            alert('정상수정되었습니다');
            this.$router.push('/contest/' + this.$route.params.contest_id + '/teams');
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
}
</script>

<style>
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
  padding: 5px;
  margin: 5px;
}


._numInput {
  width: 60%;
  margin-left: 100px;
}

._btn {
  display: block;
  margin: 10px auto;
  padding: 10px;
  background-color: lightgray;
  border-radius: 10px;
}

._btn:hover {
  background-color: gray;
}
</style>