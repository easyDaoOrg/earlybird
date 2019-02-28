
<style lang='scss' scoped>
@import "./search.scss";
</style>
<template>
  <div class="search">
    <!--清除记录-->
    <clear :queryHistory="queryHistory"></clear>

    <div class="search-bar">
      <!--切换往返-->
      <!-- <div class="search-bar-change fl">
        <Dropdown trigger="click" placement="bottom-start">
            <span>
              <b>{{menuName}}</b>
              <i><Icon type="ios-arrow-down" /></i>
            </span>
            <DropdownMenu slot="list">
              <DropdownItem v-for="(item,index) of menuList"  :class="{active: changeIndex == index}" :key="index">
                <div @click="changeTravel(item,index)">
                  <b class="fr" v-if="changeIndex == index"><Icon type="md-checkmark-circle" /></b>{{item.name}}
                </div>
              </DropdownItem>
            </DropdownMenu>
        </Dropdown>
      </div> -->

      <div class="search-bar-line fl"></div>

      <!--选择人／舱位-->
      <group></group>

      <!--选择地点／时间-->
      <div class="search-bar-tab fl">
        <trip :tabIndex="changeIndex" :searchClass="searchClass" v-if="changeIndex == 0 || changeIndex == 1"></trip>
        <moretrip :tabIndex="changeIndex" :searchClass="searchClass" :tripList="tripList" v-if="changeIndex == 2"></moretrip>
      </div>

    </div>
  </div>
</template>

<script>
import clear from './../../clear/clear'
import trip from './../../trip/trip'
import moretrip from './../../trip/moretrip'
import group from './../../group/group'

export default {
  data () {
    return {
      changeIndex: 1,
      searchClass: true,
      queryHistory: true,
      menuList: [
        { name: '往返' },
        { name: '单程' },
        { name: '多程' }
      ],
      menuName: '往返',
      tripList: [{}]
    }
  },
  components: {
    clear,
    trip,
    group,
    moretrip
  },
  watch: {

  },
  methods: {
    // 切换单程／往返／多程
    changeTravel (item, index) {
      this.changeIndex = index
      this.menuName = item.name
    }
  },
  mounted () {}
}
</script>
