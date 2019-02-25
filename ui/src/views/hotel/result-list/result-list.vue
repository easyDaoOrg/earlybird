
<style lang='scss' scoped>
@import "./result-list.scss";
</style>
<template>
  <div class="resultList">
     <Affix :offset-top="0">
        <div class='resultList-header'>
          <div class="list-header-inner">
            <source-panel
              type='result-list'
            ></source-panel>
          </div>
        </div>
    </Affix>
    <!-- 内容 -->
    <div class='resultList-content'>
      <list-page v-if='pattern' @on-map-pattern='onMapPattern' :filterData='filterData'></list-page>
      <map-pattern v-if='!pattern' @on-list-pattern='onMapPattern' :filterData='filterData'></map-pattern>
    </div>
  </div>
</template>

<script>
import listPage from './list-page/list-page'
import mapPattern from './map-pattern/map-pattern'
import sourcePanel from '../source-panel/source-panel'
import axios from 'axios/dist/axios.min'
export default {
  components: {
    sourcePanel, listPage, mapPattern
  },
  data () {
    return {
      pattern: true,
      filterData: {}
    }
  },
  mounted () {
    this.getPositionData()
  },
  methods: {
    onMapPattern (data) {
      this.pattern = data
    },
    getPositionData () {
      let self = this
      axios
        .get(
          'https://www.igola.com/web-gateway/api-hotel-static/geo/pois?city=771&lan=ZH'
        )
        .then(data => {
          self.filterData = data.data.data
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>
