
<style lang='scss' scoped>
@import "./news-detail.scss";
</style>
<style>
</style>

<template>
  <div class="detail">
    <div class='detail-top'>
       <div class='news-cover'>
          <div class="cover-mask"></div>
          <div class="header-cover-bg"  style="background-image: url('https://content.igola.com/igola-cms/prod/news/fc1bff9021af46a289f88b848dbf1d7b.jpg'); opacity: 1;"></div>
        </div>
        <div class='news-desc'>
          <div class="news-desc-content">
            <h2>AI或将重构现有OTA购票模式</h2>
            <span class="news-title-icons"><Icon type="ios-camera" size="35" color="#fff"/></span>
            <span class="news-date">
              <b class="bo-box">2018年12月17日,  周一</b>
            </span>
          </div>
          <div class="detail-mode-pagination">
              <router-link :to="{path:'/news'}">
                <a><Icon type="md-return-left" size='27' color='#fff'/></a>
              </router-link>
          </div>
        </div>
    </div>

     <div class="news-detail-content">
       <a class='news-detail-share'><Icon type="md-share" size='23s' color='#fff'/></a>
       <div class="news-details-title-one">
         <h2>易道旅行斩获龙雀奖“最佳出境旅游服务机构”</h2>
         <b>2018年12月21日,  周五</b>
       </div>
     </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {}
  },
  components: {

  },
  mounted () {

  },
  methods: {
    routerLeave () {
      this.$router.push('/news')
    }
  }

}
</script>
