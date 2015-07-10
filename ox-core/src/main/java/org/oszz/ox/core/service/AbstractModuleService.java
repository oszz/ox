package org.oszz.ox.core.service;

public abstract class AbstractModuleService implements IModuleService {

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
		// TODO Auto-generated method stub

	}

}
