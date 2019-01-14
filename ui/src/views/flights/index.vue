
<style lang='scss' scoped>
@import "./index.scss";
</style>
<template>
  <div class="flights-index">
    <div class="flights-index-search">
      <div class="flights-index-search-trip">
        <div class="flights-index-search-trip-history fl">
          <Dropdown trigger="click" placement="bottom-start">
              <span>
                <i><Icon type="ios-clock-outline" /></i>
              </span>
              <DropdownMenu slot="list">
                <DropdownItem><i class="flights-index-air"><Icon type="md-return-left" /></i> 广州(CAN) - 因斯布鲁克(INN)   11月11日 - 11月20日, 经济舱</DropdownItem>
                <DropdownItem><b class="fr"><Icon type="md-close-circle" /></b><i class="flights-index-air"><Icon type="md-return-left" /></i>广州(CAN) - 奥克兰(AKL)   11月11日 - 11月20日, 经济舱</DropdownItem>
                <p>清除记录</p>
              </DropdownMenu>
          </Dropdown>
        </div>
        <div class="flights-index-search-trip-right fl">
          <div class="flights-index-search-trip-right-tab fl">
            <ul>
              <li class="active"><i class="iconfont icon-travel-wangfan"></i>往返</li>
              <li><i class="iconfont icon-travel-find"></i>单程</li>
              <li><i class="iconfont icon-travel-list"></i>多程</li>
            </ul>
          </div>
          <div class="flights-index-search-trip-right-ove fl">
            <div class="flights-index-search-trip-right-ove-select fr">
              <Dropdown trigger="custom" :visible="visible" placement="bottom-end">
                  <span @click="handleOpen">
                    <b>1</b>
                    <b>成人</b>
                    <b>经济舱</b>
                    <i><Icon type="ios-arrow-down" /></i>
                  </span>
                  <DropdownMenu slot="list">
                    <DropdownItem><span class="flights-index-gray">一次最多选择9个旅客</span></DropdownItem>
                    <DropdownItem>
                      <span class="fr flights-index-prev">
                        <Button type="primary" shape="circle">-</Button>
                        <InputNumber v-model="value7" readonly></InputNumber>
                        <Button type="primary" shape="circle">+</Button>
                      </span>
                      <strong>成人</strong>
                    </DropdownItem>
                    <DropdownItem>
                      <span class="fr flights-index-prev">
                        <Button type="primary" shape="circle">-</Button>
                        <InputNumber v-model="value7" readonly></InputNumber>
                        <Button type="primary" shape="circle">+</Button>
                      </span>
                      <strong>儿童</strong>
                    </DropdownItem>
                    <DropdownItem>
                      <font class="fr"><Button type="primary" @click="handleClose">完成</Button></font>
                      <Button class="flights-index-b-active">经济舱</Button>
                      <Button>商务/头等舱</Button>
                    </DropdownItem>
                  </DropdownMenu>
              </Dropdown>
            </div>
          </div>
        </div>
      </div>
      <div class="flights-index-search-input">
        <div class="flights-index-search-input-left fl">
          <div class="flights-index-search-input-left-change">
            <i><Icon type="ios-swap" /></i>
          </div>
          <div class="fl col-10">
            <div class="flights-index-search-input-city fl">
              <i class="fr cursort"><Icon type="md-add-circle" /></i>
              <i class="fl"><i class="iconfont icon-travel-setsail"></i></i>
              <span class="fl">
                <Dropdown trigger="click" :visible="visibleGo"  placement="bottom-start">
                  <input type="text" placeholder="出发" class="flights-index-text" @click="handleOpenGo">
                  <DropdownMenu slot="list">
                    <b @click="handleCloseGo" class="flights-index-search-input-city-close">关闭</b>
                    <div class="flights-index-search-input-city-tab">
                      <ol>
                        <li v-for="(value, key) in listCity">{{key}}</li>
                      </ol>
                    </div>
                    <div class="flights-index-search-input-city-list" v-for="(value, key) in listCity">
                      <ul v-for="item in value">
                        <li v-for="city in item">{{city.name}}</li>
                      </ul>
                    </div>
                  </DropdownMenu>
              </Dropdown>
              </span>
            </div>
          </div>
          <div class="fl col-10">
            <div class="flights-index-search-input-city fr">
              <i class="fl"><i class="iconfont icon-travel-jiangla"></i></i>
              <span class="fl">
                <Dropdown trigger="click" :visible="visibleGoaddress"  placement="bottom-start">
                  <input type="text" placeholder="目的地" class="flights-index-text flights-index-org" @click="handleOpenGoaddress">
                  <DropdownMenu slot="list">
                    <b @click="handleCloseGoaddress" class="flights-index-search-input-city-close">关闭</b>
                    <div class="flights-index-search-input-city-tab">
                      <ol>
                        <li v-for="(value, key) in listCity">{{key}}</li>
                      </ol>
                    </div>
                    <div class="flights-index-search-input-city-list" v-for="(value, key) in listCity">
                      <ul v-for="item in value">
                        <li v-for="city in item">{{city.name}}</li>
                      </ul>
                    </div>
                  </DropdownMenu>
              </Dropdown>
              </span>
            </div>
          </div>
        </div>
        <div class="flights-index-search-input-right fl">
          <div class="flights-index-search-input-city flights-index-search-input-city-date fl ">
              <i class="fl"><Icon type="md-calendar" /></i>
              <span class="fl">
                <DatePicker type="daterange" split-panels placeholder="出发日期/返回日期" style="width: 98%;"></DatePicker>
              </span>
            </div>
            <div class="flights-index-search-input-button fl">
              <Icon type="ios-search" />
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      value7: 1,
      visible: false,
      visibleGo: false,
      visibleGoaddress: false,
      listCity: {
        "热门出发地": {
          0: [
            {name: "北京"},
            {name: "澳门"},
            {name: "首尔"},
            {name: "悉尼"}
          ],
          1: [
            {name: "上海"},
            {name: "台北"},
            {name: "东京"},
            {name: "伦敦"}
          ],
          2: [
            {name: "广州"},
            {name: "杭州"},
            {name: "曼谷"},
            {name: "巴黎"}
          ],
          3: [
            {name: "成都"},
            {name: "昆明"},
            {name: "新加坡"}
          ],
          4: [
            {name: "深圳"},
            {name: "西安"},
            {name: "吉隆坡"},
          ],
          5: [
            {name: "香港"},
            {name: "重庆"},
            {name: "纽约"}
          ],
        },
        "亚洲": {},
        "美洲": {},
        "欧洲": {},
        "中东/非洲": {},
        "大洋洲": {},
      }
    }
  },
  components: {

  },
  methods: {
    handleOpen () {
      this.visible = true;
    },
    handleClose () {
      this.visible = false;
    },
    handleOpenGo () {
      this.visibleGo = true;
    },
    handleCloseGo () {
      this.visibleGo = false;
    },
    handleOpenGoaddress () {
      this.visibleGoaddress = true;
    },
    handleCloseGoaddress () {
      this.visibleGoaddress = false;
    }
  },
  mounted () {}
}
</script>
