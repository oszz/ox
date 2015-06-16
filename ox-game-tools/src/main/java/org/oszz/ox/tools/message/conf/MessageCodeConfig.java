package org.oszz.ox.tools.message.conf;

import org.oszz.ox.common.utils.NameUtils;


public class MessageCodeConfig {

	private String hexCode;
	private String msgName;
	private String msgType;
	private String msgPackageName;
	private boolean isGenerator;
	private String comments;
	
	private String handlerClassPackageName;
	
	private String constName;
	
	public MessageCodeConfig(String hexCode, String msgName, String msgType, String msgPackageName,
			boolean isGenerator, String comments, String handlerClassPackageName){
		this.hexCode = hexCode;
		this.msgName = msgName;
		this.msgType = msgType;
		this.msgPackageName = msgPackageName;
		this.isGenerator = isGenerator;
		this.comments = comments;
		this.handlerClassPackageName = handlerClassPackageName;
		
		this.constName = NameUtils.getConstName(msgName);//常量名;
	}
	

	public String getHexCode() {
		return hexCode;
	}

	public String getMsgName() {
		return msgName;
	}

	public String getMsgType() {
		return msgType;
	}

	public String getMsgPackageName() {
		return msgPackageName;
	}

	public boolean isGenerator() {
		return isGenerator;
	}

	public String getComments() {
		return comments;
	}

	public String getHandlerClassPackageName() {
		return handlerClassPackageName;
	}
	
	/**
	 * 返回消息code的常量名
	 * @author ZZ
	 * @return 返回消息code的常量名
	 */
	public String getConstName(){
		return this.constName;
	}
	
	/**
	 * 返回消息class的类名
	 * @author ZZ
	 * @return 返回消息class的类名
	 */
	public String getMsgClassName(){
		//去掉点
		return NameUtils.getClassName(msgName.replace(".", "")) + MessageConstant.MESSAGE_CLASS_NAME_SUFFIX;
	}
	
	/**
	 * 返回消息处理class的类名
	 * @author ZZ
	 * @return 返回消息处理class的类名
	 */
	public String getMsgHandlerClassName(){
		//去掉点
		return NameUtils.getClassName(msgName.replace(".", "")) + MessageConstant.HANDLER_CLASS_NAME_SUFFIX;
	}
	
	
	
}