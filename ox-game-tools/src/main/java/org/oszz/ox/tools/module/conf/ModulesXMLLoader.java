package org.oszz.ox.tools.module.conf;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.oszz.ox.common.utils.FileUtils;
import org.oszz.ox.common.utils.XMLUtils;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulesXMLLoader {
	protected static final Logger log = LoggerFactory.getLogger("ModulesXMLLoader");
	
	public ModulesXMLConfig load(String modulesXMLFilePath) throws Exception{
        Element rootNode = XMLUtils.getRootElement(modulesXMLFilePath);
        
		List<ModuleXMLConfig> moduleXMLConfigs = getModuleXMLCoifigs(rootNode);
		String xmlPath = getModuleXMLPath(rootNode);
		ModulesXMLConfig modulesXMLConfig = new ModulesXMLConfig(xmlPath, moduleXMLConfigs);
		return modulesXMLConfig;
	}
	
	private String getModuleXMLPath(Element rootNode){
		Element xmlPathNode = XMLUtils.getChildNode(rootNode, ToolsConstant.XML_NODE_XML_PATH);
		String xmlPath = XMLUtils.getAttrStringValue(xmlPathNode, ToolsConstant.XML_ATTRIBUTE_DIR_PATH);
		xmlPath = FileUtils.getAbsolutePathForRead(xmlPath);
        return xmlPath;
	}
	
	private List<ModuleXMLConfig> getModuleXMLCoifigs(Element rootNode) throws DocumentException{
		List<Element> moduleNodes = XMLUtils.getChildNodes(rootNode, ToolsConstant.XML_NODE_MODULE);
		
		List<ModuleXMLConfig> moduleXMLConfigs = new ArrayList<ModuleXMLConfig>();
        
        for(Element moduleNode : moduleNodes){
        	String name = XMLUtils.getAttrStringValue(moduleNode, ToolsConstant.XML_ATTRIBUTE_NAME);
        	String modulePackage = XMLUtils.getAttrStringValue(moduleNode, ToolsConstant.XML_ATTRIBUTE_PACKAGE);
        	boolean isGenerator = XMLUtils.getAttrBooleanValue(moduleNode, ToolsConstant.XML_ATTRIBUTE_IS_GENERATOR);
        	String xmlFileName = XMLUtils.getAttrStringValue(moduleNode, ToolsConstant.XML_ATTRIBUTE_XML_FILE_NAME);
        	String desc = XMLUtils.getAttrStringValue(moduleNode, ToolsConstant.XML_ATTRIBUTE_DESC);
        	ModuleXMLConfig mxmlConf = new ModuleXMLConfig(name, modulePackage, isGenerator, xmlFileName, desc);
        	
          	moduleXMLConfigs.add(mxmlConf);
        }
        
        return moduleXMLConfigs;
	}
}
