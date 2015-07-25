package org.oszz.ox.core.processer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.core.player.IPlayer;

/**
 * 消息处理的线程服务
 * @author ZZ
 *
 */
public class ProcesserService implements IProcesserService {

	private AsynProcesser asynProcesser;//异步处理器
	private Map<String, SceneProcesser> sceneProcessers;//多个场景处理器
	private WorldProcesser worldProcesser;//一个世界处理器
	
	private int asynThreadSize;//异步服务的线程池大小
	private int sceneNum;//场景线程的数量
	
//	/**
//	 * 构建一个消息处理的服务
//	 * @param asynThreadSize 异步服务的线程池大小
//	 * @param sceneNum 场景线程的数量
//	 */
//	public ProcesserService(int asynThreadSize, int sceneNum){
//		this.asynThreadSize = asynThreadSize;
//		this.sceneNum = sceneNum;
//	}
	@Override
	public void setAsynThreadSize(int asynThreadSize) {
		this.asynThreadSize = asynThreadSize;
	}

	@Override
	public void setSceneNum(int sceneNum) {
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
	public void onInitialized() {
		// TODO 什么都不做
		
	}

	@Override
	public void start() {
		asynProcesser.start();
		worldProcesser.start();
		for(Map.Entry<String, SceneProcesser> sceneProcesserEntry : sceneProcessers.entrySet()){
			SceneProcesser sp = sceneProcesserEntry.getValue();
			sp.start();
		}
	}

	@Override
	public IProcesser getSceneProcesser(IPlayer player) {
		SceneProcesser sceneProcesser = null;
		String spKeyForMinPlayers = null;//最少玩家的场景线程的key
		int playerNums = 0;//场景线程的玩家数量
		Map<String, SceneProcesser> sceneProcessers = getSceneProcessers();
		for(Map.Entry<String, SceneProcesser> spEntry : sceneProcessers.entrySet()){
			String key = spEntry.getKey();
			SceneProcesser currentSceneProcesser = spEntry.getValue();
			int currentPlayerNums = currentSceneProcesser.getPlayerNum();
			if (currentSceneProcesser.contains(player)) {
				sceneProcesser = currentSceneProcesser;
				break;
			} else {
				if (spKeyForMinPlayers == null) {
					spKeyForMinPlayers = key;
					playerNums = currentPlayerNums;
				}else{
					if (playerNums > currentPlayerNums) {
						spKeyForMinPlayers = key;
						playerNums = currentPlayerNums;
					}
				}
			}
		}
		if (sceneProcesser == null) {
			sceneProcesser = sceneProcessers.get(spKeyForMinPlayers);
		}
		
		return sceneProcesser;
	}
	
}
