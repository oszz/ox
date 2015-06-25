package org.oszz.ox.core.template;

/**
 * 模板数据接口
 * @author ZZ
 *
 */
public interface ITemplateData {
	
	/**
	 * 设置id
	 * @author ZZ
	 * @param id
	 */
	public void setId(int id);
	
	/**
	 * 获得模板的id
	 * @author ZZ
	 * @return
	 */
	public int getId();

	/**
	 * 读取每一行数据数据时调用
	 * @author ZZ
	 */
	public void check();
	
	/**
	 * 读取完所有的模板数据后，再依次调用每个模板数据的该方法<br>
	 * 可用于检查外键关系等
	 * @author ZZ
	 */
	public void patchUp();
}
