
export default {
  computed: {

  },
  data () {
    return {
      typeMy: ['position', 'price', 'score', 'start', 'other'],
      // 位置
      positionList: [
        {
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
      priceList: {
        input: {
          start: 0,
          end: 0
        },
        data: [
          {
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
      scoreList: [
        {
          label: '4分以上',
          value: '4分以上'
        },
        {
          label: '3分以上',
          value: '3分以上'
        }
      ],
      // 星级
      startSelected: '不限',
      startList: [
        {
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
      otherList: [
        {
          name: '酒店品牌',
          visible: false,
          open: false,
          key: 'brands'
        },
        {
          name: '酒店设施',
          visible: false,
          open: false,
          key: 'trainAirport'
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
      hotelFacilitiesList: [
        {
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
  },
  mounted () {

  },
  methods: {
    // 价格确定
    onPriceSure () {
      let name = this.priceList.input.start + '-' + this.priceList.input.end
      let obj = {
        label: name,
        value: name
      }
      this.onSelectedChange(obj, 'price')
    },
    // 点击选中-所有
    onSelectedChange (option, type) {
      // typeMy: ['position', 'price', 'score', 'start', 'other'],
      switch (type) {
        case 'position': // 位置
          this.positionSelected = option.name
          this.positionIconInit(-1)

          let positionObj = {
            label: option.name,
            value: option.name,
            type: type
          }
          this.addSelected(positionObj)
          break
        case 'price':// 价格
          this.priceSelected = option.value
          let priceObj = {
            label: option.label,
            value: option.value,
            type: type
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
          this.startSelected = option.value
          let startObj = {
            label: option.label,
            value: option.value,
            type: type
          }
          this.addSelected(startObj)
          break
        case 'other': // 其他
          this.otherSelected = option.name
          this.otherIconInit(-1)
          const otherObj = {
            label: option.name,
            value: option.name,
            type: 'other'
          }
          this.addSelected(otherObj)
          break
      }
    },
    // 添加选中
    addSelected (option) {
      let index = -1
      this.haveChosen.forEach((item, i) => {
        if (item.type === option.type) {
          index = i
        }
      })
      if (index !== -1) {
        this.haveChosen.splice(index, 1, option)
        // 替换
      } else {
        this.haveChosen.push(option)
      }
    },
    // ------------------------//
    // 点击不限
    onUnlimitedClick (type) {
      this.removeSelected({type})
    },
    // 删除个项
    removeSelected (option) {
      let index = -1
      this.haveChosen.forEach((item, i) => {
        if (item.type === option.type) {
          index = i
        }
      })
      if (index !== -1) {
        this.haveChosen.splice(index, 1)
      }
      // typeMy: ['position', 'price', 'score', 'start', 'other'],
      switch (option.type) {
        case 'position':
          this.positionSelected = '不限'
          this.priceList.input.start = 0
          this.priceList.input.end = 0
          break
        case 'price':
          this.priceSelected = '不限'
          break
        case 'score':
          this.scoreSelected = '不限'
          break
        case 'start':
          this.startSelected = '不限'
          break
        case 'other':
          this.otherSelected = '不限'
          break
      }
      this.oncloseSelectedColor(option.type)
    },
    // 删除所有
    clearAll () {
      this.haveChosen = []
      this.positionSelected = '不限'
      this.priceList.input.start = 0
      this.priceList.input.end = 0
      this.priceSelected = '不限'
      this.scoreSelected = '不限'
      this.startSelected = '不限'
      this.otherSelected = '不限'
      this.oncloseSelectedColor('position')
      this.oncloseSelectedColor('other')
    },
    // 位置 icon
    positionIconInit (index) {
      this.positionList.forEach((item, i) => {
        if (index !== i) {
          item.visible = false
        }
      })
    },
    otherIconInit (index) {
      this.otherList.forEach((item, i) => {
        if (index !== i) {
          item.visible = false
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
          this.otherList.forEach((item) => {
            item.open = false
          })
          break
      }
    }
  }
}
