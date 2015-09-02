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
	private String msgClassName;
	
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

	public String getMsgClassName() {
		return msgClassName;
	}

	public void setMsgClassName(String msgClassName) {
		this.msgClassName = msgClassName;
	}

	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
	
}
