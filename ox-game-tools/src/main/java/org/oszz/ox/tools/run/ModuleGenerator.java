package org.oszz.ox.tools.run;

import java.util.Properties;

import org.oszz.ox.common.conf.ILoadPropertiesFile;
import org.oszz.ox.common.conf.LoadProperties;
import org.oszz.ox.tools.module.conf.ModuleCoifig;

public class ModuleGenerator {
	
	/**
	 * Module的配置文件
	 */
	private static final String CONIG_FILE_PAHT = "module/module.properties";

	public static void main(String[] args) {
		ILoadPropertiesFile lpf = new LoadProperties();
		Properties confProps = lpf.load(CONIG_FILE_PAHT);
		
		//加载配置
		ModuleCoifig moduleCoifig = lpf.load(confProps, ModuleCoifig.class);
		System.out.println(moduleCoifig);
	}

}
