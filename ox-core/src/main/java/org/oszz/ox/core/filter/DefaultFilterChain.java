package org.oszz.ox.core.filter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 默认的过滤链
 * @author ZZ
 *
 */
public class DefaultFilterChain implements IFilterChain{
	
	private Queue<IFilter> filters;
	
	private int index;
	
	public DefaultFilterChain(){
		filters = new LinkedList<IFilter>();
		index = 0;
	}

	@Override
	public void addFilterAtLast(IFilter filter) {
		filters.offer(filter);
	}

	@Override
	public void doIuputFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doOutputFilter() {
		// TODO Auto-generated method stub
		
	}
}
