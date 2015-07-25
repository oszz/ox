package org.oszz.ox.core.processer;

import org.oszz.ox.core.player.IPlayer;

public interface ISceneProcesser {

	/**
	 * 返回当前场景线程的玩家数量
	 * @author ZZ
	 * @return 返回当前场景线程的玩家数量
	 */
	public int getPlayerNum();
	
	/**
	 * 当前场景线程是否包含某个玩家
	 * @author ZZ
	 * @param player 玩家
	 * @return 如果包含返回<tt>true<tt>,否则返回<tt>false<tt>
	 */
	public boolean contains(IPlayer player);
	
	/**
	 * 向当前场景线程添加一个玩家
	 * @author ZZ
	 * @param player 玩家
	 */
	public void add(IPlayer player);
	
	/**
	 * 从当前场景线程删除一个玩家
	 * @author ZZ
	 * @param player 玩家
	 */
	public void remove(IPlayer player);
}
