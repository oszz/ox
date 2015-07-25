package org.oszz.ox.core.processer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.oszz.ox.core.player.IPlayer;
import org.oszz.ox.core.processer.AbstractSingleProcesser;

/**
 * 场景线程
 * @author ZZ
 *
 */
public class SceneProcesser extends AbstractSingleProcesser implements ISceneProcesser{
	
	private Map<String, IPlayer> players;//场景线程中的玩家

	public SceneProcesser(){
		super();
		players = new ConcurrentHashMap<String, IPlayer>();
	}

	@Override
	public int getPlayerNum() {
		return players.size();
	}

	@Override
	public boolean contains(IPlayer player) {
		String playerId = player.getId();
		return players.containsKey(playerId);
	}

	@Override
	public void add(IPlayer player) {
		String playerId = player.getId();
		players.put(playerId, player);
	}

	@Override
	public void remove(IPlayer player) {
		String playerId = player.getId();
		players.remove(playerId);
	}
	
	
}
