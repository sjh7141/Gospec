<template>
    <div id='applicant'>
        <ProfileModal :username='profile.username' />
        <span style="color:gray;">{{profile.nickname}}</span>
        <span v-for="(interest, i) in profile.interestFieldList" :key="i" class="interest">
            {{interest}}
        </span>
        <message-button :receiver="profile.username" />
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
    padding: 10px;
    margin: 20px auto;
    border-radius: 50px;
    width: 80%;
    min-height: 60px;
    border: 1px solid #eeeeee;
}

#applicant:hover {
    background-color: #eeeeee;
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
    margin: 5px;
    padding: 10px;
    border: 1px solid #eeeeee;
    border-radius: 20px;
    background-color: white;
}
</style>