<template>
  <a-form :form="form" layout="vertical" class="b-vertical-form" @submit="handleSearch">
    <a-row class="b-form-row">
      <a-col :span="4" class="b-form-item">
        <a-form-item label="名称">
          <a-input
            v-decorator="['note']"
          />
        </a-form-item>
      </a-col>
      <a-col :span="4" class="b-form-item">
        <a-form-item label="描述">
          <a-input
            v-decorator="['description']"
          />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row class="b-form-bottom">
      <a-col :span="12">
      </a-col>
      <a-col :span="12" :style="{ textAlign: 'right' }">
        <a-button type="primary" html-type="submit">
          查询
        </a-button>
        <a-button :style="{ marginLeft: '8px' }" @click="handleReset">
          清空
        </a-button>
      </a-col>
    </a-row>
  </a-form>
</template>
<script>
export default {
  data() {
    return {
      expand: false,
      form: this.$form.createForm(this, { name: 'advanced_search' })
    }
  },
  methods: {
    handleSearch(e) {
      e.preventDefault()
      this.form.validateFields((error, values) => {
        if (!error) {
          this.$emit('form-search', this.form.getFieldsValue())
        }
      })
    },
    handleReset() {
      this.form.resetFields()
    }
  }
}
</script>
