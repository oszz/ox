package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.oszz.ox.core.message.IMessage;

/**
 * 过滤Get请求的数据
 * @author ZZ
 *
 */
public class DoGetDataFilter implements IFilter {
	
	private boolean isDebug = false;//是否是debug
	
	/**
	 * 构建一个GET请求的过滤器<br>
	 * 注：只有debug模式，才会接受GET请求
	 * @param isDebug 是否是debug模式
	 */
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
