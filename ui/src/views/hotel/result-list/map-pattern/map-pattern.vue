
<style lang='scss' scoped>
@import "./map-pattern.scss";
</style>
<style>
.pattern .ivu-tabs-nav {
  height: 45px;
  line-height: 31px;
}
.pattern .ivu-tabs-ink-bar {
  width: 30px !important;
  height: 6px;
  position: absolute;
  background-image: linear-gradient(90deg, #28c8dc 0, #3296d2 100%);
  border-radius: 61px;
  margin-left: 18px;
}
.pattern .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #00bedc !important;
}
.pattern .ivu-tabs-tab {
  color: #323232 !important;
  font-size: 20px;
  padding: 2px 16px;
  font-weight: 600;
}
.pattern .ivu-tabs-bar {
  border: none;
  margin-bottom: -1px;
  padding-bottom:10px;
  box-shadow: 0 2px 14px 0 hsla(0,0%,71%,.6);
}
.pattern .ivu-radio-wrapper{
  width: 150px;
  margin:0 0 20px;
  /* font-weight: bold; */
  font-size: 14px;
}
.pattern .ivu-radio{
  margin-right: 10px;
}
.pattern .ivu-radio-checked .ivu-radio-inner{
  border-color:#00bedc;
}
.pattern .ivu-radio-inner:after{
  background-color:#00bedc;
}
.pattern .ivu-radio-wrapper span:last-child{
    width: 118px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    display: inline-block;
    position: relative;
    top: 5px;
}
</style>

<template>
  <main class="pattern">
    <section class="pattern-left">
      <Tabs :value='tabsSelected' @on-click='onTabsChange'>
        <TabPane label="酒店列表" name="list" ></TabPane>
        <TabPane label="筛选条件" name="map"></TabPane>
      </Tabs>
      <!-- 酒店列表 -->
      <div class="pattern-left-list" v-if='tabsSelected==="list"'>
            <div class='pattern-left-list-rate'>
              <Affix :offset-top="120">
                <hotel-rate @on-rate='onRate'></hotel-rate>
              </Affix>
            </div>
            <div class="hotel-all">
              <hotel-card
                type="2"
                :data="cardData"
                @on-notice-map='onNoticeMap'
              ></hotel-card>
            </div>
      </div>
      <!-- 筛选条件 -->
      <section class='pattern-left-search' v-if='tabsSelected==="map"'>
            <div class="lineMap-selected" v-if='haveChosen.length>0'>
              <p class="line-selectedTitle">您的选择<span @click='clearAll()'>清空筛选</span></p>
              <div class="selected-text" v-for='(item,index) in haveChosen' :key='index'>
                <p @click='removeSelected(item)'>
                  <b v-if='item.type==="price"'>¥ </b>
                  {{item.label}}
                  <span></span>
                  <Icon type="ios-close" />
                </p>
              </div>
            </div>

            <!-- 价格 -->
            <div class="line" :class="{'priceHeightClose':!collapse.price}">
              <p class="line-title">价格<a @click='collapse.price=!collapse.price'>[{{onRetract(collapse.price)}}]</a></p>
              <div class='priceData-input' :class="{'priceData-bg':openPrice}">
                  <InputNumber :max="10" :min="1" v-model="priceList.input.start" @on-focus='openPrice=true'></InputNumber> -
                  <InputNumber :max="10" :min="1" v-model="priceList.input.end" @on-focus='openPrice=true'></InputNumber>
                  <span @click='openPrice=false;onPriceSure()'>确定</span>
              </div>
              <div>
                 <RadioGroup v-model="priceSelected" @on-change='onPriceRadio'>
                    <Radio :label="item.value" v-for='(item,index) of priceList.data' :key='index' :class='{"selected":priceSelected==item.value}'>
                      <span>¥ {{item.label}}</span>
                    </Radio>
                 </RadioGroup>
              </div>
            </div>

            <!-- 星级 -->
            <div class="line">
              <p class="line-title">星级</p>
              <div>
                 <RadioGroup v-model="startSelected" @on-change='onStartRadio'>
                    <Radio :label="item.value" v-for='(item,index) of startList' :key='index' :class='{"selected":startSelected==item.value}'>
                      <span>{{item.label}}</span>
                    </Radio>
                 </RadioGroup>
              </div>
            </div>

             <!-- 评分 -->
            <div class="line">
              <p class="line-title">评分</p>
              <div>
                 <RadioGroup v-model="scoreSelected" @on-change='onScoreRadio'>
                    <Radio :label="item.value" v-for='(item,index) of scoreList' :key='index' :class='{"selected":scoreSelected==item.value}'>
                      <span>{{item.label}}</span>
                    </Radio>
                 </RadioGroup>
              </div>
            </div>

             <!-- 位置 -->
            <div class="line" >
              <p class="line-title">位置</p>
              <div v-for='(item,index) of positionList' :key='index'>
                 <p class="line-title">{{item.name}} <a @click='item.visible=!item.visible'>[{{onRetract(collapse.price)}}]</a></p>
                 <div :class="{'positionHeightColse':!item.visible}" class='overflow'>
                    <RadioGroup v-model="positionSelected" @on-change='onPositionRadio'>
                        <Radio :label="item.name" v-for='(item,i) in positionData[item.key]' :key='i'>
                          <span>{{item.name}}</span>
                        </Radio>
                    </RadioGroup>
                  </div>
              </div>
            </div>

             <!-- 酒店设施 -->
            <div class="line" style='border:none'>
              <p class="line-title">酒店设施</p>
              <div>
                 <RadioGroup v-model="otherSelected" @on-change='onFacilitiesRadio'>
                    <Radio :label="item.value" v-for='(item,index) of hotelFacilitiesList' :key='index' :class='{"selected":otherSelected==item.value}'>
                      <span>{{item.label}}</span>
                    </Radio>
                 </RadioGroup>
              </div>
            </div>
          </section>
    </section>
    <section class="pattern-right">
      <div class="toList" @click='onListmode()'>
        <p><img src="https://content.igola.com/static/WEB/images/other/list.png" alt="">列表模式</p>
      </div>
      <div class="map" id="allmap"></div>
    </section>
  </main>
</template>

<script>
import hotelRate from '../../components/hotel-rate/hotel-rate'
import hotelCard from '../../components/hotel-card/hotel-card'
import {welcome} from '../../../../assets/json/welcome.js'
import {evaluate} from '../../../../assets/json/evaluate.js'
import {lowPrice} from '../../../../assets/json/lowPrice.js'
import {highPrice} from '../../../../assets/json/highPrice.js'
import base from '../../components/filter-children/minxins.js'
import axios from 'axios/dist/axios.min'
export default {
  components: {
    hotelRate, hotelCard
  },
  mixins: [base],
  data () {
    return {
      tabsSelected: 'list',
      collapse: {
        price: false
      },
      // 地图
      data: [],
      cardData: [],
      maoObj: {},
      myMap: null,
      nolimit: {
        label: '不限',
        value: '不限'
      },
      positionData: []
      // 搜索
      // mixin
    }
  },
  mounted () {
    this.priceList.data.unshift(this.nolimit)
    this.startList.unshift(this.nolimit)
    this.scoreList.unshift(this.nolimit)
    this.hotelFacilitiesList.unshift(this.nolimit)
    // 获取位置list
    this.getPositionData()
  },
  methods: {
    // 价格 二次过滤
    onPriceRadio (data) {
      if (data === '不限') {
        this.onUnlimitedClick('price')
      } else {
        let option = this.priceList.data.find((item) => {
          return item.value === data
        })
        this.onSelectedChange(option, 'price')
      }
    },
    // 星级 二次过滤
    onStartRadio (data) {
      if (data === '不限') {
        this.onUnlimitedClick('start')
      } else {
        let option = this.startList.find((item) => {
          return item.value === data
        })
        this.onSelectedChange(option, 'start')
      }
    },
    // 评分 二次过滤
    onScoreRadio (data) {
      if (data === '不限') {
        this.onUnlimitedClick('score')
      } else {
        let option = this.scoreList.find((item) => {
          return item.value === data
        })
        this.onSelectedChange(option, 'score')
      }
    },
    onPositionRadio (data) {
      // let option = this.scoreList.find((item) => {
      //   return item.value === data
      // })
      let option = {
        label: data,
        value: data,
        name: data
      }
      this.onSelectedChange(option, 'position')
    },
    onFacilitiesRadio (data) {
      if (data === '不限') {
        this.onUnlimitedClick('other')
      } else {
        let option = this.hotelFacilitiesList.find((item) => {
          return item.value === data
        })
        option['name'] = option.value
        this.onSelectedChange(option, 'other')
      }
    },
    // 获取位置list
    getPositionData () {
      let self = this
      axios
        .get(
          'https://www.igola.com/web-gateway/api-hotel-static/geo/pois?city=771&lan=ZH'
        )
        .then(data => {
          self.positionData = data.data.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    onRetract (data) {
      return !data ? '展开' : '收起'
    },
    onTabsChange (data) {
      this.tabsSelected = data
    },
    // --------------------------------------地图--------------------------------------//
    getCardData () {
      // let self = this
      // axios
      //   .get(
      //     'https://www.igola.com/web-gateway/api-hotel/search?searchType=all&sort.by=recommendation&sort.order=desc&city=771&checkInDate=2019-01-16&checkOutDate=2019-01-17&geo.distance=&limiter.xsell=false&filter.price=&filter.star=&filter.score=&filter.facility=&filter.brand=&filter.type=&keyword=&adult=2&children=0&roomCount=1&pag.size=20&pag.from=0&newVersion=true&extraOtaCodes=agoda,ctrip-domestic,ctrip-international'
      //   )
      //   .then(data => {
      //     self.cardData = data.data.data
      //     if (this.cardData.length) {
      //       self.onMap(this.cardData)
      //     }
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })
    },
    onMap (data, zoom) {
      let firstData = data[0]
      let BMap = window['BMap']
      var mp = new BMap.Map('allmap')
      mp.centerAndZoom(new BMap.Point(firstData.lng, firstData.lat), zoom)
      mp.enableScrollWheelZoom()
      // 复杂的自定义覆盖物
      function ComplexCustomOverlay (point, text, mouseoverText) {
        this._point = point
        this._text = text
        this._overText = mouseoverText
      }
      ComplexCustomOverlay.prototype = new BMap.Overlay()
      ComplexCustomOverlay.prototype.initialize = function (map) {
        this._map = map
        var div = this._div = document.createElement('div')
        div.style.position = 'absolute'
        div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat)
        div.style.backgroundColor = '#fff'
        div.style.border = '1px solid #fff'
        div.style.color = '#ef7f5e'
        div.style.height = '48px'
        div.style.padding = '0px 15px 8px 15px'
        div.style.lineHeight = '48px'
        div.style.whiteSpace = 'nowrap'
        div.style.MozUserSelect = 'none'
        div.style.fontSize = '16px'
        div.style.fontWeight = 'bold'
        div.style.borderRadius = '5px'
        div.style.boxShadow = '0 2px 14px 0 rgba(181, 181, 181, 0.6)'
        var span = this._span = document.createElement('span')
        div.appendChild(span)
        span.appendChild(document.createTextNode(this._text))
        var that = this

        var arrow = this._arrow = document.createElement('div')
        arrow.style.position = 'absolute'
        arrow.style.width = 0
        arrow.style.height = 0
        arrow.style.borderWidth = '10px 10px 0'
        arrow.style.borderStyle = 'solid'
        arrow.style.borderColor = '#fff transparent transparent'
        arrow.style.margin = '40px auto'
        arrow.style.top = '6px'
        arrow.style.left = '20px'
        arrow.style.overflow = 'hidden'
        div.appendChild(arrow)

        div.onmouseover = function () {
          this.style.backgroundColor = '#ef7f5e'
          this.style.borderColor = '#ef7f5e'
          this.style.color = '#fff'
          this.style.zIndex = Number(this.style.zIndex) + 999
          this.getElementsByTagName('span')[0].innerHTML = that._overText
          arrow.style.backgroundPosition = '0px -20px'
          arrow.style.borderColor = '#ef7f5e transparent transparent'
        }

        div.onmouseout = function () {
          this.style.backgroundColor = '#fff'
          this.style.borderColor = '#fff'
          this.style.color = '#ef7f5e'
          this.style.zIndex = Number(this.style.zIndex) - 999
          this.getElementsByTagName('span')[0].innerHTML = that._text
          arrow.style.backgroundPosition = '0px 0px'
          arrow.style.borderColor = '#fff transparent transparent'
        }

        mp.getPanes().labelPane.appendChild(div)

        return div
      }
      ComplexCustomOverlay.prototype.draw = function () {
        var map = this._map
        var pixel = map.pointToOverlayPixel(this._point)
        this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + 'px'
        this._div.style.top = pixel.y - 30 + 'px'
      }
      data.forEach((item) => {
        let mouseoverTxt = `¥ ${item.refPrice}起`
        let myCompOverlay = new ComplexCustomOverlay(new BMap.Point(item.lng, item.lat), mouseoverTxt, mouseoverTxt)
        let key = item.lng + '-' + item.lat
        this.maoObj[key] = myCompOverlay
        mp.addOverlay(myCompOverlay)
      })
      this.myMap = mp
    },
    onNoticeMap (option) {
      let key = option.lng + '-' + option.lat
      for (let i in this.maoObj) {
        let map = this.maoObj[i]
        let div = map._div
        div.style.backgroundColor = '#fff'
        div.style.borderColor = '#fff'
        div.style.color = '#ef7f5e'
        div.style.zIndex = map._point.lat
        // 三角
        let arrow = map._arrow
        arrow.style.backgroundPosition = '0px 0px'
        arrow.style.borderColor = '#fff transparent transparent'
      }
      let map = this.maoObj[key]
      let div = map._div
      div.style.backgroundColor = '#ef7f5e'
      div.style.borderColor = '#ef7f5e'
      div.style.color = '#fff'
      div.style.zIndex = Number(div.style.zIndex) + 999
      // 三角
      let arrow = map._arrow
      arrow.style.backgroundPosition = '0px -20px'
      arrow.style.borderColor = '#ef7f5e transparent transparent'
      this.myMap.panTo(new window['BMap'].Point(option.lng, option.lat))
    },
    // 切换列表模式
    onListmode () {
      this.$emit('on-list-pattern', true)
    },
    onRate (data) {
      //  {
      //     label: '受欢迎度优先',
      //     value: 'welcome'
      //   },
      //   {
      //     label: '评分优先',
      //     value: 'evaluate'
      //   },
      //   {
      //     label: '低价优先',
      //     value: 'lowPrice'
      //   },
      //   {
      //     label: '高价优先',
      //     value: 'highPrice'
      //   }
      let zoom = 17
      switch (data) {
        case 'welcome':
          this.cardData = welcome.data
          zoom = 13
          break
        case 'evaluate':
          this.cardData = evaluate.data
          zoom = 11
          break
        case 'lowPrice':
          this.cardData = lowPrice.data
          zoom = 12
          break
        case 'highPrice':
          this.cardData = highPrice.data
          zoom = 13
          break
      }
      this.onMap(this.cardData, zoom)
    }
  }
}
</script>
