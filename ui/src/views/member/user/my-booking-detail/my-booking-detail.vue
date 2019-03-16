
<style lang='scss' scoped>
@import "./my-booking-detail.scss";
</style>
<template>
  <div class="my-booking-detail">
    <div class="my-booking-detail-title">
      <ul>
        <li>返回</li>
        <li>订单信息</li>
        <li>行程详情</li>
        <li>旅客</li>
        <li>联系人</li>
      </ul>
    </div>
    <div class="my-booking-detail-main">
      <div class="my-booking-detail-main-header"><i class="iconfont icon-travel-find"></i> 订单信息</div>
      <div class="my-booking-detail-main-text">
        <ul>
          <li>
            <label>订单状态:</label>
            <span>已取消</span>
          </li>
          <li>
            <label>订单号:</label>
            <span>51155178811789501</span>
          </li>
          <li>
            <label>下单日期:</label>
            <span>2019-03-05 20:20</span>
          </li>
          <li>
            <label>供应商:</label>
            <span>东方航空公司</span>
          </li>
        </ul>
        <ol>
          <span>总计：</span>
          <strong>¥ 750.00 </strong>
        </ol>
      </div>
      <div class="my-booking-detail-main-step">
        <Steps :current="current">
          <Step title="已提交"></Step>
          <Step title="付款完成"></Step>
          <Step title="出票成功"></Step>
        </Steps>
      </div>
       <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title"><b>行程详情</b></div>
        <div class="my-booking-detail-main-box-trip">
          <div class="my-booking-detail-main-box-trip-step">
            <div class="my-booking-detail-main-box-trip-step-state"><i class="iconfont icon-travel-find"></i></div>
            <div class="my-booking-detail-main-box-trip-step-col">
              <small>启程</small>
              <span>
                上海&nbsp;(SHA)
                <i class="iconfont icon-travel-find"></i>
                广州&nbsp;(CAN)
              </span>
            </div>
            <div class="my-booking-detail-main-box-trip-step-col">
              <small>日期</small>
              <b>2019-04-03 22:25</b>
            </div>
          </div>
          <div class="my-booking-detail-main-box-trip-bot">
            <h4>
              <span class="st">4月3日,&nbsp;
                <b>22:25</b>
                <i class="iconfont icon-travel-setsail"></i>
              </span>
              <span class="flight-info-text">SHA</span>
              &nbsp;上海&nbsp;虹桥机场 &nbsp;
              <b>T2</b>
            </h4>
            <div class="infoft">
              <strong>2h 20m</strong>
              <div class="infoft-airline-icon"><img data-v-d7c8966e="" src="https://content.igola.com/static/APP/images/airline_logo/1x/36x36/mu.png"></div>
              <div class="infoft-airline-name">
                <span>东航 &nbsp;</span>
                <b>MU5311</b>
                <font>经济舱</font>
              </div>
            </div>
            <h4>
              <span class="st">4月3日,&nbsp;
                <b>22:25</b>
                <i class="iconfont icon-travel-jiangla"></i>
              </span>
              <span class="flight-info-text">SHA</span>
              &nbsp;上海&nbsp;虹桥机场 &nbsp;
              <b>T2</b>
            </h4>
          </div>
        </div>
      </div>
      <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title"><b>旅客</b></div>
        <div class="my-booking-detail-main-box-list">
          <ol>
            <li>姓名</li>
            <li>性别</li>
            <li>出生日期</li>
            <li>国籍</li>
            <li>证件号</li>
            <li>证件有效期</li>
            <li>票号</li>
            <li>航司预定号</li>
          </ol>
          <ul>
            <div class="clearfix">
              <li>赵 梅</li>
              <li>女性</li>
              <li>1986-08-06</li>
              <li>中国</li>
              <li>610124198608061789</li>
              <li>-</li>
              <li>-</li>
              <li>-</li>
            </div>
          </ul>
        </div>
      </div>
      <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title"><b>联系人</b></div>
        <div class="my-booking-detail-main-box-list">
          姓名：赵 林<br>
          手机：18511181204<br>
          邮箱：602389246@qq.com
        </div>
      </div>

      <div class="my-booking-detail-footer">出票和退改签服务由购票平台提供，易道帮你比价和预订
</div>
    </div>
  </div>
</template>

<script>
import Config from './../../config.js'
import Util from '@/lib/utils.js'


export default {
  mixins: [Config],
  data () {
    return {
      current: 0
    }
  },
  components: {

  },
  methods: {

  },
  mounted () {
    this.getOrderDetail(this.$route.params.id)
  }
}
</script>
