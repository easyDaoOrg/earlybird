
<style lang='scss' scoped>
@import "./coupons.scss";
</style>
<style>
  .coupons .ivu-radio-wrapper,
  .coupons .ivu-select-item,
  .coupons .ivu-select-single .ivu-select-selection .ivu-select-selected-value{
    font-size: 14px!important;
  }
</style>

<template>
  <div class="coupons">
    <Tabs type="card" :animated="false">
        <TabPane v-for="(item,index) in tabs" :key="index" :label="item.label">
          <div class="coupons-tabs">
            <RadioGroup v-model="tabStr">
                <Radio label="全部"></Radio>
                <Radio label="全业务"></Radio>
                <Radio label="机票券"></Radio>
                <Radio label="酒店券"></Radio>
            </RadioGroup>
            <b>排序：</b>
            <Select v-model="modelSort" size="small" style="width:100px">
                <Option v-for="item in sortList" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </Select>
          </div>
          <div class="coupons-title">
            <b>优惠券</b>
          </div>
          <div class="coupons-total">总计 <b>0</b> 张优惠劵</div>
          <div class="coupons-nodata">还没有任何优惠劵</div>
        </TabPane>
    </Tabs>
    <div class="coupons-question">
      <div class="coupons-question-title">优惠券常见问题</div>
      <div class="coupons-question-content">
        <dl>
          <dt>1. 什么是易道优惠券？</dt>
          <dd>易道优惠券是由易道发行的，可抵扣易道特定产品部分订单金额的优惠产品。目前易道的优惠券分为全业务优惠券、机票优惠券和酒店优惠券。</dd>
        </dl>
        <dl>
          <dt>2. 优惠券基本使用规则有哪些？</dt>
          <dd>
            1) 每张订单都只能使用1张优惠券，不能同时使用多张；<br>
            2) 每张优惠券只可使用1次，不可找零，不能兑换现金；<br>
            3) 保险金额不参与优惠，且不计入优惠券的适用额度。
          </dd>
        </dl>
        <dl>
          <dt>3. 如何获取和使用优惠券？</dt>
          <dd>您可通过注册、分享和活动参与等方式获取优惠券。<br>
              预订时，您可在填单界面选择可用优惠券，点击确认，系统会自动抵扣优惠金额。
          </dd>
        </dl>
        <dl>
          <dt>4. 优惠券的商品时间是什么？</dt>
          <dd>易道优惠券的使用条件与两个时间密切相关：可用时间和商品时间。其中，可用时间是您在易道下单支付的时间（显示在优惠券右上角），商品时间是您购买的商品的实际消费时间（如乘机/入住时间）。
          </dd>
        </dl>
        <dl>
          <dt>5. 下单使用了优惠券，订单取消修改后，优惠券会返还吗</dt>
          <dd>
            1) 未支付订单取消后，优惠券会自动返还到您的账户；<br>
            2) 已支付订单取消，优惠券将不予退还；<br>
            3) 如订单取消时优惠券已过期，则该优惠券无法再次使用。
          </dd>
        </dl>
        <dl>
          <dt>6. 优惠券无法使用怎么办？</dt>
          <dd>
            优惠券无法使用有以下可能：<br>
            1) 在机票预订页面填写完整所有旅客和联系人信息之后可以查看到所有可用优惠券；<br>
            2) 有可能您预订的产品不在优惠券使用范围内；<br>
            3) 优惠券在有效期内只能使用一次，使用后状态为【已使用】；<br>
            4) 每张订单中只可使用一张优惠券，不支持多张；<br>
            5) 优惠券都是有期限的，建议您关注一下有效期，请在有效期内使用您的优惠券；<br>
            6) 部分优惠券是有使用限制的，请确认您的账户、设备、手机号、订单金额符合该优惠券的使用规则；<br>
            7) 同一个旅客或联系人仅能使用同类优惠券一次，“分享有礼”或特定活动除外。
          </dd>
        </dl>
      </div>
    </div>
  </div>
</template>

<script>
import usernodata from './../user-nodata/user-nodata'

export default {
  data () {
    return {
      tabStr: '全部',
      tabs: [
        {
          label: '全部'
        },
        {
          label: '可使用'
        },
        {
          label: '已使用'
        },
        {
          label: '已过期'
        },
      ],
      sortList: [
        {
            value: '到账日期',
            label: '到账日期'
        },
        {
            value: '过期日期',
            label: '过期日期'
        },
        {
            value: '优惠额度',
            label: '优惠额度'
        }
    ],
    modelSort: '到账日期',
    }
  },
  components: {
    usernodata
  },
  methods: {},
  mounted () {}
}
</script>
