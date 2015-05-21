package org.oszz.ox.core.event;

public class Test {

	public static void main(String[] args) {
		Event<EventObj> e = new Event<EventObj>("key", new EventObj(1));
		
		
		EventManager.getInstance().addListener(e, new MyEventHandler());;
		
		EventManager.getInstance().dispatch(e);

	}

}
