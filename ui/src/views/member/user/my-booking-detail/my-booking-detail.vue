
<style lang='scss' scoped>
@import "./my-booking-detail.scss";
</style>
<template>
  <div class="my-booking-detail">
    <div class="my-booking-detail-title">
      <ul>
        <router-link :to="{path:'/member/user/my-booking'}">
          <li>返回</li>
        </router-link>
        <li><a href="#order">订单信息</a></li>
        <li><a href="#detail">行程详情</a></li>
        <li><a href="#travel">旅客</a></li>
        <li><a href="#contact">联系人</a></li>
      </ul>
    </div>
    <div class="my-booking-detail-main">
      <div class="my-booking-detail-main-header" id="order"><i class="iconfont icon-travel-find"></i> 订单信息</div>
      <div class="my-booking-detail-main-text">
        <ul>
          <li>
            <label>订单状态:</label>
            <span v-if="orderAircraftDetail.order_status == 0">未支付</span>
            <span v-if="orderAircraftDetail.order_status == 1">已支付</span>
            <span v-if="orderAircraftDetail.order_status == 2">已取消</span>
          </li>
          <li>
            <label>订单号:</label>
            <span>{{orderAircraftDetail.order_no}}</span>
          </li>
          <li>
            <label>下单日期:</label>
            <span>{{orderAircraftDetail.order_start_time}}</span>
          </li>
          <li>
            <label>供应商:</label>
            <span>
              <!-- {{orderAircraftDetail.order_flight_carrier}} -->
              <airportPic :carrier="orderAircraftDetail.order_flight_carrier" :airportTitle="true"></airportPic>
            </span>
          </li>
        </ul>
        <ol>
          <span>总计：</span>
          <strong>¥ {{orderAircraftDetail.order_flight_fare}} </strong>
        </ol>
      </div>
      <div class="my-booking-detail-main-step">
        <Steps :current="orderAircraftDetail.order_status">
          <Step title="已提交"></Step>
          <Step title="付款完成"></Step>
          <Step title="出票成功"></Step>
        </Steps>
      </div>
       <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title" id="detail"><b>行程详情</b></div>
        <div class="my-booking-detail-main-box-trip">
          <div class="my-booking-detail-main-box-trip-step">
            <div class="my-booking-detail-main-box-trip-step-state"><i class="iconfont icon-travel-find"></i></div>
            <div class="my-booking-detail-main-box-trip-step-col">
              <small>启程</small>
              <span>
                {{orderAircraftDetail.order_departure}}&nbsp;({{orderAircraftDetail.order_depCode}})
                <i class="iconfont icon-travel-find"></i>
                {{orderAircraftDetail.order_destination}}&nbsp;({{orderAircraftDetail.order_arrCode}})
              </span>
            </div>
            <div class="my-booking-detail-main-box-trip-step-col">
              <small>日期</small>
              <b>{{orderAircraftDetail.order_start_time}}</b>
            </div>
          </div>
          <div class="my-booking-detail-main-box-trip-bot">
            <h4>
              <span class="st">
                <b>{{orderAircraftDetail.order_start_time}}</b>
                <i class="iconfont icon-travel-setsail"></i>
              </span>
              <span class="flight-info-text">{{orderAircraftDetail.order_depCode}}</span>
              &nbsp;{{orderAircraftDetail.order_departure}}&nbsp;{{orderAircraftDetail.order_departure_airport}} &nbsp;
              <b>T2</b>
            </h4>
            <div class="infoft">
              <!-- <strong>2h 20m</strong> -->
              <div class="infoft-airline-icon">
                <airportPic :carrier="orderAircraftDetail.order_flight_carrier" :airportTitle="true"></airportPic>
              </div>
              <div class="infoft-airline-name">
                <!-- <span>{{orderAircraftDetail.order_flight_carrier}} &nbsp;</span> -->
                <b>{{orderAircraftDetail.order_flight_no}} &nbsp; 机型: {{orderAircraftDetail.order_flight_model}}</b>
                <font>{{orderAircraftDetail.order_flight_cabin}}</font>
              </div>
            </div>
            <h4>
              <span class="st">
                <b>{{orderAircraftDetail.order_end_time}}</b>
                <i class="iconfont icon-travel-jiangla"></i>
              </span>
              <span class="flight-info-text">{{orderAircraftDetail.order_arrCode}}</span>
              &nbsp;{{orderAircraftDetail.order_destination}}&nbsp;{{orderAircraftDetail.order_destination_airport}} &nbsp;
              <b>T2</b>
            </h4>
          </div>
        </div>
      </div>
      <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title" id="travel"><b>旅客</b></div>
        <div class="my-booking-detail-main-box-list">
          <ol>
            <li>姓名/中文</li>
            <li>姓名/拼音</li>
            <li>性别</li>
            <li>国籍</li>
            <li>生日</li>
            <!-- <li>证件有效期</li>
            <li>票号</li>
            <li>航司预定号</li> -->
          </ol>
          <ul v-if="orderAircraftDetail.passengerList&&orderAircraftDetail.passengerList.length>0">
            <div class="clearfix" v-for="(item,i) in orderAircraftDetail.passengerList" :key="i">
              <li>{{item.passenger_name_cn}}</li>
              <li>{{item.passenger_name_en}}</li>
              <li>{{item.passenger_gender}}</li>
              <li>{{item.passenger_nationality}}</li>
              <li>{{item.passenger_birthday}}</li>
              <!-- <li>-</li>
              <li>-</li>
              <li>-</li> -->
            </div>
          </ul>
        </div>
      </div>
      <div class="my-booking-detail-main-box">
        <div class="my-booking-detail-main-box-title" id="contact"><b>联系人</b></div>
        <div class="my-booking-detail-main-box-list"  v-if="orderAircraftDetail.contactList&&orderAircraftDetail.contactList.length>0">
          <div class="clearfix" v-for="(item,i) in orderAircraftDetail.contactList" :key="i">
            姓名：{{item.contact_name}}<br>
            手机：{{item.contact_phone}}<br>
            邮箱：{{item.contact_email}}
          </div>
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
import airportPic from '../../../flights/airportpic/airportpic'


export default {
  mixins: [Config],
  data () {
    return {
      orderID: this.$route.params.id
    }
  },
  components: {
    airportPic
  },
  methods: {

  },
  mounted () {
    this.getOrderDetail(this.$route.params.id)
  }
}
</script>
