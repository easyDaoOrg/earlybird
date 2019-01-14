
<style lang='scss' scoped>
@import "./news.scss";
</style>

<template>
  <div class="news">
      <div  @click="routerLeave(1)">
        <Row>
          <Col span="14">
            <div
              class="cover-img"
              style="background-image: url('https://content.igola.com/igola-cms/prod/news/fc1bff9021af46a289f88b848dbf1d7b.jpg'); opacity: 1;"
            ></div>
          </Col>
          <Col span="10">
            <div class="news-padding">
              <div class="news-desc-content">
                <h2>iGola骑鹅旅行斩获龙雀奖“最佳出境旅游服务机构”</h2>
                <span class="news-title-icons">
                  <Icon type="ios-camera" size="35" color="#fff"/>
                </span>
                <span class="news-date">
                  <b class="bo-box">2018年12月21日, 周五</b>
                  <Icon type="md-share"/>
                </span>
                <p>
                  <b
                    class="bo-box"
                  >12月18日，由旅游卫视指导、知名文旅产业新媒体执惠主办的2018第三届中国文旅大消费年度峰会暨“龙雀奖”颁奖盛典颁奖盛典”在上海举行。</b>
                </p>
              </div>
            </div>
          </Col>
        </Row>
      </div>
      <div  @click="routerLeave(2)">
        <Row>
          <Col span="10">
            <div class="news-padding">
              <div class="news-desc-content">
                <h2>AI或将重构现有OTA购票模式</h2>
                <span class="news-title-icons">
                  <Icon type="ios-camera" size="35" color="#fff"/>
                </span>
                <span class="news-date">
                  <b class="bo-box">2018年12月21日, 周五</b>
                  <Icon type="md-share"/>
                </span>
                <p>
                  <b
                    class="bo-box"
                  >飞机是人们境外出差、旅行最普遍的交通工具，但是随着购票平台、航班选择等增多，以及信息不对称等因素的影响，买一张心仪的、性价比高的机票，耗费的时间也越来越多......</b>
                </p>
              </div>
            </div>
          </Col>
          <Col span="14">
            <div
              class="cover-img"
              style="background-image: url('https://content.igola.com/igola-cms/prod/news/fa0a39863f6c4e7c8750d814c6f5b086.jpg'); opacity: 1;"
            ></div>
          </Col>
        </Row>
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
    routerLeave (id) {
      this.$router.push(`/news/detail/${id}`)
    }
  }

}
</script>
