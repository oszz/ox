package org.oszz.ox.core.player;

import org.oszz.ox.core.message.IMessage;
import org.oszz.ox.core.session.ISession;

public class Player implements IPlayer {
	
	protected ISession session;
	
	private IHuman human;

	@Override
	public String getId() {
		return null;
	}

	@Override
	public void setSession(ISession session) {
		this.session = session;
	}

	@Override
	public boolean isOnLine() {
		return false;
	}
	
	@Override
	public void sendMessage(IMessage message) {
		session.send(message);
	}

	@Override
	public IHuman getHuman() {
		return this.human;
	}
}
