package org.oszz.ox.tools.conf.msg;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.tools.constant.ToolsConstant;

public class Message {
	
	private String hexCode;
	
	private String name; 
	private String type;//="CG" 
	private String processerType;//="ASYN"
	private String comments;//="玩家登陆" 
	private boolean isGenerator;//="true"
	private String packageName;
	
	private String constName;
	private String msgClassName;//生成消息类的类名
	
	private String protoName;//proto类的名称
	private String msgName;//ptoto类中的消息名称
	
	private String handlerMethodName;//处理该消息的方法名
	
	public Message(String hexCode, String name, String type, String processerType,
			String comments, boolean isGenerator, String packageName){
		this.hexCode = hexCode;
		this.name = name;
		this.type = type;
		this.processerType = processerType;
		this.comments = comments;
		this.isGenerator = isGenerator;
		this.packageName = packageName;
		
		this.constName = NameUtils.getConstName(name);//常量名;
		this.msgClassName = NameUtils.getClassName(name.replace(".", "")) + ToolsConstant.MESSAGE_CLASS_NAME_SUFFIX;;
		
		String[] nameStrs = name.split("\\.");//加载xml已检测,这里不必再检查
		this.protoName = nameStrs[0];
		this.msgName = nameStrs[1];
		
		this.handlerMethodName = NameUtils.getMethodOrParaName(msgName) + ToolsConstant.HANDLER_NAME_SUFFIX;
	}
	
	public String getHexCode() {
		return hexCode;
	}

	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getProcesserType() {
		return processerType;
	}

	public void setProcesserType(String processerType) {
		this.processerType = processerType;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isGenerator() {
		return isGenerator;
	}
	public void setGenerator(boolean isGenerator) {
		this.isGenerator = isGenerator;
	}
	
	public String getConstName() {
		return constName;
	}

	public void setConstName(String constName) {
		this.constName = constName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 返回生成消息类的类名
	 * @author ZZ
	 * @return
	 */
	public String getMsgClassName() {
		return msgClassName;
	}

	public void setMsgClassName(String msgClassName) {
		this.msgClassName = msgClassName;
	}
	
	/**
	 * 返回proto类的名称
	 * @author ZZ
	 * @return
	 */
	public String getProtoName() {
		return protoName;
	}

	/**
	 * 返回proto类中的消息名称
	 * @author ZZ
	 * @return
	 */
	public String getMsgName() {
		return msgName;
	}

	/**
	 * 返回处理该消息的方法名
	 * @author ZZ
	 * @return
	 */
	public String getHandlerMethodName() {
		return handlerMethodName;
	}

	public void setHandlerMethodName(String handlerMethodName) {
		this.handlerMethodName = handlerMethodName;
	}

	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
	
}
