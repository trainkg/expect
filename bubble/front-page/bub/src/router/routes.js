// Modules
// 功能首页
const index = {
  path: '/index',
  name: 'home-root',
  component: () => import('@/views/index/'),
  children: [
    { name: 'home-index', path: '', component: () => import('@/views/index/index-content') }
  ]
}

// 系统管理
const system = {
  path: '/system',
  name: 'system-root',
  component: () => import('@/views/system/'),
  children: [
    { name: 'system-index', path: '', component: () => import('@/views/system/system-content') }
  ]
}

// 配置中心
const auth = {
  path: '/auth',
  name: 'auth-root',
  component: () => import('@/views/auth/'),
  children: [
    { name: 'auth-index', path: '', component: () => import('@/views/auth/auth-content') },
    { name: 'auth-module', path: '/module', component: () => import('@/views/auth/module') },
    { name: 'auth-role', path: '/role', component: () => import('@/views/auth/role') },
    { name: 'form-module', path: '/form', component: () => import('@/views/auth/form') },
    { name: 'file-module', path: '/file-manager', component: () => import('@/views/auth/file/file-manager') },
    { name: 'maintain-form', path: '/maintain-form', component: () => import('@/views/auth/form-define') },
    { name: 'quartz-scheduler', path: '/maintain-form', component: () => import('@/views/auth/batch/timetask') },
    { name: 'manual-scheduler', path: '/manual-scheduler', component: () => import('@/views/auth/batch/schduler') },
    { name: 'batch-monitor', path: '/batch-monitor', component: () => import('@/views/auth/batch/monitor') }
  ]
}

// 人员机构管理
const party = {
  path: '/party',
  name: 'party-root',
  component: () => import('@/views/party/'),
  children: [
    { name: 'party-index', path: '', component: () => import('@/views/party/party-content') },
    { name: 'party-maintain', path: '/maintain', component: () => import('@/views/party/party-maintenance') },
    { name: 'create-depart', path: '/new/depart', component: () => import('@/views/party/department/create-department') },
    { name: 'user-type-list', path: '/userType/list', component: () => import('@/views/party/user-type') }
  ]
}

// 系统任务
const task = {
  path: '/task',
  name: 'task-root',
  component: () => import('@/views/task/'),
  children: [
    { name: 'task-index', path: '', component: () => import('@/views/task/task-content') },
    { name: 'task-list', path: '/list', component: () => import('@/views/task/task-list') },
    { name: 'process-start', path: '/process-start', component: () => import('@/views/task/process-start'), props: (route) => ({ query: route.query.id }) },
    { name: 'process-list', path: '/process', component: () => import('@/views/task/process-list') }
  ]
}
const routes = [index, system, auth, party, task]
export default routes
