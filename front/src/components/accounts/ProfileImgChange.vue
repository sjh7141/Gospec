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
                        v-if="imageUrl" :src="imageUrl"></v-img>
                        <v-img
                            v-else
                            src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                      </v-avatar>
                      </div>
                        <v-file-input
                            label="File input"
                            filled="filled"
                            prepend-icon="mdi-camera"
                            v-model='imgFile'
                            @change='onChangeImages'
                            ></v-file-input>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button @click="uploadImage" type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

const API_URL = 'http://localhost:8181'

    export default {
        data() {
            return {dialog: false, imgFile: null, imageUrl: null,
            }
        },
        components: {},
        methods: {
          onChangeImages() {
              const file = this.imgFile; // Get first index in files
              this.imageUrl = URL.createObjectURL(file); // Create File URL
          },
          uploadImage() {
            console.log(this.imgFile)
            const file = this.imgFile

              const config = {
                
                headers: {
                  Authorization: this.$cookies.get("auth-token"),
                  'Content-Type': 'multipart/form-data',
                }
            } 
            let formData = new FormData();
            formData.append("file", file);
            console.log(config)
            axios.post(API_URL + '/api/file/upload', formData, config)
            .then((res) => {
              console.log(res.data)
            })
            .catch((err) => console.log(err))
          }
        }
    }
</script>
