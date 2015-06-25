package org.oszz.ox.core.service;

import java.util.Map;

public interface IAllModuleService extends IService {

	public Map<Class<? extends IService>, IService> getAllService();
}
