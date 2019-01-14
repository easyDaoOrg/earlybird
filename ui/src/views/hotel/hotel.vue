
<style lang='scss' scoped>
@import "./hotel.scss";
</style>
<template>
  <div class="hotel">
    <div class="slogan-panel">最佳酒店价格，最优预订感受</div>
    <div class='hotel-panel'>
      <source-panel></source-panel>
    </div>
  </div>
</template>

<script>
import sourcePanel from './source-panel/source-panel'
export default {
  components: {
    sourcePanel
  },
  data () {
    return {}
  },
  mounted () {},
  methods: {}
}
</script>
