
<style lang='scss' scoped>
@import "./topay.scss";
</style>
<style>
.topay-footer .ivu-btn-warning {
  margin-right: 10px;
  padding: 7px 30px;
}
.topay-footer .ivu-checkbox-wrapper {
  font-size: 14px;
}

.booking-left .ivu-steps-item.ivu-steps-status-finish .ivu-steps-head-inner{
  color: #fff;
  background: #2d8cf0;
}
.booking-left .ivu-steps .ivu-steps-head-inner>.ivu-steps-icon.ivu-icon{
  color: #fff;
}
</style>

<template>
  <div class="booking">
    <div class="booking-left fl">
      <bookingStep :current="2"></bookingStep>
      <!--支付-->
      <div class="topay-content">
        <div class="topay-content-payment">
          已完成订单
        </div>

        <div class="topay-content-bot">
          易道是一家国际机票搜索引擎，辅助订购为您提供便捷订购通道，但是出票和退改签服务仍由第三方供应商提供
        </div>
      </div>
    </div>

    <div class="booking-right">
       <bookingOrder
        parentName='topay'

        :dptCity='routerObj.dptCity'
        :arrCity='routerObj.arrCity'
        :date='routerObj.date'

        :price='infoObj.price'
        :number='infoObj.number'
        :totalMoney='infoObj.totalMoney'

        :carrier='infoObj.carrier'
        :cabinType='cabinType'
        @on-place-order="onPlaceOrrder()"
      ></bookingOrder>
    </div>
  </div>
</template>

<script>
import bookingStep from "./../booking/bookingstep/bookingstep";
import bookingOrder from "./../booking/bookingOrder/bookingOrder";
export default {
  data() {
    return {
      passengers: [],
      modelClause: false,
      topayActive: false,
      topayEwm: false,
      topayEnd: true,
      // 右侧订单
      routerObj: {},
      infoObj: {},
      cabinType: null,
      single: true,
      timerTimer: "00:00",
      orderNoStr: "",
      topayCurrent: 1,
      payUrl: ""
    };
  },
  components: {
    bookingStep,
    bookingOrder
  },
  watch: {},
  methods: {
    /**
     * 设置订购信息
     */
    setOrderingInformation() {
      let router = this.$route.query;
      this.routerObj = {
        dptCity: router.dptCity,
        arrCity: router.arrCity,
        date: router.date
      };
      this.infoObj = {
        price: router.price,
        number: router.number,
        totalMoney: router.totalMoney,
        carrier: router.carrier
      };
      this.orderNoStr = router.orderNo
      this.cabinType = Number(router.cabinType);
    }
  },
  mounted() {
    this.setOrderingInformation();
  }
};
</script>
