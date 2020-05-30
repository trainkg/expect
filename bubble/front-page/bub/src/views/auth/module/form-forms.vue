<template>
  <div>
    <b-model title="查询条件">
      <div>
        <FormSearch @form-search="search" />
      </div>
    </b-model>
    <b-model title="查询结果">
      <a-table
        :columns="columns"
        :row-key="record => record.listId"
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
import FormSearch from './form-forms/form-search'
const columns = [
  {
    title: '名称',
    dataIndex: 'modName',
    sorter: false,
    width: '20%'
  },
  {
    title: '描述',
    dataIndex: 'uri',
    width: '20%',
    scopedSlots: { customRender: 'name' }
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'action' }
  }
]

export default {
  components: {
    FormSearch
  },
  data() {
    return {
      data: [],
      pagination: { pageSize: 1, current: 1 },
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
      console.log('params:', params)
      this.loading = true
      reqwest({
        url: process.env.VUE_APP_URL + '/config/list',
        method: 'get',
        data: {
          ...params
        },
        type: 'json'
      }).then(data => {
        console.log('this.pagination1 >', this.pagination)
        const pagination = { ...this.pagination }
        // Read total count from server
        pagination.total = data.totalCount
        this.loading = false
        this.data = data.results
        this.pagination = pagination
        console.log('this.pagination >', this.pagination)
      })
    }
  }
}
</script>
