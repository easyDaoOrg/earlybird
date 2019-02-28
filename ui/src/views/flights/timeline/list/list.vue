
<style lang='scss' scoped>
@import "./list.scss";
</style>
<style>
  .timeline-list .ivu-card-body{
    padding: 0;
  }
  .timeline-list-card .ivu-card-bordered{
    border: 0 none;
    border-radius: 0;
  }
  .timeline-list .ivu-scroll-wrapper,
  .timeline-list .ivu-scroll-container{
    width: 960px;
  }
</style>


<template>
  <div class="timeline-list">
    <Scroll  height="1000">
        <Card dis-hover v-for="(item, index) in airportList" :key="index">
            <div class="timeline-list-card">
              <div class="timeline-list-card-header">
                <div class="timeline-list-card-header-tips">{{item.flightTypeFullName}} , 机型: {{item.planetype}}</div>
                <!-- <div class="timeline-list-card-header-score fr">
                  <i class="iconfont icon-travel-zan"></i>
                  <b>9.9</b>
                </div> -->
              </div>
              <div class="timeline-list-card-body" :class="{'backlist' : tripBoolean}">
                <div class="timeline-list-card-body-box">
                  <span><img src="https://content.igola.com/static/APP/images/airline_logo/1x/24x24/hu.png"></span>
                </div>
                <div class="timeline-list-card-body-sted">
                  <div class="timeline-list-card-body-sted-block">
                    <div class="timeline-list-card-body-sted-block-time">
                      <i class="iconfont icon-travel-setsail"></i>
                      <b>{{item.dptTime}}</b>
                    </div>
                    <div class="timeline-list-card-body-sted-block-address">
                      {{item.dptAirport}} <b>{{item.dptTerminal}}</b>
                    </div>
                  </div>
                  <div class="timeline-list-card-body-sted-mid">
                    <div class="timeline-list-card-body-sted-mid-change">

                    </div>
                    <div class="timeline-list-card-body-sted-mid-alltime">

                    </div>
                    <div class="timeline-list-card-body-sted-mid-stopover">

                    </div>
                  </div>
                  <div class="timeline-list-card-body-sted-block">
                    <div class="timeline-list-card-body-sted-block-time">
                      <i class="iconfont icon-travel-jiangla"></i>
                      <b>{{item.arrTime}}</b>
                    </div>
                    <div class="timeline-list-card-body-sted-block-address">
                      {{item.arrAirport}} <b>{{item.arrTerminal}}</b>
                    </div>
                  </div>
                </div>
                <div class="timeline-list-card-duration">
                  <i class="iconfont icon-travel-clock"></i>
                  <small>{{item.flightTimes}}</small>
                </div>
              </div>
              <div class="timeline-list-card-footer">
                <div class="timeline-list-card-footer-chosen fr">
                  <!-- <Button type="primary" v-if="!tripBoolean"  @click="goTripModel()">选择去程</Button>
                  <Button  type="warning" v-if="tripBoolean"  @click="backTripModel(true)">选择返程</Button> -->
                  <Button  type="primary" @click="backTripModel(true)">选择去程</Button>
                </div>
                <div class="timeline-list-card-footer-price fr">
                  <p>
                    <b>¥</b>{{item.barePrice}}
                  </p>
                  <span>销售价（不含税）</span>
                </div>
                <div class="timeline-list-card-footer-box">
                  <Button @click="airDetailModel(item)">航程详情</Button>
                </div>

              </div>
            </div>
        </Card>
    </Scroll>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import { airportListData } from "../../../../assets/json/airportListData.js";


export default {
  computed: {
    ...mapGetters(['airport_list'])
  },
  data () {
    return {
      tripBoolean: false,
      airportList: []
    }
  },
  components: {

  },
  watch: {
    airport_list: {
      handler: function (val, oldVal) {
        if(val){
          this.initData(JSON.parse(JSON.stringify(val)))
        }
      },
      immediate: true
    }
  },
  methods: {
    ...mapActions(['chooseAirportTimeData']),
    //初始化
    initData(data){
      // this.airportList = data;
      this.airportList = airportListData.flightInfos
      console.log(airportListData)
    },
    airDetailModel(data){
      this.chooseAirportTimeData(data);
      this.$bus.emit('on-airdetail',false)
    },
    goTripModel() {
      this.tripBoolean = true;
      this.$bus.emit('on-triplist',1,true)
    },
    backTripModel(bool) {
      this.$bus.emit('on-airdetail',bool)
    },
  },
  mounted () {
    this.$bus.on('on-filtergoback',(bool) => {
      this.tripBoolean = bool;
    })
  }
}
</script>
