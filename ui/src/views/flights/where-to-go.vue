
<style lang='scss' scoped>
@import "./where-to-go.scss";
</style>
<template>
  <div class="flights-index">
    <div class="flights-index-search">
      <div class="flights-index-search-input">
        <div class="flights-index-search-input-left fl">
          <div class="fl col-10">
            <city :cityObj="startObj"></city>
          </div>
        </div>

        <div class="flights-index-search-input-right fl">
          <div class="fl col-10">
            <date :tabIndex="tabIndex"></date>
            <router-link :to="{path:'/flights/timeline'}">
              <div class="flights-index-search-input-button fl">
                <Icon type="ios-search" />
              </div>
            </router-link>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import city from './trip/city'
import date from './trip/date'

export default {
  data () {
    return {
      tabIndex: 0,
      startObj: {
        name: '出发',
        className: 'icon-travel-setsail',
        foat: 'fl',
        classInput: ''
      },
    }
  },
  components: {
    city,
    date
  },
  methods: {

  },
  mounted () {}
}
</script>
