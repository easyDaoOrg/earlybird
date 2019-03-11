
<style lang='scss' scoped>
@import "./bookingContacts.scss";
</style>
<template>
  <div class="booking-contacts">
    <span class="fr">
      <Button @click='clear()'>清除</Button>
      <Button icon="ios-close-circle" @click='remove()' :disabled='removeDisabled'>删除</Button>
    </span>
    常用旅客：
    <Dropdown placement="bottom-start" trigger="custom" :visible="visible" @click.native='$event.stopPropagation();'>
        <Button type="primary" @click.stop='visible=true;'>
            选择
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list">
          <div class="booking-contacts-title">请选择旅客</div>
          <div class="booking-contacts-list">
            <section class="common-passenger-item" v-for='(item,index) in peopleList' :key='index' @click='!setDisabled(index)&&add(item,index)' :class='{"disabled":setDisabled(index)}'>
              <b class="passenger-text">{{item.familyNameZh}}{{item.givenNameZh}}</b>
              <span>(成人)</span>
            </section>
          </div>
        </DropdownMenu>
      </Dropdown>
  </div>
</template>

<script>

import minxin from '../../../member/user/passengers/minxin.js'
export default {
  mixins: [minxin],
  props: {
    removeDisabled: {
      type: Boolean,
      default: false
    },
    current: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
  data () {
    return {
      visible: false,
      peopleList:[],
    }
  },
  components: {

  },
  watch: {

  },
  methods: {
    remove () {
      this.$Modal.confirm({
        title: '删除',
        content: '<p>您确定要删除吗？</p>',
        onOk: () => {
          this.$emit('on-remove', true)
        }
      })
    },
    clear () {
      this.$emit('on-clear', true)
    },
    add (data, index) {
      this.$emit('on-add-member', [data, index])
      this.visible = false
    },
    // 设置禁用
    setDisabled (index) {
      let m = false
      this.current.forEach(item => {
        if (item.memberIndex === index) {
          m = true
        }
      })
      return m
    },
    closeDropdownMenu () {
      this.visible = false
    }
  },
  mounted () {
    this.$bus.on('overall-close', this.closeDropdownMenu)
    this.getData()
  }
}
</script>
