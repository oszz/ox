package org.oszz.ox.tools.run;

import java.util.List;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.message.conf.IXMLLoader;
import org.oszz.ox.tools.template.ITemplateGenertor;
import org.oszz.ox.tools.template.conf.TemplateConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.template.conf.TemplateXMLLoader;
import org.oszz.ox.tools.template.java.JavaTemplateGenertor;

public class TemplateGenertor {

	/**
	 * template的配置
	 */
	private static final String CONIG_FILE_PAHT = "template/template.properties";
	
	/**
	 * templateData的XML配置文件
	 */
	private static final String TEMPLATE_DATA_XML_PATH = "conf/template/templateData.xml";
	
	/**
	 * 生成抽象的模板类
	 */
	private static final String  ABSTRACT_TEMPLATE_JAVA_VM_FILE = "template/vm/abstractTemplateForJava.vm";
	
	/**
	 * 生成模板数据类
	 */
	private static final String  TEMPLATE_JAVA_VM_FILE = "template/vm/templateForJava.vm";
	
	/**
	 * 生成模板类持有者的模板
	 */
	private static final String  TEMP_DATA_CLASS_HOLDER_JAVA_VM_FILE = "template/vm/templateDataClassHolderForJava.vm";
	
	
	
	public static void main(String[] args) throws Exception {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载消息配置
		TemplateConfig tempConfig = lpf.load(confProps, TemplateConfig.class);
		
		//加载消息编码的相关配置
		IXMLLoader<TemplateDataConfig> xmlLoader = new TemplateXMLLoader();
		List<TemplateDataConfig> tempDataConfigs = xmlLoader.load(TEMPLATE_DATA_XML_PATH);
		
		generatorJava(tempConfig, tempDataConfigs);
	}
	/**
	 * 生成java相关的代码
	 * @author ZZ
	 * @param tempConfig 模板的相关配置
	 * @param tempDataConfigs 模板数据的相关配置
	 */
	private static void generatorJava(TemplateConfig tempConfig, List<TemplateDataConfig> tempDataConfigs){
		ITemplateGenertor javaTempGenerator = new JavaTemplateGenertor(tempConfig, tempDataConfigs,
				ABSTRACT_TEMPLATE_JAVA_VM_FILE, TEMPLATE_JAVA_VM_FILE, TEMP_DATA_CLASS_HOLDER_JAVA_VM_FILE);
		javaTempGenerator.generate();
	
	}

}
