package org.oszz.ox.tools.launch;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.conf.Config;
import org.oszz.ox.tools.conf.ConfigManager;
import org.oszz.ox.tools.generator.IGenerator;
import org.oszz.ox.tools.launch.message.java.JavaMessageGenerator;

public class Generator implements IGenerator{
	
	private static final String CONIG_FILE_PAHT = "conf.properties";
	
	/**
	 * 消息的配置文件
	 */
	private static final String MESSAGES_XML_PATH = "conf/message/messages.xml";

	public static void main(String[] args) {
		Generator generator = new Generator();
		generator.generate();
	}

	@Override
	public void generate() {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		//加载配置
		Config config = lpf.load(confProps, Config.class);
		
		messageGenerator(config);
	}
	
	private void messageGenerator(Config config){
		ConfigManager.getInstance().init(MESSAGES_XML_PATH);
		
		JavaMessageGenerator jmg = new JavaMessageGenerator(config);
		jmg.generate();
	}
}
