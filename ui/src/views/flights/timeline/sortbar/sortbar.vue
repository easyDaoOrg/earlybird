
<style lang='scss' scoped>
@import "./sortbar.scss";
</style>
<template>
  <div class="sort-bar">
    <ul>
      <li v-for="(item,index) in sortList" :key="index" :class="{sortbaracitve: item.lbselected}" @click="sortBarChange(item)">
        <a>
          {{item.value}}
          <span>
            <i class="arrowup" :class="{active: item.upselected}"><Icon type="ios-arrow-up" /></i>
            <i class="arrowdown" :class="{active: item.dwselected}"><Icon type="ios-arrow-down" /></i>
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
          upselected: false,
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
        {
          label: 'prepare',
          value: '性价比',
          lbselected: false,
          upselected: false,
          dwselected: false
        }
      ]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    sortBarChange (item) {
      for (let i of this.sortList) {
        i.lbselected = false
      }
      item.lbselected = item.lbselected == false;
    }
  },
  mounted () {}
}
</script>
