
<style lang='scss' scoped>
@import "./bookingSubtitle.scss";
</style>
<template>
  <div class="booking-subtitle">
    <div class="booking-subtitle-title" v-if="subTitle == 0">旅客</div>
    <div class="booking-subtitle-title" v-if="subTitle == 1">联系人信息</div>
    <div v-if="subTitle == 2">
      <div class="booking-subtitle-title">报销凭证</div>
      <div class="booking-subtitle-tips">
        <p>如需报销凭证，可在出行后15天内，在易到的订单详情内申请补开，或者联系客服获取。</p>
      </div>
    </div>
    <div v-if="subTitle == 3">
      <div class="booking-subtitle-title">可用优惠券</div>
      <div class="booking-subtitle-tips">
        <span>您需要填写完整所有旅客和联系人信息。同一旅客或联系人只能使用一次优惠券，“分享有礼”或特定活动除外。</span>
        <p>当前订单暂时没有可用优惠券。</p>
      </div>
    </div>
  </div>
</template>

<script>


export default {
  props: ['subTitle'],
  data () {
    return {

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
