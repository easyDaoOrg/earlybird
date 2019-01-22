
<style lang='scss' scoped>
@import "./where-to-go.scss";
</style>
<template>
  <div class="flights-index">
    <div class="flights-index-search">
      <div class="flights-index-search-input">
        <div class="flights-index-search-input-left fl">
          <div class="fl col-10">
            <div class="flights-index-search-input-city fl">
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
        "热门城市": {
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
