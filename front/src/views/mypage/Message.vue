<template>
<div>
    <h3 style='margin: 30px;'>쪽지함</h3>
    <button @click="changeType">변경</button>
    <table id='list' class='centered'>
        <thead>
            <tr>
                <th>보낸사람</th>
                <th>내용</th>
                <th>날짜</th>
                <th>번호</th>
            </tr>
        </thead>
        <tbody>
            <template  v-if="isReceive">
                <tr v-for="(message, idx) in receiveMessages" :key="idx">
                    <td>{{message.sender}}
                    <td><router-link :to="'/readReceiveMessage?no='+message.no">{{message.contents}}</router-link></td>
                    <td>{{message.registTime}}</td>
                    <td>{{message.no}}</td>
                </tr>
            </template>
            <template v-else>
                <tr v-for="(message, idx) in sendMessages" :key="idx">
                    <td>{{message.sender}}
                    <td><router-link :to="'/readSendMessage?no='+message.no">{{message.contents}}</router-link></td>
                    <td>{{message.registTime}}</td>
                    <td>{{message.no}}</td>
                </tr>
            </template>
        </tbody>
    </table>
</div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    data(){
        return{
            isReceive : true,
        }
    },
    computed:{
        ...mapGetters(['receiveMessages', 'sendMessages']),
    },
    created(){
        this.$store.dispatch('getReceiveMessages');
        this.$store.dispatch('getSendMessages');
    },
    methods: {
        changeType(){
            this.isReceive = !this.isReceive;
        },
    },
}
</script>

<style>

</style>