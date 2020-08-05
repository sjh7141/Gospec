<template>
    <div class='user-profile'>
    <v-card>
            <v-img
              src="https://blog.sandiego.org/wp-content/uploads/2020/04/SDT_Zoom-Backgrounds_April-8_Windansea-1_1600x800.jpg"
              max-height="300"
              class="grey darken-4 d-flex align-items-end"
            >
                      <v-avatar style='background-color: white;' class='mb-4 mx-auto' size='200'>
                        <v-img 
                        v-if="userData.profileImg" :src="userData.profileImg"></v-img>
                        <v-img
                            v-else
                            src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                      </v-avatar>
                      </v-img>
          </v-card>
    </div>
</template>

<script>
export default {
  props: {
    userData: Object
  }
}
</script>

<style>

</style>