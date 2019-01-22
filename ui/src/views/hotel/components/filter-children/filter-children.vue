
<style lang='scss' scoped>
@import "./filter-children.scss";
</style>
<template>
<main class='filterWrap'>
  <section class="filter">
    <div class="filter-inner" @click='$event.stopPropagation();'>
      <span class="line-title">位置</span>
      <span class="line-content hoverColor" :class='{"selectedColor":positionSelected=="不限"}' @click='onUnlimitedClick("position")'>不限</span>
      <!-- 地点 -->
      <div class='line-content' v-for='(item,index) of positionList' :key='index'>
          <p @click="positionIconInit(index);item.visible=!item.visible">
            <span class='hoverColor' :class='{"selectedColor":item.open}'>{{item.name}}</span>
            <Icon :type="!item.visible?'md-arrow-dropdown':'md-arrow-dropup'" size='30' :class='{"selectedColor":item.open}'/>
          </p>
          <section class='Model' v-if='item.visible'>
            <p v-for='(option,i) in data[item.key]' :key='i' @click='onSelectedChange(option,"position"); oncloseSelectedColor("position");item.open=true'>
              <b :class='{"selectedColor":positionSelected==option.name}'>{{option.name}}</b>
              <span :class='{"selectedColor":positionSelected==option.name}'>{{option.nameEn}}</span>
            </p>
          </section>
      </div>
      <!-- 价格 -->
    </div>
    <div class="filter-inner overflow" @click='$event.stopPropagation();' :class="{'openPriceHeight':openPrice}">
        <span class="line-title">价格</span>
        <span class="line-content hoverColor" :class='{"selectedColor":priceSelected=="不限"}' @click='onUnlimitedClick("price")'>不限</span>
        <!-- input -->
        <div class='priceData-input' :class="{'priceData-bg':openPrice}">
            <InputNumber :max="10" :min="1" v-model="priceList.input.start" @on-focus='openPrice=true'></InputNumber> -
            <InputNumber :max="10" :min="1" v-model="priceList.input.end" @on-focus='openPrice=true'></InputNumber>
            <span @click='openPrice=false;onPriceSure()'>确定</span>
        </div>
        <!-- button -->
        <section class='priceData-button'>
           <div class='line-content hoverColor' v-for='(item,index) of priceList.data' :key='index' @click="openPrice=false;onSelectedChange(item,'price')" :class='{"selectedColor":priceSelected==item.value}'>
              ¥ {{item.label}}
            </div>
        </section>
        <div class='priceData-open' @click='openPrice=!openPrice'>{{openPrice?'合起':'展开'}}</div>
    </div>
    <!-- 评分 -->
     <div class="filter-inner overflow" @click='$event.stopPropagation();'>
        <span class="line-title">评分</span>
        <span class="line-content hoverColor" :class='{"selectedColor":scoreSelected=="不限"}' @click='onUnlimitedClick("score")'>不限</span>
        <div class='line-content hoverColor' v-for='(item,index) in scoreList' :key='index' @click="onSelectedChange(item,'score')" :class="{'selectedColor':scoreSelected==item.value}">
          {{item.label}}
        </div>
    </div>
    <!-- 星级 -->
    <div class="filter-inner overflow" @click='$event.stopPropagation();'>
        <span class="line-title">星级</span>
        <span class="line-content hoverColor" :class='{"selectedColor":startSelected=="不限"}' @click='onUnlimitedClick("start")'>不限</span>
        <div class='line-content hoverColor' v-for='(item,index) in startList' :key='index' @click="onSelectedChange(item,'start')" :class="{'selectedColor':startSelected==item.value}">
          {{item.label}}
        </div>
    </div>
    <!-- 其他 -->
     <div class="filter-inner" @click='$event.stopPropagation();' style='border:none'>
        <span class="line-title">其他</span>
        <span class="line-content hoverColor" :class='{"selectedColor":otherSelected=="不限"}' @click='onUnlimitedClick("other")'>不限</span>
        <div class='line-content' v-for='(item,index) of otherList' :key='index'>
          <p @click="otherIconInit(index);item.visible=!item.visible">
            <span class='hoverColor' :class='{"selectedColor":item.open}'>{{item.name}}</span>
            <Icon :type="!item.visible?'md-arrow-dropdown':'md-arrow-dropup'" size='30' :class='{"selectedColor":item.open}'/>
          </p>
          <section class='Model' v-if='item.visible'>
            <p v-for='(option,i) in data[item.key]' :key='i' @click='onSelectedChange(option,"other");oncloseSelectedColor("other");item.open=true'>
              <b :class='{"selectedColor":otherSelected==option.name}'>{{option.name||option.nameEn}}</b>
            </p>
          </section>
      </div>
    </div>
  </section>
  <footer class='footer' v-if='haveChosen.length>0'>
    <p class='footer-title'>您的选择</p>
    <section>
      <div class="selected-text" v-for='(item,index) in haveChosen' :key='index'>
        <p @click='removeSelected(item)'>
          <b v-if='item.type==="price"'>¥ </b>
          {{item.label}}
          <span></span>
          <Icon type="ios-close" />
        </p>
      </div>
    </section>
    <div class="footer-selectedClear" @click='clearAll()'>清空筛选</div>
  </footer>
</main>
</template>

<script>
import base from './minxins.js'
export default {
  props: ['data'],
  components: {

  },
  mixins: [base],
  data () {
    return {
      // typeMy: ['position', 'price', 'score', 'start', 'other'],
      // // 位置
      // positionList: [
      //   {
      //     name: '行政/商业区',
      //     visible: false,
      //     key: 'neighborhoods'
      //   },
      //   {
      //     name: '机场车站',
      //     visible: false,
      //     key: 'trainAirport'
      //   },
      //   {
      //     name: '地铁线路',
      //     visible: false,
      //     key: 'metroStation'
      //   },
      //   {
      //     name: '地标',
      //     visible: false,
      //     key: 'landmark'
      //   },
      //   {
      //     name: '景点',
      //     visible: false,
      //     key: 'scenicSpot'
      //   },
      //   {
      //     name: '娱乐',
      //     visible: false,
      //     key: 'entertainment'
      //   },
      //   {
      //     name: '学校',
      //     visible: false,
      //     key: 'school'
      //   }
      // ],
      // positionSelected: '不限',
      // // 价格
      // priceSelected: '不限',
      // priceList: {
      //   input: {
      //     start: 0,
      //     end: 0
      //   },
      //   data: [
      //     {
      //       label: '200以下',
      //       value: '200以下'
      //     },
      //     {
      //       label: '200-400',
      //       value: '200-400'
      //     },
      //     {
      //       label: '400-600',
      //       value: '400-600'
      //     },
      //     {
      //       label: '600-800',
      //       value: '600-800'
      //     },
      //     {
      //       label: '800-1000',
      //       value: '800-1000'
      //     },
      //     {
      //       label: '1000-1300',
      //       value: '1000-1300'
      //     },
      //     {
      //       label: '1300-1600',
      //       value: '1300-1600'
      //     },
      //     {
      //       label: '1600-2000',
      //       value: '1600-2000'
      //     },
      //     {
      //       label: '2000以上',
      //       value: '2000以上'
      //     }
      //   ]
      // },
      // openPrice: false,
      // // 评分
      // scoreSelected: '不限',
      // scoreList: [
      //   {
      //     label: '4分以上',
      //     value: '4分以上'
      //   },
      //   {
      //     label: '3分以上',
      //     value: '3分以上'
      //   }
      // ],
      // // 星级
      // startSelected: '不限',
      // startList: [
      //   {
      //     label: '3星/舒适',
      //     value: '3星/舒适'
      //   },
      //   {
      //     label: '4星/高级',
      //     value: '4星/高级'
      //   },
      //   {
      //     label: '5星/豪华',
      //     value: '5星/豪华'
      //   }
      // ],
      // // 其他
      // otherSelected: '不限',
      // otherList: [
      //   {
      //     name: '酒店品牌',
      //     visible: false,
      //     key: 'brands'
      //   },
      //   {
      //     name: '酒店设施',
      //     visible: false,
      //     key: 'trainAirport'
      //   }
      // ],
      // // 已选择
      // haveChosen: [
      //   // {
      //   //   label: '天河',
      //   //   value: 1,
      //   //   type: ''
      //   // }
      // ]
    }
  },
  mounted () {
    this.$bus.on('overall-close', () => {
      this.positionIconInit(-1)
      this.otherIconInit(-1)
    })
  },
  methods: {
    // // 价格确定
    // onPriceSure () {
    //   let name = this.priceList.input.start + '-' + this.priceList.input.end
    //   let obj = {
    //     label: name,
    //     value: name
    //   }
    //   this.onSelectedChange(obj, 'price')
    // },
    // // 点击选中-所有
    // onSelectedChange (option, type) {
    //   // typeMy: ['position', 'price', 'score', 'start', 'other'],
    //   switch (type) {
    //     case 'position': // 位置
    //       this.positionSelected = option.name
    //       this.positionIconInit(-1)
    //       let positionObj = {
    //         label: option.name,
    //         value: option.name,
    //         type: type
    //       }
    //       this.addSelected(positionObj)
    //       break
    //     case 'price':// 价格
    //       this.priceSelected = option.value
    //       let priceObj = {
    //         label: option.label,
    //         value: option.value,
    //         type: type
    //       }
    //       this.addSelected(priceObj)
    //       break
    //     case 'score': // 评分
    //       this.scoreSelected = option.value
    //       let scoreObj = {
    //         label: option.label,
    //         value: option.value,
    //         type: type
    //       }
    //       this.addSelected(scoreObj)
    //       break
    //     case 'start': // 星级
    //       this.startSelected = option.value
    //       let startObj = {
    //         label: option.label,
    //         value: option.value,
    //         type: type
    //       }
    //       this.addSelected(startObj)
    //       break
    //     case 'other': // 其他
    //       this.otherSelected = option.name
    //       this.otherIconInit(-1)
    //       const otherObj = {
    //         label: option.name,
    //         value: option.name,
    //         type: 'other'
    //       }
    //       this.addSelected(otherObj)
    //       break
    //   }
    // },
    // // 添加选中
    // addSelected (option) {
    //   let index = -1
    //   this.haveChosen.forEach((item, i) => {
    //     if (item.type === option.type) {
    //       index = i
    //     }
    //   })
    //   if (index !== -1) {
    //     this.haveChosen.splice(index, 1, option)
    //     // 替换
    //   } else {
    //     this.haveChosen.push(option)
    //   }
    // },
    // // ------------------------//
    // // 点击不限
    // onUnlimitedClick (type) {
    //   this.removeSelected({type})
    // },
    // // 删除个项
    // removeSelected (option) {
    //   let index = -1
    //   this.haveChosen.forEach((item, i) => {
    //     if (item.type === option.type) {
    //       index = i
    //     }
    //   })
    //   if (index !== -1) {
    //     this.haveChosen.splice(index, 1)
    //   }
    // },
    // // 删除所有
    // clearAll () {
    //   this.this.haveChosen = []
    // },
    // // 位置 icon
    // positionIconInit (index) {
    //   this.positionList.forEach((item, i) => {
    //     if (index !== i) {
    //       item.visible = false
    //     }
    //   })
    // },
    // 其他 icon
    // otherIconInit (index) {
    //   this.otherList.forEach((item, i) => {
    //     if (index !== i) {
    //       item.visible = false
    //     }
    //   })
    // }
  }
}
</script>
