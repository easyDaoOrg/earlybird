
<style lang='scss' scoped>
@import "./booking.scss";
</style>

<template>
  <div class="booking">
    <div class="booking-left fl">
      <bookingStep :current="0"></bookingStep>
      <!--旅客-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="0"></bookingSubtitle>
        <bookingPassenger
          ref="bookingPassenger"
          @on-ok="onPeopleOk"
          @on-price-change='onPriceChange'
          @flight-information='flightInformation=$event;buyTripBook()'
          ></bookingPassenger>
        <bookingTabList :data="passengers" @on-member-change="onMemberChange" ref="bookingTabList"></bookingTabList>
      </div>

      <!--联系人信息-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="1"></bookingSubtitle>
        <ontacts @on-clear-ontacts="onClearOntacts()" @on-add-ontacts="oAddOntacts"></ontacts>
        <ontactsForm ref="ontactsForm"></ontactsForm>
      </div>

      <!--报销凭证-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="2"></bookingSubtitle>
      </div>

      <!--可用优惠券-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="3"></bookingSubtitle>
      </div>

      <div class="booking-tips">易道是一家国际机票搜索引擎，辅助订购为您提供便捷订购通道，但是出票和退改签服务仍由第三方供应商提供</div>
    </div>

    <div class="booking-right">
      <bookingOrder
        parentName='booking'

        :dptCity='flightInformation.bairdrome + flightInformation.depTerminal'
        :arrCity='flightInformation.eairdrome + flightInformation.arrTerminal'
        :date='flightInformation.date'

        :price='infoObj.price'
        :number='infoObj.number'
        :totalMoney='infoObj.totalMoney'

        :carrier='flightInformation.carrier'
        :cabinType='flightInformation.cabinType'
        @on-place-order="onPlaceOrder()"
      ></bookingOrder>
    </div>
  </div>
</template>

<script>
import bookingStep from './bookingstep/bookingstep'
import bookingSubtitle from './bookingSubtitle/bookingSubtitle'
import bookingPassenger from './bookingPassenger/bookingPassenger'
import ontacts from './bookingTopContacts/ontacts/ontacts'
import ontactsForm from '././bookingTopContacts/ontactsForm/ontactsForm'
import bookingTabList from './bookingTabList/bookingTabList'
import bookingOrder from './bookingOrder/bookingOrder'

import Util from '@/lib/utils'
export default {
  data () {
    return {
      passengers: [],
      routerObj: this.$route.query,
      infoObj: {},
      flightInformation: {},
      resultBooking: {},
      user_id:Util.getCookie('userId'),
      orderNoStr: ''
    }
  },
  components: {
    bookingStep,
    bookingSubtitle,
    bookingPassenger,
    ontacts,
    bookingTabList,
    bookingOrder,
    ontactsForm
  },
  watch: {

  },
  mounted () {},
  methods: {
    onPeopleOk (data) {
      this.passengers = data
    },
    onMemberChange (data) {
      this.$refs.bookingPassenger.setMember(data)
    },
    // 提交订单
    onPlaceOrder () {
      let Passenger = this.$refs.bookingTabList.onSubmit()
      let Contacts = this.$refs.ontactsForm.onSubmit()
      console.log(Passenger.type + '---' + Contacts)
      if (Passenger.type && Contacts.type) {
        this.$refs.bookingPassenger.getAirportPrice().then((v) => {
          this.onOrders(Passenger.list, Contacts.obj, this.resultBooking)
        })
      }
    },
    // booking接口
    buyTripBook () {
      let obj = {
        pid: this.flightInformation.pid,
        cid: this.flightInformation.cid,
        cabinType: this.flightInformation.cabinType
      }
      let url = this.baseUrl + `/flight/book`
      this.axios
        .post(url, obj)
        .then(data => {
          if (data.status === 200) {
            this.resultBooking = data.data
          }
        }).catch(() => {})
    },
    addFlay(passengersList, contactsObj,start){
      let passenger_id=''

      passengersList.forEach(item=>{
        if(item.id!==''){
           passenger_id=passenger_id+item.id
        }
      })
      let contact_id=contactsObj.id!=='' ? contactsObj.id.toString() : ''

      let info = this.resultBooking.flightInfo[0]

      let obj = {
        user_id:this.user_id,
        contact_id:contact_id,
        passenger_id:passenger_id,
        order_flight_no:info.flightNum,// 航班号
        order_flight_carrier:info.carrier,//航班承运人,
        // order_flight_model:info.actPlaneType,// 航班机型
        order_flight_model:this.routerObj.planeType,// 航班机型
        order_flight_cabin:info.cbcn,//'经济舱',//？number还是string
        order_flight_fare:Number(this.resultBooking.extInfo.ticketPrice),// 票面价
        order_flight_construction_cost:info.arf,//机建费
        order_flight_fuel_cost:info.tof,// 燃油费
        order_departure:info.dptCity,
        order_destination:info.arrCity,
        order_departure_airport:info.dptAirport,//出发城市，汉字
        order_destination_airport:info.arrAirport,// 到达城市，汉字
        order_start_time:info.dptDate+' '+info.dptTime,
        order_end_time:info.dptDate+' '+info.arrTime,
        order_status:start// 是order接口返回的status吧？
      }
      console.log(obj)
      let url = this.loginUrl + `/aircraft/addOrderAircraft`;
      let self = this;
      this.axios
        .post(url, obj)
        .then(data => {
          if (data.data.flag) {
            self.$router.push({
              path: `/flights/topay`,
              query: {
                dptCity: self.flightInformation.bairdrome,
                arrCity: self.flightInformation.eairdrome,
                date: self.routerObj.date,
                cabinType: self.routerObj.cabinType,
                price: self.infoObj.price,
                totalMoney: self.infoObj.totalMoney,
                carrier: self.routerObj.carrier,
                orderNo: self.orderNoStr
              }
            })
          }else{
            this.$Message.error('提交订单失败')
          }
        }).catch(() => {})
    },
    // 发送请求
    onOrders (passengersList, contactsObj, resultBooking) {
      // book 中获取
      let obj = {
        cid: resultBooking.extInfo.cid,
        printPrice: resultBooking.priceInfo.priceTag.ADU[0].viewPrice,
        yPrice: Number(resultBooking.extInfo.ticketPrice),
        invoiceType: resultBooking.expressInfo.id,
        bookingTag: resultBooking.bookingTag
      }
      let info = resultBooking.flightInfo[0]
      // 13参数
      let flightInfo = {
        flightNum: info.codeShare ? info.actFlightNum : info.flightNum, // 航班号
        flightType: Number(resultBooking.extInfo.flightType), // 行程类型 1:单程，2：往返
        stopInfo: info.stops, // 经停数
        deptAirportCode: info.dpt, // 出发机场，大写，三字码
        arriAirportCode: info.arr, // 到达机场，大写，三字码
        deptCity: info.dptCity, // 出发城市，汉字
        arriCity: info.arrCity, // 到达城市，汉字
        deptDate: info.dptDate, // 出发日期
        deptTime: this.filterTime(info.dptTime), // 出发时间
        arriTime: this.filterTime(info.arrTime), // 到达时间
        cabin: info.cabin, // 舱位  // booking
        childCabin: info.childCabin || 'Y', // 儿童舱位 (当book中的值为null时，必需设置此字段为Y) //booking
        gx: info.codeShare ? info.flightNum : info.actFlightNum
      }

      obj['flightInfo'] = flightInfo
      // 旅客 10个参数
      let passengers = []
      passengersList.forEach((item) => {
        // ADU(成人)，CHI(儿童)，INF(婴儿)
        let passengerPriceTag = resultBooking.priceInfo.priceTag.ADU[0].tag
        if (item.ageType === 1 && resultBooking.priceInfo.priceTag.CHI && resultBooking.priceInfo.priceTag.CHI.length) {
          passengerPriceTag = resultBooking.priceInfo.priceTag.CHI[0].tag
        }
        let m = {
          name: item.familyNameZh + item.givenNameZh,
          ageType: item.ageType, //  0：成人；1：儿童；2：婴儿
          cardType: item.category, // NI:身份证,PP:护照
          cardNo: item.number, // 号码
          passengerPriceTag: passengerPriceTag,
          // 固定
          bx: false,
          flightDelayBx: false,
          tuipiaoBx: false
        }
        // 护照
        if (item.category === 'PP') {
          m['birthday'] = Util.timeBirthday(item.birthday)// 如1986-10-19
          m['sex'] = item.gender// 0: 女，1: 男
        }
        passengers.push(m)
      })
      // 乘机人个数
      obj['passengerCount'] = passengers.length
      // 乘机人列表
      obj['passengers'] = passengers

      let contact = contactsObj.lastName + contactsObj.firstName
      obj['contact'] = contact
      obj['sjr'] = contact
      obj['contactPreNum'] = contactsObj.areaCode
      obj['contactMob'] = contactsObj.mobile
      obj['contactEmail'] = contactsObj.email
      obj['address'] = contactsObj.address
      let url = this.baseUrl + `/flight/order`
      let self=this;
      this.axios
        .post(url, obj)
        .then(data => {
          if (data.status === 200) {
            self.orderNoStr = data.data.orderNo
            self.addFlay(passengersList, contactsObj,data.data.status)
          }
        }).catch((err) => {

          this.$Modal.error({
            title: '错误',
            content: err.response.data.message
          })
          console.log(err)
        })
    },
    // 出发到达时间
    filterTime (data) {
      let n = data.replace(':', '')
      return n
    },
    // 清空常用联系人
    onClearOntacts () {
      this.$refs.ontactsForm.clear()
    },
    // 添加常用联系人
    oAddOntacts (data) {
      this.$refs.ontactsForm.setDefaultObj(data)
    },
    // 价格
    onPriceChange (data) {
      let totalMoney = data.Adult_number * data.Adult_price
      if (data.child_number !== 0 && data.child_prive !== 0) {
        totalMoney = totalMoney + data.child_number * data.child_prive
      }
      this.infoObj = {
        totalMoney: totalMoney,
        number: data.Adult_number + data.child_number,
        price: data.Adult_price
      }
    }
  }
}
</script>
