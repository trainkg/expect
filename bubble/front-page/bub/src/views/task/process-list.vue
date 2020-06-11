<template>
  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="流程定义"
      sub-title="管理系统中的流程定义"
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
          <template v-slot:action="text, record">
            <div class="editable-cell">
              <router-link :to="{name:'process-start',query:{id:record.id}}">
                <a>流程发起</a>
              </router-link>
            </div>
          </template>

          <template v-slot:suspended="text, record">
            <div class="editable-cell">
              <a v-if="text">挂起</a>
              <a v-else>激活</a>
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
    title: '编号',
    dataIndex: 'id',
    sorter: false,
    width: '10%'
  },
  {
    title: '名称',
    dataIndex: 'name',
    sorter: false
  },
  {
    title: '分类',
    dataIndex: 'category',
    sorter: false
  },
  {
    title: '当前状态',
    dataIndex: 'suspended',
    scopedSlots: { customRender: 'suspended' },
    width: '10%'
  },
  {
    title: '版本',
    dataIndex: 'version',
    width: '10%'
  },
  {
    title: '操作',
    width: '20%',
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
        url: process.env.VUE_APP_URL + '/repository/process-definitions',
        method: 'get',
        data: {
          ...params
        },
        type: 'json'
      }).then(data => {
        const pagination = { ...this.pagination }
        // Read total count from server
        pagination.total = data.total
        this.loading = false
        this.data = data.data
        this.pagination = pagination
      })
    }
  }
}
</script>
