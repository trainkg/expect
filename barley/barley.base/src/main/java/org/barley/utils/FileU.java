
package org.barley.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.barley.freemarker.TheFreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FileU {
	/**
	 * 文件下载
	 * 
	 * @param request
	 * @param response
	 * @param fileName 文件名称
	 * @param fis      输入流
	 * @throws IOException
	 */
	public static void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName,
			InputStream is) throws IOException {
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && (-1 != agent.indexOf("MSIE") || -1 != agent.indexOf("Trident"))) {
			fileName = URLEncoder.encode(fileName, "UTF8");
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
			fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		}
		BufferedOutputStream out = null;
		try {
			if (is == null) {
				throw new Exception("无数据");
			}
			response.setContentType("application/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			out = new BufferedOutputStream(response.getOutputStream());
			if ("无数据".equals(e.getMessage())) {
				out.write("<script type='text/javascript'>alert('无数据！');window.history.back();</script>".getBytes());
			} else {
				out.write("<script type='text/javascript'>alert('下载失败！');window.history.back();</script>".getBytes());
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (out != null) {
				out.close();
				out.flush();
			}
		}
	}

	/**
	 * FTL模板与数据模型合并
	 * 
	 * @param ftlName    模板名称
	 * @param root       数据模型
	 * @param out        输出流
	 * @param clazz      模板位置的相对位置
	 * @param pathPrefix 层级包名称
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static void mergeModelToTemlate(String ftlName, Map<String, Object> root, Writer out, Class clazz,
			String pathPrefix) throws Exception {
		if (!ftlName.endsWith(".ftl")) {
			throw new Exception("模板名称不正确：" + ftlName);
		}
		TheFreemarker tf = new TheFreemarker(clazz, pathPrefix);
		Configuration configuration = tf.getConfiguration();
		Template temp = null;
		try {
			// 取得模版文件
			temp = configuration.getTemplate(ftlName);
		} catch (Exception e) {
		}
		if (temp == null) {
			throw new Exception("模板获取失败：" + ftlName);
		}
		// 合并数据模型和模版，并将结果输出到out中
		temp.process(root, out);
	}

	/**
	 * 下载图片在前端显示
	 * 
	 * @param response
	 * @param request
	 * @param inputStream
	 * @author jihailong
	 */
	public static void downLoadImg(HttpServletResponse response, HttpServletRequest request, InputStream inputStream) {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/png");

		OutputStream outputStream = null;
		BufferedInputStream buffIn = null;
		BufferedOutputStream buffOut = null;
		try {
			if (inputStream != null) {
				outputStream = response.getOutputStream();
				buffIn = new BufferedInputStream(inputStream);
				buffOut = new BufferedOutputStream(outputStream);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = buffIn.read(buff, 0, buff.length))) {
					buffOut.write(buff, 0, bytesRead);
				}
				buffOut.close();
				buffIn.close();
			} else {
				outputStream = response.getOutputStream();
				outputStream.write("".getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (buffIn != null) {
					buffIn.close();
				}
				if (buffOut != null) {
					buffOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件在前端显示
	 * 
	 * @param response
	 * @param request
	 * @param inputStream
	 * @author jihailong
	 */
	public static void getFileToSee(HttpServletResponse response, HttpServletRequest request, InputStream inputStream,
			String contentType) {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		// response.setContentType("image/png");
		response.setContentType(contentType);

		OutputStream outputStream = null;
		BufferedInputStream buffIn = null;
		BufferedOutputStream buffOut = null;
		try {
			if (inputStream != null) {
				outputStream = response.getOutputStream();
				buffIn = new BufferedInputStream(inputStream);
				buffOut = new BufferedOutputStream(outputStream);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = buffIn.read(buff, 0, buff.length))) {
					buffOut.write(buff, 0, bytesRead);
				}
				buffOut.close();
				buffIn.close();
			} else {
				outputStream = response.getOutputStream();
				outputStream.write("".getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (buffIn != null) {
					buffIn.close();
				}
				if (buffOut != null) {
					buffOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
