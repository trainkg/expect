package com.barley.config.form.layout;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.barley.config.form.FormRenderer;
import com.barley.config.form.LayoutStrategy;
import com.barley.config.form.define.BaseForm;
import com.barley.config.form.define.BaseForm.Filed;
import com.barley.config.form.define.BaseForm.Section;
import com.barley.config.service.FormService;

import lombok.Getter;

/**
 * 简单的form 渲染器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleFormRenderer.java, V1.0.0 2020年5月22日 下午9:24:51 $
 */
public class SimpleFormRenderer implements FormRenderer<SimpleRenderConfig> {

	public static final LayoutStrategy DEFAULT_LAYOUT = new SimpleLayoutStrategy();
	private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	private LayoutStrategy strategy;

	@Getter
	private String formKey;

	public LayoutStrategy getStrategy() {
		if (strategy == null) {
			return DEFAULT_LAYOUT;
		}
		return strategy;
	}

	@Override
	public boolean supportStrategy(LayoutStrategy strategy) {
		if (strategy instanceof SimpleLayoutStrategy) {
			return true;
		}
		return false;
	}

	public SimpleFormRenderer(String formKey, FormService formService) {
		this.formKey = formKey;
	}

	@Override
	public SimpleRenderConfig renderTemplete() {
		return renderInternal();
	}

	private SimpleRenderConfig renderInternal() {
		if (!supportStrategy(getStrategy())) {
			throw new RuntimeException("Not support current strategy " + getStrategy().getClass());
		}
		SimpleRenderConfig config = new SimpleRenderConfig();
		BaseForm form = loadingFromDefine();
		form.setFromKey("tes1111");
		form.setName("test name");

		List<Section> sectionList = new ArrayList<Section>();
		sectionList.add(form.new Section("1", "基本信息"));
		sectionList.add(form.new Section("2", "申请信息"));
		sectionList.add(form.new Section("3", "第三章节"));
		sectionList.add(form.new Section("4", "第四章节"));

		List<Filed> feildList = new ArrayList<Filed>();
		feildList.add(form.new Filed("1", "名称", "name", "INPUT"));
		feildList.add(form.new Filed("1", "年龄", "age", "NUMBER"));
		feildList.add(form.new Filed("1", "分类", "categrey", "RADIO"));
		feildList.add(form.new Filed("1", "地址", "address", "INPUT"));
		feildList.add(form.new Filed("1", "地址", "address", "INPUT"));
		feildList.add(form.new Filed("1", "地址", "address", "INPUT"));
		feildList.add(form.new Filed("1", "地址", "address", "INPUT"));

		sectionList.get(0).setFileds(feildList);

		List<Filed> feildList1 = new ArrayList<Filed>();
		feildList1.add(form.new Filed("3", "申请人", "name", "INPUT"));
		feildList1.add(form.new Filed("4", "申请理由", "address", "INPUT"));
		sectionList.get(1).setFileds(feildList1);

		List<Filed> feildList2 = new ArrayList<Filed>();
		feildList2.add(form.new Filed("3", "申请人", "name", "INPUT"));
		feildList2.add(form.new Filed("4", "申请理由", "address", "INPUT"));
		sectionList.get(2).setFileds(feildList2);

		List<Filed> feildList3 = new ArrayList<Filed>();
		feildList3.add(form.new Filed("3", "申请人", "name", "INPUT"));
		feildList3.add(form.new Filed("4", "申请理由", "address", "INPUT"));
		sectionList.get(3).setFileds(feildList3);

		form.setSections(sectionList);
		config.setForm(form);

		config.setLayoutStrategy(getStrategy());
		logger.info("use strategy {}", getStrategy().getClass().getSimpleName());
		return config;
	}

	private BaseForm loadingFromDefine() {
		return new BaseForm();
	}
}
