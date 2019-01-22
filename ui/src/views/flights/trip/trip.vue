
<style lang='scss' scoped>
@import "./trip.scss";
</style>
<template>
  <div class="flights-index-search-input" :class="{timelineActive: searchClass}">
    <div class="flights-index-airport clearfix">
        <div class="flights-index-search-input-left fl">
        <div class="flights-index-search-input-left-change">
          <i class="iconfont icon-travel-return"></i>
        </div>
        <div class="fl col-10">
          <city :cityObj="startObj"></city>
        </div>
        <div class="fl col-10">
          <city :cityObj="endObj"></city>
        </div>
      </div>
      <div class="flights-index-search-input-right fl">
          <date :tabIndex="tabIndex"></date>

          <div class="flights-index-search-input-city flights-index-begin fl ">
            前后3天
          </div>

          <router-link :to="{path:'/flights/timeline'}">
          <div class="flights-index-search-input-button fl">
            <Icon type="ios-search" />
          </div>
          </router-link>
      </div>
    </div>
  </div>

</template>

<script>
import city from './city'
import date from './date'

export default {
  props: ['tabIndex', 'searchClass'],
  data () {
    return {
      startObj: {
        name: '出发',
        className: 'icon-travel-setsail',
        foat: 'fl',
        classInput: ''
      },
      endObj: {
        name: '目的地',
        className: 'icon-travel-jiangla',
        foat: 'fr',
        classInput: 'flights-index-org'
      }
    }
  },
  components: {
    city,
    date
  },
  watch: {

  },
  methods: {

  },
  mounted () {}
}
</script>
