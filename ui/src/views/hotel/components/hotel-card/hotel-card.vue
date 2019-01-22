
<style lang='scss' scoped>
@import "./hotel-card.scss";
</style>
<template>
<div :class='type == 1 ? "cardWrap1" : "cardWrap2"' @click='onClickCard()'>
  <main class="card hover" v-for='(item,index) in data' :key='index' @mouseenter="onNoticeMap(item)">
    <img :src="item.images[0].url || ''" alt="" class="card-picture">
    <div class="card-detail">
      <p class="card-detail-text bold">{{item.nameChn}}</p>
      <p class="card-detail-text">{{item.nameEng}}</p>
      <div class='card-detail-starClass'>
        <p class="card-detail-starClass-star">{{item.star}}星级</p>
        <p class="card-detail-starClass-score">{{item.score}}</p>
        <span class="score-text">不错</span>
      </div>
      <div class="card-detail-address">
        <p class='card-detail-text address'>{{item.address}}</p>
        <span class="address-map" @click='$event.stopPropagation();seeMap(item)' v-if='type == 1'>
          <Icon type="md-pin" />查看地图
        </span>
      </div>
      <div class="detail-price">
        <p class="price-text">
          ¥ {{item.refPrice}} <span>起</span>
        </p>
        <p class="price-taxes">每晚含税价</p>
      </div>
    </div>
  </main>
</div>
</template>

<script>
// import axios from 'axios/dist/axios.min'
export default {
  components: {
  },
  props: ['type', 'data'],
  data () {
    return {
    }
  },
  mounted () {

  },
  methods: {
    seeMap (data) {
      this.$emit('see-map', data)
    },
    onNoticeMap (data) {
      this.$emit('on-notice-map', data)
    },
    onClickCard () {
      this.$router.push('/hotel/hotel-detail')
    }
  }
}
</script>
