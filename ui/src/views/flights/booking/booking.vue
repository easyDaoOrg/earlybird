
<style lang='scss' scoped>
@import "./booking.scss";
</style>

<template>
  <div class="booking">
    <div class="booking-left fl">
      <bookingStep></bookingStep>
      <!--旅客-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="0"></bookingSubtitle>
        <bookingPassenger
          ref="bookingPassenger"
          @on-ok="onPeopleOk"
          @on-price-change='onPriceChange'
          @on-cabinType='cabinType=$event'
          @on-carrier='carrier=$event'
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

        :dptCity='routerObj.dptCity'
        :arrCity='routerObj.arrCity'
        :date='routerObj.date'

        :price='infoObj.price'
        :number='infoObj.number'
        :totalMoney='infoObj.totalMoney'

        :carrier='carrier'
        :cabinType='cabinType'
        @on-place-order="onPlaceOrrder()"
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
import { mapGetters } from 'vuex'
import Utils from '@/lib/utils'
export default {
  computed: {
    ...mapGetters(['book_flight'])
  },
  data () {
    return {
      passengers: [],
      routerObj: this.$route.query,
      infoObj: {},
      // 舱位
      cabinType: null,
      carrier: null
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
  methods: {
    onPeopleOk (data) {
      this.passengers = data
    },
    onMemberChange (data) {
      this.$refs.bookingPassenger.setMember(data)
    },
    // 提交订单
    onPlaceOrrder () {
      let Passenger = this.$refs.bookingTabList.onSubmit()
      let Contacts = this.$refs.ontactsForm.onSubmit()
      console.log(Passenger.type + '---' + Contacts)

      if (Passenger.type && Contacts) {
        this.onOrders(Passenger.list)
        this.buyTripBook()
      }
    },
    buyTripBook () {
      // dptCity: this.airpotTrip.cityStart,
      //     dpt: this.airpotTrip.cityStartCode,
      //     arrCity: this.airpotTrip.cityEnd,
      //     arr: this.airpotTrip.cityEndCode,
      //     date: this.airpotTrip.cityDate.start,
      //     adult: this.airport_group.bigvalue,
      //     child: this.airport_group.childvalue,
      //     cabinType: item.cabinType,
      //     cid: item.cid
      let pid = this.$refs.bookingPassenger.getPid()
      let obj = {
        pid,
        cid: this.routerObj.cid,
        cabinType: this.routerObj.cabinType
      }
      let url = this.baseUrl + `/flight/price`
      let self = this
      // this.axios
      //   .post(url, obj)
      //   .then(data => {
      //     if (data.status === 200) {
      //       debugger
      //     }
      //   })
      this.$router.push({
        path: `/flights/topay`,
        query: {
          dptCity: this.routerObj.dptCity,
          arrCity: this.routerObj.arrCity,
          date: this.routerObj.date,
          cabinType: this.cabinType,
          price: this.infoObj.price,
          totalMoney: this.infoObj.totalMoney,
          carrier: this.carrier
        }
      })
    },
    // 发送请求
    onOrders (passengersValue) {
      console.log(this.book_flight)
      // let extInfo = this.book_flight.extInfo
      // let obj = {
      //   flightNum: extInfo.flightNum,
      //   flightType: extInfo.flightType,
      //   stopInfo: '',
      //   deptAirportCode: '',
      //   arriAirportCode: '',
      //   deptCity: '',
      //   arriCity: '',
      //   deptDate: '',
      //   deptTime: '',
      //   arriTime: '',
      //   cabin: extInfo.cabin,
      //   childCabin: '',
      //   actFlightNum: '',
      //   codeShare: ''
      // }
      let obj = {
        invoiceType: '',
        flightInfo: ''
      }
      // 旅客 10个参数
      let passengers = []
      passengersValue.forEach((item) => {
        let m = {
          name: item.familyNameZh + item.givenNameZh,
          ageType: item.ageType, //  0：成人；1：儿童；2：婴儿
          cardType: item.category, // NI:身份证,PP:护照
          cardNo: item.number, // 号码
          passengerPriceTag: '', // 票面价
          // 固定
          bx: false,
          flightDelayBx: false,
          tuipiaoBx: false
        }
        // 护照才性别和啥呢恶搞日
        if (item.category === 'PP') {
          m['birthday'] = Utils.timeBirthday(item.birthday)// 如1986-10-19
          m['sex'] = item.gender// 0: 女，1: 男
        }
        passengers.push(m)
      })
      // 乘机人个数
      obj['passengerCount'] = passengers.length
      // 乘机人列表
      obj['passengers'] = passengers

      // 联系人信息

      obj['invoiceType'] = ''
      obj['bookingTag'] = ''
      // let url = `http://123.206.254.186:8080/flight/orders/order`
      // let self = this
      // axios
      //   .post(url, obj)
      //   .then(data => {
      //     // 路由跳转
      //     self.$router.push(`/flights/timeline`)
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })
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
  },
  mounted () {

  }
}
</script>
