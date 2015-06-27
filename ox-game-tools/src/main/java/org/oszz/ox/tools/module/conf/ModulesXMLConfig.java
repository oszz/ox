package org.oszz.ox.tools.module.conf;

import java.util.List;

public class ModulesXMLConfig {

	private String xmlPath;
	
	private List<ModuleXMLConfig> moduleXMLConfigs;
	
	public ModulesXMLConfig(String xmlPath, List<ModuleXMLConfig> moduleXMLConfigs){
		this.xmlPath = xmlPath;
		this.moduleXMLConfigs = moduleXMLConfigs;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public List<ModuleXMLConfig> getModuleXMLCoifigs() {
		return moduleXMLConfigs;
	}

	public void setModuleXMLCoifigs(List<ModuleXMLConfig> moduleXMLConfigs) {
		this.moduleXMLConfigs = moduleXMLConfigs;
	}
	
	
}
