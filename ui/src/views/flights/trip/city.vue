
<style lang='scss' scoped>
@import "./trip.scss";
@import "./city.scss";
</style>
<style>
.cityWrap .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #0096ff !important;
}
.cityWrap .ivu-tabs-tab {
  color: #323232 !important;
}
.cityWrap .cityWrap input {
  border: none !important;
}
.cityWrap .ivu-tabs-bar {
  margin-bottom: 0;
}
</style>

<template>
  <div class="flights-index-search-input-city" :class=[cityObj.foat] @click='$event.stopPropagation();'>
    <i class="fl"><i class="iconfont" :class=[cityObj.className]></i></i>
    <span class="fl">
      <!-- 城市 -->
      <section class="flights-index-text" :class=[cityObj.classInput]>
        <Input
          v-model="cityObj.citySelected"
          :placeholder="cityObj.name"
          @on-focus="getCityWholeData()"
          @on-change="getCitySearchData()"
        />
      </section>
      <div class="flight-city">
        <Dropdown trigger="custom" :visible="cityOpen.whole"  placement="bottom-start">
          <DropdownMenu slot="list" v-if="cityOpen.whole">
            <Tabs :animated="false">
              <TabPane :label="item.label" v-for="(item,index) in cityWholeHeaderList" :key="index">
                <ul class="city-panel">
                  <li
                    v-for="(option,i) in cityWholeList"
                    :key="i"
                    @click="onCitySelection(option,0)"
                  >{{option.cityName}}</li>
                </ul>
              </TabPane>
            </Tabs>
            <b class="flights-index-search-input-city-close" @click="closeCity">关闭</b>
          </DropdownMenu>
      </Dropdown>
      </div>


    <div class="flight-inline">
    <Dropdown trigger="custom" :visible="cityOpen.search"  placement="bottom-start">
      <DropdownMenu slot="list">
        <div class="search-panel" v-if="cityOpen.search">
          <div class="list-panel" v-if="!cityOpen.searchLoading">
            <div
              class="inline-item"
              v-for="(item,index) in cityWholeSearchList"
              :key="index"
            >
              <div class="item-city">
                <dl>
                  <dt @click="onCitySelection(item,1)"> {{item.provinceName}},<span v-if="item.children&&item.children.length > 0">所有机场,</span> {{item.countyType}}({{item.countryCode}})</dt>
                  <dd v-if="item.children&&item.children.length > 0">
                    <ul>
                      <li
                        v-for="(option,i) in item.children"
                        :key="i"
                        @click="onCitySelection(option,1)"
                      >
                      ┊┈ {{option.provinceName}},{{option.cityName}},{{option.airportName}},{{option.countyType}}({{option.countryCode}})
                      </li>
                    </ul>
                  </dd>
                </dl>
              </div>
            </div>
          </div>
          <div class="tip-panel" v-if="cityOpen.searchLoading">正在加载搜索信息...</div>
          <div
            class="tip-panel"
            v-if="!cityWholeSearchList.length&&!cityOpen.searchLoading&&cityObj.citySelected"
          >抱歉未找到您搜索的结果</div>
        </div>
      </DropdownMenu>
    </Dropdown>
    </div>
    </span>
  </div>
</template>

<script>
import axios from "axios/dist/axios.min";
import { findAirport } from "../../../assets/json/findAirport.js";
import { searchAirport } from "../../../assets/json/searchAirport.js";

export default {
  props: ["cityObj"],
  components: {},
  data() {
    return {
      cityWholeHeaderList: [
        {
          label: "目的地",
          key: "hot"
        },
        // {
        //   label: "亚洲",
        //   key: "asia"
        // },
        // {
        //   label: "美洲",
        //   key: "america"
        // },
        // {
        //   label: "欧洲",
        //   key: "europe"
        // },
        // {
        //   label: "大洋洲",
        //   key: "oceania"
        // },
        // {
        //   label: "中东/非洲",
        //   key: "africa"
        // }
      ],
      cityWholeList: {},
      cityWholeSearchList: [],
      cityOpen: {
        whole: false,
        wholeLoading: false,
        search: false,
        searchLoading: false
      },
      searchBoolean: false
    };
  },
  mounted() {
  },
  methods: {
    // 城市获取所有数据
    getCityWholeData() {
      if (!this.cityObj.citySelected || !this.searchBoolean) {
        this.cityOpen.whole = true;
        this.cityWholeList = findAirport.result;
      } else {
        this.getCitySearchData();
      }
    },
    // 获取搜索信息
    getCitySearchData() {
      if(!this.cityObj.citySelected){
        this.cityOpen.whole = true;
        this.cityOpen.search = false;
      }else{
        this.cityOpen.whole = false;
        this.cityOpen.search = true;
        this.cityOpen.searchLoading = true;
        this.cityWholeSearchList = [];
        for(let item of searchAirport.result){
          for(let option of item.children){
            let str = option.cityName + option.airportName + option.countryCode + option.cityenName;
            if(str.indexOf(this.cityObj.citySelected)>=0){
              if(str !== ""){
                this.cityWholeSearchList.push(item)
              }
            }
          }
          this.cityOpen.searchLoading = false;
        }
      }
    },
    // 选中城市
    onCitySelection(data,index) {
      this.setCityData(data);
      if(index == 0){
        this.cityOpen.whole = false;
      }else{
        this.searchBoolean = true;
      }
    },
    setCityData(data,index){
      this.cityObj.citySelected = data.cityName + "(" + data.countryCode + ")";
      this.cityObj.cityName = data.cityName;
      this.cityObj.cityCode = data.countryCode;
      this.cityOpen.search = false;
    },
    closeCity() {
      this.cityOpen.whole = false;
      this.cityOpen.search = false;
    },
  }
};
</script>
