package org.oszz.ox.common.utils;

/**
 * 方法名的枚举
 * @author ZZ
 *
 */
public enum MethodNameEnum {

	/**
	 * set方法的前缀
	 */
	SETTER_PREFIX("set"),
	
	/**
	 * get方法的前缀
	 */
	GETTER_PREFIX("get"),
	
	/**
	 * is方法的前缀
	 */
	IS_PREFIX("is"),
	
	;
	
	private String name;
	private MethodNameEnum(String name){
		this.name = name;
	}
	
	/**
	 * 返回方法名
	 * @author ZZ
	 * @return 返回方法名
	 */
	public String getName(){
		return this.name;
	}
}
