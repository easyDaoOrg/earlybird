
<style lang='scss' scoped>
@import "./clear.scss";
</style>
<style>
.clear-history .ivu-select-dropdown{
  padding: 0;
}
</style>

<template>
  <div class="clear-history fl" :class="{clearactive: queryHistory}" @click='$event.stopPropagation();'>
    <Dropdown trigger="click" placement="bottom-start">
        <span>
          <i class="iconfont icon-travel-clock"></i>
          <b v-if="historyList && historyList.length > 0"></b>
        </span>
        <DropdownMenu slot="list">
          <DropdownItem v-for="(item,index) in historyList" :key="index">
            <b class="fr" v-if="historyList && historyList.length>1 && index>0" @click="deleteHistory(index)"><Icon type="md-close-circle" /></b>
            <i class="clear-hidstory-air iconfont icon-travel-wangfan" v-if="item.tripType == 0"></i>
            <i class="clear-hidstory-air iconfont icon-travel-find" v-if="item.tripType == 1"></i>
            <i class="clear-hidstory-air iconfont icon-travel-list" v-if="item.tripType == 2"></i>
            <font @click="goTrip()">
              {{item.cityStart}}({{item.cityStartCode}})
              -
              {{item.cityEnd}}({{item.cityEndCode}})
              {{item.cityDate.start}}
              <span v-if="item.cityDate.end">- {{item.cityDate.end}}</span>,
              {{item.spaceType}}
            </font>
          </DropdownItem>
          <p v-if="historyList && historyList.length > 1" @click="clearHistory()">清除记录</p>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  props: ['queryHistory'],
  computed: {
    ...mapGetters(['history_list'])
  },
  data () {
    return {
      historyList: [],
    }
  },
  components: {

  },
  watch: {
    history_list: {
      handler: function (val, oldVal) {
        if(val){
          this.initData(JSON.parse(JSON.stringify(val)))
        }
      },
      immediate: true
    }
  },
  methods: {
    ...mapActions(['setClearFilterData']),
    //初始化
    initData(data){
      this.historyList = data;
    },
    //删除历史记录
    deleteHistory(index){
      this.historyList.splice(index,1)
      this.history_list.splice(index,1)
    },
    //清除所有
    clearHistory(){
      this.historyList = this.historyList.length>0 ? this.historyList[0] : [];
      this.setClearFilterData(this.historyList[0]);
    },
    //点击查看航程
    goTrip(){

    }
  },
  mounted () {

  }
}
</script>
