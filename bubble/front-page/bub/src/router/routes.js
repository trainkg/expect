// Modules
const index = {
  path: '/index',
  name: 'home-root',
  component: () => import('@/views/index/'),
  children: [
    { name: 'home-index', path: '', component: () => import('@/views/index/index-content') }
  ]
}
const system = {
  path: '/system',
  name: 'system-root',
  component: () => import('@/views/system/'),
  children: [
    { name: 'system-index', path: '', component: () => import('@/views/system/system-content') }
  ]
}
const auth = {
  path: '/auth',
  name: 'auth-root',
  component: () => import('@/views/auth/'),
  children: [
    { name: 'auth-index', path: '', component: () => import('@/views/auth/auth-content') },
    { name: 'auth-module', path: '/module', component: () => import('@/views/auth/module') },
    { name: 'auth-role', path: '/role', component: () => import('@/views/auth/role') },
    { name: 'form-module', path: '/form', component: () => import('@/views/auth/form') }
  ]
}
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
const routes = [index, system, auth, party]
export default routes
