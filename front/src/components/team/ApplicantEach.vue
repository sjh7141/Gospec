<template>
<div id='applicant'>
    <div class="nameArea">
        <ProfileModal :username='profile.username' size="60"/>
        <span style="color:gray;">{{profile.nickname}}</span>
    </div>

    <div class="interestArea">
        <span v-for="(interest, i) in profile.interestFieldList" :key="i" class="interest">
            #{{interest}}
        </span>
    </div>

    <div class="messageArea">
        <message-button class="message" :receiver="profile.username" />
    </div>
</div>
</template>

<script>
import ProfileModal from '@/components/accounts/ProfileModal.vue'
import MessageButton from '@/components/message/MessageButton.vue'

export default {
    props: ['data'],
    components: {MessageButton, ProfileModal},
    computed: {
        profile() { return this.$props.data; },
    },

}
</script>

<style scoped>
#applicant {
    /* padding: 10px; */
    margin: 20px auto;
    border-radius: 50px;
    width: 90%;
    /* min-height: 60px; */
    border: 1px solid #eeeeee;
    display: flex;
    align-items: center;
}

#applicant:hover {
    background-color: #eeeeee;
}
.nameArea {
    flex: 0 0 auto;
}

.interestArea {
    flex: 1 1 20px;
    text-align: left;
    margin-left: 20px;
    overflow: hidden;
}

.messageArea {
    flex: 0 0 auto;
    line-height: 60px;
}

.profile {
    display: block;
    float: left;
    margin-left: 30px;
    width: 30px;
    border-radius: 50%;
    border: 1px solid #eeeeee;
}

.interest {
    display: inline-block;
    padding: 5px;
    border-radius: 20px;
    border: 1px solid #eeeeee;
    background-color: white;
    color: darkblue;
    margin: 0 5px;
}
</style>