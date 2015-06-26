package org.oszz.ox.tools.module.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.template.conf.TemplateDataConfig;
import org.oszz.ox.tools.template.conf.TemplateField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleXMLLoader {
	
	protected static final Logger log = LoggerFactory.getLogger("ModuleXMLLoader");

	@SuppressWarnings("unchecked")
	public List<TemplateDataConfig> load(String xmlPath)
			throws DocumentException {
		SAXReader reader = new SAXReader();  
        File xmlFile = new File(xmlPath);
         // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(xmlFile);  
        log.info("开始读取：" + xmlFile.getAbsolutePath());
        //获取根节点元素对象  
        Element rootNode = document.getRootElement();  
		
        List<TemplateDataConfig> configs = new ArrayList<TemplateDataConfig>();
        
		List<Element> nodes = rootNode.elements(ToolsConstant.XML_NODE_TEMPLATE);
		
		 for(Element node : nodes){
	        	String excelName = node.attribute(ToolsConstant.XML_ATTRIBUTE_NAME).getStringValue();
	        	String classAllName = node.attribute(ToolsConstant.XML_ATTRIBUTE_CLASS).getStringValue();
	        	boolean isGenerator = Boolean.parseBoolean(node.attribute(ToolsConstant.XML_ATTRIBUTE_IS_GENERATOR).getStringValue());
	        	Element commentsNode = node.element(ToolsConstant.XML_NODE_COMMENTS);//注释的节点
	          	String comments = commentsNode.getText();//注释的内容
	          	
	          	Element fieldsNode = node.element(ToolsConstant.XML_NODE_FIELDS);//fields节点
	          	List<TemplateField> tempFields = new ArrayList<TemplateField>();
	          	if(fieldsNode != null){
	          		List<Element> fieldNodes = fieldsNode.elements(ToolsConstant.XML_NODE_FIELD);//field节点
	          		 for(Element fieldNode : fieldNodes){
	          			String fieldName = fieldNode.getText();//字段名字
	          			//暂时没有类型，需要从excel中读取字段类型
	          			TemplateField tempField = new TemplateField(fieldName);
	          			
	          			tempFields.add(tempField);
	          		 }
	          	}
	          	TemplateDataConfig config = new TemplateDataConfig(excelName, classAllName, isGenerator, comments);
	          	config.setTempFields(tempFields);
	          	configs.add(config);
	        }
		return configs;
	}
}
