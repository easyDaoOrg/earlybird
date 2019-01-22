const Util = {
  // 格式化价格
  priceTransfer (value) {
    let fixedPrice
    let finalPrice
    const actualPrice = Number(value) / 1000
    const re = /(\d{1,3})(?=(\d{3})+(?:\.))/g
    if (isNaN(value)) {
      finalPrice = '--'
    }
    if (actualPrice.toString().indexOf('.') !== -1) {
      if (actualPrice !== 0 && actualPrice.toString().split('.')[1].length > 2) {
        fixedPrice = parseFloat(actualPrice).toFixed(3)
        finalPrice = fixedPrice.replace(re, '$1,')
      } else {
        fixedPrice = parseFloat(actualPrice).toFixed(2)
        finalPrice = fixedPrice.replace(re, '$1,')
      }
    } else {
      fixedPrice = parseFloat(actualPrice).toFixed(2)
      finalPrice = fixedPrice.replace(re, '$1,')
    }
    return finalPrice
  },
  timeStamp (time) {
    const date = new Date(parseInt(time, {}))
    const year = date.getFullYear()
    const month = (date.getMonth() + 1) < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1
    const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()
    const hour = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours()
    const min = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes()
    const sec = date.getSeconds() < 10 ? `0${date.getSeconds()}` : date.getSeconds()
    const times = `${year}.${month}.${day} ${hour}:${min}:${sec}`
    return times
  },
  timeStampRow (time) {
    const date = new Date(parseInt(time, {}))
    const year = date.getFullYear()
    const month = (date.getMonth() + 1) < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1
    const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()
    const times = `${year}${month}${day}`
    return times
  },
  timeStampLite (time, type) {
    const date = new Date(parseInt(time, 0))
    const year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate()
    if (month < 10) {
      const zero = '0'
      month = zero + month
    }
    if (day < 10) {
      const zero = '0'
      day = zero + day
    }
    const line = '-'
    let str = ''
    if (type === 'yyyy年MM月dd日') {
      str = year + '年' + month + '月' + day + '日'
    } else {
      str = year + line + month + line + day
    }
    return str
  },
  timeStampLiteAdd (time) {
    const date = new Date(parseInt(time, 0))
    // const year = date.getFullYear();
    let month = date.getMonth() + 1
    let day = date.getDate()
    const hour = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours()
    const minutes = date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes()
    if (month < 10) {
      const zero = '0'
      month = zero + month
    }
    if (day < 10) {
      const zero = '0'
      day = zero + day
    }
    return `${hour}:${minutes} ${month}-${day}`
  },
  title (title) {
    const pageTitle = title || '监控管理'
    window.document.title = pageTitle
  },
  /**
   *获取不同环境下的基本域名
   */
  baseUrl () {
    let baseURL = ''
    switch (process.env.NODE_ENV) {
      case 'production':
        baseURL = 'http://172.23.7.73'
        break
      case 'daily':
        baseURL = 'http://172.23.7.73'
        break
      case 'buildtest':
        baseURL = 'http://172.23.7.73'
        break
      default:
        baseURL = 'http://172.23.7.73'
        break
    }
    return baseURL
  },
  differDays (updateTime, createTime) {
    let up = new Date(updateTime.replace(/-/g, '/')) // 开始时间
    let cr = new Date(createTime.replace(/-/g, '/')) // 开始时间
    var date3 = up.getTime() - cr.getTime() // 时间差的毫秒数
    // 计算出相差天数
    var days = Math.floor(date3 / (24 * 3600 * 1000))
    // 计算出小时数
    var leave1 = date3 % (24 * 3600 * 1000) // 计算天数后剩余的毫秒数
    var hours = Math.floor(leave1 / (3600 * 1000))
    // 计算相差分钟数
    var leave2 = leave1 % (3600 * 1000) // 计算小时数后剩余的毫秒数
    var minutes = Math.floor(leave2 / (60 * 1000))
    // 计算相差秒数
    var leave3 = leave2 % (60 * 1000) // 计算分钟数后剩余的毫秒数
    var seconds = Math.round(leave3 / 1000)
    // str=" 相差 " + days + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒"
    var returnStr = seconds + '秒'
    if (minutes > 0) {
      returnStr = minutes + '分' + returnStr
    }
    if (hours > 0) {
      returnStr = hours + '小时' + returnStr
    }
    if (days > 0) {
      returnStr = days + '天' + returnStr
    }
    return returnStr
  }
}

export default Util
