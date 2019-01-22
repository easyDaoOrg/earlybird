
<style lang='scss' scoped>
@import "./brand.scss";
</style>
<template>
  <div class="brand">
    <div class="brand-justified">
      <ul>
        <router-link :to="{path:'/brand/about-us'}">
          <li :class="{brandactive: isActive == '/brand/about-us'}">
            <b>关于易到</b>
            <span style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/aboutus/brand_aboutus_bannerbg.jpg&quot;); opacity: 1;"></span>
          </li>
        </router-link>

        <router-link :to="{path:'/brand/faq'}">
          <li :class="{brandactive: isActive == '/brand/faq'}">
            <b>你问我答</b>
            <span style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/aboutus/brand_faq_bannerbg.jpg&quot;); opacity: 1;"></span>
          </li>
        </router-link>

        <router-link :to="{path:'/brand/join-us'}">
          <li :class="{brandactive: isActive == '/brand/join-us'}">
            <b>加入我们</b>
            <span style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/aboutus/brand_joinus_bannerbg.jpg&quot;); opacity: 1;"></span>
          </li>
        </router-link>

        <router-link :to="{path:'/brand/contact-us'}">
          <li :class="{brandactive: isActive == '/brand/contact-us'}">
            <b>联系我们</b>
            <span class="banner_contact_us tabs-bg" style="background-image: url(&quot;https://content.igola.com/static/WEB/images/brand/aboutus/brand_contactus_bannerbg.jpg&quot;); opacity: 1;"></span>
          </li>
        </router-link>
      </ul>
    </div>

    <div class="brand-content">
      <router-view/>
    </div>

  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      isActive: '/brand/about-us'
    }
  },
  components: {

  },
  watch: {
    '$route': 'isActivedRoute'
  },
  methods: {
    isActivedRoute () {
      this.isActive = this.$route.path
    }
  },
  mounted () {}
}
</script>
