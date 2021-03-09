<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff" />
    <a-layout style="padding: 0 12px 12px;">
      <router-view>
        <pageContent />
      </router-view>
      <!-- 组件 -->
      <div id="robot-content">
        <a-input-search v-model="component" placeholder="输入你的组件路径" size="default" @search="changeComponent">
          <a-button slot="enterButton">
            打开组件
          </a-button>
        </a-input-search>
        <div id="mount-point" style="margin:15px;">
          动态内容区域11
        </div>
      </div>
    </a-layout>
  </a-layout>
</template>
<script>
// 静态导入， 后续js中可以访问到这个组件
import Vue from 'vue'
import {
  searchData
} from '@/api/base'
export default {
  data() {
    return {
      component: '@/views/party/user-type',
      last: null,
      // 功能页面菜单列表
      modules: []
    }
  },
  created() {
    this.loadingModule()
  },
  methods: {
    loadingModule() {
      // 7 robot
      const search = { parentId: 7 }
      searchData('module', search).then((rs) => {
        this.modules = rs.data.data
      })
    },
    /**
     *
     */
    changeComponent() {
      console.log(this.component)
      const parent = this
      // @/views/basic/group/group-form
      // @/views/basic/group/commons/group-table
      // @/views/basic/group/commons/group-table
      import('@/views/basic/group/commons/group-picker').then((Component) => {
        console.log(this)
        if (parent.last) { parent.last.$destroy() }
        console.log(Component.default)
        const component = new Vue(Component.default)
        parent.last = component
        document.getElementById('mount-point').innerHTML = ''
        // console.log(component.$el)
        // component.$mount('#mount-point')
        const componentDom = component.$mount()
        document.getElementById('mount-point').appendChild(componentDom.$el)
      })
    }
  }
}
</script>
