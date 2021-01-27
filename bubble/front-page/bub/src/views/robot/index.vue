<script>
// 静态导入， 后续js中可以访问到这个组件
import userType from '@/views/party/user-type'
export default {
  components: {

  },
  props: ['cid'],
  render(createElement, context) {
    console.log('================= execute render method.')
    console.log(createElement)
    console.log(context)
    // 返回promise
    const component1 = null
    /* return Promise.all([import('@/views/party/user-type')]).then(components => {
      console.log('.....')
      console.log(components)
      component1 = components[0]
      console.log(component1)
      createElement('h1', 'this is test1')
    })*/

    /*return require(['@/views/party/user-type'], function(resolve) {
      console.log('require loading')
      console.log(resolve)
      return createElement('h1', 'this is tes1t')
    })*/
    // javascript
    /* return createElement(
        // {String | Object | Function}
        // 一个 HTML 标签名、组件选项对象，或者
        // resolve 了上述任何一种的一个 async 函数。必填项。
        'div',

        // {Object}
        // 一个与模板中 attribute 对应的数据对象。可选。
        {
            // (详情见下一节)
        },

        // {String | Array}
        // 子级虚拟节点 (VNodes)，由 `createElement()` 构建而成，
        // 也可以使用字符串来生成“文本虚拟节点”。可选。
        [
            '先写一些文字',
            createElement('h1',{}, '一则头12条'),
            component1
        ]
    )*/

    // JSX
    return (
      <userType></userType>
    )
  }
}
</script>
