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
      <#assign x=0>
      <#if formConfig.layout == "vertical">
      <a-row :gutter="[${formConfig.gutter}]">
      </#if>
  	  <#list group.fields as field>
  	  <#if formConfig.layout == "vertical">
  	    <a-col :span="${24 / formConfig.feildNum * field.feildSize}">
  	  </#if>  
	  	  <a-form-model-item label="${field.label!'label'}">
	  	    <a-input v-model="form.${field.name!''}" required="${field.required?string("true","false")}" />
	  	  </a-form-model-item>
	  <#if formConfig.layout == "vertical">	  
  	  	</a-col>
  	  </#if>
  	  <#if formConfig.layout == "vertical">	
 	  <#if field_has_next>
 	  <#assign x = x + field.feildSize>
	  <#if (x >= formConfig.feildNum)>
	  </a-row>
	  <a-row :gutter="[${formConfig.gutter}]">
	  <#assign x=0>	  
	  </#if>
  	  </#if>
  	  </#if>
  	  </#list>
  	  <#if formConfig.layout == "vertical">
  	  </a-row>
  	  </#if>
  	  
  	  
  	  <#if formConfig.layout == "horizontal">
  	  <!-- 水平布局中,在面板中的按钮 -->
  	  <a-form-model-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
         <a-button type="primary" @click="onSubmit">
             Create
         </a-button>
         <a-button style="margin-left: 10px;">
             Cancel
         </a-button>
      </a-form-model-item>
  	  </#if>
  	  
  	  <#if formConfig.layout == "inline">
  	  <a-form-model-item>
         <a-button type="primary" @click="onSubmit">
             Create
         </a-button>
         <a-button style="margin-left: 10px;">
             Cancel
         </a-button>
      </a-form-model-item>
  	  </#if>
      </a-collapse-panel>
  </#list>
  <#if hasGroup>
  </a-collapse>
  </#if>
  
  
<!-- 独立字段生成 -->
  	<#assign x=0>
    <#if formConfig.layout == "vertical">
    <a-row :gutter="[${formConfig.gutter}]">
    </#if>
	<#list formConfig.fields as field>
	<#if formConfig.layout == "vertical">
	<a-col :span="${24 / formConfig.feildNum * field.feildSize}">
	</#if>  
 	<a-form-model-item label="${field.label!'label'}">
 	  <a-input v-model="form.${field.name!''}" required="${field.required?string("true","false")}" />
 	</a-form-model-item>
 	<#if formConfig.layout == "vertical">	  
	</a-col>
	</#if>
	<#if formConfig.layout == "vertical">	
	<#if field_has_next>
	<#assign x = x + field.feildSize>
	<#if (x >= formConfig.feildNum)>
	</a-row>
	<a-row :gutter="[${formConfig.gutter}]">
	<#assign x=0>	  
	</#if>
	</#if>
	</#if>
	</#list>
	<#if formConfig.layout == "vertical">
	</a-row>
  	</#if>
  
  
   <!-- form button -->
  <#if formConfig.layout == "horizontal" && !hasGroup>
  <a-form-model-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
    <a-space size="50">
       <a-button type="primary" @click="onSubmit">
         Create
       </a-button>
       <a-button style="margin-left: 10px;">
         Cancel
       </a-button>
     </a-space>
   </a-form-model-item>
  <#elseif formConfig.layout == "vertical">
  <!-- 位置放在最下面 -->
  <a-form-model-item style="text-align: center;margin-top: 15px">
  	<a-space size="50">
       <a-button type="primary" @click="onSubmit">
         Create
       </a-button>
       <a-button style="margin-left: 10px;">
         Cancel
       </a-button>
     </a-space>
   </a-form-model-item>
  <#elseif formConfig.layout == "inline" && !hasGroup>
  <a-form-model-item>
  	<a-space size="50">
       <a-button type="primary" @click="onSubmit">
         Create
       </a-button>
       <a-button style="margin-left: 10px;">
         Cancel
       </a-button>
     </a-space>
   </a-form-model-item>
  </#if>
  </a-form-model>
</template>
<script>

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
    }
  }
}
</script>
</#compress>