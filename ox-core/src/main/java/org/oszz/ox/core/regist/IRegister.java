package org.oszz.ox.core.regist;


public interface IRegister<T> {

	public void init();

	public void regist(Class<? extends T> clazz);
}
