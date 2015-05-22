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
public class LoadProperties<T> implements ILoadPropertiesFile<T>{
	private static final String CONFIG_PREFIX = "config";

	@Override
	public T load(String filePath) {
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
			Properties properties = new Properties();
			properties.load(in);
			return load(properties);
		}catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(in != null){try {in.close();} catch (IOException e) {}}
		}
	}

	@Override
	public T load(File file) {
		return load(file.getAbsolutePath());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Properties properties) {
		Class<?>[] innerClasses = ClassUtils.getInnerClasses(getType());
		T instance = (T)ClassUtils.newInstance(getType());
		setValue(instance, false, properties);
		if(innerClasses != null && innerClasses.length != 0 ){
			for(Class<?> innerClass : innerClasses){
				Object innerClassInstance = ClassUtils.newInstanceForInnerClass(innerClass, instance);
				setValue(innerClassInstance, true, properties);
			}
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	private Class<?> getType(){
		T t = (T) new Object();
        return t.getClass();
    }
	
	private void setValue(Object instance, boolean isInnerClass, Properties properties){
		Field[] fields = ClassUtils.getFields(instance.getClass());
		if(fields != null && fields.length != 0){
			for(Field field : fields){
				String key = getKey(instance.getClass(), field, isInnerClass);
				String valueStr = properties.getProperty(key).trim();
				if(valueStr != null){
					ClassUtils.invokeSetterField(instance, field, valueStr);
				}
			}
		}
	}
	
	private String getKey(Class<?> clazz, Field field, boolean isInnerClass){
		String key = "";
		if(isInnerClass){
			key = CONFIG_PREFIX + SystemProperty.PACKAGE_SEPARATOR.getValue() +
					clazz.getSimpleName() + SystemProperty.PACKAGE_SEPARATOR.getValue() + 
					field.getName();
		}else{
			key = CONFIG_PREFIX + SystemProperty.PACKAGE_SEPARATOR.getValue() + field.getName();
		}
		return key;
	}

}
