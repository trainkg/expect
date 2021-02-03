import Vue from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import router from './router/index'
import Vuex from 'vuex'
import reqwest from 'reqwest'
import request from './utils/request'
import Barley from '@/plugin/barley'
import '@/styles/index.scss' // global cs

/**
 * 安装插件
 */
Vue.use(Vuex)
Vue.use(Barley)
Vue.use(Antd)
Vue.use(reqwest)
Vue.use(request)
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

router.push('/index').then(r => function() {})

console.log('===============================================')
console.log('===============================================')
console.log('===============================================')
console.log('===============================================')
console.log('Barley Application in running.')
console.log('Current App run process env as below ')
console.log(process.env)
console.log('===============================================')
console.log('===============================================')
console.log('===============================================')
console.log('===============================================')

