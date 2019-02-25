// import {
//   wsGetProjectDetail,
// } from '@/models/project';

const global = {
  state: {
    filter_D: []
  },
  mutations: {
    SET_HOTEL_FILTER_DATA (state, data) {
      state.filter_D = data
    }
  },
  actions: {
    setHotelFilterData ({ commit }, params) {
      commit('SET_HOTEL_FILTER_DATA', params)
    }
  }
}

export default global
