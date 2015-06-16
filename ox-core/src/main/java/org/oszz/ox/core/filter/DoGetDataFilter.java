package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;

public class DoGetDataFilter implements IFilter {
	
	private boolean isDebug = false;
	
	public DoGetDataFilter(boolean isDebug){
		this.isDebug = isDebug;
	}

	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response) {
		if(isDebug){
			
		}
	}

}
