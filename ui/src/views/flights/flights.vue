
<style lang='scss' scoped>
@import "./flights.scss";
</style>
<template>
  <div class="flights">
    <div class="flights-search">
      <div class="flights-search-slogan">
        <h1>{{flightsTitle}}</h1>
      </div>
      <div class="flights-search-bar">
        <ul>
          <router-link :to="{path:'/flights/index'}">
            <li class="flights-search-bar-active">
              <i class="iconfont icon-travel-feiji1"></i>找机票
            </li>
          </router-link>
          <router-link :to="{path:'/flights/index'}">
            <li>
              <i class="iconfont icon-travel-where"></i>飞去哪
            </li>
          </router-link>
          <router-link :to="{path:'/flights/index'}">
            <li>
              <i class="iconfont icon-travel-weibiaoti"></i>何时飞
            </li>
          </router-link>
        </ul>
      </div>
      <div class="flights-content">
        <router-view/>
      </div>
    </div>
    <div class="flights-cover" style="background-image: url(&quot;https://content.igola.com/igola-cms/41c4d6538fd64cd2b7d76fcf1b333a20.jpeg&quot;); background-position: left top;"></div>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      flightsTitle: '通往世界各处，只需搜索一下',
    }
  },
  components: {

  },
  methods: {},
  mounted () {}
}
</script>
