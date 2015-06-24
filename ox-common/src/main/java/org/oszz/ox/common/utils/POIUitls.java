package org.oszz.ox.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * POI工具类<br>
 * 用于读取Excel
 * @author ZZ
 *
 */
public class POIUitls {
	
	/**
	 * 判断一个Excel文件是否是2003版的
	 * @author ZZ
	 * @param excelFilePath Excel的文件路径
	 * @return 如果是返回<tt>true<tt>,否则返回<tt>false<tt> <br>
	 * 			如果文件不存在，也会返回<tt>false<tt>
	 */
	public static boolean isExcel2003(String excelFilePath){
		return isExcel2003(new File(excelFilePath));
	}
	
	/**
	 * 判断一个Excel文件是否是2003版的
	 * @author ZZ
	 * @param excelFile Excel的文件
	 * @return 如果是返回<tt>true<tt>,否则返回<tt>false<tt> <br>
	 * 			如果文件不存在，也会返回<tt>false<tt>
	 */
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
	
	/**
	 * 判断一个Excel文件是否是2007版的
	 * @author ZZ
	 * @param excelFilePath Excel的文件路径
	 * @return 如果是返回<tt>true<tt>,否则返回<tt>false<tt> <br>
	 * 			如果文件不存在，也会返回<tt>false<tt>
	 */
	public static boolean isExcel2007(String excelFilePath){
		return isExcel2007(new File(excelFilePath));
	}
	
	/**
	 * 判断一个Excel文件是否是2007版的
	 * @author ZZ
	 * @param excelFile Excel的文件
	 * @return 如果是返回<tt>true<tt>,否则返回<tt>false<tt> <br>
	 * 			如果文件不存在，也会返回<tt>false<tt>
	 */
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
	
	/**
	 * 返回Workbook
	 * @author ZZ
	 * @param excelFilePath Excel的文件路径
	 * @return 返回Workbook
	 * @throws FileNotFoundException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static Workbook getWorkbook(String excelFilePath) throws FileNotFoundException, InvalidFormatException, IOException{
		return getWorkbook(new File(excelFilePath));
	}
	
	/**
	 * 返回Workbook
	 * @author ZZ
	 * @param excelFile Excel的文件
	 * @return 返回Workbook
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
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
	
	/**
	 * 返回Sheet
	 * @author ZZ
	 * @param workbook Workbook
	 * @param sheetIndex sheet的索引位置
	 * @return 返回Sheet
	 */
	public static Sheet getSheet(Workbook workbook, int sheetIndex){
		return workbook.getSheetAt(sheetIndex);
	}

	/**
	 * 返回sheet的第rowNum行、第cellNum列的Cell内的字符串值
	 * @author ZZ
	 * @param sheet Sheet
	 * @param rowNum 行数
	 * @param cellNum 列数
	 * @return 返回sheet的第rowNum行、第cellNum列的Cell内的字符串值
	 */
	public static String getStringValue(Sheet sheet, int rowNum, int cellNum){
		Row row = sheet.getRow(rowNum);  
		//得到Excel工作表指定行的单元格    
		Cell cell = row.getCell(cellNum);
		return getStringValue(cell);
	}
	
	/**
	 * Cell的内容不管是什么（可能是number、boolean等），都转成字符串值
	 * @author ZZ
	 * @param cell 单元格
	 * @return 返回Cell内的字符串值
	 */
	public static String getStringValue(Cell cell){
		int cellType = cell.getCellType();
		String value = "";//将单元格中的值都转换成 字符串
		if(cellType == Cell.CELL_TYPE_BOOLEAN){
			value += cell.getBooleanCellValue();
		}else if(cellType == Cell.CELL_TYPE_NUMERIC){
			value = MathUtils.parseString(cell.getNumericCellValue());
		}else if(cellType == Cell.CELL_TYPE_STRING){
			value += cell.getStringCellValue();
		}
		return value;
	}
	
	/**
	 * 在sheet的第rowNum行找到和needFindValue一样的值，并返回该列的索引位置
	 * @author ZZ
	 * @param sheet Sheet
	 * @param rowNum 行数
	 * @param needFindValue 需要找到的值
	 * @return 如果在sheet的第rowNum行中找到了和needFindValue一样的值，就返回该列的索引位置<br>
	 * 			如果未找到，返回 -1
	 */
	public static int getCellColumnIndex(Sheet sheet, int rowNum, String needFindValue){
		Row row = sheet.getRow(rowNum);
		Iterator<Cell> cellIter = row.cellIterator();
		int index = -1;
		while(cellIter.hasNext()){
			Cell cell = cellIter.next();
			String cellValue = getStringValue(cell);
			if(needFindValue.equalsIgnoreCase(cellValue)){
				index = cell.getColumnIndex();
			}
		}
		return index;
	}
}
