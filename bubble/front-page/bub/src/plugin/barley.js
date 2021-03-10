import BModel from '@/components/layout/bmodel'
import BFormField from '@/components/layout/form-field'
import SimpeFormRender from '@/components/form/from-simple-render'
import Navbar from '@/components/layout/Navbar'
const Barley = {}
Barley.install = function(Vue, options) {
  Vue.component('b-model', BModel)
  Vue.component('b-form-field', BFormField)
  Vue.component('s-form-render', SimpeFormRender)
  Vue.component('navbar', Navbar)
  Vue.testPrint = function(name) {
    console.log(name)
  }
}
export default Barley
