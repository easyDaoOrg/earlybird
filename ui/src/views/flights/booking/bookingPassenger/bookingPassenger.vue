
<style lang='scss' scoped>
@import "./bookingPassenger.scss";
@import "./../../group/group.scss";
</style>
<style>
  .search-bar-prev .ivu-input-number-handler-wrap{
    display: none
  }
  /* .ivu-input-number-handler-wrap{
    display: none
  } */
</style>

<template>
  <div class="booking-passenger">
    <div class="booking-col-1">
      <div class="booking-col-box"><label>成人: {{Adult_v}}</label></div>
    </div>
    <div class="booking-col-1">
      <div class="booking-col-box"><label>儿童 <b>(2-12)</b>:  {{child_v}}</label></div>
    </div>
    <div class="booking-col-1 search-bar-group booking-changepeople">
      <Dropdown trigger="custom" :visible="visible" placement="bottom-start">
        <Button type="primary" @click='visible=true'>
            修改人数
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle"  @click='remove("adult")' :disabled='value1==1'>-</Button>
              <InputNumber v-model="value1" readonly></InputNumber>
              <Button type="primary" shape="circle" @click='add("adult")' :disabled='value1 + value2 >= cabinCount'>+</Button>
            </span>
            <strong>成人</strong>
          </DropdownItem>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle" @click='remove("children")' :disabled='value2==0'>-</Button>
              <InputNumber v-model="value2" readonly></InputNumber>
              <Button type="primary" shape="circle" @click='add("children")' :disabled='value1 + value2 >= cabinCount||value1*2==value2'>+</Button>
            </span>
            <strong>儿童</strong>
          </DropdownItem>
          <DropdownItem>
            <font><Button type="primary" @click='visible=false;ok()' style='float:right'>完成</Button></font>
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </div>
    <div class="booking-passenger-set" v-if='value2'>
      <b>注意： </b>年龄未满12周岁的儿童，航空公司可能不允许登机，请提前与航空公司确认。
    </div>
  </div>
</template>

<script>

export default {
  components: {},
  data () {
    return {
      Adult_v: 1,
      child_v: 1,
      value1: 1,
      value2: 0,
      visible: false,
      cabinCount: 20
    }
  },
  watch: {

  },
  mounted () {
    this.emitObj()
  },
  methods: {
    add (data) {
      if (data === 'adult') {
        if ((this.value1 + this.value2) >= this.cabinCount) return false
        this.value1 = this.value1 + 1
      } else {
        if ((this.value1 + this.value2) >= this.cabinCount) return false
        this.value2 = this.value2 + 1
      }
    },
    remove (data) {
      if (data === 'adult') {
        if (this.value1 === 1) return false
        this.value1 = this.value1 - 1
        if (this.value1 * 2 < this.value2) {
          this.value2 = this.value1 * 2
        }
      } else {
        if (this.value2 === 0) return false
        this.value2 = this.value2 - 1
      }
    },
    ok () {
      this.Adult_v = this.value1
      this.child_v = this.value2
      this.emitObj()
    },
    emitObj () {
      let list = []

      for (let i = 0; i < this.Adult_v; i++) {
        list.push(this.getEmitObj(0))
      }
      for (let i = 0; i < this.child_v; i++) {
        list.push(this.getEmitObj(1))
      }
      this.$emit('on-ok', list)
    },
    setMember (obj) {
      this.Adult_v = obj.Adult_v
      this.child_v = obj.child_v
    },
    getEmitObj (data) {
      return {
        iconType: '', // 成功失败的icom
        memberIndex: null, // 记录成员已经添加过的地方
        tips: {
          name: '',
          default: '新旅客'
        },
        ageType: data // 成人还是而儿童
      }
    }
  }
}
</script>
