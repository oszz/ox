package org.oszz.ox.tools.launch;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.launch.java.JavaDataManagerGenerator;
import org.oszz.ox.tools.launch.java.JavaMessageGenerator;
import org.oszz.ox.tools.launch.java.JavaServiceGenertor;
import org.oszz.ox.tools.launch.java.JavaTemplateGenerator;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.module.ModuleXMLLoader;
import org.oszz.ox.tools.module.ModulesXMLLoader;
import org.oszz.ox.tools.module.conf.ModuleConfig;
import org.oszz.ox.tools.module.conf.ModuleXMLConfig;
import org.oszz.ox.tools.module.conf.ModulesXMLConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;

public class ModuleGenerator {
	
	/**
	 * Module的配置文件
	 */
	private static final String CONIG_FILE_PAHT = "module/module.properties";
	
	/**
	 * modules的配置文件
	 */
	private static final String MODULES_XML_PATH = "conf/module/modules.xml";

	public static void main(String[] args) throws Exception {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载配置
		ModuleConfig moduleConfig = lpf.load(confProps, ModuleConfig.class);
		
		//加载消息编码的相关配置
		ModulesXMLLoader xmlLoader = new ModulesXMLLoader();
		ModulesXMLConfig modulesXMLConfig = xmlLoader.load(MODULES_XML_PATH);
		
		ModuleXMLLoader moduleXMLLoader = new ModuleXMLLoader();
		moduleXMLLoader.load(modulesXMLConfig);
		
		
		
		javaModuleGenerator(moduleConfig, modulesXMLConfig);
				
	}
	
	/**
	 * 生成模块相关的java代码<br>
	 * 包含：消息、temp数据、dataManager、service
	 * @author ZZ
	 * @param moduleConfig
	 * @param modulesXMLConfig
	 */
	private static void javaModuleGenerator(ModuleConfig moduleConfig, ModulesXMLConfig modulesXMLConfig){
		//所有的messgeCode配置
		List<MessageCodeConfig> totalMsgCodeConfigs = new ArrayList<MessageCodeConfig>();
		//所有的Template数据配置
		List<TemplateDataConfig> totalTempDataConfigs = new ArrayList<TemplateDataConfig>();
		
		List<ModuleXMLConfig> moduleXMLConfigs = modulesXMLConfig.getModuleXMLCoifigs();
		for(ModuleXMLConfig moduleXMLConfig : moduleXMLConfigs){
			List<MessageCodeConfig> msgCodeConfigs = moduleXMLConfig.getMsgCodeConfigs();
			if(msgCodeConfigs != null){
				totalMsgCodeConfigs.addAll(msgCodeConfigs);
			}
			List<TemplateDataConfig> tempDataConfigs = moduleXMLConfig.getTempDataConfigs();
			if(tempDataConfigs != null){
				totalTempDataConfigs.addAll(tempDataConfigs);
			}
		}
		
		JavaMessageGenerator.generator(moduleConfig, totalMsgCodeConfigs);
		JavaTemplateGenerator.generator(moduleConfig, totalTempDataConfigs);
		JavaDataManagerGenerator.generator(moduleConfig, modulesXMLConfig);
		JavaServiceGenertor.generator(moduleConfig, modulesXMLConfig);
	}

}
