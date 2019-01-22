
<style lang='scss' scoped>
@import "./bookingstep.scss";
</style>
<template>
  <div class="booking-step">
    <Steps :current="current">
        <Step title="填写"></Step>
        <Step title="支付"></Step>
        <Step title="完成"></Step>
    </Steps>
  </div>
</template>

<script>


export default {
  data () {
    return {
      current: 0
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
