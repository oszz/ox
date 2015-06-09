package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oszz.ox.core.session.GSSession;

public class CharsetFilter implements IFilter {
	
	private String charsetName;
	
	public CharsetFilter(String charsetName){
		this.charsetName = charsetName;
	}

	@Override
	public void doInputFilter(GSSession gsSession, HttpServletRequest request,
			HttpServletResponse response, IFilterChain filterChain) {
		// TODO Auto-generated method stub
		
	}

}
