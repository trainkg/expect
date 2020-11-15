<template>
  <div>
    <a-form-model :model="form" layout="vertical" class="b-vertical-form">
      <div>
        渲染模式 ： {{ formDefine.renderName }} 111 {{ form.name }}
      </div>
      <div v-for="(section, index) in formDefine.form.sections">
        <b-model :title="section.title">
          <a-row v-for="(col,cIndex) in transferToRowList(section.fileds, formDefine.layoutStrategy)" class="b-form-row">
            <div v-for="(feild, fIndex) in col">
              <a-col v-if="feild.type == 'INPUT'" :span="24/formDefine.layoutStrategy.colSize" class="b-form-item">
                <a-form-item :label="feild.label">
                  <a-input v-model="form[feild.name]" />
                </a-form-item>
              </a-col>

              <a-col v-if="feild.type == 'NUMBER'" :span="24/formDefine.layoutStrategy.colSize" class="b-form-item">
                <a-form-item :label="feild.label">
                  <a-input-number v-model="form[feild.name]" :min="feild.min" :max="feild.max" />
                </a-form-item>
              </a-col>

              <a-col v-if="feild.type == 'RADIO'" :span="24/formDefine.layoutStrategy.colSize" class="b-form-item">
                <a-form-item :label="feild.label">
                  <a-radio-group v-model="form[feild.name]">
                    <a-radio value="Radio5">
                      Radio
                    </a-radio>
                    <a-radio value="Radio4">
                      Radio1
                    </a-radio>
                    <a-radio value="Radio3">
                      Radio2
                    </a-radio>
                    <a-radio value="Radio2">
                      Radio3
                    </a-radio>
                    <a-radio value="Radio1">
                      Radio4
                    </a-radio>
                  </a-radio-group>
                </a-form-item>
              </a-col>
            </div>
          </a-row>
        </b-model>
      </div>
      <ct-select
        code-name="1"
      />
    </a-form-model>
    <ct-select-ex placeholder="Select users111" style="width: 100%">
      <a-select-option key="1">
        item1
      </a-select-option>
      <a-select-option key="2">
        item2
      </a-select-option>
    </ct-select-ex>
    <a-select
      placeholder="Select users"
      style="width: 100%"
    />
  </div>
</template>
<script>
import ctSelect from './ct-select'
import ctSelectEx from './ct-select-ex'
import reqwest from 'reqwest'
const formData = {
  name: 'Train King',
  address: '江苏无锡',
  age: 11
}
export default {
  components: {
    ctSelectEx,
    ctSelect
  },
  props: ['formKey', 'formData'],
  data() {
    const formDataInt = this.getFormDateInternal()
    return {
      formDefine: {
        form: {},
        layoutStrategy: {
          colSize: null
        },
        renderName: ''
      },
      form: formDataInt
    }
  },
  created() {
    // loading form define
    this.loadingFormDefine(this.formKey)
  },
  beforeMount() {
    console.log('before mount,this form key is ', this.formKey)
  },
  methods: {
    loadingFormDefine(formKey) {
      reqwest({
        url: process.env.VUE_APP_URL + '/form/template/' + formKey,
        method: 'get',
        type: 'json'
      }).then(data => {
        this.formDefine = data
      })
    },
    getFormDateInternal() {
      console.log('000000000000000000')
      const combinForm = Object.assign({}, formData, this.formData)
      console.log('form render data ', combinForm)
      return combinForm
    },
    transferToRowList(feilds, layoutStrategy) {
      if (!feilds) {
        return []
      }
      const colSize = layoutStrategy.colSize
      const conList = []
      let row = []
      let index = 0

      feilds.forEach(feild => {
        if (index < colSize) {
          row.push(feild)
        }
        // reset
        if (index === colSize - 1) {
          index = 0
          conList.push(row)
          row = []
        } else {
          index++
        }
      })

      if (row.length > 0) {
        conList.push(row)
      }
      return conList
    }
  }
}
</script>
