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
        <a-popconfirm title="确认停止选中的JOB?" @confirm="() => onDelete(record.jobDetail.group,record.jobDetail.name)">
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
    title: 'Job 组',
    dataIndex: 'jobDetail.group',
    sorter: false,
    width: '10%'
  },
  {
    title: 'Job 名称',
    dataIndex: 'jobDetail.name',
    width: '20%'
  },
  {
    title: 'Job 描述',
    dataIndex: 'jobDetail.description',
    width: '20%'
  },
  {
    title: 'Job CLASS',
    dataIndex: 'jobDetail.jobClass',
    width: '20%'
  },
  {
    title: '上次执行时间',
    dataIndex: 'trigger.previousFireTime',
    sorter: false,
    width: '10%'

  },
  {
    title: '下次执行时间',
    dataIndex: 'trigger.nextFireTime',
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
    onDelete(group, jobName) {
      const $this = this
      reqwest({
        url: process.env.VUE_APP_URL + '/batch/timing/del/',
        method: 'post',
        type: 'json',
        data: {
          'group': group,
          'jobName': jobName
        }
      }).then(data => {
        // 成功通知刷新
        if (data.status === 1) {
          $this.$message.success('Stop success!')
          $this.fetch()
        } else {
          // 通知信息提示
          $this.$message.error('Stop batch job ' + jobName + ' failed.')
        }
      }).fail(function() {
        $this.$message.error('Stop batch job ' + jobName + ' failed.')
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
        url: process.env.VUE_APP_URL + '/batch/qtz/run',
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
