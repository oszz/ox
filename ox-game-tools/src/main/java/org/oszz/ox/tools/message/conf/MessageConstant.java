package org.oszz.ox.tools.message.conf;

public interface MessageConstant {

	/**
	 * 消息处理类的名字后缀
	 */
	public static final String HANDLER_CLASS_NAME_SUFFIX = "Hnadler";
	
	/**
	 * 消息类的名字后缀
	 */
	public static final String MESSAGE_CLASS_NAME_SUFFIX = "Message";
	
	
	/**
	 * XML的message节点名称
	 */
	public static final String XML_NODE_MESSAGE = "message";
	
	/**
	 * XML的comments注释节点名称
	 */
	public static final String XML_NODE_COMMENTS = "comments";
	
	/**
	 * XML的handler处理类节点名称
	 */
	public static final String XML_NODE_HANDLER = "handler";
	
	/**
	 * XML的name属性名称
	 */
	public static final String XML_ATTRIBUTE_NAME = "name";
	
	/**
	 * XML的type属性名称
	 */
	public static final String XML_ATTRIBUTE_TYPE = "type";
	
	/**
	 * XML的isGenerator属性名称
	 */
	public static final String XML_ATTRIBUTE_IS_GENERATOR = "isGenerator";
	
	/**
	 * XML的paceageName属性名称
	 */
	public static final String XML_ATTRIBUTE_PACEAGE_NAME = "paceageName";
	
	/**
	 * 消息类型：GC 服务端到客户端的消息
	 */
	public static final String MESSAGE_GC_TYPE = "GC";
	
	/**
	 * 消息类型：CG 客户端到服务端的消息
	 */
	public static final String MESSAGE_CG_TYPE = "CG";
}
