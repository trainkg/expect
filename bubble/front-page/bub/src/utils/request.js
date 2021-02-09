import axios from 'axios'
import confirm from 'ant-design-vue/lib/modal/confirm'
import { message } from 'ant-design-vue'
/* import { Message, MessageBox } from 'element-ui'

import store from '../store'*/

// create an axios instance
const service = axios.create({
  timeout: 50000 // request timeout
})

// 是否正在弹窗
let isLoginTimeout = false

// request interceptor
service.interceptors.request.use(
  config => {
    // api 的 base_url
    // 在此处设置baseURL，避免直接依赖store，如果放在上面create方法，store为空
    config.baseURL = process.env.VUE_APP_URL

    // 所有请求默认是json格式，除了上传文件，不用表单
    config.headers['Content-Type'] = 'application/json; charset=UTF-8'
    // 如果使用表单，使用如下方式
    // 'Content-Type': 'application/x-www-form-urlencoded'
    // Do something before request is sent
    /* if (store.getters.token) {
      // 让每个请求携带token-- ['Authorization']为自定义key 请根据实际情况自行修改
      config.headers['Authorization'] = store.getters.token
    }*/
    return config
  },
  error => {
    // Do something with request error
    // console.log(error) // for debug
    message.error(error)
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  // response => response,
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  response => {
    const res = response.data
    if (res.Status && res.Status !== 0) {
      if (res.Status >= 500) {
        message.error('后端服务错误')
        return Promise.reject('后端服务错误')
      } else if (res.Status === 203) {
        // 请自行在引入 MessageBox
        // import { Message, MessageBox } from 'element-ui'
        // 如果已弹窗，不重复弹窗
        if (!isLoginTimeout) {
          isLoginTimeout = true
          // 如果当前就是login、页面则不作处理
          if (location.pathname.indexOf('/login') > 0) {
            return response
          }

          confirm({
            title: '确定登出',
            content: '你已被登出或者登陆失效，可以取消继续留在该页面，或者重新登录',
            okText: '重新登录',
            cancelText: '取消',
            ok: function() {
              isLoginTimeout = false
              /* store.dispatch('FedLogOut').then(() => {
                location.reload() // 为了重新实例化vue-router对象 避免bug
              })*/
            }
          })
        } else {
          return response
        }
      } else {
        message.error(res.Msg)
        return Promise.reject(res.Msg)
      }
    } else {
      return response
    }
  },
  error => {
    message.error('请求错误')
    return Promise.reject(error)
  }
)

export default service
