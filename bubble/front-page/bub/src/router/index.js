import Vue from 'vue'
import Router from 'vue-router'
import routeConfig from './routes'
import { searchData } from '@/api/base'
Vue.use(Router)

const router = new Router({
  // 配置路由
  routes: routeConfig
})

// 前置导航 等同于请求拦截器
router.beforeEach((to, form, next) => {
  next()
})

// 加载演示脚本
async function loadRobotChildren() {
  const search = { parentId: 7, loadSub: 1, flatResult: 1 }
  const rs = await searchData('module', search)
  const childrenList = []
  for (const rt of rs.data.data) {
    const page = rt.uri.replaceAll('/', '-').substring(1)
    const rtConfig = {
      name: rt.listId + '',
      path: rt.uri,
      component: resolve => require([`@/views/robot/${page}`], resolve)
    }
    if (rt.listId !== 7) { childrenList.push(rtConfig) }
  }

  // robot
  const robot = {
    path: '/robot',
    name: 'robot',
    component: () => import('@/views/robot'), props: (route) => ({ cid: route.query.cid }),
    children: childrenList
  }

  router.addRoutes([robot])
}

loadRobotChildren().then((a, b) => {
  console.log('completed')
})

export default router
