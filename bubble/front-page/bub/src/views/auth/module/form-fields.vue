<template>
  <a-table
    :columns="columns"
    :row-key="record => record.listId"
    :data-source="data"
    :pagination="pagination"
    :loading="loading"
    @change="handleTableChange"
  >
    <template v-slot:name="text, record, index">
      {{ text }} {{ index }}
    </template>

    <span slot="action" slot-scope="text,record">
      <a>Invite 一 {{ record.modName }}</a>
      <a-divider type="vertical" />
      <a>Delete</a>
      <a-divider type="vertical" />
    </span>
  </a-table>
</template>
<script>
import reqwest from 'reqwest'
const columns = [
  {
    title: '名称',
    dataIndex: 'modName',
    sorter: false,
    width: '20%'
  },
  {
    title: 'URI',
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
  name: 'fields',
  data() {
    return {
      data: [],
      pagination: { pageSize: 1, totalCount: 0 },
      loading: false,
      columns
    }
  },
  mounted() {
    this.fetch()
  },
  methods: {
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
