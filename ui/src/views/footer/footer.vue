<style lang='scss' scoped>
@import "./footer.scss";
</style>

<template>
  <div>
    <Row type="flex" justify="space-between" class="footer">
      <i-col class="footer-linkslist">
        <ul>
          <router-link :to="{path:'/brand/about-us'}">
            <li>关于易道</li>
          </router-link>
          <router-link :to="{path:'/news'}">
            <li>媒体中心</li>
          </router-link>
          <router-link :to="{path:'/brand/our-team'}">
            <li>团队介绍</li>
          </router-link>
          <router-link :to="{path:'/terms/legal-notices'}">
            <li class="footer-none">法律声明</li>
          </router-link>
        </ul>
        <ol>
          <span>友情链接：</span>
          <li>国际机票</li>
          <li>泰国签证</li>
          <li>银川旅游</li>
          <li>旅游资讯</li>
          <li>定制旅游</li>
        </ol>
      </i-col>
      <i-col class="footer-icpinfo">
        <p>广东省广州市海珠区新港东路1022号  电话: +86 400-900-7280 | +61 180-073-2481 | +64 800-424-315</p>
        <p>©2018 – 2019 北京市易到信息技术咨询服务有限公司版权所有。ICP: 京ICP备14048872号-3 </p>
      </i-col>
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
