package org.oszz.ox.tools.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.oszz.ox.common.utils.NameUtils;
import org.oszz.ox.tools.constant.ToolsConstant;

public class ModuleCoifig {

	private List<Module> modules;
	
	public void load(Properties properties){
		modules = new ArrayList<ModuleCoifig.Module>();
		for(Map.Entry<Object, Object> proEntry : properties.entrySet()){
			String name = proEntry.getKey().toString();
			String packageName = proEntry.getValue().toString();
			modules.add(new Module(name, packageName));
		}
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public class Module {
		
		private String name;
		private String packageName;
		private String serviceClassName;
		private String dataManagerClassName;
		
		public Module(String name, String packageName){
			this.name = name;
			this.packageName = packageName;
			this.serviceClassName = NameUtils.getClassName(name) + ToolsConstant.SERVICE_CLASS_NAME_SUFFIX;
			this.dataManagerClassName = ToolsConstant.DATA_HANDLER_CLASS_NAME_PREFIX +
					NameUtils.getClassName(name) + ToolsConstant.DATA_HANDLER_CLASS_NAME_SUFFIX;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public String getServiceClassName() {
			return serviceClassName;
		}

		public void setServiceClassName(String serviceClassName) {
			this.serviceClassName = serviceClassName;
		}

		public String getDataManagerClassName() {
			return dataManagerClassName;
		}

		public void setDataManagerClassName(String dataManagerClassName) {
			this.dataManagerClassName = dataManagerClassName;
		}

	}
}
