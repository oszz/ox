package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;

public class ProtobufFilter implements IFilter {

	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response, IFilterChain filterChain) {
		// TODO Auto-generated method stub

		
		filterChain.doInputFilter(gsSession, request, response);
	}

}
