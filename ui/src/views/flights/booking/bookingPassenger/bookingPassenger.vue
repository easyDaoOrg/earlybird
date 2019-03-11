
import { rejects } from 'assert';
import { rejects } from 'assert';
<style lang='scss' scoped>
@import "./bookingPassenger.scss";
@import "./../../group/group.scss";
</style>
<style>
  .search-bar-prev .ivu-input-number-handler-wrap{
    display: none
  }
</style>

<template>
<main class='main'>
   <div class="booking-passenger">
    <div class="booking-col-1">
      <div class="booking-col-box"><label>成人: {{Adult_v}}</label></div>
    </div>
    <div class="booking-col-1">
      <div class="booking-col-box"><label>儿童 <b>(2-12)</b>:  {{child_v}}</label></div>
    </div>
    <div class="booking-col-1 search-bar-group booking-changepeople">
      <Dropdown trigger="custom" :visible="visible" placement="bottom-start">
        <Button type="primary" @click.stop='visible=true;open()'>
            修改人数
            <Icon type="ios-arrow-down"></Icon>
        </Button>
        <DropdownMenu slot="list"  @click.native='$event.stopPropagation();'>
          <DropdownItem>
            <span class="fr search-bar-prev flights-index-prev">
              <Button type="primary" shape="circle"  @click='remove("adult")' :disabled='value1==1'>-</Button>
              <InputNumber v-model="value1" readonly></InputNumber>
              <Button type="primary" shape="circle" @click='add("adult")' :disabled='value1 + value2 >= cabinCount'>+</Button>
            </span>
            <strong>成人</strong>
          </DropdownItem>
          <DropdownItem v-if='chdBarePrice!==0'>
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
    <Modal v-model="empty" footer-hide :closable="false" :mask-closable="false">
      <div class="empty">
        <h2>对不起，您预订的该航班价格已经售完，请重新搜索预订</h2>
         <p>
            <router-link :to="{path:'/flights/index'}">
              <Button type="primary" class='empty-button'>查找其他航班</Button>
            </router-link>
         </p>
      </div>
    </Modal>
    <Modal v-model="priceChange" footer-hide :closable="false" :mask-closable="false">
      <div class="empty">
        <h2>您要预定的航班成人票价{{differentialPrice>= 0 ? '上升' : '下降'}}了<span>¥ {{Math.abs(this.differentialPrice)}}</span>/每人，总价为<span>¥ {{barePrice}}</span></h2>
        <p>
          <Button type="primary" @click='priceChange=false'>知道了</Button>
        </p>
      </div>
    </Modal>
    <Modal v-model="insufficientTicket" footer-hide :closable="false" :mask-closable="false">
      <div class="empty">
        <h2>余票不足，剩余<span>{{cabinCount}}</span>张票,请更换供应商或减少乘机人数以重试</h2>
        <p>
          <Button type="primary" @click='insufficientTicket=false' style='margin-right:10px'>知道了</Button>
          <router-link :to="{path:'/flights/index'}">
            <Button type="primary">查找其他航班</Button>
          </router-link>
        </p>
      </div>
    </Modal>
  </div>
  <div class='main-title'>{{ticketTitle}}</div>
</main>

</template>

<script>

export default {
  components: {},
  computed: {

  },
  data () {
    return {
      Adult_v: 1,
      child_v: 0,
      value1: 0,
      value2: 0,
      visible: false,
      cabinCount: 1,
      // 儿童价格
      chdBarePrice: 0,
      // 成人价格
      barePrice: 0,
      routerObj: this.$route.query,
      empty: false,
      priceChange: false,
      insufficientTicket: false,
      differentialPrice: 0,
      priceLoading: false,
      ticketTitle: ''
    }
  },
  watch: {

  },
  mounted () {
    this.$bus.on('overall-close', this.closeDropdownMenu)
    this.emitObj()
    this.getAirportPrice()
  },
  methods: {
    open () {
      this.value1 = this.Adult_v
      this.value2 = this.child_v
    },
    closeDropdownMenu () {
      this.visible = false
    },
    // 价格
    getAirportPrice () {
      return new Promise((resolve, reject) => {
        this.ticketTitle = '余票查询中，请稍后...'
        this.priceLoading = true
        let airportData = {
          dpt: this.routerObj.dpt,
          arr: this.routerObj.arr,
          date: this.routerObj.date,
          flightNum: this.routerObj.flightNum
        }
        let url = this.baseUrl + `/flight/price`
        // let self = this
        this.axios
          .post(url, airportData)
          .then(data => {
            this.priceLoading = false
            if (data.status === 200) {
              let val = data.data
              let vm = this.filter_data(val.vendors)
              let vendors = vm.data
              // 成人价格
              this.barePrice = vendors.vppr// barePrice
              let emitObj = Object.assign(val)
              emitObj['cid'] = vendors.cid
              emitObj['pid'] = vendors.pid
              emitObj['cabinType'] = vendors.cabinType
              this.$emit('flight-information', emitObj)
              // 儿童价格 为0认为不能添加儿童
              this.chdBarePrice = vendors.chdBarePrice
              // this.chdBarePrice = 10
              this.emitPrice()
              // 总票数
              this.cabinCount = this.filter_cabinCount(vendors.cabinCount)
              // this.cabinCount = 0
              // console.log(this.cabinCount)
              this.ticketTitle = `余票剩余${this.cabinCount}`
              // 没票了
              if (!this.cabinCount) {
                this.ticketTitle = '票已售空'
                this.empty = true
                resolve(false)
              } else if (Number(this.value1) + Number(this.value2) > this.cabinCount) {
                this.insufficientTicket = true
                resolve(false)
              } else if (!vm.open) {
                this.differentialPrice = vm.differentialPrice
                this.priceChange = true
                resolve(false)
              } else {
                resolve(true)
              }

            // 路由有儿童和成人票时打开
            // let adult = Number(self.routerObj.adult)
            // let child = Number(self.routerObj.child)
            // if (adult + child <= self.cabinCount) {
            //   self.Adult_v = adult
            //   self.child_v = child
            //   this.value1 = adult
            //   this.value2 = child
            //   if (self.chdBarePrice === 0) {
            //     this.child_v = 0
            //     this.value2 = 0
            //   }
            //   this.ok()
            // } else {
            //   self.Adult_v = self.cabinCount
            //   self.child_v = 0
            //   this.value1 = self.cabinCount
            //   this.value2 = 0
            //   this.ok()
            // }
            }
          })
          .catch(error => {
            this.priceLoading = false
            console.log(error)
          })
      })
    },
    // 过滤人数
    filter_cabinCount (data) {
      if (data === 'A') {
        return 9
      } else if (Number(data)) {
        return data
      } else {
        return 0
      }
    },
    // 根据pid过滤
    filter_data (data) {
      let price = Number(this.routerObj.vppr)
      let obj = {}
      let arr = []
      let idx = -1
      data.forEach((item, inedx) => {
        arr.push(item.vppr)
        if (price === item.vppr && item.cabinType === Number(this.routerObj.cabinType)) {
          idx = inedx
        }
      })
      // idx = -1
      if (idx !== -1) {
        obj = {
          data: data[idx],
          open: true
        }
      } else {
        let minList = []
        arr.forEach(item => {
          let jj = item >= price ? item - price : price - item
          minList.push(jj)
        })
        let minV = Math.min.apply(null, minList)
        let len = minList.indexOf(minV)
        let differentialPrice = data[len].vppr - price
        obj = {
          open: false,
          data: data[len],
          differentialPrice
        }
      }
      return obj
    },
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
      this.getAirportPrice().then((v) => {
        if (v) {
          this.Adult_v = this.value1
          this.child_v = this.value2
          this.emitObj()
        }
      })
    },
    emitObj () {
      let list = []

      for (let i = 0; i < this.Adult_v; i++) {
        list.push(this.getEmitObj(0))
      }
      for (let i = 0; i < this.child_v; i++) {
        list.push(this.getEmitObj(1))
      }
      this.emitPrice()
      this.$emit('on-ok', list)
    },
    emitPrice () {
      // 成人价格不为0
      if (this.barePrice) {
        this.$emit('on-price-change', {
          Adult_price: this.barePrice,
          Adult_number: this.Adult_v,
          child_prive: this.chdBarePrice,
          child_number: this.child_v
        })
      }
    },
    setMember (obj) {
      this.Adult_v = obj.Adult_v
      this.child_v = obj.child_v
      this.value1 = obj.Adult_v
      this.value2 = obj.child_v
      this.emitPrice()
    },
    getEmitObj (data) {
      return {
        iconType: '', // 成功失败的icom
        memberIndex: null, // 记录成员已经添加过的地方
        tips: {
          name: '',
          default: '新旅客'
        },
        ageType: data, // 成人还是而儿童
        formCustom: {
          category: 'NI',
          familyNameZh: '',
          givenNameZh: '',
          gender: 1,
          birthday: '',
          number: ''
        }
      }
    }
  }
}
</script>
