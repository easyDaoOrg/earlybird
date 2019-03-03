
<style lang='scss' scoped>
@import "./timeline.scss";
</style>
<style>
  .ivu-affix .search{
    margin: 0!important;
    background: #000;
    z-index: 10;
    padding: 0!important;
  }
  .ivu-affix .timeline-container::after{
    content: '';
    width: 100%;
    /* height: 137px; */
    height: 45px;
    background: #323232;
    position: fixed;
    left: 0;
    top: 50px;
    z-index: -1;
  }
  .ivu-affix .timeline-container {
    display: none;
  }
  .ivu-affix .timeline-barbot{
    display: block;
  }
  .ivu-affix .timeline-container .filter-panel{
    background: transparent;
    color: #fff;
  }
  .ivu-affix .timeline-container .filter-panel .filter-panel-box-airports{
    color: #fff;
  }
  .ivu-affix .timeline-container-tab,
  .ivu-affix .filter-fill,
  .ivu-affix .filter-bar{
    display: none;
  }
  .ivu-affix .sort{
    display: block;
  }
</style>

<template>
  <div class="timeline">
    <div class="timeline-tips"><b>{{timelineTotal}}</b>个结果</div>
    <Affix>
      <div class="demo-affix">
      <search></search>

      <div class="timeline-container" :class="{'sort': sortClass}">
        <!--自由搭配&航班组合-->
        <!-- <div class="timeline-container-tab">
          <div v-for="(item,index) in tabList" :key="index" :class="{'active':item.actived}" @click="tabTimeline(item,index)">
            <i class="iconfont" :class="item.tabClass"></i>
            <span>{{item.value}}</span>
          </div>
        </div> -->

        <!--选择-->
        <div class="time-container-control">
          <!-- <filterBar :pTabIndex="tabIndex"></filterBar> -->
          <!-- <sortprice v-if="tabIndex == 1"></sortprice> -->
          <sortBar v-if="tabIndex == 1"></sortBar>
        </div>

      </div>

      <div class="timeline-barbot" @click="sortShow()">
        筛选和排序
      </div>
      </div>
    </Affix>

    <div class="timeline-container">
      <list></list>
      <!-- <grouplist v-if="tabIndex == 1"></grouplist> -->
    </div>

    <triplayer></triplayer>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import search from './search/search'
import list from './list/list'
import grouplist from './grouplist/grouplist'
import sortBar from './sortbar/sortbar'
import filterBar from './filter/filter'
import sortprice from './sortprice/sortprice'
import triplayer from './triplayer/triplayer'
import { airportListData } from "../../../assets/json/airportListData.js";


export default {
  computed: {
    ...mapGetters(['airport_list'])
  },
  data () {
    return {
      tabIndex: 1,
      sortClass: false,
      tabList: [
        {
          value: "自由搭配",
          tabClass: "icon-travel-ziyou",
          actived: true
        },
        {
          value: "航班组合",
          tabClass: "icon-travel-zuhe",
          actived: false
        }
      ],
      timelineTotal: 0
    }
  },
  components: {
    search,
    list,
    sortBar,
    filterBar,
    sortprice,
    grouplist,
    triplayer
  },
  watch: {
    airport_list: {
      handler: function (val, oldVal) {
        if(val){
          console.log(val,"===========")
          // this.timelineTotal = airportListData.total;
          if(val&&val.data&&val.data.total){
            this.timelineTotal = val.data.total;
          }
        }
      },
      immediate: true
    }
  },
  methods: {
    tabTimeline(item,index){
      this.tabList[this.tabIndex].actived = false;
      this.tabList[index].actived = true;
      this.tabIndex = index;
    },
    sortShow(){
      this.sortClass = this.sortClass == true ? false : true;
    }
  },
  mounted () {
  }
}
</script>
