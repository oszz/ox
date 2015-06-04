package org.oszz.ox.core.filter;

import java.util.LinkedList;
import java.util.List;

/**
 * 默认的过滤链
 * @author ZZ
 *
 */
public class DefaultFilterChain implements IFilterChain{
	
	private List<IFilter> filters;
	
	private int index;
	
	public DefaultFilterChain(){
		filters = new LinkedList<IFilter>();
		index = 0;
	}

	@Override
	public void doFilter() {
		IFilter filter = filters.get(index);
		if(filter != null){
			filter.doFilter();
			index++;
		}
	}

	@Override
	public void addFilterAtLast(IFilter filter) {
		filters.add(filter);
	}

	@Override
	public boolean isFinishedFilter() {
		return index >= filters.size();
	}

}
