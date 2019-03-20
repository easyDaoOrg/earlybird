
<style lang='scss' scoped>
@import "./my-booking.scss";
</style>
<template>
  <div class="my-booking">
    <Tabs type="card" :animated="false">
        <TabPane v-for="(item,index) in tabs" :key="index" :label="item.label">
          <usernodata v-if="orderAircraftList&&orderAircraftList.length==0"></usernodata>
          <div class="my-booking-list" v-if="orderAircraftList&&orderAircraftList.length>0">
            <div class="my-booking-list-box" v-for="(k,ii) in orderAircraftList" :key="ii">
              <div class="my-booking-list-box-info">
                <font>
                  <b v-if="k.order_status == 0">未支付</b>
                  <b v-if="k.order_status == 1">已支付</b>
                  <b v-if="k.order_status == 2">已取消</b>
                </font>
                <i data-v-7e6ddf1c="" class="iconfont icon-travel-find"></i>
                <span>
                  订单号：<b>{{k.order_no}}</b>
                </span>
                <span class="my-booking-list-second">
                  下单日期：<b>{{k.create_date}}</b>
                </span>
              </div>

              <div class="my-booking-list-box-detail" @click="orderBookingDetail(k)">
                <div class="my-booking-list-box-detail-trip my-booking-list-box-detail-air">
                  <b>单程</b>
                  <p>{{k.order_departure_airport}}({{k.order_depCode}})
                    <i data-v-7e6ddf1c="" class="iconfont icon-travel-find"></i>
                    {{k.order_destination_airport}}({{k.order_arrCode}})
                  </p>
                </div>
                <div class="my-booking-list-box-detail-trip">
                  <b>航空公司</b>
                  <p>
                    <!-- {{k.order_flight_carrier}} -->
                    <airportPic :carrier="k.order_flight_carrier" :airportTitle="true"></airportPic>
                  </p>
                </div>
                <div class="my-booking-list-box-detail-trip">
                  <b>出发日期</b>
                  <p>{{k.order_start_time}}</p>
                </div>
                <div class="my-booking-list-box-detail-trip">
                  <b>旅客</b>
                  <p>{{k.passenger_names}}</p>
                </div>
                <div class="my-booking-list-box-detail-trip">
                  <b>总计</b>
                  <p><strong>¥ {{k.order_flight_fare}}</strong></p>
                </div>
              </div>
            </div>
          </div>
        </TabPane>
    </Tabs>
  </div>
</template>

<script>
import usernodata from './../user-nodata/user-nodata'
import Config from './../../config.js'
import Util from '@/lib/utils.js'
import airportPic from '../../../flights/airportpic/airportpic'



export default {
  mixins: [Config],
  data () {
    return {
      tabs: [
        {
          label: '全部订单'
        },
        // {
        //   label: '未出行'
        // },
        // {
        //   label: '已出行'
        // },
      ]
    }
  },
  components: {
    usernodata,
    airportPic
  },
  methods: {
    // 进入详情
    orderBookingDetail(data){
      console.log(data)
      this.$router.push(`/member/user/my-booking-detail/${data.id}`)
    }
  },
  mounted () {
    this.getOrderList(Util.getCookie('userId'))
  }
}
</script>
