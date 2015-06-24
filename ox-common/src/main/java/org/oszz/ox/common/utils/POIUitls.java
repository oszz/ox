package org.oszz.ox.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUitls {
	
	
	
	public static boolean isExcel2003(String excelFilePath){
		return isExcel2003(new File(excelFilePath));
	}
	
	public static boolean isExcel2003(File excelFile){
		boolean flag = false;
		Workbook workbook = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));
			workbook = new HSSFWorkbook(fs);
			flag = true;
		} catch (Exception e) {
			
		}finally{
			if(workbook != null){try {workbook.close();} catch (IOException e) {} }
		}
		return flag;
	}
	
	public static boolean isExcel2007(String excelFilePath){
		return isExcel2007(new File(excelFilePath));
	}
	public static boolean isExcel2007(File excelFile){
		boolean flag = false;
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);
			flag = true;
		} catch (Exception e) {
			
		}finally{
			if(workbook != null){try {workbook.close();} catch (IOException e) {} }
		}
		return flag;
	}
	
	public static Workbook getWorkbook(String excelFilePath) throws FileNotFoundException, InvalidFormatException, IOException{
		return getWorkbook(new File(excelFilePath));
	}
	
	public static Workbook getWorkbook(File excelFile) throws FileNotFoundException, IOException, InvalidFormatException{
		if(!excelFile.exists()){
			return null;
		}
		Workbook workbook = null;
		if(isExcel2003(excelFile)){
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelFile));
			workbook = new HSSFWorkbook(fs);
		}else if(isExcel2007(excelFile)){
			workbook = new XSSFWorkbook(excelFile);
		}
		return workbook;
	}
	
	public static Sheet getSheet(Workbook workbook, int sheetIndex){
		return workbook.getSheetAt(sheetIndex);
	}

	public static String getStringValue(Sheet sheet, int rowNum, int cellnum){
		Row row = sheet.getRow(rowNum);  
		//得到Excel工作表指定行的单元格    
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		String vaule = "";//将单元格中的值都转换成 字符串
		if(cellType == Cell.CELL_TYPE_BOOLEAN){
			vaule += cell.getBooleanCellValue();
		}else if(cellType == Cell.CELL_TYPE_NUMERIC){
			vaule = MathUtils.parseString(cell.getNumericCellValue());
		}else if(cellType == Cell.CELL_TYPE_STRING){
			vaule += cell.getStringCellValue();
		}
		return vaule;
	}
	public static int getIntValue(Sheet sheet, int rowNum, int cellnum){
		return Integer.parseInt(getStringValue(sheet, rowNum, cellnum));
	}
	
	public static double getDoubleValue(Sheet sheet, int rowNum, int cellnum){
		return Double.parseDouble(getStringValue(sheet, rowNum, cellnum));
	}
	
	public static int getCellColumnIndex(Sheet sheet, int rowNum, String name){
		Row row = sheet.getRow(rowNum);
		Iterator<Cell> cellIter = row.cellIterator();
		int index = -1;
		while(cellIter.hasNext()){
			Cell cell = cellIter.next();
			String cellValue = cell.getStringCellValue();
			if(name.equalsIgnoreCase(cellValue)){
				index = cell.getColumnIndex();
			}
		}
		return index;
	}
}
