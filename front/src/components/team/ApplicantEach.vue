<template>
    <div id='applicant'>
        <!-- <img class='profile' :src="'http://somewhere.com/' + profile.profileImg" alt=""/> -->
        <img class='profile' src="@/assets/anonymous.jpg" alt=""/>
        {{profile.nickname}}/{{profile.interestFieldList}}/{{profile.username}}으로쪽지기능
    </div>
</template>

<script>
export default {
    props: ['data'],
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
</style>