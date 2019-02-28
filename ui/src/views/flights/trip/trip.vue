
<style lang='scss' scoped>
@import "./trip.scss";
</style>
<template>
  <div class="flights-index-search-input" :class="{timelineActive: searchClass}">
    <div class="flights-index-airport clearfix">
        <div class="flights-index-search-input-left fl">
        <div class="flights-index-search-input-left-change">
          <i class="iconfont icon-travel-return"></i>
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

          <div class="flights-index-search-input-city flights-index-begin fl ">
            前后3天
          </div>

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
      spaceType: "经济舱"
    }
  },
  components: {
    city,
    date
  },
  watch: {

  },
  methods: {
    ...mapActions(['setAirportFilterData']),

    searchFlights(){
      if(this.startObj.citySelected && this.endObj.citySelected && this.dateObj.start){
        //向上传参
        this.tripObj = {
          cityStart: this.startObj.cityName ? this.startObj.cityName : "",
          cityStartCode: this.startObj.cityCode ? this.startObj.cityCode : "",
          cityEnd: this.endObj.cityName ? this.endObj.cityName : "",
          cityEndCoce: this.endObj.cityCode ? this.endObj.cityCode : "",
          cityDate: this.dateObj,
          tripType: this.tabIndex,
          spaceType: this.spaceType
        };

        this.setAirportFilterData(this.tripObj)

        //路由跳转
        if(this.tabIndex == 0 && this.dateObj.end){
          this.$router.push(`/flights/timeline`);
        }else{
          this.$router.push(`/flights/timeline`);
        };
      }

      console.log(this.tabIndex,'111',this.startObj.citySelected,'222',this.endObj.citySelected,'333',this.dateObj.start,this.dateObj.end)
    },
    getMsgFromChild (data) {
      console.log(data)
    }
  },
  mounted () {
    this.$bus.on('trip-airport-msg', (data) => {
      this.spaceType = data;
    })
  }
}
</script>
