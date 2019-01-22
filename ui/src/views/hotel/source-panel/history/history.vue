
<style lang='scss' scoped>
@import "./history.scss";
</style>
<style>

</style>

<template>
  <div class="history" @click='$event.stopPropagation();visible=!visible' :class="visible?'click':'noClick'" v-if='data.length'>
    <div class="history-title">
      搜索历史<span>{{data.length}}</span>
    </div>
    <div class="history-content" v-for='(item,index) in data' :key='index'>
      <span>{{item.name}}</span>
      <span>{{item.time}}</span>
      <Icon type="md-close" class='close' @click='$event.stopPropagation();onClose(item,index)'/>
    </div>
    <p class="clear-history" @click='$event.stopPropagation();onCloseAll()'>清空历史</p>
  </div>
</template>

<script>

export default {
  components: {},
  data () {
    return {
      visible: false,
      data: [
        {
          name: '上海',
          time: '01月14日 - 01月15日'
        },
        {
          name: '北京',
          time: '01月13日 - 01月14日'
        }
      ]
    }
  },
  mounted () {
    this.$bus.on('overall-close', () => {
      this.visible = false
    })
  },
  watch: {

  },
  methods: {
    onClose (option, index) {
      this.data.splice(index, 1)
    },
    onCloseAll () {
      this.data = []
    }
  }
}
</script>
