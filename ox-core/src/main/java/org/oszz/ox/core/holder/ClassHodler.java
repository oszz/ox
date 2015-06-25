package org.oszz.ox.core.holder;

import java.util.ArrayList;
import java.util.List;

public class ClassHodler<T> implements IHodler<T>{
	
	private List<T> allClasses;
	
	public ClassHodler(){
		allClasses = new ArrayList<T>();
	}

	@Override
	public void put(T Clazz) {
		allClasses.add(Clazz);
	}

	@Override
	public List<T> getAllClasses() {
		return allClasses;
	}

}
