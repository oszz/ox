package org.oszz.ox.tools.message.java.loginServer;

import java.util.List;

import org.oszz.ox.tools.message.conf.MessageCodeConfig;
import org.oszz.ox.tools.message.java.JavaAbstractMsgCodeGenerator;
import org.oszz.ox.tools.module.conf.ModuleConfig;

/**
 * MessageCode的生成器
 * @author ZZ
 *
 */
public class JavaLoginServerMsgCodeGenerator extends JavaAbstractMsgCodeGenerator {
	
	public JavaLoginServerMsgCodeGenerator(ModuleConfig moduleConfig, List<MessageCodeConfig> msgCodeConfigs, String msgCode_vmFile) {
		super(moduleConfig, msgCodeConfigs, msgCode_vmFile);
		
	}

	@Override
	public void generate() {
		super.generate();
	}
}
