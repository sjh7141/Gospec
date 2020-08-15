import Vue from 'vue'
import Vuex from 'vuex'
import ContestList from './modules/ContestList'
import Message from './modules/Message'
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    ContestList,
    Message,
  },
  plugins: [
    createPersistedState({
      paths: ['Message'],
    }),
  ]
})
