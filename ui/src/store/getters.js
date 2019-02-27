const getters = {
  errorListLoading: state => state.overviewerrors.errorListLoading,
  filter_D: state => state.hotel.filter_D,
  history_list: state => state.airport.history_list,
  airport_group: state => state.airport.airport_group
}
export default getters
