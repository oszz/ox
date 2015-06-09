package org.oszz.ox.core.filter;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;

/**
 * 默认的过滤链
 * @author ZZ
 *
 */
public class DefaultFilterChain implements IFilterChain{
	
	private List<IFilter> filters;
	
	private int index;
	
	public DefaultFilterChain(boolean isDebug, String charsetName){
		filters = new LinkedList<IFilter>();
		index = 0;
		
		addFilterAtLast(new CharsetFilter(charsetName));
		addFilterAtLast(new DoGetDataFilter(isDebug));
		addFilterAtLast(new DoPostDataFilter());
		addFilterAtLast(new ProtobufFilter());
	}

	@Override
	public void addFilterAtLast(IFilter filter) {
		filters.add(filter);
	}

	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response) {
		if(index == filters.size()){
			return;
		}
		IFilter filter = filters.get(index);
		index++;
		
		filter.doInputFilter(gsSession, request, response, this);
		
	}

}
