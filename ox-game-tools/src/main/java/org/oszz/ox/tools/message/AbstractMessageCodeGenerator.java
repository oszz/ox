package org.oszz.ox.tools.message;

import java.util.List;

import org.oszz.ox.common.utils.SystemProperty;
import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.conf.MessageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMessageCodeGenerator extends AbstractMessageGenerator implements IMessageCodeGenerator {
	protected static final Logger log = LoggerFactory.getLogger("JavaGenerator");
	protected static final String JAVA_MESSAGE_CODE_FILE_NAME = "MessageCode.java";
	
	
	protected List<MessageCodeConfig> msgCodeConfigs = null;

	public AbstractMessageCodeGenerator(MessageConfig msgConfig, List<MessageCodeConfig> msgCodeConfigs) {
		super(msgConfig);
		this.msgCodeConfigs = msgCodeConfigs;
	}
	
	@Override
	public String getFullClassName(String packageName, String className) {
		return getFullName(packageName, className)+ SystemProperty.CLASS_SUFFIX.getValue();
	}
	
	@Override
	public String getFullName(String packageName, String className) {
		return packageName + "." + className;
	}
}
