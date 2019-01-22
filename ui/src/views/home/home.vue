
<style lang='scss' scoped>
@import "./home.scss";
</style>
<template>
  <div class="home">
    <Header></Header>
    <div class='home-content'>
        <router-view/>
        <Footer v-if='footer'></Footer>
    </div>
  </div>
</template>

<script>
import Header from '../header/header'
import Footer from '../footer/footer'
export default {
  data () {
    return {
      routerData: ['result-list'],
      footer: true
    }
  },
  components: {
    Header,
    Footer
  },
  watch: {
    $route () {
      this.onShowFooter()
    }
  },
  methods: {
    onShowFooter () {
      this.footer = true
      if (!this.$route.meta.footer) {
        this.footer = false
      }
    }
  },
  mounted () {
    this.onShowFooter()
  }
}
</script>
