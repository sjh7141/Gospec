import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

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
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/index',
    component: () => import('../views/Index.vue')
  },
  {
    path: '/schedule',
    component: () => import('../views/Schedule.vue'),
  },
  {
    path: '/contest',
    component: () => import('../views/contest/ContestRoute.vue'),
    children: [
      {
        path: '',
        component: () => import('../views/contest/ContestList.vue')
      },
      {
        path: 'write',
        component: () => import('../views/contest/ContestWrite.vue')
      },
      {
        path: ':contest_id',
        component: () => import('../views/contest/ContestEach.vue'),
        children: [
          {
            path: '',
            component: () => import('../views/contest/ContestDetail.vue'),
          },
          {
            path: 'myteam',
            component: () => import('../views/mypage/MyTeam.vue'),
          },
          {
            path: 'teams',
            component: () => import('../views/team/TeamList.vue'),
          },
          {
            path: 'applicants',
            component: () => import('../views/team/ApplicantList.vue'),
          },
        ]
      },
      {
        path: ':contest_id/update',
        component: () => import('../views/contest/ContestUpdate.vue')
      },
    ]
  },
  {
    path: '/study',
    component: () => import('../views/study/Study.vue'),
  },
  {
    path: '/jobinfo',
    component: () => import('../views/jobinfo/Jobinfo.vue'),
  },
  {
    path: '/mypage',
    component: () => import('../views/mypage/Mypage.vue'),
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
