package com.barley.batch.query;

import lombok.Getter;

/**
 * 相应数据
 * 
 * @author peculiar.1@163.com
 * @version $ID: Response.java, V1.0.0 2020年11月20日 上午11:56:23 $
 */
public class Response<T> {

	public static final int RESPONSE_CODE_SUCCESS = 1;
	public static final int RESPONSE_CODE_FAILED = 0;
	/**
	 * 请求返回状态
	 */
	@Getter
	private int status = RESPONSE_CODE_SUCCESS;

	@Getter
	private T results;

	public Response<T> result(T result) {
		this.results = result;
		return this;
	}

	public Response<T> status(int status) {
		this.status = status;
		return this;
	}
}
