
<style lang='scss' scoped>
@import "./place.scss";
</style>
<style>
.placeWrap .ivu-input {
  width: 224px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  border: none !important;
  height: 40px;
  font-size: 18px;
  color: #323232;
  outline: none;
}
.placeWrap .ivu-tabs-nav {
  height: 45px;
  line-height: 31px;
}
.placeWrap .ivu-tabs-ink-bar {
  width: 30px !important;
  height: 6px;
  position: absolute;
  background-image: linear-gradient(90deg, #28c8dc 0, #3296d2 100%);
  border-radius: 61px;
  margin-left: 18px;
}
.placeWrap .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #00bedc !important;
}
.placeWrap .ivu-tabs-tab {
  color: #323232 !important;
}
.placeWrap input{
  border:none!important;
}
.placeWrap .ivu-tabs-bar{
  margin-bottom:0
}
</style>

<template>
  <div class="main placeWrap" @click='$event.stopPropagation();'>
    <!-- 城市 -->
    <section class="city row-item">
      <Input
        v-model="citySelected"
        placeholder="地标/景点/酒店名等(选填)"
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
              >{{option['name']}}</li>
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
            <div class="item-type">{{item.type}}</div>
            <div class="item-panel">
              <b>{{item.name}}</b>
              <span>{{item.address}}</span>
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
          label: '机场车站',
          key: 'trainAirport'
        },
        {
          label: '地铁线路',
          key: 'metroStation'
        },
        {
          label: '行政/商业区',
          key: 'neighborhoods'
        },
        {
          label: '地标',
          key: 'landmark'
        },
        {
          label: '景点',
          key: 'scenicSpot'
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
      // cityType: [{
      //   name: '酒店',
      //   type: 'Hotel'
      // }, {
      //   name: '城市',
      //   type: 'city'
      // }, {
      //   name: '周边',
      //   type: 'neighbor'
      // }]
    }
  },
  mounted () {
    this.$bus.on('overall-close', this.clear)
  },
  methods: {
    // 城市
    // 获取所有数据
    getCityWholeData () {
      this.$emit('other-close', 'place')
      if (!this.citySelected) {
        let self = this
        axios
          .get(
            'https://www.igola.com/web-gateway/api-hotel-static/geo/pois?city=771&lan=ZH'
          )
          .then(data => {
            this.cityOpen.whole = true
            self.cityWholeObj = data.data.data
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
      let url = `https://www.igola.com/web-gateway/api-hotel-static/typeahead?city=771&q=${this.citySelected}&lang`
      let self = this
      axios
        .get(url)
        .then(data => {
          self.cityWholeSearchList = data.data.data
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
      this.citySelected = data['name']
      this.cityOpen.whole = false
      this.cityOpen.search = false
    },
    onCitySearchSelection (data) {
      this.citySelected = data.name
      this.cityOpen.search = false
      // if (data.type === 'city') {
      //   this.citySelected = data.city
      // } else if (data.type === 'Hotel') {
      //   // 酒店
      // } else if (data.type === 'neighbor') {
      //   // 地点
      // }
    },
    clear () {
      this.cityOpen.whole = false
      this.cityOpen.search = false
    }
  }
}
</script>
