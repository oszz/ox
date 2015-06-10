package org.oszz.ox.tools.message;

import java.io.File;
import java.util.List;

public abstract class AbstractMessageGenerator implements IMessageGenerator {
	
	protected MessageConfig msgConfig;
	
	public AbstractMessageGenerator(MessageConfig msgConfig){
		this.msgConfig = msgConfig;
	}

	@Override
	public String getAbsoluteInputPath() {
		return null;
	}
	
	@Override
	public String getAbsoluteOutputPath() {
		return null;
	}
	
	@Override
	public List<File> getProtoFiles() {
		return null;
	}
}
