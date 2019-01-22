
<style lang='scss' scoped>
@import "./hotel-rate.scss";
</style>
<template>
 <div class="rate">
    <p
      :class="{'selected':firstSelected===item.value}"
      v-for="(item,index) in firstData"
      :key="index"
      @click="onFirst(item.value)"
    >{{item.label}}</p>
</div>
</template>

<script>
export default {
  components: {
  },
  data () {
    return {
      firstData: [
        {
          label: '受欢迎度优先',
          value: 'welcome'
        },
        {
          label: '评分优先',
          value: 'evaluate'
        },
        {
          label: '低价优先',
          value: 'lowPrice'
        },
        {
          label: '高价优先',
          value: 'highPrice'
        }
      ],
      firstSelected: ''
    }
  },
  mounted () {
    this.onFirst('welcome')
  },
  methods: {
    onFirst (data) {
      this.firstSelected = data
      this.$emit('on-rate', data)
    }
  }
}
</script>
