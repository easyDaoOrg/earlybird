
<style lang='scss' scoped>
@import "./filter.scss";
</style>
<style>
  .filter .ivu-checkbox-wrapper{
    font-size: 14px;
  }
  .filter-panel-box-airports-list .ivu-dropdown-item{
    width: 210px;
    float: left;
    clear: inherit;
  }
  .filter-panel-box-airports-play-dl-dt .ivu-dropdown-item{
    color: #000;
  }
</style>
<template>
  <div class="filter">
    <!--自由搭配-->
    <div class="filter-fill" v-if="pTabIndex == 0"  :class="{'filter-active': fillActived}">
      <div class="filter-fill-go" @click="fillGoBack(0,false)">
        <div class="filter-fill-go-tips" v-if="fillTabIndex == 1">启程</div>
        <div class="filter-bar-title" v-if="fillTabIndex == 0">
          <span>北京 (BJS) <i class="iconfont icon-travel-wangfan"></i> 上海 (SHA) </span>
          <div class="filter-bar-title-info">
            <span>1月18日, 周五</span>
            <span> - </span>
            <span>1月19日, 周六</span>
          </div>
        </div>
      </div>
      <div class="filter-fill-back" @click="fillGoBack(1,true)">
        <div class="filter-fill-back-tips" v-if="fillTabIndex == 0">返程</div>
        <div class="filter-bar-title" v-if="fillTabIndex == 1">
          <span>北京 (BJS) <i class="iconfont icon-travel-wangfan"></i> 上海 (SHA) </span>
          <div class="filter-bar-title-info">
            <span>1月18日, 周五</span>
            <span> - </span>
            <span>1月19日, 周六</span>
          </div>
        </div>
      </div>
    </div>

    <!--航班组合-->
    <div class="filter-bar" v-if="pTabIndex == 1">
      <div class="filter-bar-title">
        <span>北京 (BJS) <i class="iconfont icon-travel-wangfan"></i> 上海 (SHA) </span>
        <div class="filter-bar-title-info">
          <span>1月18日, 周五</span>
          <span> - </span>
          <span>1月19日, 周六</span>
        </div>
      </div>
    </div>

    <div class="filter-panel">
      <div class="filter-panel-box">
        <div class="filter-panel-box-stop">
          <Checkbox>直飞</Checkbox>
          <small><b>£</b> 143</small>
        </div>
        <div class="filter-panel-box-item">
          <Dropdown>
              中转次数
              <Icon type="ios-arrow-down"></Icon>
              <DropdownMenu slot="list">
                  <DropdownItem><Checkbox>直飞<small>£ 143</small></Checkbox></DropdownItem>
                  <DropdownItem><Checkbox>中转一次<small>£ 181</small></Checkbox></DropdownItem>
                  <DropdownItem><Checkbox>中转两次或以上<small>£ 365</small></Checkbox></DropdownItem>
              </DropdownMenu>
          </Dropdown>
        </div>
        <div class="filter-panel-box-airports">
          <Dropdown  placement="bottom-start">
              中转机场
              <Icon type="ios-arrow-down"></Icon>
              <DropdownMenu slot="list">
                <div class="filter-panel-box-airports-content filter-panel-box-airports-list">
                    <div class="filter-panel-box-airports-title">中转机场</div>
                    <DropdownItem><Checkbox>呼和浩特 白塔机场</Checkbox></DropdownItem>
                    <DropdownItem><Checkbox>怀化 芷江机场</Checkbox></DropdownItem>
                    <DropdownItem><Checkbox>深圳 宝安机场</Checkbox></DropdownItem>
                    <DropdownItem><Checkbox>厦门 高崎机场</Checkbox></DropdownItem>
                </div>
              </DropdownMenu>
          </Dropdown>
        </div>
        <div class="filter-panel-box-airports">
          <Dropdown placement="bottom-start">
              起飞/到达时间
              <Icon type="ios-arrow-down"></Icon>
              <DropdownMenu slot="list">
                <div class="filter-panel-box-airports-content filter-panel-box-airports-time">
                  <div class="filter-panel-box-airports-time-c">
                    <div class="filter-panel-box-airports-title none">去程</div>
                    <div class="filter-panel-box-airports-time-c-top">起飞时间</div>
                    <div class="filter-panel-box-airports-time-c-drop">
                      <Slider v-model="value5" :step="10" range></Slider>
                    </div>
                    <div class="filter-panel-box-airports-time-c-top">到达时间</div>
                    <div class="filter-panel-box-airports-time-c-drop">
                      <Slider v-model="value5" :step="10" range></Slider>
                    </div>
                  </div>

                  <div class="filter-panel-box-airports-time-c">
                    <div class="filter-panel-box-airports-title none orange">返程</div>
                    <div class="filter-panel-box-airports-time-c-top">起飞时间</div>
                    <div class="filter-panel-box-airports-time-c-drop">
                      <Slider v-model="value5" :step="10" range></Slider>
                    </div>
                    <div class="filter-panel-box-airports-time-c-top">到达时间</div>
                    <div class="filter-panel-box-airports-time-c-drop">
                      <Slider v-model="value5" :step="10" range></Slider>
                    </div>
                  </div>
                </div>
              </DropdownMenu>
          </Dropdown>
        </div>
        <div class="filter-panel-box-airports ">
          <Dropdown placement="bottom-start">
              航司联盟/航空公司
              <Icon type="ios-arrow-down"></Icon>
              <DropdownMenu slot="list">
                <div class="filter-panel-box-airports-content filter-panel-box-airports-play">
                  <div class="filter-panel-box-airports-play-dl">
                    <div class="filter-panel-box-airports-play-dl-dt"><DropdownItem><Checkbox>星空联盟 <small>£ 143</small></Checkbox></DropdownItem></div>
                    <div class="filter-panel-box-airports-play-dl-dd">
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                    </div>
                  </div>

                  <div class="filter-panel-box-airports-play-dl">
                    <div class="filter-panel-box-airports-play-dl-dt"><DropdownItem><Checkbox>天合联盟 <small>£ 143</small></Checkbox></DropdownItem></div>
                    <div class="filter-panel-box-airports-play-dl-dd">
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                    </div>
                  </div>

                  <div class="filter-panel-box-airports-play-dl">
                    <div class="filter-panel-box-airports-play-dl-dt"><DropdownItem><Checkbox>其它 <small>£ 143</small></Checkbox></DropdownItem></div>
                    <div class="filter-panel-box-airports-play-dl-dd">
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                      <DropdownItem><Checkbox>
                        <Icon type="logo-twitter"></Icon>
                        <span>国航</span>
                        <small>£ 143</small>
                        </Checkbox>
                      </DropdownItem>
                    </div>
                  </div>

                </div>
              </DropdownMenu>
          </Dropdown>
        </div>
        <div class="filter-panel-box-clear">清空筛选</div>
      </div>
    </div>
  </div>
</template>
<script>


export default {
  props: ['pTabIndex'],
  data () {
    return {
      value5: [0, 24],
      fillTabIndex: 0,
      fillActived: false
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    fillGoBack(index,bool){
      this.fillTabIndex = index;
      this.fillActived = bool;
      this.$bus.emit('on-filtergoback',bool)
    }
  },
  mounted () {
    this.$bus.on('on-triplist', (data,bool) => {
      this.fillTabIndex = data;
      this.fillActived = bool;
    })
  }
}
</script>
