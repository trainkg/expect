<!--form 表单配置参考 https://www.antdv.com/components/form-model-cn/ -->
<template>
  <a-form-model :model="form" :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal">
    <!-- 章节列表 -->
    <!-- 独立字段生成 -->
    <a-form-model-item label="名称">
      <a-input v-model="form.name" required="false" />
    </a-form-model-item>
    <a-form-model-item label="描述">
      <a-input v-model="form.descibe" required="false" />
    </a-form-model-item>
    <a-form-model-item label="状态">
      <a-input v-model="form.status" required="false" />
    </a-form-model-item>
    <!-- form button -->
    <a-form-model-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
      <a-space size="50">
        <a-button type="primary" @click="onSubmit">
          Create
        </a-button>
        <a-button style="margin-left: 10px;">
          Cancel
        </a-button>
      </a-space>
    </a-form-model-item>
  </a-form-model>
</template>
<script>
export default {
  data() {
    return {
      activeKey: [],
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
      form: { 'descibe': null, 'name': null, 'status': null }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!', this.form)
    }
  }
}
</script>
