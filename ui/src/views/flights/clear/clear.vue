
<style lang='scss' scoped>
@import "./clear.scss";
</style>
<template>
  <div class="clear-history fl" :class="{clearactive: queryHistory}">
    <Dropdown trigger="click" placement="bottom-start">
        <span>
          <i class="iconfont icon-travel-clock"></i>
        </span>
        <DropdownMenu slot="list">
          <DropdownItem><i class="clear-hidstory-air iconfont icon-travel-wangfan"></i> 广州(CAN) - 因斯布鲁克(INN)   11月11日 - 11月20日, 经济舱</DropdownItem>
          <DropdownItem><b class="fr"><Icon type="md-close-circle" /></b><i class="clear-hidstory-air iconfont icon-travel-find"></i>广州(CAN) - 奥克兰(AKL)   11月11日 - 11月20日, 经济舱</DropdownItem>
          <DropdownItem><b class="fr"><Icon type="md-close-circle" /></b><i class="clear-hidstory-air iconfont icon-travel-list"></i>广州(CAN) - 奥克兰(AKL)   11月11日 - 11月20日, 经济舱</DropdownItem>
          <p>清除记录</p>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  props: ['queryHistory'],
  data () {
    return {

    }
  },
  components: {

  },
  watch: {

  },
  methods: {

  },
  mounted () {}
}
</script>
