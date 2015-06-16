package org.oszz.ox.tools.message.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.oszz.ox.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageXMLLoader implements IXMLLoader<MessageCodeConfig> {
	protected static final Logger log = LoggerFactory.getLogger("XMLLoader");
	private static int CODE_START = 0x0001;

    @SuppressWarnings("unchecked")
	@Override
	public List<MessageCodeConfig> load(String xmlPath) throws DocumentException {
		 // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        File xmlFile = new File(xmlPath);
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(xmlFile);  
        log.info("开始读取：" + xmlFile.getAbsolutePath());
        //获取根节点元素对象  
        Element rootNode = document.getRootElement();  
        
		List<Element> msgNodes = rootNode.elements(MessageConstant.XML_NODE_MESSAGE);
		
		List<MessageCodeConfig> msgCodeConfigs = new ArrayList<MessageCodeConfig>();
        
        for(Element msgNode : msgNodes){
        	String msgName = msgNode.attribute(MessageConstant.XML_ATTRIBUTE_NAME).getStringValue();
        	String msgType = msgNode.attribute(MessageConstant.XML_ATTRIBUTE_TYPE).getStringValue();
        	String msgPackageName = msgNode.attribute(MessageConstant.XML_ATTRIBUTE_PACEAGE_NAME).getStringValue();
        	boolean isGenerator = Boolean.parseBoolean(msgNode.attribute(MessageConstant.XML_ATTRIBUTE_IS_GENERATOR).getStringValue());
        	String messageProcesserType = msgNode.attribute(MessageConstant.XML_ATTRIBUTE_MESSAGE_PROCESSER_TYPE).getStringValue();
          
        	Element commentsNode = msgNode.element(MessageConstant.XML_NODE_COMMENTS);//注释的节点
          	String comments = commentsNode.getText();//注释的内容
          	String handlerClassPackageName = "";
          	if(msgType.equalsIgnoreCase(MessageConstant.MESSAGE_CG_TYPE)){//如果是CG类型的消息，需要读取handler节点
          		Element handlerNode = msgNode.element(MessageConstant.XML_NODE_HANDLER);//注释的节点
          		if(handlerNode != null){
          			handlerClassPackageName = handlerNode.attribute(MessageConstant.XML_ATTRIBUTE_PACEAGE_NAME).getStringValue();
          		}else{
          			log.error("出错：{}没有配置Handler节点，因为该消息是CG类型的消息,必须有handler处理节点，并标明包名", msgName);
          			throw new RuntimeException("出错：" + msgName + "没有配置Handler节点，因为该消息是CG类型的消息,必须有handler处理节点，并标明包名");
          		}
          	}
          	String hexCode = StringUtils.toHex(CODE_START++);
          	MessageCodeConfig msgCodeConfig = new MessageCodeConfig(hexCode, msgName, msgType, msgPackageName, isGenerator, comments, handlerClassPackageName, messageProcesserType);
          	msgCodeConfigs.add(msgCodeConfig);
        }
        return msgCodeConfigs;
	}

}
