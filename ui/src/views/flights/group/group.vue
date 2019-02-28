
<style lang='scss' scoped>
@import "./group.scss";
</style>
<style>
.search-bar-group .ivu-input-wrapper{
  width: 30px;
}
</style>

<template>
  <div class="search-bar-group fl">
    <Dropdown trigger="custom" :visible="visible" placement="bottom-start">
        <span @click="handleGroupOpen">
          <b>{{bigvalue}}成人,</b>
          <b v-if="childvalue>0">{{childvalue}}儿童,</b>
          <b>{{groupType}}</b>
          <i><Icon type="ios-arrow-down" /></i>
        </span>
        <DropdownMenu slot="list">
          <DropdownItem><span class="search-bar-gray">一次最多选择9个旅客</span></DropdownItem>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle" @click="cutGroupTravel(0)" :class="{'search-disabled': bigvalue<=1}">-</Button>
              <Input v-model="bigvalue" readonly></Input>
              <Button type="primary" shape="circle" @click="addGroupTravel(0)" :class="{'search-disabled': bigvalue+childvalue >= 9}">+</Button>
            </span>
            <strong>成人</strong>
          </DropdownItem>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle" @click="cutGroupTravel(1)" :class="{'search-disabled': childvalue<=0}">-</Button>
              <Input v-model="childvalue" readonly></Input>
              <Button type="primary" shape="circle" @click="addGroupTravel(1)" :class="{'search-disabled': childvalue == bigvalue*2 || bigvalue+childvalue >= 9}">+</Button>
            </span>
            <strong>儿童</strong>
          </DropdownItem>
          <DropdownItem>
            <font class="fr"><Button type="primary" @click="handleGroupClose">完成</Button></font>
            <Button
              v-for="(item,index) in groupList"
              :key="index"
              :class="{'search-bar-b-active':item.actived}"
              @click="tabGroupTravel(item,index)"
            >
              {{item.label}}
            </Button>
          </DropdownItem>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  computed: {
    ...mapGetters(['airport_group'])
  },
  data () {
    return {
      visible: false,
      groupList: [{
        label: "经济舱",
        actived: false,
      },{
        label: "商务舱",
        actived: false,
      },{
        label: "高端经济舱",
        actived: false,
      },{
        label: "头等舱",
        actived: false,
      }],
      groupObj: {},
      bigvalue: 1,
      childvalue: 0,
      groupType: '经济舱',
      tabIndex: 0
    }
  },
  components: {

  },
  watch: {
    airport_group: {
      handler: function (val, oldVal) {
        if(val){
          this.initData(JSON.parse(JSON.stringify(val)))
        }
      },
      immediate: true
    }
  },
  methods: {
    ...mapActions(['setAirportGroupData']),
    //初始化
    initData(data){
      this.bigvalue = data.bigvalue;
      this.childvalue = data.childvalue;
      this.groupType = data.groupType;
      this.tabIndex = data.tabIndex;
      this.groupList[this.tabIndex].actived = true;
      console.log(data,"group")
    },

    handleGroupOpen(){
      this.visible = true;
    },
    handleGroupClose(){
      this.visible = false;
      //向父组建传值
      this.$bus.emit('trip-airport-msg',this.groupType);

      //vuex保存
      this.groupObj = {
          bigvalue: this.bigvalue,
          childvalue: this.childvalue,
          groupType: this.groupType,
          tabIndex: this.tabIndex
      }
      this.setAirportGroupData(this.groupObj)
    },
    tabGroupTravel(item,index){
      this.groupList[this.tabIndex].actived = false;
      this.groupList[index].actived = true;
      this.tabIndex = index;
      this.groupType = item.label;
    },
    cutGroupTravel(index){
      switch (index){
        case 0:
          this.bigvalue = this.bigvalue-1;
          break;
        case 1:
          this.childvalue = this.childvalue-1;
          break;
      }
    },
    addGroupTravel(index){
      switch (index){
        case 0:
          this.bigvalue = this.bigvalue+1 ;
          break;
        case 1:
          this.childvalue = this.childvalue+1;
          break;
      }
    }
  },
  mounted () {}
}
</script>
