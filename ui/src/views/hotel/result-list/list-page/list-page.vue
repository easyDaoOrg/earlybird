
<style lang='scss' scoped>
@import "./list-page.scss";
</style>
<template>
  <main class="listPage">
    <div class="listPage-content">
      <filter-children :filterData="filterData" ref='filterChildren'></filter-children>
      <section class="listPage-content-hotel">
         <Affix :offset-top="70">
           <hotel-rate></hotel-rate>
        </Affix>
        <div class="hotel-bottom">
          <div class="hotel-bottom-left">
            <hotel-card @see-map='seeMap' type='1' :data='cardData'></hotel-card>
          </div>
          <div class="hotel-bottom-right">
            <Affix :offset-top="100">
              <div class="map-logo" @click='onMapPattern()'>
                <img src="https://content.igola.com/static/WEB/images/hotel/list_mapGif.gif" alt>
                <p>地图模式</p>
              </div>
            </Affix>
          </div>
        </div>
      </section>
    </div>
    <Modal
      v-model="modal1"
      width='80%'
      title="地图"
      @on-ok="modal1=false"
      @on-cancel="modal1=false"
    >
      <div class="map" id='allmap'></div>
    </Modal>
  </main>
</template>

<script>
import filterChildren from '../../components/filter-children/filter-children'
import hotelCard from '../../components/hotel-card/hotel-card'
import hotelRate from '../../components/hotel-rate/hotel-rate'
import { setTimeout } from 'timers'
import {welcome} from '../../../../assets/json/welcome.js'
export default {
  components: {
    filterChildren,
    hotelCard,
    hotelRate
  },
  props: {
    filterData: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data () {
    return {
      modal1: false,
      map: null,
      cardData: []
    }
  },
  mounted () {
    this.getCardData()
  },
  methods: {
    getCardData () {
      this.cardData = welcome.data
      // let self = this
      // axios
      //   .get(
      //     'https://www.igola.com/web-gateway/api-hotel/search?searchType=all&sort.by=recommendation&sort.order=desc&city=771&checkInDate=2019-01-16&checkOutDate=2019-01-17&geo.distance=&limiter.xsell=false&filter.price=&filter.star=&filter.score=&filter.facility=&filter.brand=&filter.type=&keyword=&adult=2&children=0&roomCount=1&pag.size=20&pag.from=0&newVersion=true&extraOtaCodes=agoda,ctrip-domestic,ctrip-international'
      //   )
      //   .then(data => {
      //     self.cardData = data.data.data
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })
    },
    onMap (data) {
      let lat = data.lat
      let lng = data.lng
      let BMap = window['BMap']
      var map = new BMap.Map('allmap')
      var point = new BMap.Point(lng, lat)
      var marker = new BMap.Marker(point) // 创建标注
      map.addOverlay(marker) // 将标注添加到地图中
      var opts = {
        width: 200, // 信息窗口宽度
        height: 100, // 信息窗口高度
        title: data.nameChn || '', // 信息窗口标题
        enableMessage: true, // 设置允许信息窗发送短息
        message: data.nameEng || ''
      }
      var infoWindow = new BMap.InfoWindow(data.address, opts) // 创建信息窗口对象
      map.openInfoWindow(infoWindow, point) // 开启信息窗口
      map.centerAndZoom(point, 17)
    },
    seeMap (data) {
      this.modal1 = true
      setTimeout(() => {
        this.onMap(data)
      }, 0)
    },
    onMapPattern () {
      this.$refs.filterChildren.onStorageVuex()
      this.$emit('on-map-pattern', false)
    }
  }
}
</script>
