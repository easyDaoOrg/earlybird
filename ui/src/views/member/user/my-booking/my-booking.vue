
<style lang='scss' scoped>
@import "./my-booking.scss";
</style>
<template>
  <div class="my-booking">
    <Tabs type="card" :animated="false">
        <TabPane v-for="(item,index) in tabs" :key="index" :label="item.label">
          <usernodata></usernodata>
        </TabPane>
    </Tabs>
  </div>
</template>

<script>
import usernodata from './../user-nodata/user-nodata'

export default {
  data () {
    return {
      tabs: [
        {
          label: '全部'
        },
        {
          label: '未出行'
        },
        {
          label: '已出行'
        },
      ]
    }
  },
  components: {
    usernodata
  },
  methods: {},
  mounted () {}
}
</script>
