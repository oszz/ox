package org.oszz.ox.tools.message;

public class MessageCodeConf {
	private String name;//协议的原名 LoginProto.CGLogin
	private String constName;//常量名
	private String packageName;//包名
	private String comments;//注释
	private String codeHex;//16进制的code
	public MessageCodeConf(String name, String constName, String packageName, String comments, String codeHex){
		this.name = name;
		this.constName = constName;
		this.packageName = packageName;
		this.comments = comments;
		this.codeHex = codeHex;
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
	
}
