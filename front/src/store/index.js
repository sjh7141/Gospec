import Vue from 'vue'
import Vuex from 'vuex'
import ContestList from './modules/ContestList'
import Message from './modules/Message'
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
  }
})
