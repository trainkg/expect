
package org.barley.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelResolver {
	public final static int CELL_TYPE_STRING = 1;

	/**
	 * XLS
	 * 
	 * @param in 文件流
	 * @return 文库节点列表 每个元素是一个字符串数组 String[3]{value1,value2,value3}分别代表
	 *         {节点名，节点序号，节点层次}
	 * @throws IOException 操作异常
	 */
	public static List<String[]> readXls(InputStream in) throws IOException {

		ArrayList<String[]> values = new ArrayList<String[]>();
		// InputStream is = new FileInputStream("D:\\excel\\xls_test2.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				String[] col = new String[hssfRow.getLastCellNum()];
				values.add(col);
				// 循环列Cell
				for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						col[cellNum] = null;
					} else {
						col[cellNum] = getValue(hssfCell);
					}
				}
			}
		}
		return values;
	}

	/**
	 * XLS
	 * 
	 * @param in 文件流
	 * @return 文库节点列表 每个元素是一个字符串数组 String[3]{value1,value2,value3}分别代表
	 *         {节点名，节点序号，节点层次}
	 * @throws IOException 操作异常
	 */
	public static List<String[]> readXls(InputStream in, Integer cellType) throws IOException {

		ArrayList<String[]> values = new ArrayList<String[]>();
		// InputStream is = new FileInputStream("D:\\excel\\xls_test2.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				String[] col = new String[hssfRow.getLastCellNum()];
				values.add(col);
				// 循环列Cell
				for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						col[cellNum] = null;
					} else {
						if (cellType != null) {
							hssfCell.setCellType(cellType);
						}
						col[cellNum] = getValue(hssfCell);
					}
				}
			}
		}
		return values;
	}

	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * XLSX
	 * 
	 * @param in       文件数据流
	 * @param cellType 单元格类型设置
	 * @return 文库节点列表 每个元素是一个字符串数组 String[3]{value1,value2,value3}分别代表
	 *         {节点名，节点序号，节点层次}
	 * @throws IOException 操作异常
	 */
	public static List<String[]> readXlsx(InputStream in, Integer cellType) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		ArrayList<String[]> values = new ArrayList<String[]>();
		// 循环工作表Sheet
		/*
		 * for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets();
		 * numSheet++) {}
		 */
		if (xssfWorkbook.getNumberOfSheets() > 0) {

			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			if (xssfSheet != null) {
				// 循环行Row
				for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
					XSSFRow xssfRow = xssfSheet.getRow(rowNum);
					if (xssfRow == null) {
						continue;
					}
					String[] col = new String[xssfRow.getLastCellNum()];
					values.add(col);

					// 循环列Cell
					for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
						XSSFCell xssfCell = xssfRow.getCell(cellNum);
						if (xssfCell == null) {
							col[cellNum] = null;
						} else {
							if (cellType != null) {
								xssfCell.setCellType(cellType);
							}
							col[cellNum] = getValue(xssfCell);
						}
					}
				}
			}

		}
		return values;
	}

	/**
	 * XLSX
	 * 
	 * @param in 文件数据流
	 * @return 文库节点列表 每个元素是一个字符串数组 String[3]{value1,value2,value3}分别代表
	 *         {节点名，节点序号，节点层次}
	 * @throws IOException 操作异常
	 */
	public static List<String[]> readXlsx(InputStream in) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
		ArrayList<String[]> values = new ArrayList<String[]>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				String[] col = new String[xssfRow.getLastCellNum()];
				values.add(col);

				// 循环列Cell
				for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						col[cellNum] = null;
					} else {
						/*
						 * if (cellNum == 4) { DecimalFormat df = new DecimalFormat("#"); col[cellNum] =
						 * df.format(xssfCell.getNumericCellValue()); } else { col[cellNum] =
						 * getValue(xssfCell); }
						 */
						col[cellNum] = getCellValue(xssfCell);
					}
				}
			}
		}
		return values;
	}

	private static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	public static String getCellValue(HSSFCell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				return sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case Cell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

	public static String getCellValue(XSSFCell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				return sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case Cell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

	/**
	 * 区分调用哪个后缀方法
	 * 
	 * @param tempFileName
	 * @param xlsInStream
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readExcel(String tempFileName, InputStream xlsInStream) throws IOException {
		List<String[]> values = null;
		int index = tempFileName.lastIndexOf(".");
		if (index != -1) {
			tempFileName = tempFileName.substring(index + 1);
			if ("xls".equals(tempFileName)) {
				values = readXls(xlsInStream);
			} else if ("xlsx".equals(tempFileName)) {
				values = readXlsx(xlsInStream);
			}
		}
		return values;
	}

	/**
	 * 区分调用哪个后缀方法
	 * 
	 * @param tempFileName
	 * @param xlsInStream
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readExcel(String tempFileName, InputStream xlsInStream, Integer cellType)
			throws IOException {
		List<String[]> values = null;
		int index = tempFileName.lastIndexOf(".");
		if (index != -1) {
			tempFileName = tempFileName.substring(index + 1);
			if ("xls".equals(tempFileName)) {
				values = readXls(xlsInStream, cellType);
			} else if ("xlsx".equals(tempFileName)) {
				values = readXlsx(xlsInStream, cellType);
			}
		}
		return values;
	}

}
