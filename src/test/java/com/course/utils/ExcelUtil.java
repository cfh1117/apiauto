package com.course.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static int i; 
	/*
	 * 设置文件路径, 打开 Excel 文件
	 * 
	 * @params - Excel 路径 and 表单名字
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// 打开 Excel 文件
			FileInputStream ExcelFile = new FileInputStream(Path);

			// 访问excel表
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/*
	 * 从 Excel 单元格读取测试数据
	 * 
	 * @params - Row num 和 Col num
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * 从Excel单元格读取日期格式的测试数据
	 * 
	 * @params - Row num 和 Col num
	 */
	public static String getDateCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date dateValue = Cell.getDateCellValue();
			String dateStringFormat = df.format(dateValue);

			return dateStringFormat;
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * 写入 Excel 单元格, String 类型的结果
	 * 
	 * @params - Row num 和 Col num
	 */
	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
		try {
			
			Row = ExcelWSheet.getRow(RowNum);
			
//			System.out.println(Row.getLastCellNum());
			// 如果Row不存在就创建
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else {
				Cell.setCellValue(Result);
				}  
			
			// 文件字节输出流写入数据
			FileOutputStream fileOut = new FileOutputStream(Constants.File_Path + Constants.File_Name);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/*
	 * 写入 Excel 单元格, double 类型的结果
	 * 
	 * @params - Row num 和 Col num
	 */
	public static void setCellData(double Result, int RowNum, int ColNum) throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// 文件字节输出流写入数据
			FileOutputStream fileOut = new FileOutputStream(Constants.File_Path + Constants.File_Name);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	public static Object[][] proessExcel(String filePath,int sheetId) throws IOException {

		//数据流读入excel
		File file = new File(System.getProperty("user.dir")+filePath);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fis);

		//读取特定表单并计算行列数
		HSSFSheet sheet = wb.getSheetAt(sheetId);
		int numberOfRow = sheet.getPhysicalNumberOfRows();
		int numberOfCell = sheet.getRow(0).getLastCellNum();

		//将表单数据处理存入dtt对象
		Object[][] dttData = new Object[numberOfRow][numberOfCell];
		for(int i=0;i<numberOfRow;i++){
			if(null==sheet.getRow(i)||"".equals(sheet.getRow(i))){
				continue;
			}
			for(int j=0;j<numberOfCell;j++) {
				if(null==sheet.getRow(i).getCell(j)||"".equals(sheet.getRow(i).getCell(j))){
					continue;
				}
				HSSFCell cell = sheet.getRow(i).getCell(j);
				cell.setCellType(CellType.STRING);
				dttData[i][j] = cell.getStringCellValue();
			}
		}
		return dttData;
	}
}