
<style lang='scss' scoped>
@import "./triplayer.scss";
@import "./../list/list.scss";
</style>
<template>
  <div class="triplayer">
    <Modal v-model="tripModel" width="880" footer-hide>
        <div class="triplayer-title">航程详情</div>
        <div class="triplayer-wrap">
          <div class="triplayer-wrap-line">
            <span class="triplayer-wrap-line-departStopColor">启程</span>
            <span  class="triplayer-wrap-line-white">{{airpotObj.dptTime}}</span>
            <span class="fr">{{airpotObj.flightTimes}}</span>
          </div>
          <div class="triplayer-wrap-cont">
            <div class="triplayer-wrap-cont-info">
              <div class="triplayer-wrap-cont-info-ft">
                <span>
                  <b>2h 10m</b>
                </span>
              </div>
              <h4>
                <span class="st">
                  <i class="iconfont icon-travel-setsail"></i>
                  <b>{{airpotObj.dptTime}}</b>
                </span>
                <span class="flight-info-text">北京</span>
                <b> {{airpotObj.dptAirport}}</b>
                <b> {{airpotObj.dptTerminal}}</b>
              </h4>

              <div class="triplayer-wrap-cont-info-airport">
                <span>
                  <img src="https://content.igola.com/static/APP/images/airline_logo/1x/36x36/fm.png">
                </span>
                <span>
                  上海航空 | {{airpotObj.actFlightNum}} | 机型 : {{airpotObj.planetype}}
                </span>
              </div>

              <h4>
                <span class="st">
                  <i class="iconfont icon-travel-setsail"></i>
                  <b>{{airpotObj.arrTime}}</b>
                </span>
                <span class="flight-info-text">上海</span>
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
          <div class="triplayer-book">
            <ul>
              <li>
                <div class="triplayer-book-img fl">
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
                </div>
              </li>
            </ul>
          </div>
          <div class="triplayer-footer">
            易道是一家国际机票搜索引擎，辅助订购为您提供便捷订购通道，但是出票和退改签服务仍由第三方供应商提供
          </div>
        </div>
    </Modal>
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['airport_time'])
  },
  data () {
    return {
      tripModel: false,
      tripBuy: false,
      airpotObj: {}
    }
  },
  components: {

  },
  watch: {
    airport_time: {
      handler: function (val, oldVal) {
        if(val&&JSON.stringify(val) !==""){
          debugger
          this.airpotObj = val;
        }
      },
      immediate: true
    }
  },
  methods: {

  },
  mounted () {
    this.$bus.on('on-airdetail', (data) => {
      this.tripModel = true;
      this.tripBuy = data;
    })
  }
}
</script>
