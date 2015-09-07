package org.oszz.ox.core.message;

import java.util.Map;

public interface IMessageCodeMapping {

	public Map<Short, Class<? extends IMessageHandler>> getMapping();
}
