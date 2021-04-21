package com.iod.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {
	
	static final String datasheetPath = ".\\resources\\datasheet.xlsx";
	static XSSFSheet sheet;
	
	public static String readData(String sheetName, int rowNum, int cellNum) {
		try {
			FileInputStream fis = new FileInputStream(datasheetPath);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet.getRow(rowNum).getCell(cellNum).getStringCellValue().toString();
	}

}
