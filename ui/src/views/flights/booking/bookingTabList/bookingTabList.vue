
<style lang='scss' scoped>
@import "./bookingTabList.scss";
</style>
<template>
  <div class="booking-tablist">
    <div class="booking-tablist-title">余票有限</div>
    <div class="booking-tablist-content">
      <Tabs type="card" :animated="false">
        <TabPane label="1.成人票">
          <div class="booking-tablist-pane">
            <bookingContacts></bookingContacts>
            <bookingForm :bookingForm="0"></bookingForm>
          </div>
        </TabPane>
      </Tabs>
    </div>
  </div>
</template>

<script>
import bookingContacts from './../bookingContacts/bookingContacts'
import bookingForm from './../bookingForm/bookingForm'

export default {
  data () {
    return {

    }
  },
  components: {
    bookingContacts,
    bookingForm
  },
  watch: {

  },
  methods: {

  },
  mounted () {}
}
</script>
