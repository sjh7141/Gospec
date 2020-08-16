<template>
<div>
    <div class='container'>
        <!-- <p style='font-size: 5em;'>GoSpec</p> -->
        <div style="font-size: 20px; text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;">관심사를 클릭해주세요</div>
        <v-card-text class='p-0' style='margin-top: 40px; margin-bottom: 40px'>
            <v-chip-group v-model="amenities" column="column" multiple="multiple"
          active-class="indigo darken-3 white--text" >
                <div class='mx-auto'>
                    <v-chip
                        style='height: 50px; color: black;'
                        class='mx-2 my-4'
                        v-for='event in events'
                        @click="giveInterest(event.real_interest)"
                        :key='event.id'
                        color='white'>
                        <div class="my-4 mx-2">
                            <v-btn class='mr-2' :color="event.color" fab="fab" x-small="x-small">
                                <v-icon>{{ event.interestIcon }}</v-icon>
                            </v-btn>
                            {{ event.interest }}
                        </div>
                    </v-chip>
                </div>
            </v-chip-group>
        </v-card-text>
        <div class="my-4">
            <v-btn @click='signup' style='width: 40%; height: 60px' color='indigo darken-1' class='mx-4'><h2 class='blinking mb-0' style='color: white; '>GoSpec</h2></v-btn>
            <!-- <v-btn @click='login' x-large="x-large" color='' class='mx-4'>LOGIN</v-btn> -->
            <Modal :myInterest= 'myInterest' :checkParent='checkParent' :modalState='modalState' :modalTitle='modalTitle' :modalSize='modalSize' :check='check' @closeModal='closeModal' />
            <div class="my-2">
                <v-btn @click='homepage' color='white' text style="text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;">GUEST로 입장하기</v-btn>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import Modal from '../accounts/Modal.vue'
    export default {
        components: {
            Modal,
        },
        data() {
            return {
                myInterest: [],
                modalState: '',
                modalSize: '',
                modalTitle: '',
                checkParent: false,
                interests: [
                    '기획 / 아이디어',
                    '광고 / 마케팅',
                    '논문 / 리포트',
                    '영상 / UCC / 사진',
                    '디자인 / 캐릭터 / 웹툰',
                    '웹 / 모바일 / 플래시',
                    '게임 / 소프트웨어',
                    '과학 / 공학',
                    '문학 / 글 / 시나리오',
                    '건축 / 건설 / 인테리어',
                    '네이밍 / 슬로건',
                    '예체능 / 미술 / 음악',
                    '대외활동 / 서포터즈',
                    '봉사활동',
                    '취업 / 창업',
                    '해외',
                    '기타'
                ],
                real_interests: [
                    '기획-아이디어',
                    '광고-마케팅',
                    '논문-리포트',
                    '영상-UCC-사진',
                    '디자인-캐릭터-웹툰',
                    '웹-모바일-플래시',
                    '게임-소프트웨어',
                    '과학-공학',
                    '문학-글-시나리오',
                    '건축-건설-인테리어',
                    '네이밍-슬로건',
                    '예체능-미술-음악',
                    '대외활동-서포터즈',
                    '봉사활동',
                    '취업-창업',
                    '해외',
                    '기타'
                ],
                amenities: null,
                colors: [
                    'red darken-1',
                    'pink lighten-2',
                    'purple lighten-2',
                    'blue lighten-4',
                    'indigo darken-1',
                    'cyan',
                    'teal lighten-1',
                    'light-blue lighten-1',
                    'lime lighten-3',
                    'green lighten-2',
                    'lime accent-2',
                    'orange lighten-2',
                    'yellow lighten-2',
                    'amber darken-3',
                    'brown darken-1',
                    'blue-grey darken-1',
                    'grey lighten-2',
                    'grey lighten-1'
                ],
                interestIcons : 
                ["far fa-lightbulb interest", 
                "fas fa-bullhorn ml-2 interest",
                 "fas fa-scroll interest", 
                 "fas fa-video interest", 
                 "fas fa-palette interest", 
                 "fas fa-mobile-alt interest", 
                 "fas fa-gamepad interest", 
                 "fas fa-flask interest", 
                 "fas fa-book-open interest", 
                 "fas fa-building interest", 
                 "fas fa-quote-left interest", 
                 "fas fa-music interest",
                "fas fa-hands-helping interest",
                "fas fa-people-carry interest",
                "fas fa-id-card-alt interest",
                "fas fa-plane-departure interest",
                "fas fa-ellipsis-h interest"],
                events: [],
                check: false,
                isLoggedIn: false,
            }
        },
        methods: {
            giveInterest(interest) {

                this.myInterest.push(interest)
                console.log(this.myInterest)


            },
            getEvents() {
                const interests = this.interests
                const eventCount = interests.length

                for (let i = 0; i < eventCount; i++) {
                    this
                        .events
                        .push({
                            interestIcon: this.interestIcons[i],
                            real_interest: this.real_interests[i],
                            interest: this.interests[i],
                            color: this.colors[this.rnd(0, this.colors.length - 1)]
                        })
                }
            },
            rnd(a, b) {
                return Math.floor((b - a + 1) * Math.random()) + a
            },
            homepage(){
                this.$router.push('/home')
            },
            signup() {
                if (this.isLoggedIn) {
                    this.$router.push('/home')
                }
                else {
                    this.modalState = 'signup'
                    this.modalSize = '500'
                    this.modalTitle = '회원가입'
                    this.check = true
                    this.checkParent = true
                }
            },
            login() {
                this.modalState = 'login'
                this.modalSize = '350'
                this.modalTitle = '로그인'
                this.check = true
            },
            closeModal() {
                this.check = false
            },
        },
        mounted() {
            this.getEvents(),
            this.isLoggedIn = this.$cookies.isKey('auth-token')
        }
    }
</script>

<style scoped="scoped">
div {
    font-weight: bolder;
    font-size: large;
}
.blinking{
	-webkit-animation:blink 1.5s ease-in-out infinite alternate;
    -moz-animation:blink 1s ease-in-out infinite alternate;
    animation:blink 1s ease-in-out infinite alternate;
}
@-webkit-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@-moz-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
</style>