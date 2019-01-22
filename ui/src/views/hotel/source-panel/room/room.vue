
<style lang='scss' scoped>
@import "./room.scss";
</style>
<style>
  .roomWrap .ivu-dropdown .ivu-select-dropdown{
    top: 57px!important;
    left: 0!important;
  }
  .childrenDropdown .ivu-select-dropdown{
    overflow: hidden;
    width: 42px;
  }
  .top45 .ivu-dropdown .ivu-select-dropdown{
    top: 45px!important;;
    left: 15px!important;;
  }
  .top56 .ivu-dropdown .ivu-select-dropdown{
    top: 56px!important;
  }
</style>

<template>
  <div class="main row-item roomWrap" @click='$event.stopPropagation();' :class="type=='result-list'?'top45':'top56'">
      <Dropdown trigger="custom" :visible="visible">
        <div @click="handleOpen">
            <span>{{roomSize}}间房，每间{{adult}}成人，{{children}}儿童</span>
            <Icon type="md-arrow-dropdown" color='#323232' size='30' class='icon-dropdown'/>
        </div>
        <DropdownMenu slot="list">
          <div class='dropdownMenu'>
              <section class='dropdownMenu-left'>
                  <div class="inline-item room-item">
                  <label>房间数</label>
                  <div class="inline-content">
                      <Icon type="ios-remove-circle-outline" @click='remove("roomSize")' class='inline-item-icon' :class='{"selectedColor":keep.roomSize}'/>
                      <b>{{keep.roomSize}}</b>
                      <Icon type="ios-add-circle-outline" class='inline-item-icon' @click='add("roomSize")' :class='{"selectedColor":keep.roomSize < max.roomSize}'/>
                  </div>
                </div>

                <div class="tip-title">
                  每间住客数
                </div>

                <div class="inline-item">
                  <label>成人</label>
                  <div class="inline-content">
                      <Icon type="ios-remove-circle-outline" @click='remove("adult")' class='inline-item-icon' :class='{"selectedColor":keep.adult}'/>
                      <b>{{keep.adult}}</b>
                      <Icon type="ios-add-circle-outline" class='inline-item-icon' @click='add("adult")' :class='{"selectedColor":(keep.children + keep.adult<max.children)}'/>
                  </div>
                </div>

                <div class="inline-item">
                  <label>儿童</label>
                  <div class="inline-content">
                      <Icon type="ios-remove-circle-outline" @click='remove("children")' class='inline-item-icon' :class='{"selectedColor":keep.children}'/>
                      <b>{{keep.children}}</b>
                      <Icon type="ios-add-circle-outline" class='inline-item-icon'  @click='add("children")'  :class='{"selectedColor":(keep.children + keep.adult<max.children)}'/>
                  </div>
                </div>
              </section>
              <!-- 小孩 -->
              <section class='dropdownMenu-right' v-if='childrenList.length'>
                <div class="tip-title-age">为了选择合适住宿，请选择儿童年龄</div>
                <div class='max-scroll-children'>
                  <div class="inline-item" v-for='(option,i) in childrenList' :key='i'>
                    <label class="inline-title">第{{i+1}}位儿童</label>
                    <div class="inline-content half-width">
                      <span :class='{"greaterThan0Age":option}'><Icon type="ios-arrow-back" class='lessThan' v-if='option===0'/>{{option?option:1}} 岁</span>
                      <Dropdown trigger="click" class='childrenDropdown' @on-click='onChildrenChange($event,i)' :transfer='true'>
                            <a href="javascript:void(0)">
                                <Icon type="md-arrow-dropdown" color='#323232' size='30' class='icon-dropdown'/>
                            </a>
                            <DropdownMenu slot="list" >
                                <DropdownItem v-for='(item,index) in childrenData' :key='index' :name="item">{{item}}</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </div>
                  </div>
                </div>
              </section>
              <div class="select-check-in-btn">
                    <span @click="handleClose">确定</span>
              </div>
          </div>
        </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
// import axios from 'axios/dist/axios.min'
// import Util from '@/lib/utils'
export default {
  components: {},
  props: {
    type: {
      default: 'hotel',
      type: String
    }
  },
  data () {
    return {
      visible: false,
      roomSize: 1,
      adult: 2,
      children: 0,
      childrenList: [],
      childrenData: [0, 1, 2, 3, 4, 5],
      keep: {
        roomSize: 1,
        adult: 2,
        children: 0
      },
      max: {
        roomSize: 9,
        adult: 9,
        children: 9
      }
    }
  },
  mounted () {
    this.$bus.on('overall-close', this.clear)
  },
  watch: {

  },
  methods: {
    handleOpen () {
      this.visible = true
      this.$emit('other-close', 'room')
    },
    handleClose () {
      this.roomSize = this.keep.roomSize
      this.adult = this.keep.adult
      this.children = this.keep.children
      this.visible = false
    },
    add (type) {
      switch (type) {
        case 'roomSize':
          if (this.keep.roomSize === this.max.roomSize) return false
          this.keep.roomSize = this.keep.roomSize + 1
          break
        case 'adult':
          if (this.keep.adult === this.max.adults) return false
          if (this.keep.children + this.keep.adult >= this.max.children) return false
          this.keep.adult = this.keep.adult + 1
          break
        case 'children':
          if (this.keep.children + this.keep.adult >= this.max.children) return false
          this.keep.children = this.keep.children + 1
          this.childrenList.push(0)
          break
      }
    },
    remove (type) {
      switch (type) {
        case 'roomSize':
          if (this.keep.roomSize === 0) return false
          this.keep.roomSize = this.keep.roomSize - 1
          break
        case 'adult':
          if (this.keep.adult === 0) return false
          this.keep.adult = this.keep.adult - 1
          break
        case 'children':
          if (this.keep.children === 0) return false
          this.keep.children = this.keep.children - 1
          this.childrenList.shift()
          break
      }
    },
    onChildrenChange (d, index) {
      this.$set(this.childrenList, index, d)
    },
    clear () {
      this.visible = false
    }
  }
}
</script>
