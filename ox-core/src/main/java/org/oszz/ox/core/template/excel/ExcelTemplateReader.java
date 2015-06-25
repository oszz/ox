package org.oszz.ox.core.template.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.POIUitls;
import org.oszz.ox.core.template.AbstractTemplateData;
import org.oszz.ox.core.template.ITemplateConfig;
import org.oszz.ox.core.template.ITemplateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelTemplateReader implements IExcelTemplateReader {
	private static final String ID_FIELD_NAME = "id"; 
	
	protected static final Logger log = LoggerFactory.getLogger("ExcelTemplateReader");
	
	private ITemplateConfig templateConfig;
	
	public ExcelTemplateReader(ITemplateConfig templateConfig){
		this.templateConfig = templateConfig;
	}

	@Override
	public <T extends ITemplateData> Map<Integer, ITemplateData> read(Class<T> clazz,
			String excelFilePath) {
		Map<Integer, ITemplateData> datas = new HashMap<Integer, ITemplateData>();
		File excelFile = new File(excelFilePath);
		String excelFileName = excelFile.getName();
		int dataSheetIndex = templateConfig.getDataSheetIndex();//数据sheet的索引位置
		int nameRowIndex = templateConfig.getNameRowIndex();//名字所在的行索引
		int dataStartRowIndex = templateConfig.getDataStartRowIndex();//数据开始 的行索引
		Workbook workbook = null;
		try {
			workbook = POIUitls.getWorkbook(excelFile);
			Sheet sheet = POIUitls.getSheet(workbook, dataSheetIndex);
			int totalRowNum = sheet.getLastRowNum();//总行数
			
			Field[] fields = ClassUtils.getAllFields(clazz, AbstractTemplateData.class);
			Field idField = ClassUtils.getField(clazz, ID_FIELD_NAME);
			for(int i = dataStartRowIndex ; i<=totalRowNum; i++){
				T instance = ClassUtils.newInstance(clazz);
				//为id字段设置属性
				assignField(instance, idField, excelFileName, sheet, nameRowIndex, i);
				for(Field field : fields){
					ExcelField excelFieldAnno = field.getAnnotation(ExcelField.class);
					if(excelFieldAnno != null){//说明是ExcelField字段，进行赋值
						assignField(instance, field, excelFileName, sheet, nameRowIndex, i);
					}
				}
				int id = instance.getId();
				ITemplateData isRepeatTD = datas.get(id);
				if(isRepeatTD == null){
					instance.check();//读完每行数据后进行check
					datas.put(instance.getId(), instance);
				}else{//id重复
					throw new RuntimeException("读取 " + excelFileName + 
							" 时存在重复的id,第  " + i + " 行数据,"
							+ "id= " +id + " 出现重复.");
				}
			}
			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取 " + excelFileName  + " 时出错,原因：" + e);
		} finally {
			if(workbook != null){try {workbook.close();} catch (IOException e) {}}
		}
	}
	
	private <T extends ITemplateData> void assignField(T instance, Field field, String excelFileName, Sheet sheet, int nameRowIndex, int dataLineNum){
		String fieldName = field.getName();
		int cellIndex = POIUitls.getCellColumnIndex(sheet, nameRowIndex, fieldName);
		String value = POIUitls.getStringValue(sheet, dataLineNum, cellIndex);
		try{
			ClassUtils.invokeSetterField(instance, field, value);
		} catch (Exception e) {
			throw new RuntimeException("读取 " + excelFileName + 
					" 时出错,第  " + dataLineNum + " 行数据,id= " + instance.getId() + " -"
							+ "字段(" + fieldName +" 类型:" + field.getType().getSimpleName() + ")"
									+ "的值= " + value + " 不正确.");
		}
	}
}
