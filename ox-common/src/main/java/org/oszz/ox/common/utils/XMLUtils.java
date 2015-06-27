package org.oszz.ox.common.utils;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * XML相关工具
 * @author ZZ
 *
 */
public class XMLUtils {

	public static Element getRootElement(String xmlFilePath) throws DocumentException{
		SAXReader reader = new SAXReader();  
	    File xmlFile = new File(xmlFilePath);
	    // 通过read方法读取一个文件 转换成Document对象  
	    Document document = reader.read(xmlFile);  
	    //获取根节点元素对象  
	    Element rootNode = document.getRootElement(); 
	    return rootNode;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> getChildNodes(Element parentNode, String childNodeName) throws DocumentException{
		List<Element> childEles = parentNode.elements(childNodeName);
		return childEles;
	}
	
	public static Element getChildNode(Element parentNode, String childNodeName) {
		return parentNode.element(childNodeName);
	}
	
	public static String getAttrStringValue(Element node, String attributeName) {
		return node.attribute(attributeName).getStringValue();
	}
	
	public static int getAttrIntValue(Element node, String attributeName) {
		return Integer.parseInt(getAttrStringValue(node, attributeName));
	}
	public static double getAttrDoubleValue(Element node, String attributeName) {
		return Double.parseDouble(getAttrStringValue(node, attributeName));
	}
	
	public static boolean getAttrBooleanValue(Element node, String attributeName) {
		return Boolean.parseBoolean(getAttrStringValue(node, attributeName));
	}
	
	 
}
