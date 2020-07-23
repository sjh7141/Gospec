<template>
  <div>
    <p>{{ contest.title }}</p>
    <p>{{ contest.startDate }}</p>
    <p>{{ contest.endDate }}</p>
  </div>
</template>

<script>
export default {
    props: {
        contest: {
            type:Object
        }
    },

}
</script>

<style>

</style>