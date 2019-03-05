
<style lang='scss' scoped>
@import "./topay.scss";
</style>
<style>
.topay-footer .ivu-btn-warning{
  margin-right: 10px;
  padding: 7px 30px
}
.topay-footer .ivu-checkbox-wrapper{
  font-size: 14px;
}
</style>


<template>
  <div class="booking">
    <div class="booking-left fl">
      <bookingStep></bookingStep>
      <!--支付-->
      <div class="topay-content">
        <div class="topay-content-tips">
          <div class="topay-content-tips-title">您的订单已提交成功！</div>
          <div class="topay-content-tips-content">
            感谢您从易道旅行进行订购！订单剩余可以支付时间 <span>20:00</span>，请尽快完成付款，逾期将自动取消。若所选供应商出票失败，系统将以相同价格为您在别的供应商尽力出票。仍若失败，将有工作人员联系退款。您的预定号码是：<b>569843984950671</b>，请妥善保存。
          </div>
        </div>

        <div class="topay-content-pay">
          <div class="topay-content-pay-title">付款方式</div>
          <div class="topay-content-pay-list">
            <ul>
              <li :class="{'active': topayActive}"><img src="../../../assets/images/logo-alipay.png"></li>
            </ul>
          </div>
          <div class="topay-content-pay-ewm" v-if="topayEwm">
            <img src="../../../assets/images/logo-alipay-pay.png">
          </div>
        </div>

        <div class="topay-content-bot">
          易道是一家国际机票搜索引擎，辅助订购为您提供便捷订购通道，但是出票和退改签服务仍由第三方供应商提供
        </div>
      </div>
    </div>

    <div class="booking-right">
      <bookingOrder @on-place-rder="onPlaceRrder()"></bookingOrder>
    </div>

    <div class="topay-footer">
      <div class="topay-footer-con">
        <Button type="warning" @click="topay()" v-if="topayEnd">付款</Button>
        <Button type="warning" v-if="!topayEnd">是的，已完成付款</Button>
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
import bookingStep from './../booking/bookingstep/bookingstep'
import bookingOrder from './../booking/bookingOrder/bookingOrder'
import { mapGetters } from 'vuex'
export default {
  data () {
    return {
      passengers: [],
      modelClause: false,
      topayActive: true,
      topayEwm: false,
      topayEnd: true
    }
  },
  components: {
    bookingStep,
    bookingOrder
  },
  watch: {

  },
  methods: {
    onPlaceRrder(){

    },
    //付款
    topay(){
      this.topayEwm = true;
      this.topayEnd = false;
    }
  },
  mounted () {

  }
}
</script>
