<template>
  <div id="robot-content">
    <a-input-search placeholder="输入你的组件路径" v-model="component" size="middle" @search="changeComponent">
      <a-button slot="enterButton">
        打开组件
      </a-button>
    </a-input-search>
    <div id="mount-point" style="margin-top:15px">
      动态内容区域11
    </div>
  </div>
</template>
<script>
// 静态导入， 后续js中可以访问到这个组件
import Vue from 'vue'
export default {
  data() {
    return {
      component: '@/views/party/user-type',
      last: null
    }
  },

  methods: {
    /**
     *
     */
    changeComponent() {
      console.log(this.component)
      const parent = this
      //@/views/basic/group/group-form
      //@/views/basic/group/commons/group-table
      import('@/views/basic/group/commons/group-table').then((Component) => {
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
