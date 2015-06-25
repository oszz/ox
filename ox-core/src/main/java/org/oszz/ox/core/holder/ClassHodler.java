package org.oszz.ox.core.holder;

import java.util.ArrayList;
import java.util.List;

/**
 * 类持有者
 * @author ZZ
 *
 * @param <T>
 */
public class ClassHodler<T> implements IHodler<T>{
	
	private List<T> allClasses;
	
	public ClassHodler(){
		allClasses = new ArrayList<T>();
	}

	@Override
	public void put(T t) {
		allClasses.add(t);
	}

	@Override
	public List<T> getAllClasses() {
		return allClasses;
	}

}
