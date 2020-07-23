<template>
<div>
<button type="button" @click="onInputChange">공모전</button>
<div v-if='view == true'>
    <p> {{ contests }} </p>
</div>
</div>
  
</template>

<script>
import axios from 'axios'
const API_URL = "http://localhost:8181/api/contest/"
export default {
    data() {
        return {
        view: false,
        contests: []
        }
    },
    methods: {
        onInputChange() {
      // console.log(inputValue)
        axios.get(API_URL)
        .then(response => {
            this.view = true
            this.contests = response.data
            })
        .catch(error => { console.log(error) })
        }
    }
}


</script>

<style>

</style>