package org.oszz.ox.tools.message;

import org.oszz.ox.common.utils.NameUtils;

public class MessageCodeConf {
	/**
	 * 消息处理类的名字后缀
	 */
	private static final String HANDLER_CLASS_NAME_SUFFIX = "Hnadler";
	private static final String MESSAGE_CLASS_NAME_SUFFIX = "Message";
	
//	private String nameAndPackage;//协议的原名 org.oszz.ox.server.module.auth.msg.AuthProto.CGLogin
	private String constName;//常量名
	private String handlerClassPackageName;//消息处理类的包名
	private String comments;//注释
	private String codeHex;//16进制的code
	
	private String handlerClassName;//生成Handler时的类名
	private String msgPackageName;//message的包名
	private String msgClassNameAndParent;//message的包名

	public MessageCodeConf(String nameAndPackage, String handlerClassPackageName, 
			String comments, String codeHex){
//		String nameAndPackage = nameAndPackage;//协议的原名 org.oszz.ox.server.module.auth.msg.AuthProto.CGLogin
		
		this.handlerClassPackageName = handlerClassPackageName;
		this.comments = comments;
		this.codeHex = codeHex;
		
		int lastPointIndex = nameAndPackage.lastIndexOf(".");
		String msgClassName = nameAndPackage.substring(lastPointIndex + 1 );
		nameAndPackage = nameAndPackage.substring(0, lastPointIndex);
		lastPointIndex = nameAndPackage.lastIndexOf(".");//这个时候，其实是倒数第二点
		String msgParentClassName = nameAndPackage.substring(lastPointIndex  + 1);
		this.msgPackageName = nameAndPackage.substring(0, lastPointIndex);
		
		String constNameStr = msgParentClassName +  msgClassName;
		this.msgClassNameAndParent = NameUtils.getClassName(constNameStr) + MESSAGE_CLASS_NAME_SUFFIX;
		this.constName = NameUtils.getConstName(constNameStr);//常量名
		this.handlerClassName = NameUtils.getClassName(constNameStr) + HANDLER_CLASS_NAME_SUFFIX;//消息处理类的名字
	}
	
	public String getMsgClassName() {
		return msgClassNameAndParent;
	}

	public String getConstName() {
		return constName;
	}
	public String getHandlerClassPackageName() {
		return handlerClassPackageName;
	}
	public String getComments() {
		return comments;
	}
	public String getCodeHex() {
		return codeHex;
	}

	public String getHandlerClassName() {
		return handlerClassName;
	}

	public String getMsgPackageName() {
		return msgPackageName;
	}
}
