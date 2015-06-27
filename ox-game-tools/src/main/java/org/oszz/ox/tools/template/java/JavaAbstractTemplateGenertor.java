package org.oszz.ox.tools.template.java;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.velocity.VelocityContext;
import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.POIUitls;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.template.AbstractTemplateGenertor;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.template.conf.TemplateField;
import org.oszz.ox.tools.template.conf.TemplateFieldEnum;
import org.oszz.ox.tools.utils.VelocityUtils;

/**
 * 数据抽象模板生成器
 * @author ZZ
 *
 */
public class JavaAbstractTemplateGenertor extends AbstractTemplateGenertor implements ITemplateGenertor {

	private String abstractTemplate_vmFile;
	
	public JavaAbstractTemplateGenertor(ModuleConfig moduleConfig, List<TemplateDataConfig> tempDataConfigs,
			String abstractTemplate_vmFile){
		super(moduleConfig, tempDataConfigs);
		this.abstractTemplate_vmFile = abstractTemplate_vmFile;
	}
	
	@Override
	public void generate() {
		String inputPath = this.getAbsoluteInputPath(moduleConfig.getExcelPath()) + SystemProperty.FILE_SEPARATOR.getValue();
		String outputPath = this.getAbsoluteJavaOutputPath(moduleConfig.getJavaOutputPath());
		for(TemplateDataConfig tempDataConf : tempDataConfigs){
			if(tempDataConf.isGenerator()){
				String excelName = tempDataConf.getExcelName();
				String comments = tempDataConf.getComments();
				String packageName = tempDataConf.getPackageName();
				String abstractClassName = tempDataConf.getAbstractClassName();
				
				List<TemplateField> tempFields = tempDataConf.getTempFields();
				String excelFilePath = inputPath + excelName;
				tempFields = assignFiledType(tempFields, excelFilePath);
				
				writeFile(outputPath, packageName, comments, abstractClassName, tempFields);
			}
		}
	}
	
	private void writeFile(String outputPath, String packageName, String comments, String abstractClassName, List<TemplateField> tempFields){
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("packageName", packageName);
		ctx.put("comments", comments);
		ctx.put("abstractClassName", abstractClassName);
		ctx.put("tempFields", tempFields);
		
		String fileName = abstractClassName + SystemProperty.JAVA_CLASS_SUFFIX.getValue();
		String packagePath = ClassUtils.packageName2Path(packageName);
		
		outputPath += "/" + packagePath ;
		outputPath = FileUtils.getDirIfExists(outputPath) + "/";
		
		VelocityUtils.write(this.abstractTemplate_vmFile, ctx, outputPath + fileName, moduleConfig.getCharsetName());
		log.info("成功生成 {} . 字符集：{}", fileName, moduleConfig.getCharsetName());
	}
	
	/**
	 * 给字段的设置类型
	 * @author ZZ
	 * @param tempFields
	 * @return
	 */
	private List<TemplateField> assignFiledType(List<TemplateField> tempFields, String excelFilePath){
		int dataSheetIndex = moduleConfig.getDataSheetIndex();//数据sheet的索引位置
		int typeRowIndex = moduleConfig.getTypeRowIndex();//类型所在的行索引
		int nameRowIndex = moduleConfig.getNameRowIndex();//名字所在的行索引
		int descRowIndex = moduleConfig.getDescRowIndex();//描述所在的行索引
//		int dataStartRowIndex = tempConfig.getDataStartRowIndex();//数据开始 的行索引
		Workbook workbook = null;
		try {
			workbook = POIUitls.getWorkbook(excelFilePath);
			Sheet sheet = POIUitls.getSheet(workbook, dataSheetIndex);
			for(TemplateField tempField : tempFields){
				int cellIndex = POIUitls.getCellColumnIndex(sheet, nameRowIndex, tempField.getName());
				String typeStr = POIUitls.getStringValue(sheet, typeRowIndex, cellIndex);
				String descStr = POIUitls.getStringValue(sheet, descRowIndex, cellIndex);
				
				String typeForJava = TemplateFieldEnum.getTypeForJava(typeStr);
				tempField.setType(typeForJava);	
				tempField.setDesc(descStr);
			}
		} catch (Exception e) {
			log.info("读取 {} 时出错 . 原因：{}.", excelFilePath, e);
		}finally{
			if(workbook != null) {try {workbook.close();} catch (IOException e) {}}
		}
		return tempFields;
	}

}
