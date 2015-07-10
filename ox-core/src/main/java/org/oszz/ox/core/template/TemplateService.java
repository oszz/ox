package org.oszz.ox.core.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oszz.ox.core.template.excel.ExcelName;
import org.oszz.ox.core.template.excel.ExcelTemplateReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateService implements ITemplateService {
	protected static final Logger log = LoggerFactory.getLogger("TemplateService");
	
	private Map<Class<? extends ITemplateData>, Map<Integer, ITemplateData>> tempDataMaps;
	
//	private String tempDirPath;//模板数据的目录
	private ITemplateConfig templateConfig;//模板数据的目录
	private ITemplateReader templateReader;
	
//	/**
//	 * 构建一个模板的service
//	 * @param tempDirPath 模板文件的目录
//	 * @param templateConfig 模板配置
//	 */
//	public TemplateService(String tempDirPath, ITemplateConfig templateConfig){
//		this.tempDirPath = tempDirPath;
//		templateReader = new ExcelTemplateReader(templateConfig);
//		tempDataMaps = new HashMap<Class<? extends ITemplateData>, Map<Integer,ITemplateData>>();
//	}
	@Override
	public void setTemplateConfig(ITemplateConfig templateConfig) {
		this.templateConfig = templateConfig;
		templateReader = new ExcelTemplateReader(templateConfig);
	}
	
	@Override
	public boolean create() {
		tempDataMaps = new HashMap<Class<? extends ITemplateData>, Map<Integer,ITemplateData>>();
		return true;
	}

	@Override
	public boolean init() {
		List<Class<? extends ITemplateData>> allTempDataClasses = TemplateDataClassHolder.getInstance().getAllTemplateClass();
		log.info("开始读取模板数据文件 ...");
		for(Class<? extends ITemplateData> tempDataClazz : allTempDataClasses){
			if(tempDataClazz.isAnnotationPresent(ExcelName.class)){
				ExcelName excelNameAnno = tempDataClazz.getAnnotation(ExcelName.class);
				String excelName = excelNameAnno.value();
				if(excelName != null && !"".equals(excelName.trim())){
					log.info("读取 {} ...", excelName);
					String excelFilePath = templateConfig.getDirPath() + excelName;
					Map<Integer, ITemplateData> datas = templateReader.read(tempDataClazz, excelFilePath);
					tempDataMaps.put(tempDataClazz, datas);
				}else{
					throw new RuntimeException("模板类："+ tempDataClazz +", @ExcelName 注解的value为空.");
				}
			}else{
				throw new RuntimeException("模板类："+ tempDataClazz +",没有 @ExcelName 注解.");
			}
		}
		return true;
	}
	private void patchUp(){
		//所有数据加载完成后，调用patchUp
		for(Map.Entry<Class<? extends ITemplateData>, Map<Integer, ITemplateData>> tempDataMapEntry : tempDataMaps.entrySet()){
			Map<Integer, ITemplateData> datas = tempDataMapEntry.getValue();
			for(Map.Entry<Integer, ITemplateData> dataEntry : datas.entrySet()){
				ITemplateData tempData = dataEntry.getValue();
				tempData.patchUp();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ITemplateData> T get(int id, Class<T> clazz) {
		Map<Integer,ITemplateData> tempDatas = tempDataMaps.get(clazz);
		return (T)tempDatas.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ITemplateData> Map<Integer, T> getAll(Class<T> clazz) {
		return (Map<Integer, T>)tempDataMaps.get(clazz);
	}

	@Override
	public void onInitialized() {
		patchUp();//读取完所有的模板数据后，再依次调用每个模板数据的该方法
		log.info("读取模板数据文件结束.");
	}

	@Override
	public void start() {
		
	}
}
