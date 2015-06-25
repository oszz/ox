package org.oszz.ox.core.service;

/**
 * 将IService所有的的方法默认实现
 * @author ZZ
 *
 */
public abstract class AbstractService implements IService {

	@Override
	public boolean create() {
		return true;
	}

	@Override
	public boolean init() {
		return true;
	}
	
	@Override
	public void onInitialized() {

	}

}
