<template>
    <div>
        <table class="table table-hover table-bordered" style="height: 600px; width: 95%;" >
            <tr style="height: 15%">
                <td><h3>{{message.contents}}</h3></td>
            </tr> 
            <tr style="height: 5%">
                <td>보낸사람: {{message.sender}}</td>
            </tr>
            <tr style="height: 5%">
                <td>보낸시간: {{message.registTime}}</td>
            </tr>
            <tr style="border-top: 1px solid black">
                <td style="vertical-align: top;">{{message.contents}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    computed: {
    ...mapGetters(['message']),
  }
}
</script>

<style>

</style>