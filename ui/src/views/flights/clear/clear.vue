
<style lang='scss' scoped>
@import "./clear.scss";
</style>
<template>
  <div class="clear-history fl" :class="{clearactive: queryHistory}">
    <Dropdown trigger="click" placement="bottom-start">
        <span>
          <i class="iconfont icon-travel-clock"></i>
        </span>
        <DropdownMenu slot="list">
          <DropdownItem v-for="(item,index) in historyList" :key="index">
            <b class="fr" v-if="historyList && historyList.length > 1" @click="deleteHistory(index)"><Icon type="md-close-circle" /></b>
            <i class="clear-hidstory-air iconfont icon-travel-wangfan" v-if="item.tripType == 0"></i>
            <i class="clear-hidstory-air iconfont icon-travel-find" v-if="item.tripType == 1"></i>
            <i class="clear-hidstory-air iconfont icon-travel-list" v-if="item.tripType == 2"></i>
            <font @click="goTrip()">
              {{item.startName}}({{item.startCode}}) - {{item.endName}}({{item.endCode}})   {{item.startDate}} - {{item.endDate}}, 经济舱
            </font>
          </DropdownItem>
          <p v-if="historyList && historyList.length > 1" @click="clearHistory()">清除记录</p>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  props: ['queryHistory'],
  data () {
    return {
      historyList: [
        {
          startName: "广州",
          startCode: "CAN",
          endName: "因斯布鲁克",
          endCode: "INN",
          startDate: "11月11日",
          endDate: "11月20日",
          spaceType: "经济舱",
          tripType: 0
        },
        {
          startName: "广州",
          startCode: "CAN",
          endName: "上海",
          endCode: "SHA",
          startDate: "11月11日",
          endDate: "",
          spaceType: "经济舱",
          tripType: 1
        }
      ],
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    //删除历史记录
    deleteHistory(index){
      this.historyList.splice(index,1)
    },
    //清除所有
    clearHistory(){

    },
    //点击查看航程
    goTrip(){

    }
  },
  mounted () {}
}
</script>
