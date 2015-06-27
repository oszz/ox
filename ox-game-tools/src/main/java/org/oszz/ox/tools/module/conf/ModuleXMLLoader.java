package org.oszz.ox.tools.module.conf;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.common.utils.XMLUtils;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.module.MessageCodeProducer;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.template.conf.TemplateField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleXMLLoader {
	
	protected static final Logger log = LoggerFactory.getLogger("ModuleXMLLoader");

	public ModulesXMLConfig load(ModulesXMLConfig modulesXMLConfig)
			throws DocumentException {
		String xmlDirPath = modulesXMLConfig.getXmlPath();
		List<ModuleXMLConfig> moduleXMLConfigs = modulesXMLConfig.getModuleXMLCoifigs();
		for(ModuleXMLConfig moduleXMLConfig : moduleXMLConfigs){
			String xmlFileName = moduleXMLConfig.getXmlFileName();
			String xmlFilePath = xmlDirPath + SystemProperty.FILE_SEPARATOR.getValue() + xmlFileName;
			log.info("正在读取{} ...", xmlFileName);
			Element rootNode = XMLUtils.getRootElement(xmlFilePath);
			List<MessageCodeConfig> msgCodeConfigs = getMessageCodeConfigs(rootNode, moduleXMLConfig);
			moduleXMLConfig.setMsgCodeConfigs(msgCodeConfigs);
			
			List<TemplateDataConfig> tempDataConfigs =  getTemplateDataConfigs(rootNode, moduleXMLConfig);
			moduleXMLConfig.setTempDataConfigs(tempDataConfigs);
		}
		return modulesXMLConfig;
	}
	
	private List<MessageCodeConfig> getMessageCodeConfigs(Element rootNode, ModuleXMLConfig moduleXMLConfig) throws DocumentException{
		Element msgsNode = XMLUtils.getChildNode(rootNode, ToolsConstant.XML_NODE_MESSAGES);
		List<MessageCodeConfig> msgCodeConfigs = null;
		if(msgsNode != null){//messages 不是空
			List<Element> msgNodes = XMLUtils.getChildNodes(msgsNode, ToolsConstant.XML_NODE_MESSAGE);
			if(msgNodes != null && msgNodes.size() != 0){
				msgCodeConfigs = new ArrayList<MessageCodeConfig>();
				for(Element msgNode : msgNodes){
					String msgName = XMLUtils.getAttrStringValue(msgNode, ToolsConstant.XML_ATTRIBUTE_NAME);
					String msgType = XMLUtils.getAttrStringValue(msgNode, ToolsConstant.XML_ATTRIBUTE_TYPE);
					String msgProcesserType = XMLUtils.getAttrStringValue(msgNode, ToolsConstant.XML_ATTRIBUTE_MESSAGE_PROCESSER_TYPE);
					String comments = XMLUtils.getAttrStringValue(msgNode, ToolsConstant.XML_ATTRIBUTE_COMMENTS);
					String msgPackageName = moduleXMLConfig.getMessagePackage();
					boolean isGenerator = moduleXMLConfig.isGenerator();
					String handlerClassPackageName = moduleXMLConfig.getMessageHandlerPackage();
		          	String hexCode = MessageCodeProducer.next();
		          	
		          	MessageCodeConfig msgCodeConfig = new MessageCodeConfig(hexCode, msgName, 
		          			msgType, msgPackageName, isGenerator, comments, 
		          			handlerClassPackageName, msgProcesserType);
		          	msgCodeConfigs.add(msgCodeConfig);
				}
			}
		}
		return msgCodeConfigs;
	}
	
	private List<TemplateDataConfig> getTemplateDataConfigs(Element rootNode, ModuleXMLConfig moduleXMLConfig) throws DocumentException{
		Element tempsNode = XMLUtils.getChildNode(rootNode, ToolsConstant.XML_NODE_TEMPLATES);
		List<TemplateDataConfig> tempDataConfigs = null;
		if(tempsNode != null){//templates 不是空
			List<Element> tempNodes = XMLUtils.getChildNodes(tempsNode, ToolsConstant.XML_NODE_TEMPLATE);
			
			if(tempNodes != null && tempNodes.size() != 0){
				tempDataConfigs = new ArrayList<TemplateDataConfig>();
				for(Element tempNode : tempNodes){
		          	String excelName = XMLUtils.getAttrStringValue(tempNode, ToolsConstant.XML_ATTRIBUTE_NAME);
		        	boolean isGenerator = moduleXMLConfig.isGenerator();
		        	String comments = XMLUtils.getAttrStringValue(tempNode, ToolsConstant.XML_ATTRIBUTE_COMMENTS);
		          	
		        	String tempPackageName = moduleXMLConfig.getTemplatePackage();
		        	
		          	Element fieldsNode = XMLUtils.getChildNode(tempNode, ToolsConstant.XML_NODE_FIELDS);//fields节点
		          	List<TemplateField> tempFields = new ArrayList<TemplateField>();
		          	if(fieldsNode != null){
		          		List<Element> fieldNodes = XMLUtils.getChildNodes(fieldsNode, ToolsConstant.XML_NODE_FIELD);//field节点
		          		 for(Element fieldNode : fieldNodes){
		          			String fieldName = fieldNode.getText();//字段名字
		          			//暂时没有类型，需要从excel中读取字段类型
		          			TemplateField tempField = new TemplateField(fieldName);
		          			
		          			tempFields.add(tempField);
		          		 }
		          	}
		          	TemplateDataConfig config = new TemplateDataConfig(excelName, tempPackageName, isGenerator, comments);
		          	config.setTempFields(tempFields);
		          	tempDataConfigs.add(config);
				}
			}
		}
		return tempDataConfigs;
	}
}
