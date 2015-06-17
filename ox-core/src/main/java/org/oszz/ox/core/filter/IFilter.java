package org.oszz.ox.core.filter;

import javax.servlet.http.HttpServletRequest;

import org.oszz.ox.core.message.IMessage;


public interface IFilter {

	public IMessage doInputFilter(HttpServletRequest request);
}
