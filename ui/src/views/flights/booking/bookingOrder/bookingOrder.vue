
<style lang='scss' scoped>
@import "./bookingOrder.scss";
</style>
<style>
.booking-order .ivu-spin-fix .ivu-spin-main {
  right: 4px;
  left: auto;
}
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
            <span>{{dptCity}} - {{arrCity}}</span>
          </div>
        </div>
        <div style="padding-left: 36px;">
          <span>{{date}}</span>
        </div>
      </div>
      <div class="booking-order-summary-bot">
        <span class="fr">{{cabinName}}</span>
        <!-- <Button shape="circle">航程详情</Button> -->
      </div>
    </div>
    <div class="line"></div>
    <div class="booking-order-total clearfix">
      <section class='airline'>
         <airportPic :carrier="carrier" :airportTitle="true"></airportPic>
      </section>
      <h4 v-if="!price">
        <Spin fix></Spin>
      </h4>
      <template v-if="price">
        <h4>¥ {{price}}</h4>
        <b>订单总价</b>
      </template>
    </div>
    <div class="booking-order-list clearfix">
      <ul>
        <li>
          <span class="fr" v-if="totalMoney">¥ {{totalMoney}}</span>
          <template v-if='parentName==="booking"'>
             旅客 x <b>{{number||1}}</b>
          </template>
           <template v-if='parentName==="topay"'>
              总票价
          </template>
        </li>
      </ul>
    </div>
    <div class="booking-order-firm">
      <div class="booking-order-firm-gz">
        <Checkbox v-model="single">已阅读并同意
          <span @click="modelClause = true">易道服务条款</span>
        </Checkbox>
      </div>
      <Button type="primary" shape="circle" @click="onPlaceOrder()" :disabled='!single'>提交订单</Button>
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
import { user } from '@/assets/json/user'
import airportPic from '../../airportpic/airportpic'

export default {
  components: {airportPic},
  props: {
    // 出发城市
    dptCity: '',
    // 到达城市
    arrCity: '',
    // 日期
    date: {
      type: String
    },
    // 价格
    price: {
      type: [Number, String]
    },
    // 数量
    number: {
      type: [Number, String]
    },
    // 总价
    totalMoney: {
      type: [Number, String]
    },
    // 舱
    cabinType: {
      type: Number
    },
    // 父组件认证
    parentName: {
      type: String,
      default: ''
    },
    // 航空公司
    carrier: {
      type: String,
      default: ''
    }
  },
  computed: {
    cabinName () {
      let n = ''
      this.cabinTypeList.forEach(item => {
        if (item.value === this.cabinType) {
          n = item.label
        }
      })
      return n
    }
  },
  data () {
    return {
      single: true,
      modelClause: false,
      cabinTypeList: user.cabinList
    }
  },
  watch: {},
  mounted () {},
  methods: {
    onPlaceOrder () {
      this.$emit('on-place-order', true)
    }
  }
}
</script>
