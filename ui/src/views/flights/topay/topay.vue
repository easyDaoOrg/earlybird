
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
      <bookingStep :current="topayCurrent"></bookingStep>
      <!--支付-->
      <div class="topay-content">
        <div class="topay-content-tips">
          <div class="topay-content-tips-title">您的订单已提交成功！</div>
          <div class="topay-content-tips-content">
            感谢您从易道旅行进行订购！订单剩余可以支付时间 <span>{{timerTimer}}</span>，请尽快完成付款，逾期将自动取消。若所选供应商出票失败，系统将以相同价格为您在别的供应商尽力出票。仍若失败，将有工作人员联系退款。您的预定号码是：<b>{{routerObj.orderNo}}</b>，请妥善保存。
          </div>
        </div>

        <div class="topay-content-pay">
          <div class="topay-content-pay-title">付款方式</div>
          <div class="topay-content-pay-list">
            <ul>
              <li :class="{'active': topayActive === 'alipay'}" @click="getPayewm('alipay')"><img src="../../../assets/images/logo-alipay.png" width="120"></li>
              <li :class="{'active': topayActive === 'wxpay'}" @click="getPayewm('wxpay')"><img src="../../../assets/images/logo-wxpay.png" width="160" height="42"></li>
            </ul>
          </div>
          <div class="topay-content-pay-ewm" v-if="topayEwm">
            <!-- <img src="../../../assets/images/logo-alipay-pay.png"> -->
            <iframe :src="payUrl" width="70%" height="400" frameborder="0" scrolling="no" ></iframe>
          </div>
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

    <div class="topay-footer">
      <div class="topay-footer-con">
        <Button type="warning" @click="getPayewm('is_paid')" v-if="topayEnd">付款</Button>
        <Button type="warning" v-if="!topayEnd" @click="toPayment()">是的，已完成付款</Button>
        <router-link :to="{path:'/flights/booking'}">
        <Button v-if="!topayEnd">返回</Button>
        </router-link>
        <Checkbox v-model="single">
          已阅读并同意
          <span @click="modelClause = true">易道服务条款</span>
        </Checkbox>
      </div>
    </div>

    <Modal v-model="modelClause" footer-hide>
      <div class="booking-order-clause">
        <div class="booking-order-clause-title">法律声明</div>
        <div class="booking-order-clause-content">
          <p>易道是一家互联网信息服务提供商。</p>
        </div>
      </div>
    </Modal>
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
      routerObj: this.$route.query,
      infoObj: {},
      cabinType: null,
      single: true,
      timerTimer: "00:00",
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
    onPlaceOrrder() {},
    // 获取付款二维码
    getPayewm (str) {
      this.topayActive = str;
      let url = this.baseUrl + `/pay/`+str
      let self = this
      this.axios
        .post(url, {orderNo: self.routerObj.orderNo})
        .then(data => {
          console.log(data)
          if(data.status == 200){
            if(str === 'is_paid'&&data.data){
              self.topayEnd = false;
              this.$Message.success('付款成功!')
            }else{
              self.payUrl = data.data;
              self.topayEwm = true;
            }
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 完成订单
    toPayment(){
      this.$router.push({
        path: `/flights/payment`,
        query: {
          dptCity: this.routerObj.dptCity,
          arrCity: this.routerObj.arrCity,
          date: this.routerObj.date,
          cabinType: this.routerObj.cabinType,
          price: this.routerObj.price,
          totalMoney: this.routerObj.totalMoney,
          carrier: this.routerObj.carrier,
          orderNo: this.routerObj.orderNo
        }
      })
    },
    /**
     * 设置订购信息
     */
    setOrderingInformation() {
      this.infoObj = {
        price: this.routerObj.price,
        number: this.routerObj.number,
        totalMoney: this.routerObj.totalMoney,
        carrier: this.routerObj.carrier
      };
      this.cabinType = Number(this.routerObj.cabinType);
    },
    //单纯分钟和秒倒计时
    resetTime(time) {
      let m = 19;  //分
      let s = 59;  //秒
      let self = this;
      getCountdown();
      setInterval(function(){ getCountdown() },1000);
      function getCountdown (){
        if( m == 0 && s == 0 ){
            m = "00";
            s = "00";
        }else if( m >= 0 ){
            if( s > 0 ){
              s--;
              if(s < 10){
                s = '0' + s
              }
            }else if( s == 0 ){
              m--;
              s = 59;
            }
        }
        self.timerTimer =  m + ":" + s;
      }
    }
  },
  mounted() {
    this.setOrderingInformation();
    this.resetTime(1201)
  }
};
</script>
