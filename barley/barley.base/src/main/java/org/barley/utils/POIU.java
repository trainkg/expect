
package org.barley.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIU {
	/**
	 * 对excel工作簿进行合并单元格(.XLSX)
	 * 
	 * @param workbook 工作簿对象
	 * @param str      合并后内容
	 * @param row1     开始行
	 * @param col1     开始列
	 * @param row2     结束行
	 * @param col2     结束列
	 */
	public static void MergedCells(XSSFWorkbook workbook, String str, int row1, int col1, int row2, int col2) {
		XSSFSheet xssfSheet = workbook.getSheetAt(0);
		XSSFRow row = xssfSheet.createRow((short) row1); // 创建一个单元格
		XSSFCell cell = row.createCell((short) col1); // 防止乱码
		RichTextString strEncode = new XSSFRichTextString();
		cell.setCellValue(strEncode);
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(style);
		// 设置单元格内容
		cell.setCellValue(str);
		// Region各参数的意思为合并的起始行列row1,col1和终止行列row2,col2
		CellRangeAddress cellRangeAddress = new CellRangeAddress(row1, row2, col1, col2);
		xssfSheet.addMergedRegion(cellRangeAddress);
	}

	/**
	 * 对excel工作簿进行合并单元格(.XLS)
	 * 
	 * @param workbook 工作簿对象
	 * @param str      合并后内容
	 * @param row1     开始行
	 * @param col1     开始列
	 * @param row2     结束行
	 * @param col2     结束列
	 */
	public static void MergedCells(HSSFWorkbook workbook, String str, int row1, int col1, int row2, int col2) {
		HSSFSheet hssfSheet = workbook.getSheetAt(0);
		HSSFRow row = hssfSheet.createRow((short) row1); // 创建一个单元格
		HSSFCell cell = row.createCell(col1); // 防止乱码
		RichTextString strEncode = new XSSFRichTextString();
		cell.setCellValue(strEncode);
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(style);
		// 设置单元格内容
		cell.setCellValue(str);
		// Region各参数的意思为合并的起始行列row1,col1和终止行列row2,col2
		CellRangeAddress cellRangeAddress = new CellRangeAddress(row1, row2, col1, col2);
		hssfSheet.addMergedRegion(cellRangeAddress);
	}

	/**
	 * 读取Excel工作簿(.XLS)
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook getHSSFWorkbook(InputStream inputStream) throws Exception {
		if (inputStream == null) {
			throw new Exception("模板工作簿加载失败");
		}
		POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		// 读取excel模板
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		inputStream.close();
		return wb;
	}

	/**
	 * 读取Excel工作簿(.XLSX)
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static XSSFWorkbook getXSSFWorkbook(InputStream inputStream) throws Exception {
		if (inputStream == null) {
			throw new Exception("模板工作簿加载失败");
		}
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		inputStream.close();
		return wb;
	}

	/**
	 * 单元格数据作成(.XLS)
	 * 
	 * @param sheet
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void makeCellData(HSSFSheet sheet, int row, int col, String value) {
		HSSFCell cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value);
	}

	/**
	 * 单元格数据作成(.XLSX)
	 * 
	 * @param sheet
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void makeCellData(XSSFSheet sheet, int row, int col, String value) {
		XSSFCell cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value);
	}

	/**
	 * 锁定单元格
	 * 
	 * @param wb    工作簿对象
	 * @param sheet 页
	 * @param row   行数
	 * @param col   列数
	 * @author jihailong
	 */
	public static void lockedCell(XSSFWorkbook wb, XSSFSheet sheet, int row, int col) {
		XSSFCellStyle style = wb.createCellStyle();
		XSSFCell cell = sheet.getRow(row).getCell(col);
		style = (XSSFCellStyle) cell.getCellStyle().clone();
		if (cell.getCellStyle().getLocked()) {
			return;
		}
		style.setLocked(true);
		cell.setCellStyle(style);
	}

	/**
	 * 锁定单元格
	 * 
	 * @param wb    工作簿对象
	 * @param sheet 页
	 * @param row   行数
	 * @param col   列数
	 * @author jihailong
	 */
	public static void unLockedCell(XSSFWorkbook wb, XSSFSheet sheet, int row, int col) {
		XSSFCellStyle style = wb.createCellStyle();
		XSSFCell cell = sheet.getRow(row).getCell(col);
		style = (XSSFCellStyle) cell.getCellStyle().clone();
		if (!cell.getCellStyle().getLocked()) {
			return;
		}
		style.setLocked(false);
		cell.setCellStyle(style);
	}

	/**
	 * 设置单元格前景色
	 * 
	 * @param wb    工作簿对象
	 * @param sheet 页
	 * @param row   行数
	 * @param col   列数
	 * @param fg    颜色（例如：IndexedColors.LEMON_CHIFFON.getIndex()）
	 * @param fp    填充方式（例如：CellStyle.SOLID_FOREGROUND）
	 * @author jihailong
	 */
	public static void setCellForegroundColor(XSSFWorkbook wb, XSSFSheet sheet, int row, int col, short fg, short fp) {
		XSSFCellStyle style = wb.createCellStyle();
		XSSFCell cell = sheet.getRow(row).getCell(col);
		style = (XSSFCellStyle) cell.getCellStyle().clone();
		// style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setFillForegroundColor(fg);
		// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillPattern(fp);
		cell.setCellStyle(style);
	}

	/**
	 * 创建单元格批注
	 * 
	 * @param sheet
	 * @param row   行数
	 * @param col   列数
	 * @param tip   备注内容
	 * @author jihailong
	 */
	public static void makeCellTips(XSSFSheet sheet, int row, int col, String tip) {
		XSSFCell cell = sheet.getRow(row).getCell(col);
		// 创建绘图对象
		XSSFDrawing p = sheet.createDrawingPatriarch();
		// 获取批注对象
		// (int dx1, int dy1, int dx2, int dy2, short col1, int row1, short
		// col2, int row2)
		// 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
		XSSFComment comment = p.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 4));
		// 输入批注信息
		comment.setString(new XSSFRichTextString(tip));
		// 将批注添加到单元格对象中
		cell.setCellComment(comment);
	}

	/**
	 * 插入行
	 * 
	 * @param sheet
	 * @param startRow 插入的行位置
	 * @param n        表示要插入的行数
	 * @param cs       按照哪一行的style设置插入行的单元格
	 */
	public static void insertRows(XSSFSheet sheet, int startRow, int n, int cs) {
		// 将尾部内容移动n行
		if (startRow < sheet.getLastRowNum()) {
			sheet.shiftRows(startRow, sheet.getLastRowNum(), n, true, false);
		}
		// 列数
		short cellSize = sheet.getRow(cs).getLastCellNum();
		// 计数
		int j = startRow;
		while (j < startRow + n) {
			XSSFRow newRow = sheet.createRow(j);
			for (int i = 0; i < cellSize; i++) {
				XSSFCell newCell = newRow.createCell(i, sheet.getRow(cs).getCell(i).getCellType());
				newCell.setCellStyle(sheet.getRow(cs).getCell(i).getCellStyle());
			}
			j++;
		}
	}

	public static void main(String[] args) {
		// 创建工作簿对象
		XSSFWorkbook wb = new XSSFWorkbook();
		// 创建工作表对象
		XSSFSheet sheet = wb.createSheet("我的工作表");
		// 创建绘图对象
		XSSFDrawing p = sheet.createDrawingPatriarch();
		// 创建单元格对象,批注插入到4行,1列,B5单元格
		XSSFCell cell = sheet.createRow(4).createCell(1);
		// 插入单元格内容
		cell.setCellValue(new XSSFRichTextString("批注"));
		XSSFCellStyle style = wb.createCellStyle();
		// style.setFillBackgroundColor(new XSSFColor(java.awt.Color.GRAY));

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		// 获取批注对象
		// (int dx1, int dy1, int dx2, int dy2, short col1, int row1, short
		// col2, int row2)
		// 前四个参数是坐标点,后四个参数是编辑和显示批注时的大小.
		XSSFComment comment = p.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
		// 输入批注信息
		comment.setString(new XSSFRichTextString("这是批注内容!"));
		// 添加作者,选中B5单元格,看状态栏
		comment.setAuthor("toad");
		// 将批注添加到单元格对象中
		cell.setCellComment(comment);
		sheet.protectSheet("sugon");
		// 创建输出流
		try {
			FileOutputStream out = new FileOutputStream("d:/writerPostil.xlsx");
			wb.write(out);
			// 关闭流对象
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
