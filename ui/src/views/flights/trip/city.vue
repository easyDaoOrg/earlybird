
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
.cityWrap .cityWrap input{
  border:none!important;
}
.cityWrap .ivu-tabs-bar{
  margin-bottom:0
}
</style>

<template>
  <div class="flights-index-search-input-city" :class=[cityObj.foat] @click='$event.stopPropagation();'>
    <i class="fl"><i class="iconfont" :class=[cityObj.className]></i></i>
    <span class="fl">
      <!-- 城市 -->
      <section class="flights-index-text" :class=[cityObj.classInput]>
        <Input
          v-model="citySelected"
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
                >{{option['city-ZH']}}</li>
              </ul>
            </TabPane>
          </Tabs>
          <b class="flights-index-search-input-city-close" @click="closeCity">关闭</b>
        </DropdownMenu>
    </Dropdown>

    <Dropdown trigger="custom" :visible="cityOpen.search"  placement="bottom-start">
      <DropdownMenu slot="list">


        <!-- <div class="search-panel">
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
          <div class="tip-panel" v-if="cityOpen.searchLoading"><Spin fix></Spin></div>
          <div
            class="tip-panel"
            v-if="!cityWholeSearchList.length&&!cityOpen.searchLoading&&citySelected"
          >抱歉未找到您搜索的结果</div>
        </div> -->
      </DropdownMenu>
    </Dropdown>
    </span>
  </div>
</template>

<script>
import axios from 'axios/dist/axios.min'
export default {
  props: ['cityObj'],
  components: {},
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
    this.$bus.on('overall-close', () => {
      this.cityOpen.whole = false
      this.cityOpen.search = false
    })
  },
  methods: {
    // 城市获取所有数据
    getCityWholeData () {
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
    },
    // 获取搜索信息
    getCitySearchData () {
      this.cityOpen.whole = false
      this.cityOpen.search = true
      this.cityOpen.searchLoading = true
      let url = `https://www.igola.com/web-gateway/api-data-service/data/find-airport?text=5YyX5Lqs&lang=Wkg=&timestamp=1548289539465`
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
    closeCity () {
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
    }
  }
}
</script>
