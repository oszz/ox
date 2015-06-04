package org.oszz.ox.core.filter;

/**
 * 过滤链
 * @author ZZ
 *
 */
public interface IFilterChain extends IFilter{

	public void addFilterAtLast(IFilter filter);
}
