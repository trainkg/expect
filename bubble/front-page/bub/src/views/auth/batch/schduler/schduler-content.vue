<template>
  <a-layout-content class="ant-layout-content-ext">
    <b-model title="作业信息">
      <a-select
        mode="default"
        placeholder="输入作业名称"
        :value="selectedItems.jobName"
        style="width: 100%"
        @change="handleChange"
      >
        <a-select-option v-for="item in filteredOptions" :key="item.listId" :value="item.listId">
          {{ item.jobName }}
        </a-select-option>
      </a-select>
    </b-model>

    <div id="form-footer-buttons" style="text-align: center;margin-top:400px">
      <a-button-group>
        <a-button type="primary" @click="submitJob">
          提交
        </a-button>
      </a-button-group>
    </div>
  </a-layout-content>
</template>
<script>
import reqwest from 'reqwest'
let OPTIONS = ['Apples', 'Nails', 'Bananas', 'Helicopters']
export default {
  data() {
    return {
      selectedItems: {}
    }
  },
  computed: {
    filteredOptions() {
      return OPTIONS.filter(o => this.selectedItems.listId !== o.listId)
    }
  },
  mounted() {
    this.initModalData()
  },
  methods: {
    handleChange(selectedItems) {
      const selectObj = OPTIONS.filter(o => o.listId === selectedItems)
      this.selectedItems = selectObj[0]
    },
    initModalData() {
      reqwest({
        url: process.env.VUE_APP_URL + '/batch/repo/actjobs',
        method: 'get',
        type: 'json'
      }).then(data => {
        OPTIONS = data
      })
    },
    submitJob() {
      if (!this.selectedItems.listId) {
        this.$message.success('Please select job.')
        return
      }

      reqwest({
        url: process.env.VUE_APP_URL + '/batch/opr/submit',
        method: 'get',
        data: {
          jobId: this.selectedItems.listId
        },
        type: 'json'
      }).then(data => {
        this.$message.success('Submit job success.')
      })
    }
  }
}
</script>
