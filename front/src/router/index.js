import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import Index from '../views/Index.vue'
import Schedule from '../views/Schedule.vue'

import ContestRoute from '../views/contest/ContestRoute.vue'
import ContestList from '../views/contest/ContestList.vue'
import ContestWrite from '../views/contest/ContestWrite.vue'
import ContestEach from '../views/contest/ContestEach.vue'
import ContestDetail from '../views/contest/ContestDetail.vue'
import ContestUpdate from '../views/contest/ContestUpdate.vue'

import TeamList from '../views/team/TeamList.vue'
import TeamEach from '../views/team/TeamEach.vue'
import TeamUpdate from '../views/team/TeamUpdate.vue'
import TeamWrite from '../views/team/TeamWrite.vue'
import ApplicantList from '../views/team/ApplicantList.vue'

import Study from '../views/study/Study.vue'
import Jobinfo from '../views/jobinfo/Jobinfo.vue'
import Mypage from '../views/mypage/Mypage.vue'
import MyTeam from '../views/mypage/MyTeam.vue'
import Profile from '../views/mypage/Profile.vue'
import Userinfo from '../views/mypage/Userinfo.vue'

import VueCookies from 'vue-cookies'

Vue.use(VueCookies)
/*
- 라우팅 테이블 작성시 참고사항 정리 주석

- '@' is an alias to '/src'

- 동적라우트매칭시 변수명에 -대신 _권장
  ex) path: '/contest/:contest_id'
  여기서 -쓰면 정규표현식처럼 알아듣는지 의도한대로 동작하지 않음. _로 대체시 정상작동 확인

- 스켈레톤코드에서는 name을 통한 라우팅시에 constant.js를 활용하여 name을 상수관리했다.
  규모 확장시에 고려해 볼 것.

- route level code-splitting
  this generates a separate chunk (about.[hash].js) for this route
  which is lazy-loaded when the route is visited.
  {
    path: '/about',
    name: 'About',
    component: () => import(webpackChunkName: "about" '../views/About.vue')
  }
*/
Vue.use(VueRouter)

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/index', component: Index },
  { path: '/schedule', component: Schedule },
  { path: '/contest', component: ContestRoute, children: [
    { path: '', component: ContestList },
    { path: 'write', component: ContestWrite },
    { path: ':contest_id', component: ContestEach, children: [
      { path: '', component: ContestDetail },
      { path: 'myteam', component: MyTeam },
      { path: 'teams', component: TeamList },
      { path: 'teams/write', component: TeamWrite },
      { path: 'teams/:team_id', component: TeamEach },
      { path: 'teams/:team_id/update', component: TeamUpdate },
      { path: 'applicants', component: ApplicantList },
    ]},
    { path: ':contest_id/update', component: ContestUpdate },
  ]},
  { path: '/study', component: Study },
  { path: '/jobinfo', component: Jobinfo },
  { path: '/mypage', component: Mypage, children: [
    { path: '', component: Profile },
    { path: 'userinfo', component: Userinfo },
    { path: 'myteam', component: MyTeam },
  ]},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
