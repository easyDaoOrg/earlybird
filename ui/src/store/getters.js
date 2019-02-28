const getters = {
  errorListLoading: state => state.overviewerrors.errorListLoading,
  filter_D: state => state.hotel.filter_D,
  history_list: state => state.airport.history_list,
  airport_group: state => state.airport.airport_group,
  airport_list: state => state.airport.airport_list,
  airport_time: state => state.airport.airport_time
}
export default getters
