
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
  .timeline-list-card-footer-chosen .ivu-btn,
  .timeline-list-card-footer-box .ivu-btn{
    border-radius: 40px;
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
                  <span v-if="item.com == '南方航空'"><img src="../../../../assets/images/airportIcon/cz.png"></span>
                  <span v-if="item.com == '春秋航空'"><img src="../../../../assets/images/airportIcon/9c.png"></span>
                  <span v-if="item.com == '中国航空'"><img src="../../../../assets/images/airportIcon/cz.png"></span>
                  <span v-if="item.com == '吉祥航空'"><img src="../../../../assets/images/airportIcon/ho.png"></span>
                  <span v-if="item.com == '海南航空'"><img src="../../../../assets/images/airportIcon/hu.png"></span>
                  <span v-if="item.com == '上海航空'"><img src="../../../../assets/images/airportIcon/fm.png"></span>
                  <span v-if="item.com == '东方航空'"><img src="../../../../assets/images/airportIcon/mu.png"></span>
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
                  <Button  type="primary" @click="backTripModel(item,true)">选择去程</Button>
                </div>
                <div class="timeline-list-card-footer-price fr">
                  <p>
                    <b>¥</b>{{item.barePrice}}
                  </p>
                  <span>销售价（不含税）</span>
                </div>
                <div class="timeline-list-card-footer-box">
                  <Button @click="airDetailModel(item,false)">航程详情</Button>
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
      airportList: [],
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
      immediate: true,
    }
  },
  methods: {
    ...mapActions(['chooseAirportTimeData']),
    //初始化
    initData(data){
      console.log(data,"&&&&&&&&&&&&&")
      if(data&&data.data&&data.data.flightInfos){
        this.airportList = data.data.flightInfos;
        console.log(this.airportList)
      }
      // this.airportList = airportListData.flightInfos;

    },
    airDetailModel(data,bool){
      this.chooseAirportTimeData(data);
      this.$bus.emit('on-airdetail',{type: bool, item: data})
    },
    goTripModel() {
      this.tripBoolean = true;
      this.$bus.emit('on-triplist',1,true)
    },
    backTripModel(data,bool) {
      this.chooseAirportTimeData(data);
      this.$bus.emit('on-airdetail',{type: bool, item: data})
    },
    //判断类型
    saveSortAirportList(data){
      switch (data.label){
        case 'price':
          //价格
          if(data.upselected){
            this.airportList = this.airportList.sort(this.compareUpPrice('barePrice'))
          }
          if(data.dwselected){
            this.airportList = this.airportList.sort(this.compareDwPrice('barePrice'))
          }
          break;
        case 'longtime':
          //去除时间冒号
          this.decideTimeList('flightTimes','longTime');
          //飞行时长
          if(data.upselected){
            this.airportList = this.airportList.sort(this.compareUpPrice('longTime'))
          }
          if(data.dwselected){
            this.airportList = this.airportList.sort(this.compareDwPrice('longTime'))
          }
          break;
        case 'starttime':
          //去除时间冒号
          this.decideTimeList('dptTime','startTime');
          //开始时间
          if(data.upselected){
            this.airportList = this.airportList.sort(this.compareUpPrice('startTime'))
          }
          if(data.dwselected){
            this.airportList = this.airportList.sort(this.compareDwPrice('startTime'))
          }
          break;
        case 'endtime':
          this.decideTimeList('arrTime','endTime');
          //结束时间
          if(data.upselected){
            this.airportList = this.airportList.sort(this.compareUpPrice('endTime'))
          }
          if(data.dwselected){
            this.airportList = this.airportList.sort(this.compareDwPrice('endTime'))
          }
          break;
      }
    },
    decideTimeList(key,time){
      if(this.airportList.length > 0){
        for(let item of this.airportList){
          if(key == 'flightTimes'){
            item[time] = item[key].replace("小时","");
            item[time] = parseInt(item[time].replace("分钟",""))
          }else{
            item[time] = parseInt(item[key].replace(":",""))
          }
        }
      }
      console.log(this.airportList)
    },
    //过滤价格
    compareUpPrice(property){
        return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return value1 - value2;
        }
    },
    compareDwPrice(property){
        return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return value2 - value1;
        }
    }
  },
  mounted () {
    this.$bus.on('on-filtergoback',(bool) => {
      this.tripBoolean = bool;
    });
    this.$bus.on('trip-airport-list', (data) => {
      this.saveSortAirportList(data)
    })
  }
}
</script>
