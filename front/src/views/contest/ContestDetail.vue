<template>
    <div class='content'>{{ this.content }}</div>
</template>

<script>
export default {
    name: 'contestDetail',
    props: ['content']
}
</script>

<style scoped>
.content {
    width: 80%;
    white-space: pre-line;
    text-align: left;
    margin: 30px auto 0 auto;
}
</style>