
<style lang='scss' scoped>
@import "./user-nav.scss";
</style>
<template>
  <div class="user-nav">
    <dl>
      <router-link :to="{path:'/member/user/my-booking'}">
      <dt :class="{activetop: isActive == '/member/user/my-booking' || isActive == '/member/user/my-hotel-booking'}">
        <i><Icon type="ios-clipboard-outline" /></i>我的订单
      </dt>
      </router-link>
      <dd v-if="isActive == '/member/user/my-booking' || isActive == '/member/user/my-hotel-booking'">
        <ul>
          <router-link :to="{path:'/member/user/my-booking'}">
            <li :class="{active: isActive == '/member/user/my-booking'}">机票订单</li>
          </router-link>
          <router-link :to="{path:'/member/user/my-hotel-booking'}">
            <li :class="{active: isActive == '/member/user/my-hotel-booking'}">酒店订单</li>
          </router-link>
        </ul>
      </dd>
    </dl>
    <dl>
      <router-link :to="{path:'/member/user/my-cashback'}">
      <dt :class="{activetop: isActive == '/member/user/my-cashback'}">
        <i class="iconfont icon-travel-qianbao"></i>我的返现
      </dt>
      </router-link>
    </dl>
    <dl>
      <router-link :to="{path:'/member/user/account-setting'}">
      <dt :class="{activetop: isActive == '/member/user/account-setting'}">
        <i><Icon type="ios-settings-outline" /></i>账户设置
      </dt>
      </router-link>
    </dl>
    <dl>
      <router-link :to="{path:'/member/user/passengers'}">
      <dt :class="{activetop: isActive == '/member/user/passengers'}">
        <i><Icon type="ios-contacts-outline" /></i>常用旅客
      </dt>
      </router-link>
    </dl>
    <dl>
       <router-link :to="{path:'/member/user/contacts'}">
        <dt :class="{activetop: isActive == '/member/user/contacts'}">
          <i><Icon type="ios-people-outline" /></i>常用联系人
        </dt>
      </router-link>
    </dl>
    <dl>
      <router-link :to="{path:'/member/user/coupons'}">
      <dt v-bind:class="{activetop: isActive == '/member/user/coupons'}">
        <i class="iconfont icon-travel-coupon"></i><b>优惠券</b>
      </dt>
      </router-link>
    </dl>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      isActive: '/member/user/my-booking'
    }
  },
  components: {

  },
  watch: {
    '$route': 'isActivedRoute'
  },
  methods: {
    isActivedRoute () {
      this.isActive = this.$route.path
    }
  },
  mounted () {
    this.isActivedRoute()
  }
}
</script>
