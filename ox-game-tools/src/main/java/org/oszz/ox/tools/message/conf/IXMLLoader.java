package org.oszz.ox.tools.message.conf;

import java.util.List;

import org.dom4j.DocumentException;

public interface IXMLLoader<T> {

	public List<T> load(String xmlPath) throws DocumentException ;
}
