package org.oszz.ox.tools.module.conf;

import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.constant.ToolsConstant;

public class ModuleXMLCoifig {

	private String name;
	private String modulePackage;
	private String serviceClassName;
	private String dataManagerClassName;
	
	private String messagePackage;//message的包名
	private String templatePackage;//template的包名
	
	public ModuleXMLCoifig(String name, String packageName){
		this.name = name;
		this.modulePackage = packageName;
		
		this.serviceClassName = NameUtils.getClassName(name) + ToolsConstant.SERVICE_CLASS_NAME_SUFFIX;
		this.dataManagerClassName = ToolsConstant.DATA_HANDLER_CLASS_NAME_PREFIX +
				NameUtils.getClassName(name) + ToolsConstant.DATA_HANDLER_CLASS_NAME_SUFFIX;
		
		this.messagePackage = packageName + SystemProperty.PACKAGE_SEPARATOR + ToolsConstant.MESSAGE_PACKAGE;
		this.templatePackage = packageName + SystemProperty.PACKAGE_SEPARATOR + ToolsConstant.TEMPLATE_PACKAGE;

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
	
	
}
