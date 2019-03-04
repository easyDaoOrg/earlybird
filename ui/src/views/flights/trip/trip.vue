
<style lang='scss' scoped>
@import "./trip.scss";
</style>
<template>
  <div class="flights-index-search-input" :class="{timelineActive: searchClass}">
    <div class="flights-index-airport clearfix">
        <div class="flights-index-search-input-left fl">
        <div class="flights-index-search-input-left-change">
          <i class="iconfont icon-travel-return" @click="changeTravelData()"></i>
        </div>
        <div class="fl col-10">
          <city :cityObj="startObj"></city>
        </div>
        <div class="fl col-10">
          <city :cityObj="endObj"></city>
        </div>
      </div>
      <div class="flights-index-search-input-right fl">
          <date :tabIndex="tabIndex" :dateObj="dateObj"></date>

          <!-- <div class="flights-index-search-input-city flights-index-begin fl ">
            前后3天
          </div> -->

          <div class="flights-index-search-input-button fl" @click="searchFlights(tabIndex)">
            <Icon type="ios-search" />
          </div>
      </div>
    </div>
  </div>

</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import city from './city'
import date from './date'

export default {
  props: ['tabIndex', 'searchClass'],
  computed: {
    // ...mapGetters(['history_list'])
  },
  data () {
    return {
      startObj: {
        name: '出发',
        className: 'icon-travel-setsail',
        foat: 'fl',
        classInput: '',
        citySelected: '',
        cityName: '',
        cityCode: ''
      },
      endObj: {
        name: '目的地',
        className: 'icon-travel-jiangla',
        foat: 'fr',
        classInput: 'flights-index-org',
        citySelected: '',
        cityName: '',
        cityCode: ''
      },
      dateObj: {
        start: '',
        end: ''
      },
      tripObj: {},
      spaceType: '经济舱',
      saveStartObj: {},
      saveEndObj: {}
    }
  },
  components: {
    city,
    date
  },
  watch: {
    $route: {
      handler: function (val, oldVal) {
        this.initData();
      },
      immediate: true
    }
  },
  methods: {
    ...mapActions(['setAirportFilterData']),
    ...mapActions(['searchAirportListData']),
    // 初始化
    initData () {
      let data = this.$route.query;
      if(data&&JSON.stringify(data) !== '{}'){
        this.startObj.citySelected = data.dptCity + '(' + data.dpt + ')';
        this.startObj.cityName = data.dptCity;
        this.startObj.cityCode = data.dpt;
        this.endObj.citySelected = data.arrCity + '(' + data.arr + ')';
        this.endObj.cityName = data.arrCity;
        this.endObj.cityCode = data.arr;
        this.dateObj.start = data.date;
        this.postAirportSearchData();
      }
    },
    searchFlights () {
      if (this.startObj.citySelected && this.endObj.citySelected && this.dateObj.start) {
        // 向上传参
        this.tripObj = {
          cityStart: this.startObj.cityName ? this.startObj.cityName : '',
          cityStartCode: this.startObj.cityCode ? this.startObj.cityCode : '',
          cityEnd: this.endObj.cityName ? this.endObj.cityName : '',
          cityEndCode: this.endObj.cityCode ? this.endObj.cityCode : '',
          cityDate: this.dateObj,
          tripType: this.tabIndex,
          spaceType: this.spaceType
        }

        // 存储搜索信息
        this.setAirportFilterData(this.tripObj)

        // 搜索航班信息
        this.postAirportSearchData()
      }
      console.log(this.tabIndex, '111', this.startObj.citySelected, '222', this.endObj.citySelected, '333', this.dateObj.start, this.dateObj.end)
    },
    // 搜索航班信息
    postAirportSearchData () {
      let airportData = {
        dpt: this.startObj.cityCode,
        arr: this.endObj.cityCode,
        date: this.dateObj.start
      }
      let url = this.baseUrl + `/flight/search`
      let self = this
      this.axios
        .post(url, airportData)
        .then(data => {
          // 存储搜索结果
          self.searchAirportListData(data)
          // 路由跳转
          if(self.$route.name !== 'timeline'){
            self.$router.push({
              path: `/flights/timeline`,
              query: {
                dptCity: self.startObj.cityName,
                dpt: self.startObj.cityCode,
                arrCity: self.endObj.cityName,
                arr: self.endObj.cityCode,
                date: self.dateObj.start
              }
            })
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 切换
    changeTravelData () {
      this.saveStartObj = JSON.parse(JSON.stringify(this.startObj))
      this.saveEndObj = JSON.parse(JSON.stringify(this.endObj))
      this.startObj.citySelected = this.saveEndObj.citySelected
      this.startObj.cityName = this.saveEndObj.cityName
      this.startObj.cityCode = this.saveEndObj.cityCode
      this.endObj.citySelected = this.saveStartObj.citySelected
      this.endObj.cityName = this.saveStartObj.cityName
      this.endObj.cityCode = this.saveStartObj.cityCode
    }
  },
  mounted () {
    this.initData();
    this.$bus.on('trip-airport-msg', (data) => {
      this.spaceType = data
    })
  }
}
</script>
