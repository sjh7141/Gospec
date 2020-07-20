import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
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
  /*
  {
    path: '/contest/write',
    component: () => import('../views/contest/ContestWrite.vue')
  },
  {
    path: '/contest/:contest_id', // 아 여기서 -쓰면 정규표현식처럼 알아듣나봄. _로 대체
    component: () => import('../views/contest/ContestDetail.vue')
  },
   */
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
