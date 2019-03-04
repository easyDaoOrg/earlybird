
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
        <bookingPassenger @on-ok="onPeopleOk" ref="bookingPassenger"></bookingPassenger>
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
      <bookingOrder @on-place-rder="onPlaceRrder()"></bookingOrder>
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
      passengers: []
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
    onPlaceRrder () {
      let Passenger = this.$refs.bookingTabList.onSubmit()
      let Contacts = this.$refs.ontactsForm.onSubmit()
      console.log(Passenger.type + '---' + Contacts)

      if (Passenger.type) {
        this.onOrders(Passenger.list)

        // Passenger.list
      }
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
    }
  },
  mounted () {

  }
}
</script>
