package org.oszz.ox.db.entity;

import java.io.Serializable;

import org.oszz.ox.common.utils.ClassUtils;

public abstract class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return ClassUtils.toString(this);
	}
}
