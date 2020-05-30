<template>
  <div>
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>人员管理</a-breadcrumb-item>
      <a-breadcrumb-item>人员类别</a-breadcrumb-item>
    </a-breadcrumb>

    <b-model title="用户类型列表">
      <div>
        <a-button class="editable-add-btn" @click="handleAdd">
          添加
        </a-button>
      </div>
      <a-table
        :columns="columns"
        :row-key="record => record.listId"
        :data-source="data"
        :pagination="pagination"
        :loading="loading"
        :bordered="false"
        size="middle"
        @change="handleTableChange"
      >
        <template
          v-for="col in ['typeName', 'typeCode','typeCode']"
          :slot="col"
          slot-scope="text, record, index"
        >
          <div :key="col">
            <a-input
              v-if="record.editable"
              style="margin: -5px 0"
              :value="text"
              @change="e => handleChange(e.target.value, record.key, col)"
            />
            <template v-else>
              {{ text }}
            </template>
          </div>
        </template>

        <template slot="operation" slot-scope="text, record, index">
          <div class="editable-row-operations">
            <span v-if="record.editable">
              <a @click="() => save(record.key)">保存</a>
              <a-popconfirm title="Sure to cancel?" @confirm="() => cancel(record.key)">
                <a>取消</a>
              </a-popconfirm>
            </span>
            <span v-else>
              <a :disabled="editingKey !== ''" @click="() => edit(record.key)">编辑</a>
            </span>
          </div>
        </template>
      </a-table>
    </b-model>
  </div>
</template>
<script>
import reqwest from 'reqwest'
const columns = [
  {
    title: '名称',
    dataIndex: 'typeName',
    sorter: false,
    width: '20%',
    scopedSlots: { customRender: 'typeName' }
  },

  {
    title: '编号',
    dataIndex: 'typeCode',
    width: '20%',
    scopedSlots: { customRender: 'typeCode' }
  },
  {
    title: '状态',
    dataIndex: 'typeStatus',
    width: '20%',
    editable: false,
    scopedSlots: { customRender: 'typeStatus' }
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'operation' }
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
      columns,
      editingKey: ''
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
    edit(key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      this.editingKey = key
      if (target) {
        target.editable = true
        this.data = newData
      }
    },
    handleChange(value, key, column) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.data = newData
      }
    },
    save(key) {
      const newData = [...this.data]
      const newCacheData = [...this.cacheData]
      const target = newData.filter(item => key === item.key)[0]
      const targetCache = newCacheData.filter(item => key === item.key)[0]

      reqwest({
        url: process.env.VUE_APP_URL + '/usertype/inactive',
        method: 'post',
        data: target,
        type: 'json'
      }).then(data => {
        if (target && targetCache) {
          delete target.editable
          this.data = newData
          Object.assign(targetCache, target)
          this.cacheData = newCacheData
        }
        this.editingKey = ''
      })
    },
    handleAdd() {
      const { count, dataSource } = this
      const newData = {
        key: count,
        name: `Edward King ${count}`,
        age: 32,
        address: `London, Park Lane no. ${count}`
      }
      this.dataSource = [...dataSource, newData]
      this.count = count + 1
    },
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination }
      pager.current = pagination.current
      this.pagination = pager
    },
    cancel(key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      this.editingKey = ''
      if (target) {
        Object.assign(target, this.cacheData.filter(item => key === item.key)[0])
        delete target.editable
        this.data = newData
      }
    },
    fetch(params = {}) {
      this.loading = true
      reqwest({
        url: process.env.VUE_APP_URL + '/usertype/list',
        method: 'get',
        data: {
          ...params
        },
        type: 'json'
      }).then(data => {
        const pagination = { ...this.pagination }
        // Read total count from server
        this.loading = false
        this.data = data
        this.cacheData = data.map(item => ({ ...item }))
        this.pagination = pagination
      })
    }
  }
}
</script>
<style>
  .editable-row-operations a {
    margin-right: 8px;
  }
</style>
