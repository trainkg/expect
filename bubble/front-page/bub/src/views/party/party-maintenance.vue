<template>
  <div>
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>主页1</a-breadcrumb-item>
      <a-breadcrumb-item>配置中心1</a-breadcrumb-item>
      <a-breadcrumb-item>菜单管理</a-breadcrumb-item>
    </a-breadcrumb>
    <b-model title="查询条件">
      <div>
        <partySearch  @form-search="search"/>
      </div>
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
  </div>
</template>
<script>
import reqwest from 'reqwest'
import partySearch from './party-maintenance/party-search'
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
    partySearch
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
  created() {},
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
        url: process.env.VUE_APP_URL + '/sysUser/pqry',
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
