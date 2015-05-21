package org.oszz.ox.core.event;

public class EventObj {

	private int id;
	public EventObj(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Event id : " + this.id;
	}
}
