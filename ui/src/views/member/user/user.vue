
<style lang='scss' scoped>
@import "./user.scss";
</style>
<template>
  <div class="user">
    <div class="user-header">
      <div class="user-header-img">
        <span>更换头像</span>
      </div>
      <div class="user-header-tel">18655554567</div>
    </div>
    <div class="user-content">
      <div class="user-content-left fl">
        <usernav></usernav>
      </div>
      <div class="user-content-right">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import usernav from './user-nav/user-nav'

export default {
  data () {
    return {}
  },
  components: {
    usernav
  },
  methods: {},
  mounted () {}
}
</script>
