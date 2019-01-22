
<style lang='scss' scoped>
@import "./../list/list.scss";
@import "./grouplist.scss";
</style>
<style>
  .timeline-list .ivu-card-body{
    padding: 0;
  }
  .timeline-list-card .ivu-card-bordered{
    border: 0 none;
    border-radius: 0;
  }
  .timeline-list .ivu-scroll-wrapper,
  .timeline-list .ivu-scroll-container{
    width: 960px;
  }
</style>

<template>
  <div class="timeline-list">
    <Scroll :on-reach-edge="handleReachEdge"  height="1000">
        <Card dis-hover v-for="(item, index) in list3" :key="index">
            <div class="timeline-list-card">
              <div class="timeline-grouplist-l fl">
                <!--去程-->
                <div class="timeline-grouplist-l-air">
                  <div class="timeline-list-card-header">
                    <div class="timeline-list-card-header-tips">海南航空 HU7611 , 机型: 789</div>
                  </div>
                  <div class="timeline-list-card-body">
                    <div class="timeline-list-card-body-box">
                      <span><img src="https://content.igola.com/static/APP/images/airline_logo/1x/24x24/hu.png"></span>
                    </div>
                    <div class="timeline-list-card-body-sted">
                      <div class="timeline-list-card-body-sted-block">
                        <div class="timeline-list-card-body-sted-block-time">
                          <i class="iconfont icon-travel-setsail"></i>
                          <b>08:01</b>
                        </div>
                        <div class="timeline-list-card-body-sted-block-address">
                          首都机场 <b>T1</b>
                        </div>
                      </div>
                      <div class="timeline-list-card-body-sted-mid">
                        <div class="timeline-list-card-body-sted-mid-change">

                        </div>
                        <div class="timeline-list-card-body-sted-mid-alltime">

                        </div>
                        <div class="timeline-list-card-body-sted-mid-stopover">

                        </div>
                      </div>
                      <div class="timeline-list-card-body-sted-block">
                        <div class="timeline-list-card-body-sted-block-time">
                          <i class="iconfont icon-travel-jiangla"></i>
                          <b>10:15</b>
                        </div>
                        <div class="timeline-list-card-body-sted-block-address">
                          浦东机场机场 <b>T2</b>
                        </div>
                      </div>
                    </div>
                    <div class="timeline-list-card-duration fr">
                      <i class="iconfont icon-travel-clock"></i>
                      <small>2h 5m</small>
                    </div>
                  </div>
                </div>

                <!--航程详情-->
                <div class="timeline-grouplist-c">
                  <span><Button @click="airDetailModel(false)">航程详情</Button></span>
                </div>

                <!--返程-->
                <div class="timeline-grouplist-l-air">
                  <div class="timeline-list-card-header">
                    <div class="timeline-list-card-header-tips">海南航空 HU7611 , 机型: 789</div>
                  </div>
                  <div class="timeline-list-card-body">
                    <div class="timeline-list-card-body-box">
                      <span><img src="https://content.igola.com/static/APP/images/airline_logo/1x/24x24/hu.png"></span>
                    </div>
                    <div class="timeline-list-card-body-sted">
                      <div class="timeline-list-card-body-sted-block">
                        <div class="timeline-list-card-body-sted-block-time">
                          <i class="iconfont icon-travel-setsail"></i>
                          <b>08:01</b>
                        </div>
                        <div class="timeline-list-card-body-sted-block-address">
                          首都机场 <b>T1</b>
                        </div>
                      </div>
                      <div class="timeline-list-card-body-sted-mid">
                        <div class="timeline-list-card-body-sted-mid-change">

                        </div>
                        <div class="timeline-list-card-body-sted-mid-alltime">

                        </div>
                        <div class="timeline-list-card-body-sted-mid-stopover">

                        </div>
                      </div>
                      <div class="timeline-list-card-body-sted-block">
                        <div class="timeline-list-card-body-sted-block-time">
                          <i class="iconfont icon-travel-jiangla"></i>
                          <b>10:15</b>
                        </div>
                        <div class="timeline-list-card-body-sted-block-address">
                          浦东机场机场 <b>T2</b>
                        </div>
                      </div>
                    </div>
                    <div class="timeline-list-card-duration fr">
                      <i class="iconfont icon-travel-clock"></i>
                      <small>2h 5m</small>
                    </div>
                  </div>
                </div>
              </div>

              <!--right-->
              <div class="timeline-grouplist-r fl">
                <div class="timeline-list-card-header-score fr">
                  <i class="iconfont icon-travel-zan"></i>
                  <b>9.9</b>
                </div>
                <div class="timeline-list-card-footer-price fr">
                  <p>
                    <b>£</b>144
                  </p>
                  <span>含税总价</span>
                </div>
                <div class="timeline-grouplist-btn">
                  <Button type="warning"  @click="airDetailModel(true)">帮我买</Button>
                </div>
              </div>

            </div>
        </Card>
    </Scroll>
  </div>
</template>

<script>

export default {
  data () {
    return {
      list3: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    airDetailModel(bool){
      this.$bus.emit('on-airdetail',bool)
    },
    handleReachEdge (dir) {
        return new Promise(resolve => {
            setTimeout(() => {
                if (dir > 0) {
                    const first = this.list3[0];
                    for (let i = 1; i < 11; i++) {
                        this.list3.unshift(first - i);
                    }
                } else {
                    const last = this.list3[this.list3.length - 1];
                    for (let i = 1; i < 11; i++) {
                        this.list3.push(last + i);
                    }
                }
                resolve();
            }, 2000);
        });
    }
  },
  mounted () {}
}
</script>
