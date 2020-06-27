<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="流程申请"
      sub-title="流程启动"
      @back="() => null"
    />
    <a-layout-content class="ant-layout-content-ext">
      <a-descriptions title="流程信息">
        <a-descriptions-item label="流程名称">
          Zhou Maomao
        </a-descriptions-item>
        <a-descriptions-item label="申请人">
          Zhou Maomao
        </a-descriptions-item>
        <a-descriptions-item label="申请时间">
          Zhou Maomao
        </a-descriptions-item>
      </a-descriptions>
      <s-form-render ref="formEntity" :form-key="query" :form-data="form" />
    </a-layout-content>

    <div id="form-footer-buttons" style="text-align: center;margin-top:10px">
      <a-button-group>
        <a-button type="primary" @click="back">
          <a-icon type="left" />返回
        </a-button>
        <a-button type="primary" @click="submitProcess">
          提交<a-icon type="right" />
        </a-button>
      </a-button-group>
    </div>
  </div>
</template>
<script>
import reqwest from 'reqwest'
import router from '@/router/index'

const a = {
  name: '朱媛媛',
  address: '银河系伯恩斯星球111',
  age: 12,
  test:1231
}

export default {
  props: {
    query: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      process: {
        id: '',
        name: '',
        submitTime: ''
      },
      form: a
    }
  },
  created() {
    console.log(this.query)
  },
  methods: {
    back() {
      router.back()
    },
    getFormData() {
      return a
    },
    submitProcess() {
      console.log(this.query)
      console.log('start ............')
      console.log(this.$data)
      console.log(this.$refs.formEntity.$data.form)
      /* reqwest({
        url: process.env.VUE_APP_URL + '/flow/start/' + this.query,
        method: 'get',
        data: {
        },
        type: 'json'
      }).then(data => {
        alert('提交成功')
      })*/
    }
  }
}
</script>
