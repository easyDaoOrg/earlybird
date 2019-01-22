
<style lang='scss' scoped>
@import "./bed-card.scss";
</style>
<template>
<main class='bedCardwrap'>
  <!-- {{data | json}} -->
  <section class="bedCardwrap-left">
    <div class="item-name">{{data.rateName}}<span class='font-light-mac'>房间详情</span></div>
    <div class="info-text font-light-mac">双床 标准入住2人 最大入住2人 </div>
    <div class="info-text font-light-mac">无早餐 </div>
    <div class="info-text font-light-mac">不可取消 </div>
  </section>
  <section class="bedCardwrap-right">
      <div class="item-price">
        <b>¥ {{data.rawNightlyRate}}</b>
        <span>含税</span>
      </div>
      <div class="book-btn active" @click='onReserve()' :class='{"bg":data["my-open"]}'>
        <span v-if='!data["my-open"]'>选择</span>
        <Icon type="md-checkmark" v-if='data["my-open"]'/>
      </div>
  </section>
</main>
</template>

<script>
// import axios from 'axios/dist/axios.min'
export default {
  components: {
  },
  props: ['data'],
  data () {
    return {
    }
  },
  mounted () {
    // this.$emit('on-reserve', this.data)
  },
  methods: {
    onReserve () {
      this.$emit('on-reserve', this.data)
    }
  }
}
</script>
