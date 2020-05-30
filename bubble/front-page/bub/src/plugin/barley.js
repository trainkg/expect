import BModel from '@/components/layout/bmodel'
import BFormField from '@/components/layout/form-field'
const Barley = {}
Barley.install = function(Vue, options) {
  Vue.component('b-model', BModel)
  Vue.component('b-form-field', BFormField)
  Vue.testPrint = function(name) {
    console.log(name)
  }
}
export default Barley
