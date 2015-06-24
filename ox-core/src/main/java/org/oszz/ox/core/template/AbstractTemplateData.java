package org.oszz.ox.core.template;

import org.oszz.ox.common.utils.ClassUtils;

/**
 * 抽象的模板数据类，所有的模板数据都应该继承该类
 * @author ZZ
 *
 */
public abstract class AbstractTemplateData implements ITemplateData {
	
	protected int id;
	

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
