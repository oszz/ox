package org.oszz.ox.server.base.scene;

import java.util.Set;

import org.oszz.ox.core.player.IPlayer;

/**
 * 场景接口
 * @author ZZ
 *
 */
public interface IScene {

	public Set<String> getAllPlayerIds();
	
	public int getPlayerNum();
	
	public void putPlayer(IPlayer player);
	
	public void putPlayer(String playerId);
	
	public boolean isContains(IPlayer player);
	
	public boolean isContains(String playerId);
}
