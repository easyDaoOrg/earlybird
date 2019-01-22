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
          <router-link :to="{path:'/member/account/sign-in'}">
            <span class="header-right-login-icon"><Icon type="ios-person-outline"/></span>登录
          </router-link>
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
                <p>易到旅行</p>
              </div>
            </span>
          </span>
        </div>
      </i-col>

      <!-- <i-col>
        <router-link :to="{path:'/home'}">
          <span style="display:inline-block;vertical-align: top;">
           <img style="width: 118px;height: 32px;" src="../assets/logo.png" alt>
          </span>
        </router-link>
        <div @click="updateErrorOvierDataList(true)">点击我--vuex会通讯</div>
        <div @click="busChange()">点击我--bus会通讯</div>
        {{errorListLoading}}
      </i-col> -->
    </Row>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'App',
  data () {
    return {
      projectName: '项目选择',
      projectList: [],
      activeName: 'projectName'
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
    }
  },
  watch: {
    // $route(newVal) {
    //   if (newVal.name !== 'login' && newVal.name !== 'home') {
    //     this.getProjectOview();
    //   }
    //   if (newVal.name === 'projectcontent') {
    //     this.activeName = 'manage';
    //   }
    //   if (newVal.name === 'erroroverview') {
    //     this.activeName = 'control';
    //   }
    // },
  },
  mounted () {}
}
</script>
