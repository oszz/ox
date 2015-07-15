package org.oszz.ox.login.base.scene;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.oszz.ox.core.player.IPlayer;

public class Scene implements IScene {
	
	private Set<String> playerIds;
	
	public Scene(){
		playerIds = Collections.synchronizedSet(new HashSet<String>());
	}

	@Override
	public Set<String> getAllPlayerIds() {
		return playerIds;
	}

	@Override
	public void putPlayer(IPlayer player) {
		putPlayer(player.getId());
	}

	@Override
	public void putPlayer(String playerId) {
		playerIds.add(playerId);
	}

	@Override
	public boolean isContains(IPlayer player) {
		return isContains(player.getId());
	}

	@Override
	public boolean isContains(String playerId) {
		return playerIds.contains(playerId);
	}

	@Override
	public int getPlayerNum() {
		return playerIds.size();
	}

}
