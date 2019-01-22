
<style lang='scss' scoped>
@import "./trip.scss";
@import "./moretrip.scss";
</style>
<style>
    .flights-moretrip-tcc .flights-index-text input{
      height: 26px;
    }
</style>

<template>
<!-- searchClass -->
  <div>
    <div class="flights-moretrip timelineActive flights-moretrip-three" v-if="searchClass">
      <div class="flights-moretrip-segment">
        <span>第一程</span>
        <b>请选择第1程</b>
      </div>
      <div class="flights-moretrip-more" @click="moretripLayer()">
        <i><Icon type="ios-more" /></i>
      </div>
      <div class="flights-moretrip-segment">
        <span>第二程</span>
        <b>请选择第2程</b>
      </div>
      <div class="flights-index-search-input-button fl">
        <Icon type="ios-search" />
      </div>
    </div>

    <div :class="{'flights-moretrip-tcc': layerBoolean}">
        <div :class="{'flights-moretrip-layer': searchClass}">
        <div class="flights-index-search-input">
          <div class="flights-index-airport clearfix">
              <div class="flights-index-search-input-left fl">
              <div class="flights-index-search-input-left-change">
                <i class="iconfont icon-travel-return"></i>
              </div>
              <div class="fl col-10">
                <city :cityObj="startObj"></city>
              </div>
              <div class="fl col-10">
                <city :cityObj="endObj"></city>
              </div>
            </div>
            <div class="flights-index-search-input-right fl">
                <date :tabIndex="tabIndex"></date>

                <!-- <div class="flights-index-search-input-button fl" v-if="searchClass">
                  <Icon type="ios-search" />
                </div> -->

                <div class="flights-index-search-input-delete fl" v-if="tripList.length > 1">
                  <Icon type="md-remove-circle" />
                </div>
            </div>
          </div>

          <div class="flights-index-addbutton clearfix">
            <div class="flights-over fr" v-if="searchClass">
              <Button @click="cancel()">取消</Button>
              <Button type="primary" @click="confirm()">完成</Button>
            </div>
            <div class="flights-index-addbutton-addadress fl">增加目的地<span>(最多3段)</span></div>
            <div class="flights-index-search-input-button fl flights-index-search-input-disabled" v-if="!searchClass">
              <Icon type="ios-search" />
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import city from './city'
import date from './date'

export default {
  props:['tabIndex','searchClass','tripList'],
  data () {
    return {
      startObj: {
        name: "出发",
        className: "icon-travel-setsail",
        foat: "fl",
        classInput: ""
      },
      endObj: {
        name: "目的地",
        className: "icon-travel-jiangla",
        foat: "fr",
        classInput: "flights-index-org"
      },
      layerBoolean: false
    }
  },
  components: {
    city,
    date
  },
  watch: {

  },
  methods: {
    moretripLayer(){
      this.layerBoolean = true;
    },
    cancel(){
      this.layerBoolean = false;
    },
    confirm(){
      this.layerBoolean = false;
    }
  },
  mounted () {}
}
</script>
