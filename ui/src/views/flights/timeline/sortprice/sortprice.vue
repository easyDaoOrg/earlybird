
<style lang='scss' scoped>
@import "./sortprice.scss";
</style>
<template>
  <div class="sortprice">
    <ul>
      <li v-for="(item,index) in sortpriceList" :key="index" :class="{'active':item.actived}" @click="tabSortPrice(item,index)">
        <div class="sortprice-icon">
          <i><Icon :type="item.type" /></i>
        </div>
        <div class="sortprice-info">
          <p>{{item.value}}</p>
          <span>£  {{item.price}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>


export default {
  data () {
    return {
      sortpriceIndex: 0,
      sortpriceList: [
        {
          value: "最低价",
          price: "326",
          type: 'ios-appstore-outline',
          actived: true
        },
        {
          value: "最快速",
          price: "326",
          type: 'md-analytics',
          actived: false
        },
        {
          value: "最佳性价比",
          price: "326",
          type: 'ios-heart',
          actived: false
        }
      ]
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    tabSortPrice(item,index){
      this.sortpriceList[this.sortpriceIndex].actived = false;
      this.sortpriceList[index].actived = true;
      this.sortpriceIndex = index;
    }
  },
  mounted () {}
}
</script>
