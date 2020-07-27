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
    white-space: pre-line;
    text-align: left;
    margin: 30px;
}
</style>