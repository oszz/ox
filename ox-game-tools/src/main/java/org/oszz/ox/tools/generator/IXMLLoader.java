package org.oszz.ox.tools.generator;

import org.dom4j.DocumentException;

public interface IXMLLoader<T> {

//	public List<T> load(String xmlPath) throws DocumentException ;
	
	public T load(String xmlPath) throws DocumentException ;
}
