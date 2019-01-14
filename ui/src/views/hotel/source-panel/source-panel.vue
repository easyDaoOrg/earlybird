
<style lang='scss' scoped>
@import "./source-panel.scss";
</style>

<template>
  <div class="panel">
    <!-- 城市 -->
    <city></city>
  </div>
</template>

<script>
import city from './city/city'
export default {
  components: {city},
  data () {
    return {

    }
  },
  mounted () {},
  methods: {

  }
}
</script>
