package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;

/**
 * 过滤链
 * @author ZZ
 *
 */
public interface IFilterChain {

	public void addFilterAtLast(IFilter filter);
	
	public void doInputFilter(GSSession gsSession, HttpServletRequest request, HttpServletResponse response);
}
