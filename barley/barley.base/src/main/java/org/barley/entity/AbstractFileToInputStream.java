package org.barley.entity;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractFileToInputStream {
	/**
	 * 模板输入流
	 */
	protected InputStream inputStream;

	protected String name;

	/**
	 * 构造方法
	 * 
	 * @param clazz    参照资源对象
	 * @param filePath 文件路径,相对于@param clazz的相对路径(目录+文件名)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractFileToInputStream(Class clazz, String filePath) {
		inputStream = clazz.getResourceAsStream(filePath);
	}

	public AbstractFileToInputStream() {
		super();
	}

	public AbstractFileToInputStream(String name) {
		super();
		this.name = name;
	}

	/**
     * 获取文件流对象
     * 
     * @param request
     * @param filePath 文件路径(目录+文件名)
     * @throws Exception
     */
    public abstract InputStream getFileInputSteam(HttpServletRequest request, String filePath) throws Exception;

	/**
	 * @return 返回 inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream 设置 inputStream
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
