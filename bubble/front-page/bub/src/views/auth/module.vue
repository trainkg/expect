<template>
  <div>
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>配置中心</a-breadcrumb-item>
      <a-breadcrumb-item>菜单管理</a-breadcrumb-item>
    </a-breadcrumb>
    <a-layout-content class="ant-layout-content-ext">
      <div>
        <a-row>
          <a-col :span="24" style="text-align: right">
            <a-button-group size="small">
              <a-button size="small" type="primary" icon="plus-circle">
                创建
              </a-button>
            </a-button-group>
          </a-col>
          <a-col :span="24" >
            <moduleList></moduleList>
          </a-col>
        </a-row>
      </div>
    </a-layout-content>
  </div>
</template>

<script>
import moduleList from './module/module-datagride'
export default {
  components: {
    moduleList
  }
}

</script>
