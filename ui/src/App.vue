<template>
  <div id="app" @click='close()'>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {}
  },
  components: {},
  methods: {
    close () {
      this.$bus.emit('overall-close', false)
    }
  }
}
</script>

<style lang="scss">
#app {
  width: 100%;
  height: 100%;
  font-size: 14px;
  overflow: hidden;
}
</style>
