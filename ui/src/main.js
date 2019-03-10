// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import Vuex from 'vuex'
import store from './store'
import 'iview/dist/styles/iview.css'
import './assets/style/index.scss'
import './assets/font/iconfont.css'

import Util from './lib/utils.js'
import axios from 'axios/dist/axios.min'

Vue.prototype.baseUrl = Util.baseUrl()
Vue.prototype.loginUrl = Util.loginUrl()
Vue.prototype.isToken = Util.getCookie('token')
Vue.prototype.axios = axios
Vue.use(iView)
Vue.use(Vuex)
Vue.config.productionTip = false

axios.interceptors.request.use(function (config) {
  // 判断如果登录了就把token配置到axios的协议中
  // let token = localStorage.getItem('token')
  let token = Util.getCookie('token')
  console.log('token', '===========', token)
  if (token) {
    config.headers['token'] = token
  }
  // 处理请求前代码
  return config
}, function (error) {
  // 一些事情伴随着错误
  return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})
