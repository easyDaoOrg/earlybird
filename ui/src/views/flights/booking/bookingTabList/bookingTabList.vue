
<style lang='scss' scoped>
@import "./bookingTabList.scss";
</style>
<style>
.booking-tablist .ivu-menu{
  width: 120px!important;
}
.booking-tablist .ivu-menu-item{
  width: 120px!important;
  height: 50px;
  color: #00b4ff!important;
  background-color: #f0f0f0;
}
.booking-tablist .ivu-menu-item{
  padding: 3px 12px 0!important;
  border-right: 1px solid #c8c8c8;
  border-bottom: 1px solid #c8c8c8;
}
.booking-tablist .ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu){
  background: #fff!important;
  border-right: none;
}
.booking-tablist .ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu):after{
   background: #fff;
}
</style>

<template>
  <div class="booking-tablist">
    <div class="booking-tablist-content">
      <Menu :active-name="selected" @on-select='onMenuChange'>
         <MenuItem :name="index" v-for='(item,index) in current' :key='index'>
            <div class='menu'>
               <!-- <p>{{index+1}}.{{item.ageType===0?'成人票':'儿童票'}}-{{field.length}}</p> -->
               <p>{{index+1}}.{{item.ageType===0?'成人票':'儿童票'}}</p>
               <div>
                 <Icon type="ios-information-circle-outline" v-if='item.iconType==="error"' color='#ff484a'/>
                 <Icon type="ios-checkmark-circle" v-if='item.iconType==="success"' color='#8ecf01'/>
               </div>
               <span>{{item.tips.name||item.tips.default}}</span>
              <!-- <span v-if='field[index]'>{{item.tips.name||item.tips.default}}-{{field[index]._uid}}-{{field[index].formCustom.familyNameZh}}{{field[index].formCustom.givenNameZh}}</span> -->
            </div>
          </MenuItem>
       </Menu>
        <div class="booking-tablist-pane" v-for='(item,index) in current' :key='index' v-show='selected==index'>
            <!-- 选择成员 重置&&删除 -->
             <bookingContacts
              @on-remove='onRemove(index)'
              @on-clear='onClear(index)'
              @on-add-member='onAddMember($event,index)'
              :removeDisabled='adultLens<=1&&index===0'
              :current='current'
              ></bookingContacts>
            <!-- 表单 -->
             <bookingForm
              :formCustom='item.formCustom'
              @on-emit-top='onEmitTop'
              @on-form-item-remove='onFormItemRemove'
              @on-name-change='onNameChange($event,index)'
              ></bookingForm>
        </div>
    </div>
  </div>
</template>

<script>
import bookingContacts from './../bookingContacts/bookingContacts'
import bookingForm from './../bookingForm/bookingForm'
import { setTimeout } from 'timers'

export default {
  props: {
    data: {
      type: Array
    }
  },
  data () {
    return {
      selected: 0,
      field: [],
      current: this.data
    }
  },
  components: {
    bookingContacts,
    bookingForm
  },
  watch: {
    data (newV, o) {
      this.current = this.filterCurrent(newV)
      this.selected = 1
      setTimeout(() => {
        this.selected = 0
      }, 0)
    }
  },
  computed: {
    adultLens () {
      let n = this.current.filter((item) => {
        return item.ageType === 0
      })
      return n.length
    }
  },
  created () {
  },
  mounted () {

  },
  methods: {
    filterCurrent (newV) {
      let _D = this.current.filter(item => {
        return item.ageType === 0
      })
      let _x = this.current.filter(item => {
        return item.ageType === 1
      })
      let _Dn = newV.filter(item => {
        return item.ageType === 0
      })
      let _xn = newV.filter(item => {
        return item.ageType === 1
      })
      // 大人
      if (_D.length < _Dn.length) {
        let cha = _Dn.slice(_D.length)
        _D = _D.concat(cha)
      } else if (_D.length > _Dn.length) {
        _D = _D.slice(0, _Dn.length)
      }
      // 小孩
      if (_x.length < _xn.length) {
        let cha = _xn.slice(_x.length)
        _x = _x.concat(cha)
      } else if (_x.length > _xn.length) {
        _x = _x.slice(0, _xn.length)
      }
      let n = _D.concat(_x)
      return n
    },
    onMenuChange (data) {
      this.selected = data
    },
    onEmitTop (data) {
      if (data) this.field.push(data)
    },
    onFormItemRemove (data) {
      if (data) this.field.splice(this.field.indexOf(data), 1)
    },
    onSubmit () {
      let type = true
      this.field.forEach((item, index) => {
        let m = item.onSubmit()
        this.current[index].iconType = m ? 'success' : 'error'
        if (!m) {
          type = false
        }
      })
      let list = []
      if (type) {
        this.field.forEach((item, index) => {
          let obj = item.formCustom
          // 设置成人还是儿童
          obj['ageType'] = this.current[index].ageType
          list.push(obj)
        })
      }
      return {
        type,
        list
      }
    },
    onRemove (index) {
      this.current.splice(index, 1)
      // this.field.splice(1, 1)
      this.selected = 0
      let obj = {
        Adult_v: 0,
        child_v: 0
      }
      this.current.forEach(item => {
        if (item.ageType === 0) {
          obj.Adult_v = obj.Adult_v + 1
        }
        if (item.ageType === 1) {
          obj.child_v = obj.child_v + 1
        }
      })
      this.$emit('on-member-change', obj)
    },
    onClear (index) {
      this.field[index].clear()
      this.current[index].iconType = ''// 清除icon
      this.current[index].memberIndex = null// 清除成员添加进入的索引
    },
    // 记录成员已经添加过的地方
    onAddMember (data, index) {
      // let idx = -1
      // for (let i = 0; i < this.current.length; i++) {
      //   if (this.current[i].memberIndex === data[1]) {
      //     idx = i
      //     break
      //   }
      // }
      // if (idx === -1) {
      //   this.field[index].setFormCustom(data[0])
      //   this.current[index].memberIndex = data[1]
      // }
      this.field[index].setFormCustom(data[0])
      this.current[index].memberIndex = data[1]
    },
    // 设置用户名称
    onNameChange (data, index) {
      this.current[index].tips.name = data
    }
  }
}
</script>
