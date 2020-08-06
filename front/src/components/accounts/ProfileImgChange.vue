<template>
    <div>
        <!-- Button trigger modal -->
        <button
            type="button"
            class="btn btn-primary"
            data-toggle="modal"
            data-target="#exampleModal">
            
            프로필 사진 변경
        </button>

        <!-- Modal -->
        <div
            class="modal fade"
            id="exampleModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">프로필 사진 변경</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                      <div class='d-flex justify-content-center'>
                      <v-avatar class='mx-auto' size='200'>
                        <v-img 
                        v-if="imgData.imageUrl" :src="imgData.imageUrl"></v-img>
                        <v-img
                            v-else
                            src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                      </v-avatar>
                      </div>
                        <v-file-input
                            label="File input"
                            filled="filled"
                            prepend-icon="mdi-camera"
                            v-model='imgData.imgFile'
                            @change='onChangeImages'
                            ></v-file-input>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button @click="saveImage" type="button" class="btn btn-primary" data-dismiss='modal'>ok</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {dialog: false,
            imgData: {
                imgFile: null, 
                imageUrl: null
            } ,
            }
        },
        props: {
            profileImg: String
        },
        components: {},
        methods: {
          onChangeImages() {
              const file = this.imgData.imgFile; // Get first index in files
              this.imgData.imageUrl = URL.createObjectURL(file); // Create File URL
          },
          saveImage() {
              this.$emit('submit-image-data', this.imgData)
          },
        },
    }
</script>
