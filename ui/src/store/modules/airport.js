// import {
//   wsGetProjectDetail,
// } from '@/models/project';

const global = {
  state: {
    history_list: [],
    airport_group: {
      bigvalue: 1,
      childvalue: 0,
      groupType: '经济舱',
      tabIndex: 0
    }
  },
  mutations: {
    SET_AIRPORT_FILTER_DATA (state, data) {
      console.log(data)
      state.history_list.unshift(data)
    },
    SET_AIRPORT_GROUP_DATA (state, data) {
      state.airport_group = data
    }
  },
  actions: {
    setAirportFilterData ({ commit }, params) {
      commit('SET_AIRPORT_FILTER_DATA', params)
    },
    setAirportGroupData ({ commit }, params) {
      commit('SET_AIRPORT_GROUP_DATA', params)
    }
  }
}

export default global
