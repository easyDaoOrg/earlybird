
<style lang='scss' scoped>
@import "./triplayer.scss";
@import "./../list/list.scss";
</style>
<style>
.ivu-btn-warning{
  border-radius: 40px;
}
</style>

<template>
  <div class="triplayer">
    <Modal v-model="tripModel" width="880" footer-hide>
        <div class="triplayer-title">航程详情</div>
        <div class="triplayer-wrap">
          <div class="triplayer-wrap-line">
            <span class="triplayer-wrap-line-departStopColor">启程</span>
            <span  class="triplayer-wrap-line-white">{{airpotTrip.date}},{{airpotObj.dptTime}}</span>
            <span class="fr">{{airpotObj.flightTimes}}</span>
          </div>
          <div class="triplayer-wrap-cont">
            <div class="triplayer-wrap-cont-info">
              <div class="triplayer-wrap-cont-info-ft">
                <span>
                  <b>{{airpotObj.flightTimes}}</b>
                </span>
              </div>
              <h4>
                <span class="st">
                  <i class="iconfont icon-travel-setsail"></i>
                  <b>{{airpotObj.dptTime}}</b>
                </span>
                <span class="flight-info-text">{{airpotTrip.dptCity}}</span>
                <b> {{airpotObj.dptAirport}}</b>
                <b> {{airpotObj.dptTerminal}}</b>
              </h4>

              <div class="triplayer-wrap-cont-info-airport">
                  <airportPic :carrier="airpotObj.carrier" :airportTitle="true"></airportPic>
                <span>
                  {{airpotObj.com}} | {{airpotObj.actFlightNum}} | 机型 : {{airpotObj.planetype}}
                </span>
              </div>

              <h4>
                <span class="st">
                  <i class="iconfont icon-travel-setsail"></i>
                  <b>{{airpotObj.arrTime}}</b>
                </span>
                <span class="flight-info-text">{{airpotTrip.arrCity}}</span>
                <b> {{airpotObj.arrAirport}}</b>
                <b> {{airpotObj.arrTerminal}}</b>
              </h4>

            </div>
          </div>
        </div>

        <div v-if="tripBuy">
          <div class="triplayer-guide">
            选择供应商进行订购
          </div>
          <div class="triplayer-loading" v-if="!airportPriceObj.vendors"><Spin size="large" fix></Spin></div>
          <div class="triplayer-none" v-if="airportPriceObj.vendors&&airportPriceObj.vendors.length==0">抱歉未找到供应商</div>
          <div class="triplayer-book" v-if="airportPriceObj.vendors&&airportPriceObj.vendors.length>0">
            <ol>
              <li>票面价</li>
              <li>销售特价</li>
              <li>儿童价</li>
              <!-- <li>婴儿价</li> -->
              <li>折扣</li>
              <li>剩余座位数</li>
              <li>舱位等级</li>
              <li>&nbsp;</li>
            </ol>
            <div class="clear">
              <ul v-for="(item, index) in airportPriceObj.vendors" :key="index">
                <li><b>¥</b>{{item.vppr}}</li>
                <li><b>¥</b>{{item.barePrice}}</li>
                <li><b>¥</b>{{item.chdBarePrice}}</li>
                <!-- <li><b>¥</b>{{item.babyBarePrice}}</li> -->
                <li>{{item.discount}}</li>
                <li>
                  <span v-if="item.cabinCount == 'A'">有票</span>
                  <span v-if="item.cabinCount !== 'A' && isNaN(item.cabinCount)==true">无</span>
                  <span v-if="isNaN(item.cabinCount)==false">{{item.cabinCount}}</span>
                </li>
                <li>
                  <span v-if="item.cabinType == 0">经济舱</span>
                  <span v-if="item.cabinType == 1">头等舱</span>
                  <span v-if="item.cabinType == 2">商务舱</span>
                  <span v-if="item.cabinType == 3">经济舱精选</span>
                  <span v-if="item.cabinType == 4">经济舱y舱</span>
                  <span v-if="item.cabinType == 5">超值头等舱</span>
                </li>
                <li>
                  <Button type="warning" @click="buyTripBook(item)">帮我买</Button>
                </li>
              </ul>
            </div>

            <!-- <div class="triplayer-book-img fl">
              <img src="https://content.igola.com/static/APP/images/ota_logo/1x/ota_justtravel@1x.png" alt="ota logo">
            </div>
            <div class="triplayer-book-policy fl">行程单，极速退单</div>
            <div class="triplayer-book-policy fl">退改规则</div>
            <div class="timeline-list-card-footer-chosen fr">
              <router-link :to="{path:'/flights/booking'}">
              <Button type="warning">帮我买</Button>
              </router-link>
            </div>
            <div class="timeline-list-card-footer-price fr">
              <p><b>£</b>144</p> <span data-v-b7b3a472="">含税总价</span>
            </div> -->

          </div>
          <div class="triplayer-footer">
            易道是一家国际机票搜索引擎，辅助订购为您提供便捷订购通道，但是出票和退改签服务仍由第三方供应商提供
          </div>
        </div>
    </Modal>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import { airportPriceData } from '../../../../assets/json/airportPriceData.js'
import { airportBookData } from '../../../../assets/json/airportBookData.js'
import airportPic from '../../airportpic/airportpic'

export default {
  computed: {
    ...mapGetters(['airport_time']),
    ...mapGetters(['airport_group'])
  },
  data () {
    return {
      tripModel: false,
      tripBuy: false,
      airpotObj: {},
      airpotTrip: {
        dptCity: this.$route.query.dptCity,
        dpt: this.$route.query.dpt,
        arrCity: this.$route.query.arrCity,
        arr: this.$route.query.arr,
        date: this.$route.query.date
      },
      airportPriceObj: {},
      saveFlightNum: null,
      savePlaneType: null
    }
  },
  components: {
    airportPic
  },
  watch: {
    airport_time: {
      handler: function (val, oldVal) {
        if (val && JSON.stringify(val) !== '') {
          this.airpotObj = val
        }
      },
      immediate: true
    }
  },
  methods: {
    // 查询指定航班价格
    getAirportPrice () {
      let item = this.airpotTrip
      let airportData = {
        dpt: item.dpt,
        arr: item.arr,
        date: item.date,
        flightNum: this.saveFlightNum
      }
      let url = this.baseUrl + `/flight/price`
      let self = this
      this.axios
        .post(url, airportData)
        .then(data => {
          if (data.status == 200) {
            console.log(data, '???????')
            self.airportPriceObj = data.data
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 预定指定渠道的航班
    buyTripBook (item) {
      let airObj = this.airpotTrip
      this.tripModel = false
      this.$router.push({
        path: `/flights/booking`,
        query: {
          dpt: airObj.dpt,
          arr: airObj.arr,
          flightNum: this.saveFlightNum,
          planeType: this.savePlaneType,
          date: airObj.date,
          // adult: this.airport_group.bigvalue,
          // child: this.airport_group.childvalue,
          cabinType: item.cabinType,
          vppr: item.vppr
          // dptCity: airObj.dptCity,
          // arrCity: airObj.arrCity
          // cid: item.cid
        }
      })
    }
  },
  mounted () {
    this.$bus.on('on-airdetail', (data) => {
      this.tripModel = true
      this.tripBuy = data.type
      if (data.type) {
        this.saveFlightNum = data.item.flightNum
        this.savePlaneType = data.item.planetype
        this.getAirportPrice()
      }
    })
  }
}
</script>
