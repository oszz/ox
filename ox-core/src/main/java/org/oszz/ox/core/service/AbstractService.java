package org.oszz.ox.core.service;

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
	public boolean start() {
		return true;
	}

	@Override
	public boolean restart() {
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

}
