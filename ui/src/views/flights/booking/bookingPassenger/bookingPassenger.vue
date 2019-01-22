
<style lang='scss' scoped>
@import "./bookingPassenger.scss";
@import "./../../group/group.scss";
</style>
<template>
  <div class="booking-passenger">
    <div class="booking-col-1">
      <div class="booking-col-box"><label>成人: 1</label></div>
    </div>
    <div class="booking-col-1">
      <div class="booking-col-box"><label>儿童 <b>(2-12)</b>: 1</label></div>
    </div>
    <div class="booking-col-1 search-bar-group booking-changepeople">
      <Dropdown  trigger="click" placement="bottom-start">
        <Button type="primary">
            修改人数
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
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
            <font><Button type="primary">完成</Button></font>
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </div>
    <div class="booking-passenger-set">
      <b>注意： </b>年龄未满12周岁的儿童，航空公司可能不允许登机，请提前与航空公司确认。
    </div>
  </div>
</template>

<script>


export default {
  data () {
    return {
      value1: 1,
      value2: 2
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
