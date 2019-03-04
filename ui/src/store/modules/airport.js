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
    },
    airport_list: {},
    airport_time: {},
    // 存储票信息
    book_flight: {}
  },
  mutations: {
    SET_AIRPORT_FILTER_DATA (state, data) {
      console.log(data)
      state.history_list.unshift(data)
    },
    SET_AIRPORT_GROUP_DATA (state, data) {
      state.airport_group = data
    },
    SET_AIRPORT_LIST_DATA (state, data) {
      console.log(data)
      state.airport_list = data
    },
    SET_AIRPORT_Time_DATA (state, data) {
      state.airport_time = data
    },
    SET_CLEAR_FILTER_DATA (state, data) {
      state.history_list = data
    },
    SET_AIRPORT_PRICE_DATA (state, data) {
      state.book_flight = data
    }
  },
  actions: {
    setAirportFilterData ({ commit }, params) {
      commit('SET_AIRPORT_FILTER_DATA', params)
    },
    setAirportGroupData ({ commit }, params) {
      commit('SET_AIRPORT_GROUP_DATA', params)
    },
    searchAirportListData ({ commit }, params) {
      commit('SET_AIRPORT_LIST_DATA', params)
    },
    chooseAirportTimeData ({ commit }, params) {
      commit('SET_AIRPORT_Time_DATA', params)
    },
    setClearFilterData ({ commit }, params) {
      commit('SET_CLEAR_FILTER_DATA', params)
    },
    setAirportPriceData ({ commit }, params) {
      commit('SET_AIRPORT_PRICE_DATA', params)
    }
  }
}

export default global
