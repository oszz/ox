package org.oszz.ox.tools.conf.msg;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.oszz.ox.common.utils.XMLUtils;
import org.oszz.ox.tools.constant.ToolsConstant;
import org.oszz.ox.tools.module.MessageCodeProducer;

public class MessagesXMLLoader {

	public List<Message> load(String xmlFilePath) throws Exception{
        Element rootNode = XMLUtils.getRootElement(xmlFilePath);
        List<Message> messages = getMessages(rootNode);
		return messages;
	}
	
	private List<Message> getMessages(Element rootNode) throws DocumentException{
		List<Element> messageNodes = XMLUtils.getChildNodes(rootNode, ToolsConstant.XML_NODE_MESSAGE);
		
		List<Message> messages = new ArrayList<Message>();
        
        for(Element messageNode : messageNodes){
        	String hexCode = MessageCodeProducer.next();
        	String name = XMLUtils.getAttrStringValue(messageNode, ToolsConstant.XML_ATTRIBUTE_NAME);
        	String type = XMLUtils.getAttrStringValue(messageNode, ToolsConstant.XML_ATTRIBUTE_TYPE);
        	String processerType = XMLUtils.getAttrStringValue(messageNode, ToolsConstant.XML_ATTRIBUTE_MESSAGE_PROCESSER_TYPE);
        	String comments = XMLUtils.getAttrStringValue(messageNode, ToolsConstant.XML_ATTRIBUTE_COMMENTS);
        	boolean isGenerator = XMLUtils.getAttrBooleanValue(messageNode, ToolsConstant.XML_ATTRIBUTE_IS_GENERATOR);
        	String packageName = XMLUtils.getAttrStringValue(messageNode, ToolsConstant.XML_ATTRIBUTE_PACKAGE);
        	
        	Message message = new Message(hexCode, name, type, processerType, comments, isGenerator, packageName);
        	
        	messages.add(message);
        }
        return messages;
	}
}
