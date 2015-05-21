package org.oszz.ox.core.event;

public class MyEventHandler implements IEventHandler {

	@Override
	public <T> void handler(T para) {
		System.out.println(" MyEventHandler handler: " + para);
	}

}
