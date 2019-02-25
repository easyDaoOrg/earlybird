import { mapActions, mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters(['filter_D'])
  },
  data () {
    return {
      typeMy: ['position', 'price', 'score', 'start', 'other'],
      // 位置
      positionList: [{
        name: '行政/商业区',
        visible: false,
        open: false,
        key: 'neighborhoods'
      },
      {
        name: '机场车站',
        visible: false,
        open: false,
        key: 'trainAirport'
      },
      {
        name: '地铁线路',
        visible: false,
        open: false,
        key: 'metroStation'
      },
      {
        name: '地标',
        visible: false,
        open: false,
        key: 'landmark'
      },
      {
        name: '景点',
        visible: false,
        open: false,
        key: 'scenicSpot'
      },
      {
        name: '娱乐',
        visible: false,
        open: false,
        key: 'entertainment'
      },
      {
        name: '学校',
        visible: false,
        open: false,
        key: 'school'
      }
      ],
      positionSelected: '不限',
      // 价格
      priceSelected: '不限',
      // start: '',
      // end: '',
      priceList: {
        // input: {
        //   start: '',
        //   end: ''
        // },
        start: '',
        end: '',
        data: [{
          label: '200以下',
          value: '200以下'
        },
        {
          label: '200-400',
          value: '200-400'
        },
        {
          label: '400-600',
          value: '400-600'
        },
        {
          label: '600-800',
          value: '600-800'
        },
        {
          label: '800-1000',
          value: '800-1000'
        },
        {
          label: '1000-1300',
          value: '1000-1300'
        },
        {
          label: '1300-1600',
          value: '1300-1600'
        },
        {
          label: '1600-2000',
          value: '1600-2000'
        },
        {
          label: '2000以上',
          value: '2000以上'
        }
        ]
      },
      openPrice: false,
      // 评分
      scoreSelected: '不限',
      scoreList: [{
        label: '4分以上',
        value: '4分以上'
      },
      {
        label: '3分以上',
        value: '3分以上'
      }
      ],
      // 星级
      startSelected: {
        no: '不限',
        data: []
      },
      startList: [{
        label: '3星/舒适',
        value: '3星/舒适'
      },
      {
        label: '4星/高级',
        value: '4星/高级'
      },
      {
        label: '5星/豪华',
        value: '5星/豪华'
      }
      ],
      // 其他
      otherSelected: '不限',
      otherList: [{
        name: '酒店品牌',
        visible: false,
        open: false,
        key: 'brands',
        // 在mao-pattern组件用到
        data: []
      },
      {
        name: '酒店设施',
        visible: false,
        open: false,
        key: 'trainAirport',
        // 在mao-pattern组件用到
        data: []
      }
      ],
      // 已选择
      haveChosen: [
        // {
        //   label: '天河',
        //   value: 1,
        //   type: ''
        // }
      ],
      hotelFacilitiesList: [{
        label: 'WiFi',
        value: 'WiFi'
      },
      {
        label: '停车场',
        value: '停车场'
      },
      {
        label: '票务',
        value: '票务'
      },
      {
        label: '餐厅',
        value: '餐厅'
      },
      {
        label: '洗衣间',
        value: '洗衣间'
      },
      {
        label: '健身设施',
        value: '游泳池'
      },
      {
        label: 'SPA',
        value: 'SPA'
      },
      {
        label: '酒吧',
        value: '酒吧'
      },
      {
        label: '儿童看护',
        value: '儿童看护'
      },
      {
        label: '电梯',
        value: '电梯'
      }
      ]
    }
  },
  watch: {
    // 监听已选中标签
    haveChosen (newV, oldV) {
      let obj = {
        other: false,
        start: false,
        position: false,
        price: false,
        score: false
      }
      newV.forEach(item => {
        if (item.type === 'other') {
          obj.other = true
        }
        if (item.type === 'start') {
          obj.start = true
        }
        if (item.type === 'position') {
          obj.position = true
        }
        if (item.type === 'price') {
          obj.price = true
        }
        if (item.type === 'score') {
          obj.score = true
        }
      })
      if (!obj.position) {
        this.positionSelected = '不限'
      }
      if (!obj.price) {
        this.priceSelected = '不限'
        this.initPricelist()
      }
      if (!obj.score) {
        this.scoreSelected = '不限'
      }
      // 控制-星级-不限
      if (obj.start) {
        this.startSelected.no = ''
      } else {
        this.startSelected.no = '不限'
        this.startSelected.data = []// 控制选中
        if (this.componentsType === 'map-pattern') {
          this.startSelected.data = ['不限']
        }
      }
      // 控制-其他-不限
      if (obj.other) {
        this.otherSelected = ''
      } else {
        this.otherSelected = '不限'
        this.otherList.forEach(item => {
          if (this.componentsType === 'map-pattern') {
            item.data = ['不限']
          } else {
            item.data = []
          }
        })
      }
    },
    filter_D: {
      handler: function (val, oldVal) {
        if (this.componentsType === 'filter-children') {
          this.initFilterList(JSON.parse(JSON.stringify(val)))
        } else {
          this.initData(JSON.parse(JSON.stringify(val)))
        }
      },
      immediate: true
    }
  },
  mounted () {

  },
  methods: {
    ...mapActions(['setHotelFilterData']),
    // 初始化选中
    initFilterList (val) {
      this.haveChosen = val
      val.forEach((item) => {
        switch (item.type) {
          case 'position': // 位置
            this.positionSelected = item.value
            if (this.componentsType === 'filter-children') {
              // 开启位置选中
              this.onOpenPositionColorTriangle(item.type_position)
            }
            break
          case 'price': // 价格
            if (item.price) {
              this.priceList.start = item.start
              this.priceList.end = item.end
              this.priceSelected = ''
            } else {
              this.priceSelected = item.value
            }
            break
          case 'score': // 评分
            this.scoreSelected = item.value
            break
        }
      })
      // 初始化星级
      let _start = val.filter((item) => {
        return item.type === 'start'
      })
      if (_start.length) {
        let list = []
        _start.forEach((item) => { list.push(item.value) })
        this.startSelected.data = list
      }
      // 初始化其他
      let _other = val.filter((item) => {
        return item.type === 'other'
      })
      if (_other.length) {
        this.otherList.forEach(item => {
          _other.forEach(option => {
            if (option.type_other === item.key) {
              item.data.push(option.label)
            }
          })
        })
        this.otherList.forEach((item) => {
          if (!item.data.length) {
            item.data = ['不限']
          }
        })
        if (this.componentsType === 'filter-children') {
          this.oncloseSelectedColor('other')
        }
      } else {
        this.otherList.forEach((item) => {
          item.data = ['不限']
        })
      }
    },
    // 价格确定
    onPriceSure () {
      let name = ''
      if (this.priceList.start === '' && this.priceList.end === '') {
        return
      } else if (this.priceList.start === '' && this.priceList.end !== '') {
        this.priceList.start = 0
        name = this.priceList.start + '~' + this.priceList.end
      } else if (this.priceList.start !== '' && this.priceList.end === '') {
        name = this.priceList.start + '以上'
      } else {
        if (Number(this.priceList.start) >= Number(this.priceList.end)) {
          name = this.priceList.end + '-' + this.priceList.start
          let c = this.priceList.start
          this.priceList.start = this.priceList.end
          this.priceList.end = c
        } else {
          name = this.priceList.start + '-' + this.priceList.end
        }
      }

      let obj = {
        label: name,
        value: name
      }
      this.onSelectedChange(obj, 'price', 'highAndLowPrice')
    },
    // 初始化价格 最低最高
    initPricelist () {
      this.priceList.start = ''
      this.priceList.end = ''
    },
    // 价格正则过滤
    filterPriceReg (data) {
      let reg = /[^0-9]/g
      data = data.replace(reg, '')
      if (data === '') {
        return ''
      }
      return parseInt(data)
    },
    // 点击选中-所有
    /**
     *
     * @param {*} option
     * @param {*} type
     * @param {*} priceOrOther  价格【代表是价格】或者其他【代表索引】
     */
    onSelectedChange (option, type, priceOrOther) {
      // typeMy: ['position', 'price', 'score', 'start', 'other'],
      switch (type) {
        case 'position': // 位置
          this.positionSelected = option.name
          this.positionIconInit(-1)
          let positionObj = {
            label: option.name,
            value: option.name,
            type: type,
            type_position: option.type_position
          }
          this.addSelected(positionObj)
          break
        case 'price': // 价格
          this.priceSelected = option.value
          let priceObj = {
            label: option.label,
            value: option.value,
            type: type
          }
          if (priceOrOther) {
            priceObj['price'] = true
            priceObj['start'] = this.priceList.start
            priceObj['end'] = this.priceList.end
          }
          this.addSelected(priceObj)
          break
        case 'score': // 评分
          this.scoreSelected = option.value
          let scoreObj = {
            label: option.label,
            value: option.value,
            type: type
          }
          this.addSelected(scoreObj)
          break
        case 'start': // 星级
          let list = []
          this.startSelected.data.forEach((item) => {
            list.push({
              label: item,
              value: item,
              type: type
            })
          })

          this.addSelected(list, type)
          break
        case 'other': // 其他
          if (!option.name) {
            console.log('字段name为bull')
          }
          let key = priceOrOther
          let idxOther = this.otherList[key].data.indexOf(option.name)
          if (idxOther === -1) {
            this.otherList[key].data.push(option.name)
          } else {
            this.otherList[key].data.splice(idxOther, 1)
          }
          break
      }
    },
    // 其他-确定
    otherSure () {
      this.otherIconInit(-1)
      this.otherList.forEach((option) => {
        // if (option.data.length) {
        this.addSelected(option, 'other')
        // }
      })
    },
    // 只找到匹配到的第一个索引
    findFirstIndex (key, type) {
      let index = -1
      for (let i = 0; i < this.haveChosen.length; i++) {
        if (this.haveChosen[i][type] && this.haveChosen[i][type] === key) {
          index = i
          break
        }
      }
      return index
    },
    // 添加选中
    addSelected (option, _array) {
      if (_array && (_array === 'other')) {
        let list = []
        option.data.forEach((item) => {
          list.push({
            label: item,
            value: item,
            type: 'other',
            type_other: option.key
          })
        })
        if (!list.length) {
          this.haveChosen = this.haveChosen.filter((item) => {
            return item.type_other !== option.key
          })
          return false
        }
        // 只找到匹配到的第一个索引
        let index = this.findFirstIndex(option.key, 'type_other')

        if (index !== -1) {
        // 找到一共有几个（一定是连在一起的）
          let n = this.haveChosen.filter((item) => {
            return item.type_other === option.key
          })
          if (list.length >= n.length) {
            // 循环要添加的list 当索引小于总长度n.length-1  执行splice替换/否则执行splice添加（不用splic直接替换一部分的原因是list是个数组，splice第三个参数不支持直接数组中）
            list.forEach((item, i) => {
              if (i <= n.length - 1) {
                // 替换
                this.haveChosen.splice(index + i, 1, item) // 第二参数为1为替换
              } else {
                // 添加
                this.haveChosen.splice(index + i, 0, item) // 第二参数为0为添加
              }
            })
          } else {
            this.haveChosen = this.haveChosen.filter((item) => {
              return item.type_other !== option.key
            })
            list.forEach((item, i) => {
              this.haveChosen.splice(index + i, 0, item) // 第二参数为0为添加
            })
          }
        } else {
          this.haveChosen = this.haveChosen.concat(list)
        }
      } else if (_array && _array === 'start') {
        // 只找到匹配到的第一个索引
        let list = option
        if (!list.length) {
          this.haveChosen = this.haveChosen.filter((item) => {
            return item.type !== 'start'
          })
          return false
        }
        let index = this.findFirstIndex(_array, 'type')
        if (index !== -1) {
          // 找到一共有几个（一定是连在一起的）
          let n = this.haveChosen.filter((item) => {
            return item.type === 'start'
          })
          if (list.length >= n.length) {
            // 循环要添加的list 当索引小于总长度n.length-1  执行splice替换/否则执行splice添加（不用splic直接替换一部分的原因是list是个数组，splice第三个参数不支持直接数组中）
            list.forEach((item, i) => {
              if (i <= n.length - 1) {
              // 替换
                this.haveChosen.splice(index + i, 1, item) // 第二参数为1为替换
              } else {
              // 添加
                this.haveChosen.splice(index + i, 0, item) // 第二参数为0为添加
              }
            })
          } else {
            this.haveChosen = this.haveChosen.filter((item) => {
              return item.type !== 'start'
            })
            list.forEach((item, i) => {
              this.haveChosen.splice(index + i, 0, item) // 第二参数为0为添加
            })
          }
        } else {
          this.haveChosen = this.haveChosen.concat(list)
        }
      } else {
        let index = -1
        this.haveChosen.forEach((item, i) => {
          if (item.type === option.type) {
            index = i
          }
        })
        if (index !== -1) {
          // 替换
          this.haveChosen.splice(index, 1, option)
        } else {
          this.haveChosen.push(option)
        }
      }
    },
    // ------------------------//
    // 点击不限
    onUnlimitedClick (type) {
      this.removeSelected({
        type
      }, '不限')
    },
    // 删除个项
    removePatternSelected (option, not) {
      this.haveChosen.forEach((item, i) => {
        if (item.value === option.value) {
          this.haveChosen.splice(i, 1)
        }
      })
      let filter = this.otherList.filter((item) => {
        return item.key === option.type_other
      })
      // 设置星级自身选中
      if (filter && filter.length) {
        let idx = filter[0].data.indexOf(option.value)
        if (idx !== -1) {
          filter[0].data.splice(idx, 1)
          // 长度为0 设为不限
          if (!filter[0].data.length) {
            filter[0].data = ['不限']
          }
        }
      }
    },
    // 删除个项
    removeSelected (option, not) {
      let _array = option.type
      if (this.componentsType === 'map-pattern' && _array === 'other') {
        this.removePatternSelected(option, not)
        return false
      }

      // not不限
      if (not) {
        this.haveChosen = this.haveChosen.filter((item) => {
          return item.type !== option.type
        })
      } else if (_array === 'other' || _array === 'start') {
        this.haveChosen.forEach((item, i) => {
          if (item.value === option.value) {
            this.haveChosen.splice(i, 1)
          }
        })
        // 星级删除自身选中
        if (option.type === 'start') {
          this.startSelected.data.forEach((item, i) => {
            if (item === option.value) {
              this.startSelected.data.splice(i, 1)
            }
          })
        }
      } else {
        this.haveChosen = this.haveChosen.filter((item) => {
          return item.type !== option.type
        })
      }

      this.oncloseSelectedColor(option.type)
    },

    // 位置 icon
    positionIconInit (index) {
      this.positionList.forEach((item, i) => {
        if (index !== i) {
          item.visible = false
        }
      })
    },
    // 开启位置选中-三角以及color
    onOpenPositionColorTriangle (type) {
      let list = []
      this.haveChosen.forEach(item => {
        list.push(item.label)
      })
      this.positionList.forEach((item, index) => {
        if (item.key === type) {
          item.open = true
        }
      })
    },
    // filter 组件tab的color是open控制
    oncloseSelectedColor (type) {
      switch (type) {
        case 'position':
          this.positionList.forEach((item) => {
            item.open = false
          })
          break
        case 'other':
          this.otherList.forEach((option) => {
            option.open = false
            this.haveChosen.forEach(item => {
              if (option.key === item.type_other) {
                option.open = true
              }
            })
          })

          // let list = []
          // this.haveChosen.forEach(item => {
          //   list.push(item.label)
          // })
          // this.otherList.forEach((item, index) => {
          //   let idx = false
          //   this.filterData[item.key].forEach(option => {
          //     if (list.indexOf(option.name) !== -1) {
          //       idx = true
          //     }
          //   })
          //   this.otherList[index].open = idx
          // })
          break
      }
    },
    // ------------------------其他-----------------------
    // 其他展开关闭
    otherIconInit (index) {
      this.otherList.forEach((item, i) => {
        if (index !== i) {
          item.visible = false
        }
      })
    },
    // 其他 展开时 初始化选中
    initOtherSelected (open) {
      if (open) {
        this.otherList.forEach((item) => {
          item.data = []
          this.haveChosen.forEach(option => {
            if (option.type === 'other' && option.type_other === item.key) {
              item.data.push(option.label)
            }
          })
        })
      }
    },
    // 删除所有
    clearAll () {
      this.haveChosen = []
      // 地点
      this.positionSelected = '不限'
      this.oncloseSelectedColor('position')
      // 价格
      this.priceSelected = '不限'
      this.initPricelist()
      // 评分
      this.scoreSelected = '不限'
      // 星级
      // watch会清除
      // 其他
      this.otherSelected = '不限'
      this.startSelected.data = []
      this.otherList.forEach((item) => {
        item.data = ['不限']
      })
      if (this.componentsType === 'filter-children') {
        this.oncloseSelectedColor('other')
      }
    }
  }
}
