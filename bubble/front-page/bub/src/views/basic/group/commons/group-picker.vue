<!-- search panel -->
<template>
  <div>
    <a-modal v-model="visible" title="选择器" ok-text="确认" cancel-text="取消" @ok="onSubmit">
      <a-collapse v-model="activeKey" expand-icon-position="left">
        <!-- search panel -->
        <a-collapse-panel key="search" header="查询条件">
          <a-row :gutter="[16,8]">
            <a-col :span="6">
              <a-form-model-item label="名称">
                <a-input v-model="form.name" required="false" />
              </a-form-model-item>
            </a-col>
            <a-col :span="6">
              <a-form-model-item label="描述">
                <a-input v-model="form.descibe" required="false" />
              </a-form-model-item>
            </a-col>
            <a-col :span="6">
              <a-form-model-item label="状态">
                <a-input v-model="form.status" required="false" />
              </a-form-model-item>
            </a-col>
          </a-row>
          <a-row style="text-align: right">
            <a-button-group>
              <a-button type="primary" @click="fetch">
                查询
              </a-button>
              <a-button @click="reset">
                重置
              </a-button>
            </a-button-group>
          </a-row>
        </a-collapse-panel>
        <!-- result panel -->
        <a-collapse-panel key="result" header="结果列表">
          <a-icon slot="extra" type="setting" />
          <a-table
            :row-key="record => record.listId"
            :columns="columns"
            :data-source="data"
            :loading="loading"
            :bordered="true"
            size="middle"
            :scroll="{ y: 240 }"
            :row-selection="rowSelection"
            :pagination="pagination"
            @change="handleTableChange"
          />
        </a-collapse-panel>
      </a-collapse>
    </a-modal>
    <a-button type="primary" @click="openPicker">
      打开picker
    </a-button>
  </div>
</template>

<script>
/**
 * 导入API基础接口封装方法
 */
import { pageSearchData } from '@/api/base'
const columns = [{ 'dataIndex': 'name', 'title': '名称' }, { 'dataIndex': 'descibe', 'title': '描述' }, { 'dataIndex': 'status', 'title': '状态' }]
const _initForm = { 'descibe': null, 'name': null, 'status': null }

export default {
  props: {
    multiple: {
      type: Boolean, default: false
    },
    pageSize: {
      type: Number,
      default: 10
    }
  },
  data() {
    return {
      visible: false,
      activeKey: ['search', 'result'],
      data: [],
      columns,
      loading: false,
      // 选中key, 可以进行初始化选中
      selectedRowKeys: [],
      // 选中行信息
      selectedRows: [],
      // 分页设定
      pagination: {
        pageSize: this.pageSize, current: 1
      },
      form: { 'descibe': null, 'name': null, 'status': null }
    }
  },
  computed: {
    rowSelection() {
      return {
        selectedRowKeys: this.selectedRowKeys,
        onChange: this.onSelectChange,
        type: this.multiple ? 'checkbox' : 'radio'
      }
    }
  },
  mounted() {
    this.fetch()
  },
  methods: {
    /**
     ** 只能使用箭头函数, 箭头函数不会修改this指向
     * @rs object {config, data, headers, request, status, statusText}
     */
    fetch(params = {}) {
      const pageParam = {
        pageSize: this.pagination.pageSize,
        page: this.pagination.current
      }
      pageSearchData('group', pageParam, this.form).then(rs => {
        const pagination = { ...this.pagination }
        // Read total count from server
        pagination.total = rs.data.total
        this.loading = false
        this.data = rs.data.list
        this.pagination = pagination
        this.selectedRowKeys = []
        this.selectedRows = []
      })
    },
    reset() {
      this.form = { ..._initForm }
    },
    /**
     * 提供table自身事件监听处理
     *
     * @param pagination
     * @param filters
     * @param sorter
     */
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination }
      pager.current = pagination.current
      this.pagination = pager
      // table 自身筛选先不支持
      this.fetch({
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters
      })
    },
    onSelectChange(selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    onSubmit() {
      // validate
      // 选中的项
      if (this.$data.selectedRowKeys.length === 0) {
        this.$message.warn('选择项为空')
      } else {
        // 事件传播
        this.$emit('selectItems', this.$data.selectedRowKeys)
        this.visible = false
      }
    },
    openPicker() {
      this.visible = true
    }
  }
}
</script>
