/*
 * 文件名称: AbstractExcelApi.java 版权所有@ 2014——2015 KISUN 时间: 2015年6月4日 上午12:12:03
 */

package org.barley.entity;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

/**
 * FileToInputStream 用于获取文件输入流的抽象类
 * 
 * <p>
 * <p>
 * 
 * @项目 petrel.commonU
 *     <p>
 * @作者: jihailong
 *      <p>
 * @日期: 2015年6月4日
 *      <p>
 * @负责人: KISUN
 *       <p>
 * @负责小组:
 *        <p>
 *        <p>
 */
public abstract class AbstractFileToInputStream {
    /**
     * 模板输入流
     */
    protected InputStream inputStream;

    protected String name;

    /**
     * 构造方法
     * 
     * @param clazz 参照资源对象
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
