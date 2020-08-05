<template>
    <div>
        <v-card class='row' style='padding: 50px;' color='grey lighten-4'>
            <div class='col-12 text-right'>
                <button @click='clickEditBtn' class='btn btn-primary'>
                    <i class="fas fa-pen mr-2"></i>
                    <p v-if='!editBtn' class='d-inline'>Edit</p>
                    <p v-else class='d-inline'>OK</p>
                </button>
            </div>
            <div class='col-3'>
                <h3 class='my-4'>
                    <i class="fas fa-square mr-1" style='color: red; font-size:15px;'></i>Profile</h3>
                <v-avatar class='mx-auto' size='200'>
                    <v-img v-if="profileImg" :src="profileImg"></v-img>
                    <v-img
                        v-else
                        src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                </v-avatar>
                <p style='margin-bottom: 50px; margin-top: 50px;'>{{ name }}
                    |
                    {{ nickname }}</p>
                <i class="fas fa-phone-square-alt"></i>
                {{ phone }}
                <br>
                    <i class="fas fa-envelope-square"></i>
                    {{ username }}
                </div>

                <!-- 프로필 기본 -->
                <v-card
                    v-if='!editBtn'
                    class='col-8 row'
                    style='margin-left: 5%; text-align: left;'>
                    <div class='col-6 pb-0'>
                        <div style='min-height: 200px'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>경력사항</h5>
                            <div v-for='career in careerList' :key='career.id'>{{ career.startYear }}{{ career.careername }}{{ career.status }}</div>
                        </div>
                        <div>
                            <h5>
                                <i
                                    class="fas fa-square mr-1"
                                    style='color: red; font-size:10px; margin-bottom:18px;'></i>자기소개</h5>
                            {{ selfIntroduction }}
                        </div>
                    </div>
                    <div class='col-6 pb-0'>
                        <div style='min-height: 140px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>관심 분야</h5>
                            <div style='margin-top: 30px'>
                                <v-tooltip bottom="bottom">
                                    <template v-slot:activator="{ on, attrs }">
                                        <i
                                            class="far fa-lightbulb interest"
                                            v-bind="attrs"
                                            v-on="on"
                                            style="color: #FDD835"></i>
                                    </template>
                                    <span>기획/아이디어</span>
                                </v-tooltip>
                                <v-tooltip bottom="bottom">
                                    <template v-slot:activator="{ on, attrs }">
                                        <i class="fas fa-bullhorn ml-2 interest" v-bind="attrs" v-on="on"></i>
                                    </template>
                                    <span>광고/마케팅</span>
                                </v-tooltip>
                            </div>
                        </div>
                        <div style='min-height: 110px'>

                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>활동 지역</h5>
                            <v-chip
                                :ripple="false"
                                class='mr-2'
                                v-for='region in activeRegionList'
                                :key='region.id'>{{ region }}</v-chip>
                        </div>
                        <div>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>수상내역 / 자격증</h5>
                            <div v-for='license in licenseList' :key='license.id'>{{ license }}</div>
                        </div>
                    </div>
                </v-card>

                <!-- 프로필 수정 -->
                <v-card
                    v-else
                    class='col-8 row'
                    style='margin-left: 5%; text-align: left;'>
                    <div class='col-6 pb-0'>
                        <div style='min-height: 200px'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>경력사항</h5>
                            <p>ex) 2018 ssafy대학교 졸업</p>
                            <div v-for='(career, index) in careerList' :key='index'>
                                {{ career.startYear }}{{ career.careername }}{{ career.status }}
                                <v-icon slot="append" color="red" type='button' @click='removeCareer(index)'>mdi-minus</v-icon>
                            </div>
                            <div class='d-flex'>

                                <v-text-field label="Date" v-model='startYear' style='width: 20%'></v-text-field>
                                <v-text-field label="Content" v-model='careername' style='width: 60%'></v-text-field>
                                <v-text-field
                                    label="State"
                                    v-model='status'
                                    style='width: 20%'
                                    @keypress.enter="addCareer"></v-text-field>
                                <v-icon slot="append" color="green" type='button' @click='addCareer'>mdi-plus</v-icon>
                            </div>
                        </div>
                        <div>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>자기소개</h5>
                            <v-textarea v-model='selfIntroduction'></v-textarea>
                        </div>
                    </div>
                    <div class='col-6 pb-0'>
                        <div style='min-height: 140px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>관심 분야</h5>
                        </div>
                        <div style='min-height: 110px'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>활동 지역</h5>
                            ex)
                            <v-chip :ripple="false" class='mr-2'>서울 전체</v-chip>
                            <v-chip :ripple="false">서울시 강남구</v-chip>
                            <v-combobox
                                label="Regions"
                                v-model="activeRegionList"
                                :items="items"
                                chips="chips"
                                multiple="multiple">
                                <template v-slot:selection="{ attrs, item, select, selected }">
                                    <v-chip
                                        v-bind="attrs"
                                        :input-value="selected"
                                        close="close"
                                        @click="select"
                                        @click:close="remove(item)">
                                        <strong>{{ item }}</strong>&nbsp;
                                    </v-chip>
                                </template>
                            </v-combobox>
                        </div>
                        <div>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>수상내역 / 자격증</h5>
                            <p>ex) 서울시 공공데이터 공모전 입상</p>
                            <div v-for='(license, index) in licenseList' :key='index'>
                                {{ license }}
                                <v-icon slot="append" color="red" type='button' @click='removeLicense(index)'>mdi-minus</v-icon>
                            </div>
                            <div class='d-flex'>
                                <v-text-field
                                    label="license"
                                    v-model='licenseInput'
                                    @keypress.enter="addLicense"></v-text-field>
                                <v-icon slot="append" color="green" type='button' @click='addLicense'>mdi-plus</v-icon>
                            </div>
                        </div>
                    </div>
                </v-card>
            </v-card>

        </div>
    </template>

    <script>
        import axios from 'axios'

        const API_URL = 'http://localhost:8181'

        export default {
            components: {},
            data() {
                return {
                    userData: null,
                    username: '',
                    nickname: '',
                    authority: '',
                    editBtn: false,
                    profileImg: '',
                    startYear: '',
                    careername: '',
                    status: '',
                    careerList: [],
                    activeRegionList: [],
                    items: [],
                    licenseList: [],
                    licenseInput: '',
                    interestFieldList: [],
                    selfIntroduction: ''
                }
            },
            methods: {
                getUserInfo() {
                    const config = {
                        headers: {
                            Authorization: this
                                .$cookies
                                .get("auth-token")
                        }
                    }
                    axios
                        .get(API_URL + '/api/users', config)
                        .then(res => {
                            console.log(res.data)
                            this.profileImg = res.data.profileImg
                            this.username = res.data.username
                            this.nickname = res.data.nickname
                            this.authority = res.data.authority
                            this.licenseList = res.data.licenseList
                            this.interestFieldList = res.data.interestFieldList
                            this.careerList = res.data.careerList
                            this.activeRegionList = res.data.activeRegionList
                            this.selfIntroduction = res.data.selfIntroduction
                        })
                        .catch(err => console.log(err.response))
                    },
                clickEditBtn() {
                    this.editBtn = !this.editBtn
                    if (!this.editBtn) {
                        this.updateProfileData()
                    }
                },
                addCareer() {
                    this
                        .careerList
                        .push(
                            {startYear: this.startYear, careername: this.careername, status: this.status}
                        )
                    this.careername = ''
                    this.startYear = ''
                    this.status = ''
                    console.log(this.careerList)
                },
                removeLicense(index) {
                    this
                        .licenseList
                        .splice(index, 1)
                },
                addLicense() {
                    this
                        .licenseList
                        .push(this.licenseInput)
                    this.licenseInput = ''
                },
                removeCareer(index) {
                    this
                        .careerList
                        .splice(index, 1)
                },
                remove(item) {
                    this
                        .chips
                        .splice(this.chips.indexOf(item), 1)
                    this.chips = [...this.chips]
                },
                updateProfileData() {
                    const config = {
                        headers: {
                            Authorization: this
                                .$cookies
                                .get("auth-token")
                        }
                    }
                    axios
                        .patch(API_URL + '/api/users', this.profileData, config)
                        .then(res => {
                            this
                                .profileData
                                console
                                .log(res.data)
                        })
                        .catch(err => {
                            console.log(err.response.data)
                        })
                    }
            },
            mounted() {
                this.getUserInfo()
            },
            computed: {
                profileData() {
                    return {
                        user: {
                            username: this.username,
                            nickname: this.nickname,
                            authority: this.authority,
                            selfIntroduction: this.selfIntroduction
                        },
                        type: 'profile',
                        fields: this.interestFieldList,
                        regions: this.activeRegionList,
                        licenses: this.licenseList,
                        careers: this.careerList
                    }
                }
            }
        }
    </script>

    <style scoped="scoped">
        .interest {
            font-size: 40px;
        }
        .hide {
            display: none;
        }
    </style>