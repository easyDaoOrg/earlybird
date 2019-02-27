
<style lang='scss' scoped>
@import "./trip.scss";
</style>
<style>
  .flights-index-search-input-city .ivu-input{
    background: transparent;
    box-shadow: 0 0 0;
  }
</style>

<template>
  <div class="flights-index-search-input-city flights-index-search-input-city-date fl ">
    <i class="fl"><Icon type="md-calendar" /></i>
    <span class="fl" v-if="tabIndex === 0">
      <DatePicker
        format="MM月dd日"
        type="daterange"
        split-panels
        placeholder="出发日期/返回日期"
        style="width: 98%;"
        @on-change="handleChange"
      >
      </DatePicker>
    </span>
    <span class="fl" v-if="tabIndex === 1 || tabIndex === 2">
      <DatePicker
        format="MM月dd日"
        type="date"
        confirm
        placeholder="出发日期"
        style="width: 98%;"
        @on-change="handleChange"
      ></DatePicker>
    </span>
  </div>
</template>

<script>
import Util from '@/lib/utils'

export default {
  props: ['tabIndex','dateObj'],
  data () {
    return {

    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    handleChange(date){
      if(typeof date == 'string'){
        this.dateObj.start = date ? date : "";
      }else{
        this.dateObj.start = date[0] ? date[0] : "";
        this.dateObj.end = date[1] ? date[1] : ""
      }
    },
  },
  mounted () {

  }
}
</script>
