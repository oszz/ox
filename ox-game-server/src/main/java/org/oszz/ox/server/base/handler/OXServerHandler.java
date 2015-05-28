package org.oszz.ox.server.base.handler;

import org.oszz.ox.core.IPlayer;
import org.oszz.ox.core.server.AbstractJettyHandler;


@SuppressWarnings("unchecked")
public class OXServerHandler extends AbstractJettyHandler{

	@Override
	public <T> void handle(IPlayer palyer, T para) {
		System.out.println("OXServerHandler OXServerHandler");
	}

}
