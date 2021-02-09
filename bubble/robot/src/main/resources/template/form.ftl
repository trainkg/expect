<#include "./form/form-feild.ftl"/>
<#compress>
<!--form 表单配置参考 https://www.antdv.com/components/form-model-cn/ -->
<template>
  <#if formConfig.layout == "horizontal">
  <a-form-model :model="form"  :label-col="labelCol" :wrapper-col="wrapperCol" layout="horizontal">
  <#elseif formConfig.layout == "vertical">
  <a-form-model :model="form" layout="vertical">
  <#else>
  <a-form-model :model="form" layout="inline">
  </#if>
  <#if hasGroup>
  <a-collapse v-model="activeKey" expandIconPosition="left">
  </#if>
  <!-- 章节列表 -->
  <#list formConfig.groups as group>
      <a-collapse-panel key="${group.key!0}" header="${group.header!'header'}" >
      <a-icon slot="extra" type="setting" />
      <@mfield formConfig=formConfig fields=group.fields/>
  	  
  	  <#if formConfig.layout == "horizontal">
  	  <!-- 水平布局中,在面板中的按钮 -->
  	  <a-form-model-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
         <#include "./form/form-button.ftl"/>
      </a-form-model-item>
  	  </#if>
  	  
  	  <#if formConfig.layout == "inline">
  	  <a-form-model-item>
         <#include "./form/form-button.ftl"/>
      </a-form-model-item>
  	  </#if>
      </a-collapse-panel>
  </#list>
  <#if hasGroup>
  </a-collapse>
  </#if>
  
  
  <!-- 独立字段生成 -->
  <#if formConfig.fields?size != 0>
  <@mfield formConfig=formConfig fields=formConfig.fields/>
  </#if>
  
   <!-- form button -->
  <#if formConfig.layout == "horizontal" && !hasGroup>
  <a-form-model-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
    <#include "./form/form-button.ftl"/>
   </a-form-model-item>
  <#elseif formConfig.layout == "vertical">
  <!-- 位置放在最下面 -->
  <a-form-model-item style="text-align: center;margin-top: 15px">
  	<#include "./form/form-button.ftl"/>
   </a-form-model-item>
  <#elseif formConfig.layout == "inline" && !hasGroup>
  <a-form-model-item>
  	<#include "./form/form-button.ftl"/>
   </a-form-model-item>
  </#if>
  </a-form-model>
</template>
<script>


import router from '@/router/index'
import {createData} from '@/api/base'
export default {
  data() {
    return {
      <#if hasGroup>
      activeKey: [${activeKey}],
      </#if>
      <#if formConfig.layout == "horizontal">
      labelCol: { span: ${formConfig.feildNum}},
      wrapperCol: { span: ${formConfig.wrapperCol}},
      </#if>
      form: ${editFeilds}
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!', this.form)
      createData('${beanName}', this.form).then((rs) => {
        // 提示消息
        this.$message.info('提交成功')
        // 页面跳转, 根据实际调整
        router.push('index')
      })
    }
  }
}
</script>
</#compress>