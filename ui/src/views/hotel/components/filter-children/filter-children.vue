
<style lang='scss' scoped>
@import "./filter-children.scss";
</style>

<template>
<main class='filterWrap'>

  <section class="filter">
    <div class="filter-inner" @click='$event.stopPropagation();'>
      <span class="line-title">位置</span>
      <!-- {{filter_D}} -->
      <span class="line-content hoverColor" :class='{"selectedColor":positionSelected=="不限"}' @click='onUnlimitedClick("position")'>不限</span>

      <!-- 位置 -->
      <div class='line-content' v-for='(item,index) of positionList' :key='index'>
          <p @click="positionIconInit(index);item.visible=!item.visible">
            <span class='hoverColor' :class='{"selectedColor":item.open}'>{{item.name}}</span>
            <Icon :type="!item.visible?'md-arrow-dropdown':'md-arrow-dropup'" size='30' :class='{"selectedColor":item.open}'/>
          </p>
          <section class='Model' v-if='item.visible'>
            <p v-for='(option,i) in filterData[item.key]' :key='i' @click='onSelectedChange(onPushObjPositionTypePosition(option,item.key),"position"); oncloseSelectedColor("position");item.open=true'>
              <b :class='{"selectedColor":positionSelected==option.name}'>{{option.name}}</b>
              <span :class='{"selectedColor":positionSelected==option.name}'>{{option.nameEn}}</span>
            </p>
          </section>
      </div>
    </div>

     <!-- 价格 -->
    <div class="filter-inner overflow priceStyle" @click='$event.stopPropagation();' :class="{'openPriceHeight':openPrice}">
        <span class="line-title">价格</span>
        <span class="line-content hoverColor" :class='{"selectedColor":priceSelected=="不限"}' @click='onUnlimitedClick("price")'>不限</span>
        <!-- input -->
        <div class='priceData-input' :class="{'priceData-bg':openPrice}">
          <p>
            <!-- 这里用的原声的input iview的input 过滤会有匹配问题-->
            <input type="text" v-model="priceList.start"  @input="priceList.start=filterPriceReg(priceList.start)" @focus='openPrice=true' placeholder="最低价">
          </p>
          <strong>-</strong>
          <p>
            <input type="text" v-model="priceList.end"  @input="priceList.end=filterPriceReg(priceList.end)" @focus='openPrice=true' placeholder="最高价">
          </p>
            <span @click='openPrice=false;onPriceSure()'>确定</span>
        </div>
        <!-- button -->
        <section class='priceData-button'>
           <div class='line-content hoverColor' v-for='(item,index) of priceList.data' :key='index' @click="openPrice=false;onSelectedChange(item,'price');initPricelist()" :class='{"selectedColor":priceSelected==item.value}'>
              ¥ {{item.label}}
            </div>
        </section>
        <div class='priceData-open' @click='openPrice=!openPrice'>{{openPrice?'收起':'展开'}}</div>
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
        <span class="line-content hoverColor" :class='{"selectedColor":startSelected.no=="不限"}' @click='onUnlimitedClick("start")'>不限</span>
        <div class='line-content hoverColor' v-for='(item,index) in startList' :key='index' @click="onstartfirstChange(item,'start')" :class="{'selectedColor': startSelected.data.indexOf(item.label)!=-1}">
          {{item.label}}
        </div>
    </div>

    <!-- 其他 -->
     <div class="filter-inner" @click='$event.stopPropagation();' style='border:none'>
        <span class="line-title">其他</span>
        <span class="line-content hoverColor" :class='{"selectedColor":otherSelected=="不限"}' @click='onUnlimitedClick("other")'>不限</span>
        <div class='line-content' v-for='(item,index) of otherList' :key='index'>
          <p @click="otherIconInit(index);item.visible=!item.visible;initOtherSelected(item.visible)">

            <span class='hoverColor' :class='{"selectedColor":item.open}'>{{item.name}}</span>
            <Icon :type="!item.visible?'md-arrow-dropdown':'md-arrow-dropup'" size='30' :class='{"selectedColor":item.open}'/>

          </p>
          <section class='Model' v-if='item.visible'>
            <p v-for='(option,i) in filterData[item.key]' :key='i' @click='onSelectedChange(option,"other",index);'>
              <!-- :class='{"selectedColor":item.data.indexOf(option.name)!=-1}' -->
              <b :class='{"selectedColor":item.data.indexOf(option.name)!=-1}'>{{option.name||option.nameEn}}</b>
            </p>
            <Button @click='otherSure();oncloseSelectedColor("other");' class='otherSrueStyle'>确定</Button>
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
  props: ['filterData'],
  components: {

  },

  mixins: [base],
  data () {
    return {
      componentsType: 'filter-children'
    }
  },
  mounted () {
    this.$bus.on('overall-close', () => {
      this.positionIconInit(-1)
      this.otherIconInit(-1)
    })
  },
  methods: {
    // 添加type_position属性
    onPushObjPositionTypePosition (oition, key) {
      oition['type_position'] = key
      return oition
    },
    // 星级点击添加 改变存储的数组-方便-》（minxins负责统一处理，因为map-pattern组件里是复选框返回是数组，所以统一处理）
    onstartfirstChange (option, type) {
      const idxStart = this.startSelected.data.indexOf(option.value)
      if (idxStart === -1) {
        this.startSelected.data.push(option.value)
      } else {
        this.startSelected.data.splice(idxStart, 1)
      }
      this.onSelectedChange(option, type)
    },
    onStorageVuex () {
      this.setHotelFilterData(this.haveChosen)
    }
  }
}
</script>
