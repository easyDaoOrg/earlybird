
<style lang='scss' scoped>
@import "./select.scss";
</style>

<template>
  <Select v-model="value" :placeholder="bookingFormPlaceholder" style="width:100%"  @on-change="selectChange">
    <Option v-for="item in bookingFormList" :value="item.value" :key="item.value">{{ item.label }}</Option>
  </Select>
</template>

<script>


export default {
  props: ['bookingFormModel','bookingFormList','bookingFormPlaceholder'],
  data () {
    return {
      value: ''
    }
  },
  components: {

  },
  watch: {

  },
  computed: {

  },
  methods: {
    selectChange(){
      this.$bus.emit('on-selectchange',this.bookingFormModel,this.value)
    }
  },
  mounted () {}
}
</script>
