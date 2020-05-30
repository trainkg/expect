<template>
  <div>
    <div id="form-search">

    </div>
    <a-layout-content class="ant-layout-content-ext">
      <div>
        <a-tabs default-active-key="1">
          <a-tab-pane key="1" tab="表单定义" @change="callback">
            <forms />
          </a-tab-pane>
          <a-tab-pane key="2" tab="表单域" force-render>
            <fields />
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-layout-content>
  </div>
</template>

<script>
import FormForms from './module/form-forms'
import FormFields from './module/form-fields'
export default {
  name: 'bub-form',
  components: {
    'forms': FormForms,
    'fields': FormFields
  },
  data() {
    return {}
  },
  methods: {
    callback(key) {
      console.log(key)
    }
  }
}
</script>
