import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import BaiduMap from 'vue-baidu-map'
import axios from 'axios'
import $ from 'jquery'
import jquery from 'jquery'
// 引入echarts
import echarts from 'echarts'
//在vue中设置Echart
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.prototype.$http = axios
axios.defaults.baseURL = 'http://119.29.23.140:9000'
new Vue({
  router,
  $,
  jquery,
  render: h => h(App)
}).$mount('#app')
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'HVeWlAhFAEUYaw8Lf2vaquR4hyIoq9dQ'
})