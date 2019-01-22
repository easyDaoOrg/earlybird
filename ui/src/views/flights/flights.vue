
<style lang='scss' scoped>
@import "./flights.scss";
</style>
<template>
  <div class="flights">
    <div class="flights-search">
      <div class="flights-search-slogan">
        <h1>{{flightsTitle.default}}</h1>
      </div>
      <div class="flights-search-bar">
        <ul>
          <router-link :to="{path:'/flights/index'}">
            <li :class="{flightssSearchBaractive: isActive == '/flights/index'}">
              <i class="iconfont icon-travel-feiji1"></i>找机票
            </li>
          </router-link>
          <router-link :to="{path:'/flights/where-to-go'}">
            <li :class="{flightssSearchBaractive: isActive == '/flights/where-to-go'}">
              <i class="iconfont icon-travel-where"></i>飞去哪
            </li>
          </router-link>
          <router-link :to="{path:'/flights/when-to-go'}">
            <li :class="{flightssSearchBaractive: isActive == '/flights/when-to-go'}">
              <i class="iconfont icon-travel-weibiaoti"></i>何时飞
            </li>
          </router-link>
        </ul>
      </div>
      <div class="flights-content">
        <router-view/>
      </div>
    </div>
    <div v-if="isActive == '/flights/index'">
      <div class="flights-cover" style="background-image: url(&quot;https://content.igola.com/igola-cms/41c4d6538fd64cd2b7d76fcf1b333a20.jpeg&quot;); background-position: left top;"></div>
    </div>
    <div v-else-if="isActive == '/flights/where-to-go'">
      <div class="flights-cover" style="background-image: url(&quot;https://content.igola.com/igola-cms/25424de0c6d14db48a983fa5c02664c9.jpg&quot;); background-position: left top;"></div>
    </div>
    <div v-else-if="isActive == '/flights/when-to-go'">
      <div class="flights-cover" style="background-image: url(&quot;https://content.igola.com/igola-cms/aee39c86b95044328a6d6b99817cdf54.jpg&quot;); background-position: left top;"></div>
    </div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      flightsTitle: {
        default: '通往世界各处，只需搜索一下',
        '/flights/index': '通往世界各处，只需搜索一下',
        '/flights/where-to-go': '想出发却不知道去哪？让灵感从这里触发。输入出发地和旅行时间，发现最佳目的地。',
        '/flights/when-to-go': '在最合适的时间，去最想去的地方。输入出发地和目的地，发现最佳出行时间。'
      },
      isActive: '/flights/index'
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
      this.flightsTitle.default = this.flightsTitle[this.$route.path]
    }
  },
  mounted () {
    this.isActivedRoute();
  }
}
</script>
