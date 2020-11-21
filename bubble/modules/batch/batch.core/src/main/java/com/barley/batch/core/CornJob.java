package com.barley.batch.core;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * 
   系统batch定义 , 在总体分类上面，batch分为定时任务和day end batch任务
 * 
 * @author peculiar.1@163.com
 * @version $ID: CornJob.java, V1.0.0 2020年11月15日 下午5:05:41 $
 */
public class CornJob implements Job, NodeData<Long>, Serializable {

	
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long listId;

	@Getter
	@Setter
	private Long parentId;

	@Setter
	private Boolean jobNet;

	@Setter
	@Getter
	private String jobName;

	@Getter
	@Setter
	private String jobFreq;
	
	
	/**
	  如果是目录，忽略Jobstatus设定
	 */
	@Getter
	@Setter
	private JobStatus jobStatus;

	@Setter
	private boolean leaf;

	@Getter
	@Setter
	private String jobClass;

	/**
	 * 是否是day end batch
	 * 
	 * 关于dayend batch将拥有特殊的支持，比如依赖关系等定义，是参与当日晚上的执行，执行计划优先级等
	 */
	@Getter
	@Setter
	private boolean dayEnd;

	@Getter
	@Setter
	private List<JobParam> jobParams;

	public CornJob() {
	}

	public CornJob(String jobName, String cornExpression) {
		this.jobName = jobName;
		this.jobFreq = jobName;
	}

	@Override
	public String jobName() {
		return jobName;
	}

	@Override
	public Long getNodeId() {
		return getListId();
	}

	@JsonIgnore
	@Override
	public Long getParentNodeId() {
		return getParentId();
	}

	@Override
	public boolean isLeaf() {
		return this.leaf;
	}

	public Boolean isJobNet() {
		return jobNet != null ? jobNet : Boolean.TRUE;
	}

}
