package com.barley.robot.modal.frontpage.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.barley.web.utils.JsonMapper;

import com.barley.robot.modal.frontpage.form.FormConfig.Field;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * form 配置
 * 
 * JAXB add annotation at get method.
 * 
 * @author peculiar.1@163.com
 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:02:30 $
 */
public final class FormConfigWapper {

	@Getter
	@Setter
	private FormConfig formConfig;
	
	/**
	 * 	JAVA 模型名称（用于controller路径中）
	 */
	@Setter
	@Getter
	private String beanName;

	public FormConfigWapper(FormConfig formConfig) {
		this.formConfig = formConfig;
	}

	/**
	 * 
	 * 是否要分组显示
	 * 
	 * @return
	 */
	public boolean isHasGroup() {
		return formConfig.getGroups().size() > 0;
	}


	/**
	 * 获取可编辑列表
	 * 
	 * @return
	 */
	public String getEditFeilds() {
		Map<String, Object> feilds = new HashMap<String, Object>();
		extractFeilds(feilds, formConfig.getFields());
		formConfig.getGroups().forEach(group -> {
			extractFeilds(feilds, group.getFields());
		});
		JsonMapper jsonMapper = new JsonMapper(Include.ALWAYS);
		return jsonMapper.toJson(feilds);
	}

	private void extractFeilds(Map<String, Object> feildsMap, List<Field> fileds) {
		if (fileds == null)
			return;
		for (Field feild : fileds) {
			feildsMap.put(feild.getName(), feild.getDefaultValue());
		}
	}

	/**
	 * 
	 * 获取激活的栏位
	 * 
	 * @return
	 */
	public String getActiveKey() {
		List<String> keys = new ArrayList<String>();

		if (formConfig.getGroups() != null) {
			formConfig.getGroups().forEach(group -> {
				if (group.isActive()) {
					keys.add(group.getKey());
				}
			});
		}

		if (keys.isEmpty()) {
			return "";
		}
		return StringUtils.join(keys, ',');
	}

	/**
	 * 如果有分组，我们将合并进入的域默认加入到第一个章节中
	 * 
	 * @param feild
	 */
	public void addField(Field feild) {
		if (isHasGroup()) {
			formConfig.getGroups().get(0).getFields().add(feild);
		} else {
			formConfig.getFields().add(feild);
		}
	}

}
