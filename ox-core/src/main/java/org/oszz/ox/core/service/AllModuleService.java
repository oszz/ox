package org.oszz.ox.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oszz.ox.common.utils.ClassUtils;

public class AllModuleService extends AbstractService implements
		IAllModuleService {
	
	private Map<Class<? extends IService>, IService> allServices;
	
	@Override
	public boolean create() {
		allServices = new HashMap<Class<? extends IService>, IService>();
		return true;
	}
	@Override
	public boolean init() {
		List<Class<? extends IService>> serviceClasses = ModuleServiceClassHolder.getInstance().getAllClasses();
		for(Class<? extends IService> serviceClass : serviceClasses){
			allServices.put(serviceClass, ClassUtils.newInstance(serviceClass));
		}
		return true;
	}

	@Override
	public Map<Class<? extends IService>, IService> getAllService() {
		return allServices;
	}
	
	@Override
	public void onInitialized() {
		// TODO Auto-generated method stub
		
	}

}
