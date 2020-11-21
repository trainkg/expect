<template>
  <a-layout-content class="ant-layout-content-ext">
    <a-table
      :columns="columns"
      :row-key="record => record.listId"
      :data-source="data"
      :pagination="pagination"
      :loading="loading"
      bordered
      @change="handleTableChange"
    >
      <template v-slot:type="text, record, index">
        <span v-if="record.jobParameters.parameters.BATCH_SUBMIT_TYPE">
          {{ record.jobParameters.parameters.BATCH_SUBMIT_TYPE.value }}
        </span>
        <span v-else>
          BATCH
        </span>
      </template>

      <span slot="action" slot-scope="text,record">
        <a-popconfirm title="确认停止选中的JOB?" @confirm="() => onDelete(record.id)">
          <a href="javascript:;">STOP</a>
        </a-popconfirm>
      </span>
    </a-table>
  </a-layout-content>
</template>
<script>
import reqwest from 'reqwest'
const columns = [
  {
    title: 'Job 执行编号',
    dataIndex: 'id',
    sorter: false,
    width: '10%'
  },
  {
    title: 'Job Type',
    dataIndex: 'type',
    sorter: false,
    width: '10%',
    scopedSlots: { customRender: 'type' }
  },
  {
    title: 'Job 名称',
    dataIndex: 'jobInstance.jobName',
    width: '20%'
  },
  {
    title: '启动时间',
    dataIndex: 'startTime',
    sorter: false,
    width: '10%'

  },
  {
    title: '当前状态',
    dataIndex: 'status',
    sorter: false,
    width: '10%'
  },
  {
    title: '退出状态',
    dataIndex: 'exitStatus.exitCode',
    sorter: false,
    width: '10%'
  },
  {
    title: '操作人员',
    dataIndex: 'operator',
    sorter: false,
    width: '10%'
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  data() {
    return {
      data: [],
      pagination: { pageSize: 20, totalCount: 0 },
      loading: false,
      columns
    }
  },
  mounted() {
    this.fetch()
  },
  methods: {
    onDelete(executionId) {
      const $this = this
      reqwest({
        url: process.env.VUE_APP_URL + '/batch/opr/stop/' + executionId,
        method: 'get',
        type: 'json'
      }).then(data => {
        // 成功通知刷新
        if (data.status === 1) {
          $this.$message.success('Stop success!')
          $this.fetch()
        } else {
          // 通知信息提示
          $this.$message.error('Stop batch job ' + executionId + ' failed.')
        }
      }).fail(function() {
        $this.$message.error('Stop batch job ' + executionId + ' failed.')
      })
    },
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination }
      pager.current = pagination.current
      this.pagination = pager
      this.fetch({
        pageSize: pagination.pageSize,
        page: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters
      })
    },
    fetch(params = {}) {
      console.log('params:', params)
      this.loading = true
      reqwest({
        url: process.env.VUE_APP_URL + '/batch/run',
        method: 'get',
        data: {
          ...params
        },
        type: 'json'
      }).then(data => {
        const pagination = { ...this.pagination }
        // Read total count from server
        pagination.total = data.totalCount
        this.loading = false
        this.data = data.results
        this.pagination = pagination
      })
    }
  }
}
</script>
