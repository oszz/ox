package org.oszz.ox.login.base.conf;

/**
 * excel的默认配置
 * @author ZZ
 *
 */
public enum TemplateDefaultCoifig {

	/**
	 * 数据sheet的索引位置
	 */
	DATA_SHEET(0),
	
	/**
	 * 类型所在的行索引
	 */
	TYPE_ROW(1),
	/**
	 * 名字所在的行索引
	 */
	NAME_ROW(2),
	
	/**
	 * 描述所在的行索引
	 */
	DESC_ROW(3),
	
	/**
	 * 数据开始 的行索引
	 */
	DATA_START_ROW(4),
	
	;
	
	private int index;
	
	private TemplateDefaultCoifig(int index) {
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
	}
}
