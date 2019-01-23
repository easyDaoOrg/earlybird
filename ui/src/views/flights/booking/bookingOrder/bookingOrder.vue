
<style lang='scss' scoped>
@import "./bookingOrder.scss";
</style>
<template>
  <div class="booking-order">
    <div class="booking-order-title">订购信息</div>
    <div class="booking-order-summary">
      <div class="booking-order-summary-top clearfix">
        <div class="booking-order-summary-top-info">
          <div class="booking-order-summary-top-info-icon">
            <i class="iconfont icon-travel-find"></i>
          </div>
          <div class="booking-order-summary-top-info-and">
            <span class="fr">02月04日, 周一</span>
            <span>北京</span>
          </div>
        </div>
        <div class="booking-order-summary-top-info booking-order-orange">
          <div class="booking-order-summary-top-info-icon">
            <i class="iconfont icon-travel-find"></i>
          </div>
          <div class="booking-order-summary-top-info-and">
            <span class="fr">02月04日, 周一</span>
            <span>上海</span>
          </div>
        </div>
      </div>
      <div class="booking-order-summary-bot">
        <span class="fr">经济舱</span>
        <Button shape="circle">航程详情</Button>
      </div>
    </div>
    <div class="booking-order-price">
      <div class="booking-order-price-exchange">
        <span>兑换后约为： £ 110.67 </span>（此金额为估算值，易道订单以人民币交易，接受外币支付，成交金额需按实际交易汇率结算。）
      </div>
    </div>
    <div class="booking-order-total clearfix">
      <h4>¥ 966</h4>
      <p>(≈  £ 110.67)</p>
      <b>订单总价</b>
    </div>
    <div class="booking-order-list clearfix">
      <ul>
        <li><span class="fr">¥ 866.00</span>旅客 x 2</li>
        <li><span class="fr">¥ 100.00</span>税费</li>
      </ul>
    </div>
    <div class="booking-order-rice clearfix">
      <p><span>飞行米</span></p>
      <p>搭乘航班后奖<span>13.36</span>个.<b>首次支付奖10个</b></p>
    </div>
    <div class="booking-order-firm">
      <!-- <div class="booking-order-firm-gz">退改签规则</div> -->
      <div class="booking-order-firm-gz">
        <Checkbox v-model="single">已阅读并同意<span @click="modelClause = true">易道服务条款</span></Checkbox>
      </div>
      <router-link :to="{path:'/member/user/my-booking'}">
        <Button type="primary" shape="circle">提交订单</Button>
      </router-link>
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


export default {
  data () {
    return {
      single: '',
      modelClause: false
    }
  },
  components: {

  },
  watch: {

  },
  methods: {

  },
  mounted () {}
}
</script>
