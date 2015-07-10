package org.oszz.ox.core.processer;

import org.oszz.ox.core.service.ISystemService;

public interface IProcesserService extends ISystemService{

	public void setAsynThreadSize(int asynThreadSize);//异步服务的线程池大小
	public void setSceneNum(int sceneNum);//场景线程的数量
}
