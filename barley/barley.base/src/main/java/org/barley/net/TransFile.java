
package org.barley.net;

import java.io.InputStream;
import java.io.OutputStream;

public class TransFile {
	private OutputStream os;
	private FileType type;
	private String fileName;
	private String fileId;
	private InputStream is;

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
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

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

}
