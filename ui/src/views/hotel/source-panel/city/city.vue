
<style lang='scss' scoped>
@import "./city.scss";
</style>
<style>
.cityWrap .ivu-input {
  width: 184px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border: none !important;
  height: 40px;
  font-size: 18px;
  color: #323232;
  outline: none;
}
.cityWrap .ivu-tabs-nav {
  height: 45px;
  line-height: 31px;
}
.cityWrap .ivu-tabs-ink-bar {
  width: 30px !important;
  height: 6px;
  position: absolute;
  background-image: linear-gradient(90deg, #28c8dc 0, #3296d2 100%);
  border-radius: 61px;
  margin-left: 18px;
}
.cityWrap .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #00bedc !important;
}
.cityWrap .ivu-tabs-tab {
  color: #323232 !important;
}
.cityWrap .cityWrap input{
  border:none!important;
}
.cityWrap .ivu-tabs-bar{
  margin-bottom:0
}
</style>

<template>
  <div class="main cityWrap" @click='$event.stopPropagation();'>
    <!-- 城市 -->
    <section class="city row-item">
      <Input
        v-model="citySelected"
        placeholder="请输入目的城市"
        style="width: 100%"
        @on-focus="getCityWholeData()"
        @on-change="getCitySearchData()"
      />
    </section>
    <div class="city-picker" :class="{'top70':type=='result-list'}">
      <div class="city-picker-panel" v-if="cityOpen.whole">
        <Tabs :animated="false">
          <TabPane :label="item.label" v-for="(item,index) in cityWholeList" :key="index">
            <ul class="city-panel">
              <li
                v-for="(option,i) in cityWholeObj[item.key]"
                :key="i"
                @click="onCitySelection(option)"
              >{{option['city-ZH']}}</li>
            </ul>
          </TabPane>
        </Tabs>
      </div>
      <div class="search-panel" v-if="cityOpen.search">
        <div class="list-panel" v-if="!cityOpen.searchLoading">
          <div
            class="inline-item"
            v-for="(item,index) in cityWholeSearchList"
            :key="index"
            @click="onCitySearchSelection(item)"
          >
            <div class="item-type">{{cityType[item.type]}}</div>
            <div class="item-panel">
              <b>{{item.fullNameZh}}</b>
              <span>{{item.addressCn?item.addressCn:item.fullNameLang}}</span>
            </div>
          </div>
        </div>
        <div class="tip-panel" v-if="cityOpen.searchLoading">正在加载城市列表...</div>
        <div
          class="tip-panel"
          v-if="!cityWholeSearchList.length&&!cityOpen.searchLoading&&citySelected"
        >抱歉未找到您搜索的结果</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios/dist/axios.min'
export default {
  components: {},
  props: {
    type: {
      default: 'hotel',
      type: String
    }
  },
  data () {
    return {
      citySelected: '',
      cityWholeList: [
        {
          label: '热门目的地',
          key: 'hot'
        },
        {
          label: '亚洲',
          key: 'asia'
        },
        {
          label: '美洲',
          key: 'america'
        },
        {
          label: '欧洲',
          key: 'europe'
        },
        {
          label: '大洋洲',
          key: 'oceania'
        },
        {
          label: '中东/非洲',
          key: 'africa'
        }
      ],
      cityWholeObj: {},
      cityWholeSearchList: [],
      cityOpen: {
        whole: false,
        wholeLoading: false,
        search: false,
        searchLoading: false
      },
      cityType: {
        Hotel: '酒店',
        city: '城市',
        neighbor: '周边'
      }
    }
  },
  mounted () {
    this.$bus.on('overall-close', this.clear)
  },
  methods: {
    // 城市
    // 获取所有数据
    getCityWholeData () {
      this.$emit('other-close', 'city')
      if (!this.citySelected) {
        let self = this
        axios
          .get(
            'https://www.igola.com/web-gateway/api-hotel-management/getHotCitys'
          )
          .then(data => {
            this.cityOpen.whole = true
            self.cityWholeObj = data.data.result
          })
          .catch(error => {
            this.cityOpen.whole = true
            console.log(error)
          })
      } else {
        this.getCitySearchData()
      }
    },
    // 获取搜索信息
    getCitySearchData () {
      this.cityOpen.whole = false
      this.cityOpen.search = true
      this.cityOpen.searchLoading = true
      let url = `https://www.igola.com/web-gateway/api-hotel-static/geo/city-search?key=${
        this.citySelected
      }&lang=ZH`
      let self = this
      axios
        .get(url)
        .then(data => {
          self.cityWholeSearchList = data.data.result
          this.cityOpen.searchLoading = false
        })
        .catch(error => {
          this.cityOpen.searchLoading = false
          self.cityWholeSearchList = []
          console.log(error)
        })
    },
    // 选中城市
    onCitySelection (data) {
      this.citySelected = data['city-ZH']
      this.cityOpen.whole = false
      this.cityOpen.search = false
    },
    onCitySearchSelection (data) {
      this.cityOpen.search = false
      if (data.type === 'city') {
        this.citySelected = data.city
      } else if (data.type === 'Hotel') {
        // 酒店
      } else if (data.type === 'neighbor') {
        // 地点
      }
    },
    clear () {
      this.cityOpen.whole = false
      this.cityOpen.search = false
    }
  }
}
</script>
