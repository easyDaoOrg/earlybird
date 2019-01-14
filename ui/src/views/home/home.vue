
<style lang='scss' scoped>
@import "./home.scss";
</style>
<template>
  <div class="home">
    <Header></Header>
    <div class='home-content'>
        <router-view/>
        <Footer></Footer>
    </div>
  </div>
</template>

<script>
import Header from '../header/header'
import Footer from '../footer/footer'
export default {
  data () {
    return {}
  },
  components: {
    Header,
    Footer
  },
  methods: {},
  mounted () {}
}
</script>
