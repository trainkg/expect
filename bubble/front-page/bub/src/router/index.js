import Vue from 'vue'
import Router from 'vue-router'
import routeConfig from './routes'
Vue.use(Router)
const router = new Router({
  // 配置路由
  routes: routeConfig
})
router.beforeEach((to, form, next) => {
  console.log(to)
  next()
})
export default router
