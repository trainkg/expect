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
        />
      </b-model>
    </a-layout-content>
  </div>
</template>
<script>
import reqwest from 'reqwest'

const columns = [
  {
    title: '编号',
    dataIndex: 'id',
    sorter: false,
    width: '20%'
  },
  {
    title: '名称',
    dataIndex: 'loginName',
    sorter: false,
    width: '20%'
  },

  {
    title: '邮箱',
    dataIndex: 'email',
    width: '20%'
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
  beforeCreate() {

  },
  created() {

  },
  updated() {},
  activated() {
    console.log('activated')
  },
  deactivated() {},
  beforeDestroy() {
    console.log('before destroy')
  },
  destroyed() {
    console.log('destroyed')
  },
  errorCaptured() {
    console.log('has error')
  },
  beforeUpdate() {
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
        url: process.env.VUE_APP_URL + '/user/list',
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
        this.data = data.list
        this.pagination = pagination
        console.log('this.pagination >', this.pagination)
      })
    }
  }
}
</script>
