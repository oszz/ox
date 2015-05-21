package org.oszz.ox.common.utils;


import java.util.Properties;

/**
 * 系统的相关属性
 * @author ZZ
 * @since 1.0.0
 */
public enum SystemProperty {
	
	
	/**
	 * 类路径
	 */
	JAVA_CLASS_PATH("java.class.path"){
		@Override
		public String getValue() {
			return props.getProperty(this.getKey());
		}
	},
	
	/**
	 * 文件分隔符
	 */
	FILE_SEPARATOR("file.separator") {
		@Override
		public String getValue() {
			return props.getProperty(this.getKey());
		}
	},
	
	/**
	 * 系统路径分隔符<br>
	 * widows是 ';'
	 * Linux是 ':'
	 */
	PATH_SEPARATOR("path.separator") {
		@Override
		public String getValue() {
			return props.getProperty(this.getKey());
		}
	},
	
	/**
	 * 换行符
	 */
	LINE_SEPARATOR("line.separator") {
		@Override
		public String getValue() {
			return props.getProperty(this.getKey());
		}
	},
	
	
	/**
	 * 工作目录
	 */
	USRE_DIR("user.dir") {
		@Override
		public String getValue() {
			return props.getProperty(this.getKey());
		}
	},
	
	/**
	 * 包路径分隔符
	 */
	PACKAGE_SEPARATOR(".") {
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	
	/**
	 * 类文件的后缀
	 */
	CLASS_SUFFIX(".class") {
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	/**
	 * 内部类的分隔符
	 */
	INNER_CLASS_SEPARATOR("$"){
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	/**
	 * 下划线：变量的连接符
	 */
	UNDERLINE_CHAR("_"){
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	
	/**
	 * 制表符，缩进符
	 */
	TABLE_CHAR("\t"){
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	
	/**
	 * 短横线：-
	 */
	SHORT_LINE_CHAR("-"){
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	
	/**
	 * 空字符串：""
	 */
	EMPTY_STRING(""){
		@Override
		public String getValue() {
			return this.getKey();
		}
	},
	
	;
	private static Properties props = System.getProperties();
	private String key;//属性对应的key
	
	private SystemProperty(String key){
		this.key = key;
	}
	
	/**
	 * 返回属性值
	 * @author ZZ
	 * @return 返回属性值
	 * @since 1.0.0
	 */
	public abstract String getValue();
	
	/**
	 * 返回属性key
	 * @author ZZ
	 * @return 返回属性key
	 * @since 1.0.0
	 */
	protected String getKey(){
		return this.key;
	}
}
