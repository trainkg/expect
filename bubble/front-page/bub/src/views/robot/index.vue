<script>
// 静态导入， 后续js中可以访问到这个组件
import userType1 from '@/views/party/user-type'
import Vue from 'vue'
Vue.component('component-fallback', {
  template: `<div>This is not the component you're looking for</div>`
})

const getComponent = async path => {
  /* I recomend having an switch with the possible components you will load, this
     *   will allow you only load specific components.
     */
  console.log(path)
  if (path == 1) {
    console.log('====1')
    return () => {
      `<p>component 0</p>`
    }
  } else {
    console.log('====2')
    return async() => {
      `<p>${path}</p>`
    }
  }
}

export default {
  components: {
    'userType1': userType1
  },

  props: ['cid'],
  data() {
    return {
      componentIndex: 0,
      component: 'component-fallback'
    }
  },

  methods: {
    changeComponent() {
      const newIndex = ++this.componentIndex
      this.loadComponent(newIndex)
    },

    // returns the component
    loadComponent(name) {
      const componentFunc = getComponent(name)
        .then(x => {
          console.log(x)
          this.component = x
        })
        .catch(e => {
          this.component = 'component-fallback'
        })
    }
  },
  template: `
        <div>
            Component: {{componentIndex}} <button @click="changeComponent">change</button>
            <component :is="component"></component>
        </div>
        `
}
</script>
