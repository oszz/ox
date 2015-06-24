package org.oszz.ox.common.conf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.oszz.ox.common.utils.ClassUtils;
import org.oszz.ox.common.utils.SystemProperty;

/**
 * 加载properties文件
 * @author ZZ
 *
 * @param <T>
 */
public class LoadProperties implements ILoadPropertiesFile {
	
	@Override
	public <T> T load(String filePath, Class<T> clazz) {
		return load(load(filePath), clazz);
	}

	@Override
	public <T> T load(File file, Class<T> clazz) {
		return load(file.getAbsolutePath(), clazz);
	}

	@Override
	public <T> T load(Properties properties, Class<T> clazz) {
		T instance = ClassUtils.newInstance(clazz);
		setValue(instance, properties);
		return instance;
	}
	
	private void setValue(Object instance, Properties properties){
		Field[] fields = ClassUtils.getFields(instance.getClass());
		if(fields != null && fields.length != 0){
			for(Field field : fields){
				String key = getKey(instance.getClass(), field);
				String valueStr = properties.getProperty(key);
				if(valueStr != null){
					try {
						ClassUtils.invokeSetterField(instance, field, valueStr.trim());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
	
	private String getKey(Class<?> clazz, Field field){
		String key = clazz.getSimpleName() + SystemProperty.PACKAGE_SEPARATOR.getValue() + field.getName();
		return key;
	}

	@Override
	public Properties load(String filePath) {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		}catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(in != null){try {in.close();} catch (IOException e) {}}
		}
	}

}
