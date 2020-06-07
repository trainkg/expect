<template>
  <b-model title="表单元素 - 表单细项">
    <template v-slot:header="props">
      {{ props.title }}
    </template>
    <a-button :disabled="editable" @click="addNew">
      新增
    </a-button>
    <a-table
      :columns="columns"
      :row-key="record => record.id"
      :data-source="dataSource"
      :loading="false"
      :bordered="false"
      size="middle"
    >
      <template v-slot:label="text, record">
        <div class="editable-cell">
          <div v-if="record.editable" class="editable-cell-input-wrapper">
            <a-input :value="text" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
          </div>
        </div>
      </template>

      <template v-slot:fieldType="text, record">
        <div class="editable-cell">
          <div v-if="record.editable" class="editable-cell-input-wrapper">
            <a-select default-value="1">
              <a-select-option value="1">
                  Input 输入框
              </a-select-option>
                <a-select-option value="2">
                    自动完成
                </a-select-option>
                <a-select-option value="3">
                    Cascader 级联选择
                </a-select-option>
                <a-select-option value="4">
                    Checkbox多选框
                </a-select-option>
                <a-select-option value="5">
                    DatePicker 日期选择框
                </a-select-option>
                <a-select-option value="6">
                    DatePicker 日期选择框
                </a-select-option>
                <a-select-option value="6">
                    数字输入框
                </a-select-option>
                <a-select-option value="6">
                    Mentions提及
                </a-select-option>
                <a-select-option value="6">
                    单选框
                </a-select-option>
                <a-select-option value="6">
                    Rate - 评分组件
                </a-select-option>
                <a-select-option value="6">
                Select 选择器
            </a-select-option>
                <a-select-option value="6">
                    Slider 滑动输入条
                </a-select-option>
                <a-select-option value="6">
                    Switch 开关
                </a-select-option>
                <a-select-option value="6">
                    TimePicker 时间选择框
                </a-select-option>
                <a-select-option value="6">
                    穿梭框
                </a-select-option>
                <a-select-option value="6">
                    树型选择控件
                </a-select-option>
                <a-select-option value="6">
                    上传控件
                </a-select-option>
            </a-select>
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
          </div>
        </div>
      </template>

      <template v-slot:name="text, record">
        <div class="editable-cell">
          <div v-if="record.editable" class="editable-cell-input-wrapper">
            <a-input :value="text" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
          </div>
        </div>
      </template>

      <template slot="operation" slot-scope="text, record">
        <div class="editable-cell">
          <div v-if="record.editable" class="editable-cell-input-wrapper">
            <a href="javascript:;" @click="saveRecord(record.key)">保存</a>
          </div>
          <div v-else class="editable-cell-text-wrapper">
            <a href="javascript:;" :disabled="editable" @click="editRecord(record.key)">编辑</a>
            <a-popconfirm
              v-if="dataSource.length"
              title="确认删除?"
              @confirm="() => onDelete(record.key)"
            >
              <a href="javascript:;" :disabled="editable">删除</a>
            </a-popconfirm>
          </div>
        </div>
      </template>
    </a-table>
  </b-model>
</template>
<script>

const columns = [
  {
    title: '名称',
    dataIndex: 'label',
    sorter: false,
    width: '20%',
    scopedSlots: { customRender: 'label' }
  },
  {
    title: '表单名称',
    dataIndex: 'field_name',
    width: '20%',
    scopedSlots: { customRender: 'name' }
  },
  {
    title: '字段类型',
    dataIndex: 'field_type',
    width: '20%',
    scopedSlots: { customRender: 'fieldType' }
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'operation' }
  }
]

export default {
  props: ['data'],
  data() {
    this.cacheData = []
    return {
      columns,
      editable: false,
      dataSource: [],
      count: 0
    }
  },
  methods: {
    addNew() {
      const { count, dataSource } = this
      const newData = {
        key: count,
        editable: true,
        label: '',
        field_name: ''
      }
      this.dataSource = [...dataSource, newData]
      this.count = count + 1
      this.editable = true
    },
    onDelete(key) {
      const dataSource = [...this.dataSource]
      this.dataSource = dataSource.filter(item => item.key !== key)
    },
    saveRecord(key) {
      const cacheData = [...this.cacheData]
      this.cacheData = cacheData.filter(item => item.key !== key)
      const dataSource = [...this.dataSource]
      const target = dataSource.filter(item => item.key === key)[0]
      if (target) {
        target.editable = false
        this.editable = false
        this.dataSource = dataSource
      }
    },
    editRecord(key) {
      const cacheData = [...this.cacheData]
      const dataSource = [...this.dataSource]
      const editRecord = this.dataSource.filter(item => item.key === key)[0]
      if (editRecord) {
        this.cacheData = cacheData.filter(item => item.key !== key)
        this.cacheData = [...this.cacheData, editRecord]
        this.editable = true
        editRecord.editable = true
        this.dataSource = dataSource
      }
    }
  }
}
</script>
