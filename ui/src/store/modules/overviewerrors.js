// import { wsGetProjectChartData } from '@/models/project';
// import { wsGetErrorsList } from '@/models/error';

const overviewData = {
  state: {
    errorListLoading: false
  },
  mutations: {
    SET_ERROR_OVIER_LIST_LOADING (state, data) {
      state.errorListLoading = data
    }
  },
  actions: {
    updateErrorOvierDataList ({ commit }, params) {
      console.log(params)
      commit('SET_ERROR_OVIER_LIST_LOADING', params)
    }
  }
}

export default overviewData
