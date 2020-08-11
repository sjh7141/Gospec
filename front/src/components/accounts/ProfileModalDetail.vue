<template>
    <div>
        <v-card class='row' style='padding: 50px;' color='grey lighten-4'>
            <div class='col-12 text-right'>
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
                        <div style='min-height: 200px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 23px;'></i>경력사항</h5>
                            <div v-for='career in careerList' :key='career.id' class='d-flex row' style='margin-left: 1px;'>
                                <p class='m-0' style='color: gray; width:50px;'>{{ career.startYear }}</p>
                                <p class='m-0' style='width: 180px;'>{{ career.careername }}</p>
                                <p class='m-0' style='color: #78909C;'>{{ career.status }}</p>
                            </div>
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

            </v-card>

        </div>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                name: '',
                nickname: '',
                authority: '',
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
        props: {
            userData: Object
        },
        methods: {
            getUserInfo() {
                this.birthday = this.userData.birthday
                this.address = this.userData.address
                this.gender = this.userData.gender
                this.major = this.userData.major
                this.name = this.userData.name
                this.nickname = this.userData.nickname
                this.phone = this.userData.phone
                this.profileImg = this.userData.profileImg
                this.selfIntroduction = this.userData.selfIntroduction
                this.imageUrl = this.userData.profileImg
                this.age = this.userData.age,
                this.interestFieldList = this.userData.interestFieldList,
                this.activeRegionList = this.userData.activeRegionList,
                this.careerList = this.userData.careerList,
                this.licenseList = this.userData.licenseList,
                this.username = this.userData.username
                
                this.interestIcon = []
                for(let i=0; i<this.interestFieldList.length; i++) {
                    const index = this.interestsData.indexOf(this.interestFieldList[i])
                    const data = [this.interestIconClass[index], this.interests[index]]
                    this.interestIcon.push(data)
                }
            },
        },
        mounted() {
            this.getUserInfo()
        },
        computed: {
        }
    }
</script>

<style scoped="scoped">
    .interest {
        font-size: 40px;
        margin-right: 15px;
    }
    .hide {
        display: none;
    }
</style><template>
        <v-card class='row' style='padding: 50px;' color='grey lighten-4'>
        
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
                        <div style='min-height: 220px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px; margin-bottom: 23px;'></i>경력사항</h5>
                            <div v-for='career in careerList' :key='career.id' class='d-flex row' style='margin-left: 1px;'>
                                <p class='m-0' style='color: gray; width:50px;'>{{ career.startYear }}</p>
                                <p class='m-0' style='width: 180px;'>{{ career.careername }}</p>
                                <p class='m-0' style='color: #78909C;'>{{ career.status }}</p>
                            </div>
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
                        <div style='min-height: 180px'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>수상내역 / 자격증</h5>
                            <div v-for='license in licenseList' :key='license.id'>{{ license }}</div>
                        </div>
                        
                        <div style='min-height: 160px'>

                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>활동 지역</h5>
                            <v-chip
                                :ripple="false"
                                class='mr-2'
                                v-for='region in activeRegionList'
                                :key='region.id'>{{ region }}</v-chip>
                        </div>

                        <div style='min-height: 140px;'>
                            <h5>
                                <i class="fas fa-square mr-1" style='color: red; font-size:10px;'></i>관심 분야</h5>
                            <div style='margin-top: 30px'>
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
                    </div>
                </v-card>

            </v-card>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                name: '',
                nickname: '',
                authority: '',
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
        props: {
            userData: Object
        },
        methods: {
            getUserInfo() {
                this.birthday = this.userData.birthday
                this.address = this.userData.address
                this.gender = this.userData.gender
                this.major = this.userData.major
                this.name = this.userData.name
                this.nickname = this.userData.nickname
                this.phone = this.userData.phone
                this.profileImg = this.userData.profileImg
                this.selfIntroduction = this.userData.selfIntroduction
                this.imageUrl = this.userData.profileImg
                this.age = this.userData.age,
                this.interestFieldList = this.userData.interestFieldList,
                this.activeRegionList = this.userData.activeRegionList,
                this.careerList = this.userData.careerList,
                this.licenseList = this.userData.licenseList,
                this.username = this.userData.username
                
                this.interestIcon = []
                for(let i=0; i<this.interestFieldList.length; i++) {
                    const index = this.interestsData.indexOf(this.interestFieldList[i])
                    const data = [this.interestIconClass[index], this.interests[index]]
                    this.interestIcon.push(data)
                }
            },
        },
        mounted() {
            this.getUserInfo()
        },
        computed: {
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
</style>