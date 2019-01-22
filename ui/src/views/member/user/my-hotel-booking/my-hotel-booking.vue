
<style lang='scss' scoped>
@import "./my-hotel-booking.scss";
</style>
<template>
  <div class="my-hotel-booking">
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
          label: '未入住'
        },
        {
          label: '已入住'
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
