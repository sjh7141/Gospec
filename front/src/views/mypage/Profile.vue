<template>
    <div class='profile'>
        <v-card class='row' style='padding: 50px;' color='grey lighten-4'>

            <div class='col-3'>
                <h3 style='margin-top:40px; margin-bottom:20px;'>
                </h3>
                <v-avatar class='mx-auto' size='200'>
                    <v-img v-if="profileImg" :src="profileImg"></v-img>
                    <v-img
                        v-else
                        src="https://www.popularitas.com/wp-content/uploads/2018/04/user-hero-blue.png"></v-img>
                </v-avatar>
                <div style='margin-bottom: 50px; margin-top: 30px;'>
                    <p class='mb-1' style='font-size: 1.2rem; font-weight: 700;'>{{ name }}</p>
                    <p>{{ nickname }}</p>
                </div>
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
                        <div style='min-height: 250px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 12px;'></i>경력사항</h5>
                            <div v-if='careerList[0]'>
                                <div v-for='career in careerList' :key='career.id' class='d-flex row' style='margin-left: 1px;'>
                                    <p class='m-0' style='color: gray; width:50px;'>{{ career.startYear }}</p>
                                    <p class='m-0' style='width: 180px;'>{{ career.careername }}</p>
                                    <p class='m-0' style='color: #78909C;'>{{ career.status }}</p>
                                </div>
                            </div>
                            <div v-else>
                                <p class='none-info'> * 경력사항을 등록해주세요.</p>
                            </div>
                        </div>
                        <div>
                            <h5>
                                    <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 12px;'></i>자기소개</h5>
                            <div v-if='selfIntroduction'>
                                
                                {{ selfIntroduction }}
                            </div>
                            <div v-else>
                                <p class='none-info'> * 자기소개를 입력해주세요.</p>
                            </div>
                        </div>
                    </div>
                    <div class='col-6 pb-0'>
                        <div style='min-height: 180px'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 12px;'></i>수상내역 / 자격증</h5>
                            <div v-if='licenseList[0]'>
                                <div v-for='license in licenseList' :key='license.id'>{{ license }}</div>
                            </div>
                            <div v-else>
                                <p class='none-info'> * 수상내역 및 자격증을 등록해주세요.</p>
                            </div>
                        </div>
                        
                        <div style='min-height: 160px'>

                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 12px;'></i>활동 지역</h5>
                            <div v-if='activeRegionList[0]'>
                                <v-chip
                                    :ripple="false"
                                    class='mr-2'
                                    v-for='region in activeRegionList'
                                    :key='region.id'>{{ region }}</v-chip>
                            </div>
                            <div v-else>
                                <p class='none-info'> * 활동 지역을 등록해주세요.</p>
                            </div>
                        </div>

                        <div style='min-height: 110px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 12px;'></i>관심 분야</h5>
                            <div v-if='interestIcon[0]'>
                                <div style='margin-top: 15px'>
                                    <v-tooltip bottom="bottom" v-for='icon in interestIcon' :key='icon.id'>
                                            <template v-slot:activator="{ on, attrs }">
                                                <i
                                                    :class='icon[0]'
                                                    v-bind="attrs"
                                                    v-on="on"
                                                    style="color: gray"></i>
                                            </template>
                                            <span>{{icon[1]}}</span>
                                    </v-tooltip>
                                </div>
                            </div>
                            <div v-else>
                                <p class='none-info'> * 관심사를 등록해주세요.</p>
                            </div>
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
                            <div v-for='(career, index) in careerList' :key='index' class='d-flex justify-content-between'>
                                <div class='d-flex'>
                                <p class='m-0' style='color: gray; width:50px;'>{{ career.startYear }}</p>
                                <p class='m-0' style='width: 180px;'>{{ career.careername }}</p>
                                <p class='m-0' style='color: #78909C;'>{{ career.status }}</p>
                                </div>
                                <v-icon slot="append" color="red" type='button' @click='removeCareer(index)'>mdi-minus</v-icon>
                            </div>
                            <div class='d-flex'>

                                <v-text-field label="Date" color="#ff5252" v-model='startYear' style='width: 20%'></v-text-field>
                                <v-text-field label="Content" color="#ff5252" v-model='careername' style='width: 60%'></v-text-field>
                                <v-text-field
                                    label="State"
                                    v-model='status'
                                    style='width: 20%'
                                    @keypress.enter="addCareer"
                                    color="#ff5252" ></v-text-field>
                                <v-icon slot="append" color="green" type='button' @click='addCareer'>mdi-plus</v-icon>
                            </div>
                        </div>
                        <div>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>자기소개</h5>
                            <v-textarea v-model='selfIntroduction' maxlength="150" cors='30' rows='10' color="#ff5252" ></v-textarea>
                        </div>
                    </div>
                    <div class='col-6 pb-0'>

                        <div style='min-height: 180px'>
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
                                    @keypress.enter="addLicense"
                                    color="#ff5252" ></v-text-field>
                                <v-icon slot="append" color="green" type='button' @click='addLicense'>mdi-plus</v-icon>
                            </div>
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
                                multiple="multiple"
                                color="#ff5252" >
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

                        <div style='min-height: 140px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>관심 분야</h5>
                                
                                <v-card-text class='p-0'>
                                <v-chip-group
                                    v-model="amenities"
                                    column
                                    multiple
                                >
                                    <v-chip v-for='interest in interests' :key='interest.id' filter outlined>{{ interest }}</v-chip>
                                </v-chip-group>
                                </v-card-text>
                        </div>

                    </div>
                </v-card>
            <div class='col-12 text-right'>
                <button @click='clickEditBtn' class='btn btn-primary btnStyle' style="margin-right: 25px;">
                    <i class="fas fa-pen mr-2"></i>
                    <p v-if='!editBtn' class='d-inline'>Edit</p>
                    <p v-else class='d-inline'>OK</p>
                </button>
            </div>
            </v-card>

        </div>
</template>

<script>
    import axios from 'axios'

    const API_URL = 'http://i3a202.p.ssafy.io:8181'

    export default {
        components: {},
        data() {
            return {
                name: '',
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
                selfIntroduction: '',
                interests: ['기획/아이디어', '광고/마케팅', '논문/리포트', '영상/UCC/사진', '디자인/캐릭터/웹툰', '웹/모바일/플래시', '게임/소프트웨어', '과학/공학', '문학/글/시나리오', '건축/건설/인테리어', '네이밍/슬로건', '예체능/미술/음악', '대외활동/서포터즈', '봉사활동', '취업/창업', '해외', '기타'],
                interestsData: ['기획-아이디어', '광고-마케팅', '논문-리포트', '영상-UCC-사진', '디자인-캐릭터-웹툰', '웹-모바일-플래시', '게임-소프트웨어', '과학-공학', '문학-글-시나리오', '건축-건설-인테리어', '네이밍-슬로건', '예체능-미술-음악', '대외활동-서포터즈', '봉사활동', '취업-창업', '해외', '기타'],
                amenities: [],
                interestIconClass : ["far fa-lightbulb interest", "fas fa-bullhorn ml-2 interest", "fas fa-scroll interest", "fas fa-video interest", "fas fa-palette interest", "fas fa-mobile-alt interest", "fas fa-gamepad interest", "fas fa-flask interest", "fas fa-book-open interest", "fas fa-building interest", "fas fa-quote-left interest", "fas fa-music interest", "fas fa-hands-helping interest", "fas fa-people-carry interest", "fas fa-id-card-alt interest", "fas fa-plane-departure interest", "fas fa-ellipsis-h interest"],
                interestIcon: [],
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
                        this.birthday = res.data.birthday
                        this.address = res.data.address
                        this.gender = res.data.gender
                        this.major = res.data.major
                        this.name = res.data.name
                        this.nickname = res.data.nickname
                        this.phone = res.data.phone
                        this.profileImg = res.data.profileImg
                        this.selfIntroduction = res.data.selfIntroduction
                        this.imageUrl = res.data.profileImg
                        this.age = res.data.age,
                        this.interestFieldList = res.data.interestFieldList,
                        this.activeRegionList = res.data.activeRegionList,
                        this.careerList = res.data.careerList,
                        this.licenseList = res.data.licenseList,
                        this.username = res.data.username
                        
                        this.interestIcon = []
                        for(let i=0; i<this.interestFieldList.length; i++) {
                            const index = this.interestsData.indexOf(this.interestFieldList[i])
                            const data = [this.interestIconClass[index], this.interests[index]]
                            this.interestIcon.push(data)
                        }
                    })
                    .catch(err => console.log(err.response))
                },
            clickEditBtn() {
                this.editBtn = !this.editBtn
                if (!this.editBtn) {
                    this.interestFieldList = []
                    for(let i=0; i<this.amenities.length; i++) {
                        this.interestFieldList.push(this.interestsData[this.amenities[i]])
                    }
                    this.interestIcon = []
                    for(let i=0; i<this.interestFieldList.length; i++) {
                        const data = [this.interestIconClass[this.amenities[i]], this.interests[this.amenities[i]]]
                        this.interestIcon.push(data)
                    }
                    this.updateProfileData()
                }
                else {
                    this.setAmenities()
                }
            },
            setAmenities() {
                this.amenities = []
                for(let i=0; i<this.interestFieldList.length; i++) {
                    this.amenities.push(this.interestsData.indexOf(this.interestFieldList[i]))
                }
            },
            addCareer() {
                this
                    .careerList
                    .push(
                        {startYear: this.startYear, careername: this.careername, status: this.status}
                    )
                this.careerList.sort()
                this.careername = ''
                this.startYear = ''
                this.status = ''
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
                    .then(//res => {
                        // this
                        //     .profileData
                        //     console
                        //     .log(res.data)
                    //})
                    )
                    .catch(err => {
                        console.log(err.response.data)
                    })
                },
        },
        mounted() {
            this.getUserInfo()
        },
        computed: {
            profileData() {
                return {
                    user: {
                        name: this.name,
                        nickname: this.nickname,
                        selfIntroduction: this.selfIntroduction,
                        phone: this.phone,
                        birthday: this.birthday,
                        address: this.address,
                        profileImg: this.profileImg,
                        gender: this.gender,
                        major: this.major,
                        age: this.age,
                        username: this.username,
                        authority: this.authority,
                    },
                    fields: this.interestFieldList,
                    type: 'profile',
                    regions: this.activeRegionList,
                    licenses: this.licenseList,
                    careers: this.careerList,
                }
            }
        }
    }
</script>

<style scoped="scoped">
    .interest {
        font-size: 30px;
        margin-right: 15px;
    }
    .hide {
        display: none;
    }
    .none-info {
        font-size: 0.8rem;
        color: #00897B;
    }
    .btnStyle {background-color: #ff5252;}
    .btnStyle:hover {background-color: #ff3030;}
    .btnStyle:active:focus {background-color: #ff3030;}
    .profile {
        margin-bottom: 60px;
    }
</style>