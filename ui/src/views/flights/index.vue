
<style lang='scss' scoped>
@import "./index.scss";
</style>
<template>
  <div class="flights-index">
    <div class="flights-index-search">
      <div class="flights-index-search-trip">
        <clear></clear>
        <div class="flights-index-search-trip-right fl">
          <div class="flights-index-search-trip-right-tab fl">
            <ul>
              <!-- <li @click="changeTravel(0)" :class="{active: changeIndex == 0}"><i class="iconfont icon-travel-wangfan"></i>往返</li> -->
              <li @click="changeTravel(1)" :class="{active: changeIndex == 1}"><i class="iconfont icon-travel-find"></i>
              <!-- 单程 -->
              请选择要查找的机票信息
              </li>
              <!-- <li @click="changeTravel(2)" :class="{active: changeIndex == 2}"><i class="iconfont icon-travel-list"></i>多程</li> -->
            </ul>
          </div>
          <div class="flights-index-search-trip-right-ove fl">
            <div class="fr">
              <group></group>
            </div>
          </div>
        </div>
      </div>
      <div class="flights-index-search-trip">
        <trip :tabIndex="changeIndex" v-if="changeIndex == 0 || changeIndex == 1"></trip>
        <moretrip :tabIndex="changeIndex" :tripList="tripList" v-if="changeIndex == 2"></moretrip>
      </div>
    </div>
  </div>
</template>

<script>
import clear from './clear/clear'
import trip from './trip/trip'
import moretrip from './trip/moretrip'
import group from './group/group'

export default {
  data () {
    return {
      value7: 1,
      visible: false,
      changeIndex: 1,
      tripList: [{}],
    }
  },
  components: {
    clear,
    trip,
    group,
    moretrip
  },
  methods: {
    // 切换单程／往返／多程
    changeTravel (index) {
      this.changeIndex = index
      switch (index) {
        case 0:
          this.tripList = []
        case 1:
          this.tripList = []
        case 2:
          this.tripList.push({})
      }
    },
  },
  mounted () {}
}
</script>
