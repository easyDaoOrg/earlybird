
<style lang='scss' scoped>
@import "./my-cashback.scss";
</style>
<template>
  <div class="my-cashback">
    <div class="my-cashback-title">
      <i class="fr" @click="modelQuestion = true"><Icon type="md-help-circle" /></i>
      <b>我的返现</b>
    </div>

    <div class="my-cashback-content">
      <div class="my-cashback-content-balance">余额</div>
      <div class="my-cashback-content-info">
        <b>0</b>
        <span>可用余额：¥0</span>
      </div>
    </div>
    <Modal v-model="modelQuestion" footer-hide :width="700">
      <div class="my-cashback-question">
        <div class="my-cashback-question-title">易道返现活动Q&A</div>
        <div class="my-cashback-question-list">
          <dl>
            <dt>Q1. 如何参与返现活动？</dt>
            <dd>答：即日起，易道会员登录易道官网或客户端，购买参与返现的国际机票且订单完成后即可获得返现。</dd>
          </dl>
          <dl>
            <dt>Q2. 返现金额能用来做什么？</dt>
            <dd>答：绑定手机的会员能够使用返现余额抵现，即在购买国际机票时1:1抵扣订单费用。</dd>
          </dl>
          <dl>
            <dt>Q3. 什么是订单完成？</dt>
            <dd>答：在出票成功且至少一位乘机人没有退票的前提下，订单最后一班飞机起飞视为订单完成。</dd>
          </dl>
        </div>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  data () {
    return {
      modelQuestion: false
    }
  },
  components: {

},
  methods: {},
  mounted () {}
}
</script>
