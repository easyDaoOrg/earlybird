
<style lang='scss' scoped>
@import "./when-to-go.scss";
</style>
<template>
  <div class="flights-index">
    <div class="flights-index-search">
      <div class="flights-index-search-input">
        <div class="flights-index-search-input-left fl">
          <div class="fl col-10">
            <city :cityObj="startObj"></city>
          </div>

          <div class="fl col-10 flights-index-go">
            <city :cityObj="endObj"></city>
          </div>

          <router-link :to="{path:'/flights/timeline'}">
          <div class="flights-index-search-input-button fl">
            <Icon type="ios-search" />
          </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import city from './trip/city'

export default {
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
    city
  },
  methods: {

  },
  mounted () {}
}
</script>
