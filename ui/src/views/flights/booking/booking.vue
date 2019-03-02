
<style lang='scss' scoped>
@import "./booking.scss";
</style>

<template>
  <div class="booking">
    <div class="booking-left fl">
      <bookingStep></bookingStep>
      <!--旅客-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="0" ></bookingSubtitle>
        <bookingPassenger @on-ok='onPeopleOk' ref='bookingPassenger'></bookingPassenger>
        <bookingTabList :data='passengers' @on-member-change='onMemberChange'></bookingTabList>
      </div>

      <!--联系人信息-->
      <div class="booking-box">
        <bookingSubtitle :subTitle="1"></bookingSubtitle>
        <bookingContacts></bookingContacts>
        <bookingForm :bookingForm="1"></bookingForm>
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
      <bookingOrder></bookingOrder>
    </div>
  </div>
</template>

<script>
import bookingStep from './bookingstep/bookingstep'
import bookingSubtitle from './bookingSubtitle/bookingSubtitle'
import bookingPassenger from './bookingPassenger/bookingPassenger'
import bookingContacts from './bookingContacts/bookingContacts'
import bookingForm from './bookingForm/bookingForm'
import bookingTabList from './bookingTabList/bookingTabList'
import bookingOrder from './bookingOrder/bookingOrder'

export default {
  data () {
    return {
      passengers: []
    }
  },
  components: {
    bookingStep,
    bookingSubtitle,
    bookingPassenger,
    bookingContacts,
    bookingForm,
    bookingTabList,
    bookingOrder
  },
  watch: {

  },
  methods: {
    onPeopleOk (data) {
      this.passengers = data
    },
    onMemberChange (data) {
      this.$refs.bookingPassenger.setMember(data)
    }
  },
  mounted () {}
}
</script>
