<template>
    <v-app>
            <v-card-text class='p-0'>
                <v-chip-group v-model="amenities" column="column" multiple="multiple">
                    <div class='mx-auto'>
                        <v-chip
                            style='height: 50px;'
                            class='mx-2 my-4'
                            v-for='event in events1'
                            :key='event.id'
                            filter="filter"
                            outlined="outlined">
                            <div class="my-4 mx-2">
                                <v-btn class='mr-2' :color="event.color" fab="fab" x-small="x-small">
                                    <v-icon>mdi-dialpad</v-icon>
                                </v-btn>
                                {{ event.interest }}
                            </div>
                        </v-chip>
                    </div>
                    <div class='mx-auto'>
                        <v-chip
                            style='height: 50px;'
                            class='mx-2 my-4'
                            v-for='event in events2'
                            :key='event.id'
                            filter="filter"
                            outlined="outlined">
                            <div class="my-4 mx-2">
                                <v-btn class='mr-2' :color="event.color" fab="fab" x-small="x-small">
                                    <v-icon>mdi-dialpad</v-icon>
                                </v-btn>
                                {{ event.interest }}
                            </div>
                        </v-chip>
                    </div>
                    <div class='mx-auto'>
                        <v-chip
                            style='height: 50px;'
                            class='mx-2 my-4'
                            v-for='event in events3'
                            :key='event.id'
                            filter="filter"
                            outlined="outlined">
                            <div class="my-4 mx-2">
                                <v-btn class='mr-2' :color="event.color" fab="fab" x-small="x-small">
                                    <v-icon>mdi-dialpad</v-icon>
                                </v-btn>
                                {{ event.interest }}
                            </div>
                        </v-chip>
                    </div>
                    <div class='mx-auto'>
                        <v-chip
                            style='height: 50px;'
                            class='mx-2 my-4'
                            v-for='event in events4'
                            :key='event.id'
                            filter="filter"
                            outlined="outlined">
                            <div class="my-4 mx-2">
                                <v-btn class='mr-2' :color="event.color" fab="fab" x-small="x-small">
                                    <v-icon>mdi-dialpad</v-icon>
                                </v-btn>
                                {{ event.interest }}
                            </div>
                        </v-chip>
                    </div>
                </v-chip-group>
            </v-card-text>
            <div class="my-2">
                <v-btn x-large="x-large" color=''>시작하기</v-btn>
            </div>
    </v-app>
</template>

<script>
    export default {
        data() {
            return {
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
                events1: [],
                events2: [],
                events3: [],
                events4: []
            }
        },
        methods: {
            getEvents() {
                const interests = this.interests
                const eventCount = interests.length

                for (let i = 0; i < eventCount; i++) {
                    if (i < 5) {
                        this
                            .events1
                            .push({
                                interest: this.interests[i],
                                color: this.colors[this.rnd(0, this.colors.length - 1)]
                            })
                    } else if (i < 9) {
                        this
                            .events2
                            .push({
                                interest: this.interests[i],
                                color: this.colors[this.rnd(0, this.colors.length - 1)]
                            })
                    } else if (i < 13) {
                        this
                            .events3
                            .push({
                                interest: this.interests[i],
                                color: this.colors[this.rnd(0, this.colors.length - 1)]
                            })
                    } else {
                        this
                            .events4
                            .push({
                                interest: this.interests[i],
                                color: this.colors[this.rnd(0, this.colors.length - 1)]
                            })
                    }
                }
            },
            rnd(a, b) {
                return Math.floor((b - a + 1) * Math.random()) + a
            }
        },
        mounted() {
            this.getEvents()
        }
    }
</script>

<style scoped="scoped">
    div {
        font-weight: bolder;
        font-size: large;
    }
</style>