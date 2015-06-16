package org.oszz.ox.server.base.processer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.core.service.IService;

/**
 * 消息处理的线程服务
 * @author ZZ
 *
 */
public class ProcesserService implements IService{

	private AsynProcesser asynProcesser;//异步处理器
	private Map<String, SceneProcesser> sceneProcessers;//多个场景处理器
	private WorldProcesser worldProcesser;//一个世界处理器
	
	private int asynThreadSize;//异步服务的线程池大小
	private int sceneNum;//场景线程的数量
	
	/**
	 * 构建一个消息处理的服务
	 * @param asynThreadSize 异步服务的线程池大小
	 * @param sceneNum 场景线程的数量
	 */
	public ProcesserService(int asynThreadSize, int sceneNum){
		this.asynThreadSize = asynThreadSize;
		this.sceneNum = sceneNum;
	}

	public AsynProcesser getAsynProcesser() {
		return asynProcesser;
	}

	public Map<String, SceneProcesser> getSceneProcessers() {
		return sceneProcessers;
	}

	public WorldProcesser getWorldProcesser() {
		return worldProcesser;
	}

	@Override
	public boolean create() {
		return true;
	}

	@Override
	public boolean init() {
		asynProcesser = new AsynProcesser(asynThreadSize);
		worldProcesser = new WorldProcesser();
		sceneProcessers = new ConcurrentHashMap<String, SceneProcesser>();
		for(int i=0;i<sceneNum;i++){
			SceneProcesser sceneProcesser = new SceneProcesser();
			sceneProcessers.put(i+"", sceneProcesser);
		}
		return true;
	}

	@Override
	public boolean start() {
//		asynProcesser.start();
		worldProcesser.start();
		for(Map.Entry<String, SceneProcesser> sceneProcesserEntry : sceneProcessers.entrySet()){
			SceneProcesser sp = sceneProcesserEntry.getValue();
			sp.start();
		}
		return true;
	}

	@Override
	public boolean restart() {
		stop();
		init();
		start();
		return true;
	}

	@Override
	public boolean stop() {
		asynProcesser.stop();
		worldProcesser.stop();
		for(Map.Entry<String, SceneProcesser> sceneProcesserEntry : sceneProcessers.entrySet()){
			SceneProcesser sp = sceneProcesserEntry.getValue();
			sp.stop();
		}
		return true;
	}
	
}
