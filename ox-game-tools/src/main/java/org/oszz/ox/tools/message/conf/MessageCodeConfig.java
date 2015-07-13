package org.oszz.ox.tools.message.conf;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.tools.constant.ToolsConstant;


public class MessageCodeConfig {

	private String hexCode;
	private String msgName;
	private String msgType;
	private String msgPackageName;
	private boolean isGenerator;
	private String comments;
	
	private String handlerClassPackageName;
	
	private String constName;
	
	private String messageProcesserType;
	
	public MessageCodeConfig(String hexCode, String msgName, String msgType, String msgPackageName,
			boolean isGenerator, String comments, String handlerClassPackageName, 
			String messageProcesserType){
		this.hexCode = hexCode;
		this.msgName = msgName;
		this.msgType = msgType;
		this.msgPackageName = msgPackageName;
		this.isGenerator = isGenerator;
		this.comments = comments;
		this.handlerClassPackageName = handlerClassPackageName;
		this.messageProcesserType = messageProcesserType;
		
		
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
	
	public String getMessageProcesserType() {
		return messageProcesserType;
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
		return NameUtils.getClassName(msgName.replace(".", "")) + ToolsConstant.MESSAGE_CLASS_NAME_SUFFIX;
	}
	
	/**
	 * 返回消息处理class的类名
	 * @author ZZ
	 * @return 返回消息处理class的类名
	 */
	public String getMsgHandlerClassName(){
		//去掉点
		return NameUtils.getClassName(msgName.replace(".", "")) + ToolsConstant.HANDLER_CLASS_NAME_SUFFIX;
	}
	
	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
	
	
}
