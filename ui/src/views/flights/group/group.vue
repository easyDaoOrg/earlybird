
<style lang='scss' scoped>
@import "./group.scss";
</style>
<template>
  <div class="search-bar-group fl">
    <Dropdown trigger="click" placement="bottom-start">
        <span>
          <b>1</b>
          <b>成人</b>
          <b>经济舱</b>
          <i><Icon type="ios-arrow-down" /></i>
        </span>
        <DropdownMenu slot="list">
          <DropdownItem><span class="search-bar-gray">一次最多选择9个旅客</span></DropdownItem>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle">-</Button>
              <InputNumber v-model="value1" readonly></InputNumber>
              <Button type="primary" shape="circle">+</Button>
            </span>
            <strong>成人</strong>
          </DropdownItem>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle">-</Button>
              <InputNumber v-model="value2" readonly></InputNumber>
              <Button type="primary" shape="circle">+</Button>
            </span>
            <strong>儿童</strong>
          </DropdownItem>
          <DropdownItem>
            <font class="fr"><Button type="primary">完成</Button></font>
            <Button class="search-bar-b-active">经济舱</Button>
            <Button>商务/头等舱</Button>
          </DropdownItem>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
// import { wsGetProjectOview } from '@/models/project';

export default {
  data () {
    return {
      value1: 1,
      value2: 2,
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
