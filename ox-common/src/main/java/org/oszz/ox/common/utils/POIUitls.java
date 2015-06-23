package org.oszz.ox.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIUitls {
	
	public static HSSFSheet getSheet(HSSFWorkbook workbook, int sheetIndex){
		return workbook.getSheetAt(sheetIndex);
	}

	public static String getStringValue(HSSFSheet sheet, int rowNum, int cellnum){
		//POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("d:/test.xls"));   
		//得到Excel工作簿对象    
		//HSSFWorkbook wb = new HSSFWorkbook(fs);  
		//得到Excel工作表对象    
		//HSSFSheet sheet = wb.getSheetAt(0);   
		//得到Excel工作表的行    
		HSSFRow row = sheet.getRow(rowNum);  
		//得到Excel工作表指定行的单元格    
		HSSFCell cell = row.getCell(cellnum);
		return cell.getStringCellValue();
	}
	public static int getIntValue(HSSFSheet sheet, int rowNum, int cellnum){
		return Integer.parseInt(getStringValue(sheet, rowNum, cellnum));
	}
	
	public static double getDoubleValue(HSSFSheet sheet, int rowNum, int cellnum){
		return Double.parseDouble(getStringValue(sheet, rowNum, cellnum));
	}
}
