
<style lang='scss' scoped>
@import "./sortbar.scss";
</style>
<template>
  <div class="sort-bar">
    <ul>
      <li v-for="(item,index) in sortList" :key="index" :class="{sortbaracitve: item.lbselected}">
        <a>
          <font @click="sortBarChange(item,'up')">{{item.value}}</font>
          <span>
            <i class="arrowup">
              <Icon type="ios-arrow-up"  :class="{active: item.upselected}"  @click="sortBarChange(item,'up')"/>
            </i>
            <i class="arrowdown">
              <Icon type="ios-arrow-down" :class="{active: item.dwselected}"  @click="sortBarChange(item,'down')"/>
            </i>
          </span>
        </a>
      </li>
    </ul>
  </div>
</template>
<script>

export default {
  data () {
    return {
      sortList: [
        {
          label: 'price',
          value: '价格',
          lbselected: false,
          upselected: true,
          dwselected: false
        },
        {
          label: 'longtime',
          value: '飞行时长',
          lbselected: false,
          upselected: false,
          dwselected: false
        },
        {
          label: 'starttime',
          value: '起飞时间',
          lbselected: false,
          upselected: false,
          dwselected: false
        },
        {
          label: 'endtime',
          value: '到达时间',
          lbselected: false,
          upselected: false,
          dwselected: false
        },
        // {
        //   label: 'prepare',
        //   value: '性价比',
        //   lbselected: false,
        //   upselected: false,
        //   dwselected: false
        // }
      ]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    sortBarChange (item,type) {
      for (let i of this.sortList) {
        i.lbselected = false;
        i.upselected = false;
        i.dwselected = false;
      }
      item.lbselected = true;
      switch (type){
        case 'up':
          item.upselected = true;
          break;
        case 'down':
          item.dwselected = true;
          break;
      }
      this.$bus.emit('trip-airport-list',item)
    }
  },
  mounted () {}
}
</script>
