<#macro mfield formConfig fields>
<#assign x=0>
<#if formConfig.layout == "vertical">
<a-row :gutter="[${formConfig.gutter}]">
</#if>
<#list fields as field>
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
</#macro>