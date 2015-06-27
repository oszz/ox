package org.oszz.ox.tools.module.conf;

import java.util.List;

import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;

public class ModuleXMLConfig {

	private String name;
	private String modulePackage;
	private boolean isGenerator;
	private String xmlFileName;//关联的模块xml配置
	private String desc;//模块描述
	/*-- 以上属性来自于Modules.xml --*/
	
	
	private String serviceClassName;//模块的serviceClass名称
	private String dataManagerClassName;//模块的数据管理类的名称
	
	private String messagePackage;//message的包名
	private String messageHandlerPackage;//message handler的包名
	private String templatePackage;//template的包名
	
	private List<MessageCodeConfig> msgCodeConfigs;
	private List<TemplateDataConfig> tempDataConfigs;
	
	public ModuleXMLConfig(String name, String packageName, 
			boolean isGenerator, String xmlFileName, String desc){
		this.name = name;
		this.modulePackage = packageName;
		this.isGenerator = isGenerator;
		this.xmlFileName = xmlFileName;
		this.desc = desc;
		
		this.serviceClassName = NameUtils.getClassName(name) + ToolsConstant.SERVICE_CLASS_NAME_SUFFIX;
		this.dataManagerClassName = ToolsConstant.DATA_HANDLER_CLASS_NAME_PREFIX +
				NameUtils.getClassName(name) + ToolsConstant.DATA_HANDLER_CLASS_NAME_SUFFIX;
		
		this.messagePackage = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + ToolsConstant.MESSAGE_PACKAGE;
		this.templatePackage = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + ToolsConstant.TEMPLATE_PACKAGE;
		this.messageHandlerPackage = packageName + SystemProperty.PACKAGE_SEPARATOR.getValue() + ToolsConstant.MESSAGE_HANDLER_PACKAGE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getModulePackage() {
		return modulePackage;
	}

	public void setModulePackage(String modulePackage) {
		this.modulePackage = modulePackage;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getDataManagerClassName() {
		return dataManagerClassName;
	}

	public void setDataManagerClassName(String dataManagerClassName) {
		this.dataManagerClassName = dataManagerClassName;
	}

	public String getMessagePackage() {
		return messagePackage;
	}

	public void setMessagePackage(String messagePackage) {
		this.messagePackage = messagePackage;
	}

	public String getTemplatePackage() {
		return templatePackage;
	}

	public void setTemplatePackage(String templatePackage) {
		this.templatePackage = templatePackage;
	}

	public boolean isGenerator() {
		return isGenerator;
	}

	public void setGenerator(boolean isGenerator) {
		this.isGenerator = isGenerator;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<MessageCodeConfig> getMsgCodeConfigs() {
		return msgCodeConfigs;
	}

	public void setMsgCodeConfigs(List<MessageCodeConfig> msgCodeConfigs) {
		this.msgCodeConfigs = msgCodeConfigs;
	}

	public String getMessageHandlerPackage() {
		return messageHandlerPackage;
	}

	public void setMessageHandlerPackage(String messageHandlerPackage) {
		this.messageHandlerPackage = messageHandlerPackage;
	}

	public List<TemplateDataConfig> getTempDataConfigs() {
		return tempDataConfigs;
	}

	public void setTempDataConfigs(List<TemplateDataConfig> tempDataConfigs) {
		this.tempDataConfigs = tempDataConfigs;
	}
	
	
}
