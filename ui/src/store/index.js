import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import hotel from './modules/hotel'
import overviewerrors from './modules/overviewerrors'
import VueBus from 'vue-bus'

Vue.use(Vuex)
Vue.use(VueBus)
const store = new Vuex.Store({
  state: {},
  modules: {
    hotel,
    overviewerrors
  },
  getters
})

export default store
