package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.message.IMessageGenerator;
import org.oszz.ox.tools.message.JavaMsgGenerator;
import org.oszz.ox.tools.message.MessageConfig;

public class MessageGenerator {
	
	private static final String CONIG_FILE_PAHT = "message.properties";

	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		MessageConfig msgConfig = lpf.load(confProps, MessageConfig.class);
		
		IMessageGenerator javaMsgGenerator = new JavaMsgGenerator(msgConfig);
		javaMsgGenerator.generate();
	}
}
