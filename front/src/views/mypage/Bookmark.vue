<template>
<div>
    <h3 style='margin: 30px;'>북마크한 공모전 목록</h3>
    <table id='list' class='centered'>
        <tr>
            <th>글번호</th>
            <th>공모전명</th>
            <th>주최사</th>
            <th>현재현황</th>
            <th>조회수</th>
            <th>관리</th>
        </tr>
        <tr v-for="(eachB, idx) in bookmarkList" :key="idx">
            <td>{{ eachB.contestNo }}</td>
            <td class='right'>
                <router-link :to="'/contest/' + eachB.contestNo" @click.native='addViewCount(eachB)'>{{ eachB.title }}</router-link>
            </td>
            <td>{{ maxLengthFilter(eachB.host) }}</td>
            <td><dday :data='eachB'/></td>
            <td>{{ eachB.viewCount }}</td>
            <td>
                <vue-confirmation-button class='confirmBtn'
                    :messages='customMsgToProp'
                    @confirmation-success="removeBookmark(eachB.contestNo)"/>
            </td>
        </tr>
    </table>
</div>
</template>

<script>
import axios from 'axios'
import Dday from '@/components/contest/Dday.vue'
import vueConfirmationButton from 'vue-confirmation-button';

const URL = {
    DOMAIN: 'http://i3a202.p.ssafy.io:8181',
    BOOKMARK: 'api/contest/bookmark',
    DATE: { START: '2000-01-01', END: '2100-01-01' },
    build() { return Array(this.DOMAIN, this.BOOKMARK).join('/') },
    bmkBuild(name) { return Array(this.DOMAIN, this.BOOKMARK, name, this.DATE.START, this.DATE.END).join('/') },
}

export default {
    name: 'bookmark',
    components: {Dday, vueConfirmationButton},
    data() {return {
        bookmarkList: [],
        customMsgToProp: ['제외','진짜?','ㅇㅋ'],
    }},
    created() {
        this.loadList();
    },
    methods: {
        loadList() {
            this.bookmarkList = [];
            axios.get(URL.bmkBuild(this.userName))
                .then(response => {
                    this.bookmarkList = response.data;
                }).catch(error => console.log(error));
        },
        maxLengthFilter(string, baseLength = 15) {
            return string.length > baseLength ? `${string.substr(0, baseLength)}...` : string;
        },
        removeBookmark(ctstNo) {
            const headers = {Authorization: this.$cookies.get("auth-token")};
            axios.delete(URL.build(), {headers,data: {contestNo:ctstNo}})
                .then(response => {if (response.status == 200) this.loadList();})
                .catch(error => console.log(error))
        },
    },
    computed: {
        userName() {
            try {
                let base64Url = this.$cookies.get('auth-token').split('.')[1];
                let decodedValue = JSON.parse(window.atob(base64Url));
                return decodedValue.sub;
            } catch {
                return '';
            }
        },
    },
}
</script>

<style>
#list {
  min-width: 80%;
}

#list th {
  background-color: #dddddd;
  border: 2px solid white;
}

#list td, th {
  border-bottom: 1px solid #dddddd;
  padding: 5px;
  margin: 5px;
}
.centered {
  margin: auto;
}
.right {
  text-align: left;
}
._inlineBlock {
  display: inline-block;
}
.confirmBtn {
    margin: auto auto;
    min-width: 60px;
    height: 27px;
    background-color: #ff5252;
}
</style>