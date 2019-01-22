
<style lang='scss' scoped>
@import "./hotel-detail.scss";
</style>
<style>
  .detailWrap .ivu-carousel-list{
    width: 60%;
    margin: 0 auto;
  }
  .pattern .ivu-tabs-nav {
  height: 45px;
  line-height: 31px;
}
.detailWrap .ivu-tabs-ink-bar {
  width: 30px !important;
  height: 6px;
  position: absolute;
  background-image: linear-gradient(90deg, #28c8dc 0, #3296d2 100%);
  border-radius: 61px;

}
.detailWrap .ivu-tabs-nav .ivu-tabs-tab-active {
  color: #00bedc !important;
  font-weight: 600;
}
.detailWrap .ivu-tabs-tab {
  color: #464646!important;
  font-size: 16px;
  padding: 12px 0px;
}
.detailWrap .ivu-tabs-bar {
  border: none;
  margin-bottom: -1px;
  padding-bottom:10px;
  box-shadow: 0 2px 14px 0 hsla(0,0%,71%,.6);
}
</style>

<template>
<main class="detailWrap">
  <div class='mark' v-if='markOpen'>
    <div class='mark-content'>
      <Carousel v-model="value1" autoplay loop style='wdith:100%;height:500px' dots='outside' arrow='always' :radius-dot="radiusDot" :easing='easing'>
        <CarouselItem v-for='(item,index) in imgList' :key='index'>
            <div class="mark-carousel">
              <img :src="item" alt=""/>
              <div class="total">
                <span>{{index+1}}</span>/{{imgList.length}}
              </div>

            </div>
        </CarouselItem>
       </Carousel>
        <div class="icon-close"><Icon type="md-close"  @click='markOpen=false'/></div>
    </div>
  </div>
  <div class="detailWrap-panel">
    <div class="detailWrap-panel-box" @click='markOpen=true'>
      <img src="https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45240371.jpg" alt="" class="image-style">
      <img src="https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45162497.jpg" alt="" class="image-style">
      <div class="detailWrap-panel-box-total">{{imgList.length}}张图片</div>
    </div>
  </div>
  <!-- 内容 -->
  <div class="detailWrap-content">
    <section class='detailWrap-content-left'>
      <div class="title-panel">
        <div class="title-text">广州珠江帝景酒店 <span class="star-panel">四星/高端型</span></div>
        <div class="score-panel">
          <p>不错</p>
          <div class="icon-panel">
            <b></b>
            <span class="score-text">3.3</span>
          </div>
        </div>
      </div>
      <div class="tiny-text font-light-mac">Regal Riviera Hotel Guangzhou</div>
       <div class="tiny-text font-light-mac">1 Haojing Street (Haojing Jie), Yizhou Road (Yizhou Lu), Guangzhou, China
         <div class="map-panel" @click='seeMap()'> <Icon type="md-pin"/>查看地图</div>
       </div>
       <div class="tab-panel">
         <Tabs :value='tabsSelected' @on-click='onTabsChange'>
            <TabPane :label="item.label" :name="item.value" v-for='(item,index) in tabsList' :key='index'></TabPane>
          </Tabs>
       </div>
       <!-- 床 -->
       <div class="rate-plan-panel">
         <div class="main-title-text">房型选择</div>

         <!-- 过滤 -->
         <section>
           <div class="setting-panel paddingTop20">
             <div class='setting-panel-top'>
                <my-date></my-date>
                <my-room></my-room>
                <div class="confirm-btn">确定</div>
             </div>
           </div>
           <div class="setting-panel" style='border-top:none'>
              <Dropdown trigger="click" @on-click='onBreakfastChange'>
                  <p class='setting-panel-serachBtn' :class='{"selected-filterBth":breakfastSelected}'>{{breakfastSelected?breakfastSelected:'早餐'}}<Icon type="ios-arrow-down"></Icon></p>
                  <DropdownMenu slot="list">
                      <DropdownItem v-for='(item,index) in breakfastList' :key='index' :name='item.value'>{{item.label}}</DropdownItem>
                  </DropdownMenu>
              </Dropdown>
              <p class='setting-panel-serachBtn' @click='cancellingSelected=!cancellingSelected' :class='{"selected-filterBth":cancellingSelected}'>可取消</p>
              <p class='setting-panel-serachBtn' @click='discountSelected=!discountSelected' :class='{"selected-filterBth":discountSelected}'>优惠</p>
               <Dropdown trigger="click" @on-click='onPriceChange'>
                  <p class='setting-panel-serachBtn' :class='{"selected-filterBth":priceSelected}'>{{priceSelected?priceSelected:'价格'}} <Icon type="ios-arrow-down"></Icon></p>
                  <DropdownMenu slot="list">
                      <DropdownItem v-for='(item,index) in priceList.data' :key='index' :name='item.value'>¥ {{item.label}}</DropdownItem>
                  </DropdownMenu>
              </Dropdown>
              <div class="clear-btn" @click='clearFilter()'>清空筛选</div>
           </div>
         </section>

         <!-- 精选 -->
         <section class="rateplan-main">
           <!-- 携程 -->
           <div class="room-style">
              <img src="https://content.igola.com/static/WEB/images/hotel/ctrip@2x.png" alt="">
              <div  v-for='(item,index) in bedList.ctripKeyList' :key='index'>
                <p class="rate-title">{{item}}</p>
                <div style='margin-bottom: 10px;' v-for='(option, i) in bedList.ctrip[item]' :key='i'>
                   <bed-card :data='option' @on-reserve='onReserve'></bed-card>
                </div>
              </div>
           </div>
           <!-- igola -->
           <div class="room-style">
              <img src="https://content.igola.com/static/WEB/images/hotel/igola@2x.png" alt="">
              <div  v-for='(item,index) in bedList.igolaKeyList' :key='index'>
                <p class="rate-title">{{item}}</p>
                <div style='margin-bottom: 10px;' v-for='(option, i) in bedList.igola[item]' :key='i'>
                   <bed-card :data='option'></bed-card>
                </div>
              </div>
           </div>
         </section>
       </div>
      <!-- 酒店信息 -->
      <section>
        <!-- 酒店简介 -->
        <div class="detail-panel auto-height" :class="{'heightInhert':hotelDetailObj.hotelProfile}">
          <div class="title-1">酒店简介</div>
          <div>
              <div class="detail-panel-item">
                <div class="left-item">酒店简介</div>
                <div class="right-item font-light-mac">
                  广州珠江帝景酒店位于广州新城市中轴线与广州横轴珠江交汇处，城市绿化生态轴线的核心区域，距广交新址琶洲国际会展中心约5分钟车程，是三轴交汇处的至尊地标；酒店与广州商业中心（CBD）地段相邻，环市路和天河北近在咫尺。酒店拥有各类豪华的客房，均拥有独立阳台、豪华卫生间和全智能化的房间控制系统，包括智能温控、智能灯控、电子门锁系统、宽频直驳国际互联网络、卫星及有线电视、音响系统等。酒店拥有能容纳近230人的多功能会议中心，配套先进的智能化会议系统，包括美国REAVEY媒体矩阵音频控制系统、会议智能集中控制系统、远程视像会议系统、多媒体演示系统和会议自动跟踪摄影系统等，配合会议的各种需求。酒店大堂配套齐全，为您提供各项便捷服务。票务中心可直接预订领取折扣机票；更有珠宝玉器店、便利商店、稻香海鲜火锅酒家、让您享受购物、餐饮的乐趣。酒店还设有康乐中心、室内恒温泳池、国际标准的室内多功能球场、羽毛球馆、乒乓球馆和棋牌室。入住还可享受恒温泳池和健身室等，让您在繁忙的商旅中得到身心的放松，体验一段舒心的假期。
                </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">娱乐</div>
                    <div class="right-item  font-light-mac">
                     酒店内有室内游泳池和SPA 浴缸。其他娱乐设施包括桑拿和健身中心。
                   </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">酒店周边</div>
                    <div class="right-item  font-light-mac">
                        显示的距离精确至最近的 0.1 英里或公里。 广州图书馆 - 2.3 公里 / 1.4 英里 广州塔 - 2.5 公里 / 1.6 英里 花城广场 - 2.9 公里 / 1.8 英里 广东省博物馆 - 3.2 公里 / 2 英里 中山大学 - 3.2 公里 / 2 英里 广东美术馆 - 3.6 公里 / 2.2 英里 星海音乐厅 - 3.8 公里 / 2.4 英里 广交会展馆 - 4 公里 / 2.5 英里 黄金海岸水上乐园 - 4 公里 / 2.5 英里 海印公园 - 4.4 公里 / 2.7 英里 沙面岛 - 5.8 公里 / 3.6 英里 天河体育场 - 6.1 公里 / 3.8 英里 中信广场 - 6.2 公里 / 3.9 英里 广州美术学院 - 6.3 公里 / 3.9 英里 第二工人文化宫 - 6.7 公里 / 4.2 英里 最近的机场有：佛山（FUO-沙堤机场） - 36.3 公里 / 22.5 英里 广州 (CAN-白云国际机场) - 43.6 公里 / 27.1 英里 广州珠江帝景酒店的首选机场是广州 (CAN-白云国际机场)。
                   </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">娱乐</div>
                    <div class="right-item  font-light-mac">
                     您可享受室内游泳池、SPA 浴缸和桑拿等丰富度假设施。此酒店的其他特色包括礼宾服务、游乐厅/游戏室和礼品店/报摊
                   </div>
              </div>
          </div>
         <div class="mask-panel">
            <div class="opacity-transition"></div>
            <div class="more-btn" @click='hotelDetailObj.hotelProfile=!hotelDetailObj.hotelProfile'>[{{!hotelDetailObj.facilitiesServices?'查看更多':'收起'}}]</div>
         </div>
        </div>

        <!-- 酒店政策 -->
         <div class="detail-panel auto-height" :class="{'heightInhert':hotelDetailObj.hotelPolicy}">
          <div class="title-1">酒店简介</div>
          <div>
              <div class="detail-panel-item">
                <div class="left-item">入住时间/退房时间</div>
                <div class="right-item font-light-mac">
                  入住时间 12 PM离店时间 12 PM
                </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">取消政策</div>
                    <div class="right-item  font-light-mac">
                     不同类型的客房附带不同的取消政策，详情请参阅客房的政策描述
                   </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">酒店接受的银行卡类型</div>
                    <div class="right-item  font-light-mac">
                        万事达(Master) 威士(VISA) 运通(AMEX) 大来(Diners Club) JCB
                   </div>
              </div>
               <div class="detail-panel-item">
                 <div class="left-item">儿童政策</div>
                    <div class="right-item  font-light-mac">
                     不接受18岁以下客人单独入住。不接受18岁以下客人在无监护人陪同的情况下入住
                   </div>
              </div>
          </div>
         <div class="mask-panel">
            <div class="opacity-transition"></div>
            <div class="more-btn" @click='hotelDetailObj.hotelPolicy=!hotelDetailObj.hotelPolicy'>[{{!hotelDetailObj.hotelPolicy?'查看更多':'收起'}}]</div>
         </div>
        </div>
        <!-- 设施服务 -->
        <div class="detail-panel auto-height" :class="{'heightInhert':hotelDetailObj.facilitiesServices}">
          <div class="title-1">设施服务</div>
          <div>
              <div class="detail-panel-item">
                <div class="left-item">酒店设施服务</div>
                <div class="right-item item-row-hotel font-light-mac">
                  <p class="detail-item">咖啡厅</p>
                  <p class="detail-item">会议厅</p>
                  <p class="detail-item">外币兑换服务</p>
                  <p class="detail-item">旅游票务服务</p>
                  <p class="detail-item">洗衣服务</p>
                  <p class="detail-item">叫车服务</p>

                  <p class="detail-item">邮政服务</p>
                  <p class="detail-item">前台贵重物品保险柜</p>
                  <p class="detail-item">专职行李员</p>
                  <p class="detail-item">行李寄存</p>
                  <p class="detail-item">叫醒服务</p>
                  <p class="detail-item">免费旅游交通图(可赠送)</p>
                </div>
              </div>

               <div class="detail-panel-item">
                 <div class="left-item">房间设施服务</div>
                    <div class="right-item item-row-hotel font-light-mac">
                      <p class="detail-item">国内长途电话</p>
                      <p class="detail-item">国际长途电话</p>
                      <p class="detail-item">拖鞋</p>
                      <p class="detail-item">雨伞</p>
                      <p class="detail-item">书桌</p>
                      <p class="detail-item">浴室化妆放大镜</p>
                   </div>
              </div>
          </div>
         <div class="mask-panel">
            <div class="opacity-transition"></div>
            <div class="more-btn" @click='hotelDetailObj.facilitiesServices=!hotelDetailObj.facilitiesServices'>[{{!hotelDetailObj.facilitiesServices?'查看更多':'收起'}}]</div>
         </div>

        </div>
      </section>

      <!-- iGola 酒店问答 -->
      <section>
          <div class="detail-panel auto-height" style='height: 350px' :class="{'heightInhert':hotelDetailObj.questionsAndAnswers}">
            <div class="title-1">iGola 酒店问答</div>
            <section>
              <p class="item-title"> <span class='icon-wen'>问</span>如何在iGola找到优质且低价的酒店？</p>
              <div class="content-panel">
                iGola精选众多酒店供应商，利用自身在数据搜索领域长期的深厚积累，将海量酒店信息进行收集、筛选、分析、整合，只为您呈现优质、低价且可靠的酒店资源。
              </div>

              <p class="item-title"> <span class='icon-wen'>问</span>我能够放心预订酒店吗？</p>
              <div class="content-panel">
                iGola酒店拥有强大的数据搜索和人工智能技术的支撑，利用辅助预订的功能，确保预订时下单快速、无跳价、支付便利。和iGola签署合作协议的酒店供应商均经过iGola严格评估筛选，您可以放心通过iGola预订酒店产品。
              </div>

              <p class="item-title"> <span class='icon-wen'>问</span>iGola辅助预订和跳转预订有什么不一样？</p>
              <div class="content-panel">
                iGola辅助预订让您不跳出即可快速筛选、预订心仪的酒店和房型，完全在平台内部完成支付流程。
              </div>

              <p class="item-title"> <span class='icon-wen'>问</span>如何在iGola得知我的预订结果？</p>
              <div class="content-panel">
                预订成功后，iGola会给您发送一封酒店确认函的电子邮件以及预订成功短信。您也可以在iGola网页端和APP端的“个人中心-我的订单”中查询到所有订单。
              </div>

              <p class="item-title"> <span class='icon-wen'>问</span>我如何索取我的酒店确认函？</p>
              <div class="content-panel">
                  预订成功后，系统将会向您预留的电子邮箱中发送酒店预订确认函。
              </div>
            </section>
            <div class="mask-panel">
              <div class="opacity-transition"></div>
              <div class="more-btn" @click='hotelDetailObj.questionsAndAnswers=!hotelDetailObj.questionsAndAnswers'>[{{!hotelDetailObj.questionsAndAnswers?'查看更多':'收起'}}]</div>
          </div>
          </div>
      </section>
      <section>
        <div class="title-1">联系方式</div>
        <div class="detail-panel" style='height: inherit;border-bottom:none'>
          <div class="row-content">
            <b>酒店电话</b>
            <span>020-83918883</span>
          </div>
          <div class="row-content">
            <b>iGola 服务热线</b>
            <span>400-900-7280</span>
          </div>
          <div class="row-content">
            <b>iGola服务时间 7x24小时</b>
            <span></span>
          </div>
        </div>
      </section>
    </section>
    <section class='detailWrap-content-right'>
       <Affix :offset-top="80">
          <div class='detailWrap-content-right-border'>
            <div class='item-title'>{{bedDetailObj.rateName}}</div>
            <div class="price-text">¥ {{bedDetailObj.rawNightlyRate}}</div>
            <div class="price-panel">
              <div class="inline-item">1晚x1间</div>
              <div class="inline-item">
                <b>01月20日</b>
                <span>¥ {{bedDetailObj.rawNightlyRate}}</span>
              </div>
              <div class="inline-item">
                <b>应付总价(含税)</b>
                <span class="total-price">¥ {{bedDetailObj.rawNightlyRate}}</span>
              </div>
            </div>
            <div class="book-btn" @click='onhotelReserve()'>预订</div>
          </div>
        </Affix>
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
// import axios from 'axios/dist/axios.min'
import myDate from '../source-panel/date/date'
import myRoom from '../source-panel/room/room'
import bedCard from '../components/bed-card/bed-card'
import {bed} from '../../../assets/json/bed.js'
export default {
  components: {
    myDate, myRoom, bedCard
  },
  data () {
    return {
      modal1: false,
      markOpen: false,
      value1: 0,
      radiusDot: true,
      easing: 'swing',
      imgList: [
        'https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45240371.jpg',
        'https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45162497.jpg',
        'https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45162025.jpg',
        'https://content.igola.com/hotel/detail/a/474306/a_474306_65311_45162027.jpg'
      ],
      tabsSelected: '房型选择',
      tabsList: [
        {
          label: '房型选择',
          value: '房型选择'
        },
        {
          label: '酒店信息',
          value: '酒店信息'
        },
        {
          label: 'iGola酒店问答',
          value: 'iGola酒店问答'
        },
        {
          label: '联系信息',
          value: '联系信息'
        }
      ],
      // 早餐
      breakfastSelected: '',
      breakfastList: [
        {
          label: '含早餐',
          value: '含早餐'
        },
        {
          label: '单份早餐',
          value: '单份早餐'
        },
        {
          label: '双份早餐',
          value: '双份早餐'
        }
      ],
      // 价格
      priceSelected: '',
      priceList: {
        input: {
          start: 0,
          end: 0
        },
        data: [
          {
            label: '200以下',
            value: '200以下'
          },
          {
            label: '200-400',
            value: '200-400'
          },
          {
            label: '400-600',
            value: '400-600'
          },
          {
            label: '600-800',
            value: '600-800'
          },
          {
            label: '800-1000',
            value: '800-1000'
          },
          {
            label: '1000-1300',
            value: '1000-1300'
          },
          {
            label: '1300-1600',
            value: '1300-1600'
          },
          {
            label: '1600-2000',
            value: '1600-2000'
          },
          {
            label: '2000以上',
            value: '2000以上'
          }
        ]
      },
      // 可取消
      cancellingSelected: false,
      // 优惠
      discountSelected: false,
      bedList: {
        ctrip: {},
        igola: {},
        ctripKeyList: [],
        igolaKeyList: []
      },
      bedDetailObj: {},
      // 酒店详情
      hotelDetailObj: {
        hotelProfile: false,
        hotelPolicy: false,
        facilitiesServices: false,
        questionsAndAnswers: false
      }
    }
  },
  mounted () {
    this.getBedData()
  },
  methods: {
    onTabsChange () {

    },
    // 早餐
    onBreakfastChange (data) {
      this.breakfastSelected = data
    },
    // 价格
    onPriceChange (data) {
      this.priceSelected = data
    },
    // 清空筛选
    clearFilter () {
      this.breakfastSelected = ''
      this.priceSelected = ''
      this.cancellingSelected = false
      this.discountSelected = false
    },
    // 获取位置list
    getBedData () {
      this.bedList.ctrip = bed.data.rateplans.ctrip.rooms
      this.bedList.igola = bed.data.rateplans.igola.rooms
      let arr1 = []
      for (let i in this.bedList.ctrip) {
        // this.bedList.ctrip[i].forEach(element => {
        //   element['my-open'] = true
        // })
        arr1.push(i)
      }

      this.bedList.ctripKeyList = arr1
      if (this.bedList.ctripKeyList.length) {
        let m = this.bedList.ctrip[this.bedList.ctripKeyList[0]]
        if (m.length) {
          this.bedDetailObj = m[0]
        }
      }
      let arr2 = []
      for (let i in this.bedList.igola) {
        // this.bedList.igola[i].forEach(element => {
        //   element['my-open'] = true
        // })
        arr2.push(i)
      }
      this.bedList.igolaKeyList = arr2
    },
    // 床选择
    onReserve (data) {
      this.bedDetailObj = data
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
      var infoWindow = new BMap.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts) // 创建信息窗口对象
      map.openInfoWindow(infoWindow, point) // 开启信息窗口
      map.centerAndZoom(point, 17)
    },
    seeMap () {
      this.modal1 = true
      setTimeout(() => {
        this.onMap({lat: 23.106316, lng: 113.321942})
      }, 0)
    },
    onhotelReserve () {
      this.$router.push('/hotel/hotel-booking')
    }
  }
}
</script>
