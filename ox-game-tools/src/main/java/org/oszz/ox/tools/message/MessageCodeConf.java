package org.oszz.ox.tools.message;

import org.oszz.ox.common.utils.NameUtils;

public class MessageCodeConf {
	/**
	 * 消息处理类的名字后缀
	 */
	private static final String HANDLER_CLASS_NAME_SUFFIX = "Hnadler";
	
	private String name;//协议的原名 LoginProto.CGLogin
	private String constName;//常量名
	private String packageName;//包名
	private String comments;//注释
	private String codeHex;//16进制的code
	
	private String handlerClassName;//生成Handler时的类名

	public MessageCodeConf(String name, String packageName, 
			String comments, String codeHex){
		this.name = name;
		this.packageName = packageName;
		this.comments = comments;
		this.codeHex = codeHex;
		
		String nameStr = name.replace(".", "");//去掉名字里的点
		this.constName = NameUtils.getConstName(nameStr);//常量名
		this.handlerClassName = NameUtils.getClassName(nameStr) + HANDLER_CLASS_NAME_SUFFIX;//消息处理类的名字
	}
	
	public String getName() {
		return name;
	}

	public String getConstName() {
		return constName;
	}
	public String getPackageName() {
		return packageName;
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
	
}
