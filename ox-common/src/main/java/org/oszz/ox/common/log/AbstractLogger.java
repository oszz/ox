package org.oszz.ox.common.log;

import org.oszz.ox.common.utils.StringUtils;

public abstract class AbstractLogger implements Logger{

	@Override
	public Logger getLogger(String name) {
		if(StringUtils.isBlank(name)){
			return this;
		}
		
		return null;
	}
}
