<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="文件中心"
      sub-title=""
      @back="() => null"
    />

    <a-layout-content
      class="ant-layout-content-ext"
    >
      <!-- <a-modal
              title="Title"
              :visible="true"
              @ok="handleOk"
              @cancel="handleCancel"
      >
        <p>test</p>
      </a-modal>-->

      <!--<b-model title="查询条件">
        <a-form-model :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">
          <a-form-model-item label="文件名称">
            <a-input v-model="form.name" />
          </a-form-model-item>
          <a-form-model-item label="文件格式">
            <a-input v-model="form.format" />
          </a-form-model-item>
        </a-form-model>
      </b-model>-->
      <a-row class="b-form-bottom">
        <a-col :span="24" :style="{ textAlign: 'right' }">
          <a-button type="primary" html-type="submit" @click="handleSearch">
            查询
          </a-button>
          <a-button :style="{ marginLeft: '8px' }">
            索引
          </a-button>
        </a-col>
      </a-row>
    </a-layout-content>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelCol: { span: 2 },
      wrapperCol: { span: 4 },
      form: {
        name: '',
        format: undefined
      }
    }
  },
  methods: {
    handleSearch() {
      console.log(this.form)
    },
    handleOk() {

    },
    handleCancel() {

    }
  }
}
</script>
