package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;


public interface IFilter {

	public void doInputFilter(GSSession gsSession, HttpServletRequest request, 
			HttpServletResponse response);
	
}
