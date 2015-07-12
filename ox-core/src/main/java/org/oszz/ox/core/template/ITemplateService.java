package org.oszz.ox.core.template;

import java.util.Map;

import org.oszz.ox.core.service.ISystemService;

/**
 * 模板数据服务接口
 * @author ZZ
 *
 */
public interface ITemplateService extends ISystemService{
	
	/**
	 * 根据id获得某个Class<T extends ITemplateData>的模板数据
	 * @author ZZ
	 * @param id 模板id
	 * @param clazz 模板数据class
	 * @return 返回id对应的某行模板数据
	 */
	public <T extends ITemplateData> T get(int id, Class<T> clazz);
	
	/**
	 * 获得某个Class<T extends ITemplateData>的所有模板数据
	 * @author ZZ
	 * @param clazz 模板数据class
	 * @return 返回所有模板数据
	 */
	public <T extends ITemplateData> Map<Integer, T> getAll(Class<T> clazz);
	
	public void setTemplateConfig(ITemplateConfig templateConfig);

}
