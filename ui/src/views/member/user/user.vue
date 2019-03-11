
<style lang='scss' scoped>
@import "./user.scss";
</style>
<template>
  <div class="user">
    <div class="user-header">
      <div class="user-header-img">
        <span>更换头像</span>
      </div>
      <div class="user-header-tel">{{isUserAccount}}</div>
    </div>
    <div class="user-content">
      <div class="user-content-left">
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
import Util from '@/lib/utils.js'

export default {
  data () {
    return {
      isUserAccount: Util.getCookie('userAccount')
    }
  },
  components: {
    usernav
  },
  methods: {},
  mounted () {}
}
</script>
