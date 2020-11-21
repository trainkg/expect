package com.barley.batch.query;

import lombok.Getter;

/**
 * 相应数据
 * 
 * @author peculiar.1@163.com
 * @version $ID: Response.java, V1.0.0 2020年11月20日 上午11:56:23 $
 */
public class GridResponse<T> extends Response<T> {

	@Getter
	private Integer totalCount;

	public GridResponse<T> setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		return this;
	}

}
