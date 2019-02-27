
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
      <Dropdown trigger="custom" :visible="cityOpen.whole"  placement="bottom-start">
        <DropdownMenu slot="list" v-if="cityOpen.whole">
          <Tabs :animated="false">
            <TabPane :label="item.label" v-for="(item,index) in cityWholeList" :key="index">
              <ul class="city-panel">
                <li
                  v-for="(option,i) in cityWholeObj[item.key]"
                  :key="i"
                  @click="onCitySelection(option)"
                >{{option['d']}}</li>
              </ul>
            </TabPane>
          </Tabs>
          <b class="flights-index-search-input-city-close" @click="closeCity">关闭</b>
        </DropdownMenu>
    </Dropdown>

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
                  <dt @click="onCitySearchSelection(item,1)"> {{item.ct}},<span v-if="item.s">所有机场,</span> {{item.cy}}({{item.c}})</dt>
                  <dd v-if="item.s">
                    <ul>
                      <li
                        v-for="(option,i) in item.s"
                        :key="i"
                        @click="onCitySearchSelection(option,2)"
                      >
                      ┊┈ {{option.ct}},{{option.d}},{{option.cy}}({{option.c}})
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
      cityWholeList: [
        {
          label: "热门目的地",
          key: "hot"
        },
        {
          label: "亚洲",
          key: "asia"
        },
        {
          label: "美洲",
          key: "america"
        },
        {
          label: "欧洲",
          key: "europe"
        },
        {
          label: "大洋洲",
          key: "oceania"
        },
        {
          label: "中东/非洲",
          key: "africa"
        }
      ],
      cityWholeObj: {},
      cityWholeSearchList: [],
      cityOpen: {
        whole: false,
        wholeLoading: false,
        search: false,
        searchLoading: false
      }
    };
  },
  mounted() {
  },
  methods: {
    // 城市获取所有数据
    getCityWholeData() {
      if (!this.cityObj.citySelected) {
        let self = this;
        axios
          .get(
            "https://www.igola.com/web-gateway/api-hotel-management/getHotCitys"
          )
          .then(data => {
            this.cityOpen.whole = true;
            // self.cityWholeObj = data.data.result
            self.cityWholeObj = findAirport.result;
          })
          .catch(error => {
            this.cityOpen.whole = true;
            console.log(error);
          });
      } else {
        this.getCitySearchData();
      }
    },
    // 获取搜索信息
    getCitySearchData() {
      this.cityOpen.whole = false;
      this.cityOpen.search = true;
      this.cityOpen.searchLoading = true;
      let url = `https://www.igola.com/web-gateway/api-data-service/data/find-airport?text=5YyX5Lqs&lang=Wkg=&timestamp=1548289539465`;
      let self = this;
      axios
        .get(url)
        .then(data => {
          // self.cityWholeSearchList = data.data.result;
          self.cityWholeSearchList = searchAirport.result;
          this.cityOpen.searchLoading = false;
        })
        .catch(error => {
          this.cityOpen.searchLoading = false;
          self.cityWholeSearchList = [];
          console.log(error);
        });
    },
    // 选中城市
    onCitySelection(data) {
      this.cityObj.citySelected = data["d"] + "(" + data["c"] + ")";
      this.cityObj.cityName = data["d"];
      this.cityObj.cityCode = data["c"];
      this.cityOpen.whole = false;
      this.cityOpen.search = false;
    },
    closeCity() {
      this.cityOpen.whole = false;
      this.cityOpen.search = false;
    },
    onCitySearchSelection(data,index) {
      switch (index){
        case 1:
          this.cityObj.citySelected = data["ct"] + "(" + data["c"] + ")";
          this.cityObj.cityName = data["ct"];
          this.cityObj.cityCode = data["c"];
          break;
        case 2:
          this.cityObj.citySelected = data["d"] + "(" + data["c"] + ")";
          this.cityObj.cityName = data["d"];
          this.cityObj.cityCode = data["c"];
          break;
      }
      this.cityOpen.search = false;
    }
  }
};
</script>
