
<style lang='scss' scoped>
@import "./trip.scss";
</style>
<style>
  .flights-index-search-input-city .ivu-input{
    background: transparent;
  }
</style>

<template>
  <div class="flights-index-search-input-city flights-index-search-input-city-date fl ">
    <i class="fl"><Icon type="md-calendar" /></i>
    <span class="fl" v-if="tabIndex === 0">
      <DatePicker type="daterange" split-panels placeholder="出发日期/返回日期" style="width: 98%;"></DatePicker>
    </span>
    <span class="fl" v-if="tabIndex === 1 || tabIndex === 2">
      <DatePicker type="date" confirm placeholder="出发日期" style="width: 98%"></DatePicker>
    </span>
  </div>
</template>

<script>


export default {
  props: ['tabIndex'],
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
