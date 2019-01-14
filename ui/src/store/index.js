import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
// import global from './modules/global';
import overviewerrors from './modules/overviewerrors'
import VueBus from 'vue-bus'

Vue.use(Vuex)
Vue.use(VueBus)
const store = new Vuex.Store({
  state: {},
  modules: {
    // global,
    overviewerrors
  },
  getters
})

export default store
