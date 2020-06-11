<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="流程申请"
      sub-title="流程启动"
      @back="() => null"
    />
    <a-layout-content
      class="ant-layout-content-ext"
    >
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

      <b-model title="流程表单">
        流程表单
      </b-model>
    </a-layout-content>

    <div id="form-footer-buttons" style="text-align: center;margin-top:10px">
      <a-button-group>
        <a-button type="primary" @click="back">
          <a-icon type="left" />返回
        </a-button>
        <a-button type="primary">
          提交<a-icon type="right" />
        </a-button>
      </a-button-group>
    </div>
  </div>
</template>
<script>
import reqwest from 'reqwest'
import router from '@/router/index'
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
      form: {}
    }
  },
  created() {
    console.log(this.query)
  },
  methods: {
    back() {
      router.back()
    }
  }
}
</script>
