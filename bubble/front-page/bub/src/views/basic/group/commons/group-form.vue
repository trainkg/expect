<!--form 表单配置参考 https://www.antdv.com/components/form-model-cn/ -->
<template>
  <a-form-model :model="form" layout="vertical">
    <a-collapse v-model="activeKey" expand-icon-position="left">
      <!-- 章节列表 -->
      <a-collapse-panel key="1" header="test1">
        <a-icon slot="extra" type="setting" />
        <a-row :gutter="[16,8]">
          <a-col :span="6">
            <a-form-model-item label="名称">
              <a-input v-model="form.name" required="false" />
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="描述">
              <a-input v-model="form.descibe" required="false" />
            </a-form-model-item>
          </a-col>
          <a-col :span="6">
            <a-form-model-item label="状态">
              <a-input v-model="form.status" required="false" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-collapse-panel>
    </a-collapse>
    <!-- 独立字段生成 -->
    <!-- form button -->
    <!-- 位置放在最下面 -->
    <a-form-model-item style="text-align: center;margin-top: 15px">
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
import router from '@/router/index'
import { createData } from '@/api/base'
export default {
  data() {
    return {
      activeKey: [],
      form: { 'descibe': null, 'name': null, 'status': null }
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!', this.form)
      createData('group', this.form).then((rs) => {
        // 提示消息
        this.$message.info('提交成功')
        // 页面跳转, 根据实际调整
        router.push('index')
      })
    }
  }
}
</script>
