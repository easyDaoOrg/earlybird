
<style lang='scss' scoped>
@import "./date.scss";
</style>
<style>
   .top56 .ivu-date-picker .ivu-select-dropdown{
    top: 56px!important;;
    left: 15px!important;;
  }
  .top45 .ivu-date-picker .ivu-select-dropdown{
      top:45px!important;
  }
</style>

<template>
  <div class="main row-item" @click='$event.stopPropagation();' :class="type=='result-list'?'top45':'top56'">
    <DatePicker
        format="yyyy年MM月dd日"
        :open="open"
        :value="date"
        type="daterange"
        @on-change="handleChange"
        >
        <div @click="handleClick">
            <!-- <template v-if="!date.length">Select date</template> -->
            <template>
              <span>{{startTime}}</span> - <span class='marginRight'>{{endTime}}</span><span>共{{night}}晚</span><Icon type="md-arrow-dropdown" color='#323232' size='30' class='icon-dropdown'/>
            </template>
        </div>
    </DatePicker>
  </div>
</template>

<script>
// import axios from 'axios/dist/axios.min'
import Util from '@/lib/utils'
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
      open: false,
      date: [],
      startTime: '',
      endTime: '',
      night: 1
    }
  },
  mounted () {
    let d = new Date().getTime()
    let e = d + 24 * 60 * 60 * 1000
    this.startTime = this.filterDate(Util.timeStampLite(d, 'yyyy年MM月dd日'))
    this.endTime = this.filterDate(Util.timeStampLite(e, 'yyyy年MM月dd日'))
    this.$bus.on('overall-close', this.clear)
  },
  watch: {
    'date': {
      handler: function (val, oldVal) {
        this.startTime = this.filterDate(val[0])
        this.endTime = this.filterDate(val[1])
      },
      deep: true
    }
  },
  methods: {
    handleClick () {
      this.open = !this.open
      if (this.open) {
        this.$emit('other-close', 'date')
      }
    },
    handleChange (date) {
      this.date = date
      this.filterDays()
      this.open = false
    },
    filterDate (data) {
      return data.slice(data.indexOf('年') + 1)
    },
    filterDays () {
      let s = this.filterC(this.date[0])
      let e = this.filterC(this.date[1])
      let d = Util.differDays(e, s).slice()
      this.night = d.slice(0, d.indexOf('天'))
    },
    filterC (data) {
      let arr = ['年', '月']
      arr.forEach((item) => {
        if (data.indexOf(item) !== -1) {
          data = data.replace(item, '/')
        }
      })
      data = data.replace('日', '')
      return data
    },
    clear () {
      this.open = false
    }
  }
}
</script>
