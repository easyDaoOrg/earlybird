<style lang='scss' scoped>
@import "./header.scss";
</style>

<template>
  <div class='hewderWrap'>
    <Row type="flex" justify="space-between" class="header">
      <i-col class="header-list">
        <div class="header-list-menu">
          <div class="header-list-menu-title">机票</div>
          <div class="header-list-menu-down"><Icon type="ios-arrow-down" /></div>
          <div class="header-list-menu-list">
            <ul>
              <router-link :to="{path:'/flights/index'}">
                <li>机票</li>
              </router-link>
              <router-link :to="{path:'/hotel'}">
                <li>酒店</li>
              </router-link>
              <router-link :to="{path:'/member/user/my-booking'}">
              <li>订单查询</li>
              </router-link>
              <router-link :to="{path:'/news'}">
                <li>媒体中心</li>
              </router-link>
              <router-link :to="{path:'/brand/about-us'}">
                <li>关于易道</li>
              </router-link>
              <router-link :to="{path:'/terms/yidao-guarantee'}">
                <li>易道保障</li>
              </router-link>
            </ul>
          </div>
        </div>
      </i-col>

      <i-col class="header-logo">
        <router-link :to="{path:'/'}">
          <img src="../../assets/images/yidao-logo-1.png" alt width="90">
        </router-link>
      </i-col>

      <i-col class="header-right">
        <div class="header-right-login">
          <div v-if="!isToken">
            <router-link :to="{path:'/member/account/sign-in'}">
              <span class="header-right-login-icon"><Icon type="ios-person-outline"/></span>登录
            </router-link>
          </div>

          <div class="header-right-login-list" v-if="isToken">
            <span class="header-right-login-icon"><Icon type="ios-person-outline"/></span>
            <span class="header-list-menu-down"><Icon type="ios-arrow-down" /></span>
            <ul>
              <router-link :to="{path:'/member/user/my-booking'}">
                <li>我的订单</li>
              </router-link>
              <router-link :to="{path:'/member/user/account-setting'}">
                <li>我的返现</li>
              </router-link>
              <router-link :to="{path:'/member/user/account-setting'}">
                <li>账户设置</li>
              </router-link>
              <router-link :to="{path:'/member/user/passengers'}">
                <li>常用旅客</li>
              </router-link>
              <router-link :to="{path:'/member/user/contacts'}">
                <li>常用联系人</li>
              </router-link>
              <router-link :to="{path:'/member/user/coupons'}">
                <li>优惠券</li>
              </router-link>
              <li @click="layoutEasydao()">退出</li>
            </ul>
          </div>

        </div>
        <div class="header-right-side">
          <span class="header-right-side-languagebtn">
            <i><img src="../../assets/images/china.svg" alt="" width="32" height="24">
</i>简体中文 (GBP)
          </span>
          <span class="header-right-side-media">
            <b>关注我们</b>
            <span><i class="iconfont icon-travel-sina" /></span>
            <span class="header-right-side-media-wx"><i class="iconfont icon-travel-wechat" />
              <div class="header-right-side-media-weixin">
                <img src="../../assets/images/weixin.jpg">
                <p>易道旅行</p>
              </div>
            </span>
          </span>
        </div>
      </i-col>
    </Row>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import Common from '@/lib/comm.js'
import Util from '@/lib/utils.js'

export default {
  mixins: [Common],
  name: 'App',
  data () {
    return {
      projectName: '项目选择',
      projectList: [],
      activeName: 'projectName',
      isToken: Util.getCookie('token')
    }
  },
  components: {
    // User,
  },
  computed: {
    ...mapGetters(['errorListLoading'])
  },
  methods: {
    ...mapActions(['updateErrorOvierDataList']),
    busChange () {
      this.$bus.emit('on-userName', '我是bus')
    },

    //退出
    layoutEasydao(){
      this.deleteCookie('token');
      this.deleteCookie('userId');
      this.isToken = null;
      this.$router.push(`/member/account/sign-in`);
    }
  },
  watch: {
    $route: {
      handler: function (val, oldVal) {
        if(val){
          this.isToken = Util.getCookie('token');
        }
      },
      immediate: true
    }
  },
  mounted () {

  }
}
</script>
