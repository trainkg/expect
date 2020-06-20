<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="待办任务中心"
      sub-title="任务管理 - 待办任务， 日终任务"
      @back="() => null"
    />
    <a-layout-content
      class="ant-layout-content-ext"
    >
      <b-model title="查询条件">
        这个地方放查询条件
      </b-model>
      <b-model title="查询结果">
        <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="data"
          :pagination="pagination"
          :loading="loading"
          :bordered="false"
          size="middle"
          @change="handleTableChange"
        >
          <template v-slot:suspended="text, record">
            <div class="editable-cell">
              <a v-if="text">挂起</a>
              <a v-else>正常</a>
            </div>
          </template>

          <template v-slot:action="text, record">
            <div class="editable-cell">
              <a v-if="text">挂起</a>
              <a v-else>正常</a>
            </div>
          </template>
        </a-table>
      </b-model>
    </a-layout-content>
  </div>
</template>
<script>
import reqwest from 'reqwest'

const columns = [
  {
    title: '流程编号',
    dataIndex: 'id',
    sorter: false
  },
  {
    title: '流程名称',
    dataIndex: 'processDefinitionId',
    sorter: false
  },
  {
    title: '任务节点',
    dataIndex: 'name',
    sorter: false
  },
  {
    title: '受理人员',
    dataIndex: 'email'
  },
  {
    title: '委托人员',
    dataIndex: 'assignee'
  },
  {
    title: '创建时间',
    dataIndex: 'createTime'
  },
  {
    title: '处理时间',
    dataIndex: 'dueDate'
  },
  {
    title: '是否挂起',
    scopedSlots: { customRender: 'suspended' },
    dataIndex: 'suspended'
  },
  {
    title: '分类',
    dataIndex: 'category'
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  components: {
  },
  data() {
    return {
      data: [],
      pagination: { pageSize: 10, current: 1 },
      loading: false,
      filter: {},
      columns
    }
  },
  mounted() {
    this.fetch()
  },
  methods: {
    search(form) {
      this.filter = form
      this.handleTableChange(this.pagination, this.filter, {})
    },
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination)
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
      this.loading = true
      reqwest({
        url: process.env.VUE_APP_URL + '/runtime/tasks',
        method: 'get',
        data: {
          ...params
        },
        type: 'json'
      }).then(data => {
        console.log('this.pagination1 >', this.pagination)
        const pagination = { ...this.pagination }
        // Read total count from server
        pagination.total = data.total
        this.loading = false
        this.data = data.data
        this.pagination = pagination
        console.log('this.pagination >', this.pagination)
      })
    }
  }
}
</script>
