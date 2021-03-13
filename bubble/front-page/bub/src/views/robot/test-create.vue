<template>
  <!--
      初始化基本设定, key id, 支持加载状态，无边框，格式为中等大小, 提供了默认的Table (分页、排序、筛选变化时触发)事件处理监听
      具体情况根据实际情况设定
  -->
  <a-table
    :row-key="record => record.listId"
    :columns="columns"
    :data-source="data"
    :loading="loading"
    :bordered="true"
    size="middle"
    @change="handleTableChange"
  />
</template>
<script>
/**
 * 导入API基础接口封装方法
 */
import {
  searchData
} from '@/api/base'
// 根据配置生成
const columns = [{ 'dataIndex': 'name', 'title': '名称' }, { 'dataIndex': 'descibe', 'title': '描述' }, { 'dataIndex': 'status', 'title': '状态' }]

export default {
  data() {
    // table 配置
    // 使用可以参考文档 https://www.antdv.com/components/table-cn/
    return {
      data: [],
      columns,
      loading: false
    }
  },
  // 生命周期-组件挂载之后
  // 使用可以参考文档 https://cn.vuejs.org/v2/api/#mounted
  mounted() {
    this.fetch()
  },
  methods: {
    fetch(params = {}) {
      console.log('params:', params)
      /**
       * 只能使用箭头函数, 箭头函数不会修改this指向
       * @rs object {config, data, headers, request, status, statusText}
       */
      searchData('group').then((rs) => {
        this.loading = false
        this.data = rs.data.data
      })
    },

    /**
     * 提供table自身事件监听处理
     *
     * @param pagination
     * @param filters
     * @param sorter
     */
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination)
      const pager = { ...this.pagination }
      pager.current = pagination.current
      this.pagination = pager
      this.fetch({
        results: pagination.pageSize,
        page: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters
      })
    }
  }

}
</script>
