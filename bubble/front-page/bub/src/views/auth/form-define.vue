<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="表单模型定义"
      sub-title="表单定义"
      @back="() => null"
    />
    <a-layout-content class="ant-layout-content-ext">
      <a-form :form="form" layout="vertical" class="b-vertical-form" @submit="submit">
        <a-tabs default-active-key="1">
          <a-tab-pane key="1" tab="主题结构" @change="callback">
            <formBase />
            <formField />
          </a-tab-pane>
          <a-tab-pane key="2" tab="表单配置" force-render>
          </a-tab-pane>
        </a-tabs>
      </a-form>
    </a-layout-content>
    <div id="form-footer-buttons" style="text-align: center;margin-top:10px">
      <a-button-group>
        <a-button type="primary" @click="back">
          <a-icon type="left" />返回
        </a-button>
        <a-button type="primary">
          保存<a-icon type="right" />
        </a-button>
      </a-button-group>
    </div>
  </div>
</template>
<script>
import router from '@/router/index'
import formBase from './form-define/form-base'
import formField from './form-define/form-field'
export default {
  name: 'bub-form',
  components: {
    formBase,
    formField
  },
  data() {
    return {
      form: this.$form.createForm(this, { name: 'form-define' })
    }
  },
  methods: {
    callback(key) {
      // validate
    },
    back() {
      router.back()
    },
    submit() {
    }
  }
}
</script>
