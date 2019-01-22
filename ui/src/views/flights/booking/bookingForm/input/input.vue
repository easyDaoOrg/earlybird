
<style lang='scss' scoped>
@import "./input.scss";
</style>

<template>
  <Input v-model="value" :placeholder="bookingFormPlaceholder" @on-change="inputChange"></Input>
</template>

<script>


export default {
  props: ['bookingFormModel','bookingFormPlaceholder'],
  data () {
    return {
      value: ''
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    inputChange(){
      this.$bus.emit('on-selectchange',this.bookingFormModel,this.value)
    }
  },
  mounted () {}
}
</script>
