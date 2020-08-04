<template>
  <div>
    <v-file-input
      label="File input"
      filled
      prepend-icon="mdi-camera"
      v-model='imgFile'
    ></v-file-input>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgFile: null,
    }
  }
}
</script>

<style>

</style>