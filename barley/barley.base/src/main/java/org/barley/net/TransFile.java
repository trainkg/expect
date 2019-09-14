/*
 * 文件名称:          DownloadFile.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             下午4:03:40
 */

package org.barley.net;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 解决一些需要和硬盘一一对应的文件定义问题,内存文件定义
 * <p>
 * <p>
 * @版本:       CloudView V1.8
 * <p>
 * @作者:       zhuyy
 * <p>
 * @日期:       2014年10月20日
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */
public class TransFile
{
    private OutputStream os;
    private FileType type;
    private String fileName;
    private String fileId;
    private InputStream is;

    public OutputStream getOs()
    {
        return os;
    }

    public void setOs(OutputStream os)
    {
        this.os = os;
    }

    public FileType getType()
    {
        return type;
    }

    public void setType(FileType type)
    {
        this.type = type;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @return the fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public InputStream getIs()
    {
        return is;
    }

    public void setIs(InputStream is)
    {
        this.is = is;
    }

}
