
<style lang='scss' scoped>
@import "./source-panel.scss";
</style>

<template>
  <div class="panel" :class="{'resultStyle':type=='result-list'}">
    <!-- 城市 -->
    <city :type="type" @other-close="otheClose" ref='city'></city>
    <date :type="type" @other-close="otheClose" ref='date'></date>
    <room :type="type" @other-close="otheClose" ref='room'></room>
    <place :type="type" @other-close="otheClose" ref='place'></place>
    <div class="search-btn" @click="onSearch()">搜索</div>
    <history v-if="type!='result-list'"></history>
  </div>
</template>

<script>
import city from './city/city'
import date from './date/date'
import room from './room/room'
import place from './place/place'
import history from './history/history'
export default {
  components: { city, date, room, place, history },
  props: {
    type: {
      default: 'hotel',
      type: String
    }
  },
  data () {
    return {
      list: ['city', 'date', 'room', 'place']
    }
  },
  mounted () {},
  methods: {
    onSearch () {
      this.$emit('on-search', '')
    },
    otheClose (type) {
      this.list.forEach((item) => {
        if (item !== type) {
          this.$refs[item].clear()
        }
      })
    }
  }
}
</script>
