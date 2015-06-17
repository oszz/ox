package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.oszz.ox.core.message.IMessage;

/**
 * 过滤Get请求的数据
 * @author ZZ
 *
 */
public class DoGetDataFilter implements IFilter {
	
	private boolean isDebug = false;
	
	public DoGetDataFilter(boolean isDebug){
		this.isDebug = isDebug;
	}

	@Override
	public IMessage doInputFilter( HttpServletRequest request) {
		if(isDebug){
			
		}
		return null;
	}

}
