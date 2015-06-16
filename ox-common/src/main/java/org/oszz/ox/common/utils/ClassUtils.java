package org.oszz.ox.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.oszz.ox.common.tuple.TwoTuple;

/**
 * Class的工具类
 * @author ZZ
 *
 */
public class ClassUtils {
	
	public static Class<?>[] getInnerClasses(Class<?> clazz){
		return clazz.getDeclaredClasses();
	}
	
	public static Field[] getFields(Class<?> clazz){
		return clazz.getDeclaredFields();
	}
	
	/**
	 * 调用属性字段的set方法
	 * @author ZZ
	 * @param instance
	 * @param field
	 * @param valueStr
	 */
	public static <T> void invokeSetterField(T instance, Field field, String valueStr){
		TwoTuple<String, Object> smnv = getSetterMethodNameAndValue(field, valueStr);
		String setterMethodName = smnv.getFirst();
		Object value = smnv.getSecond();
		Method setterMethod = getMethod(instance.getClass(), setterMethodName, value);
		invokeSetter(instance, setterMethod, value);
	}
	
	/**
	 * 调用实例对象instance的set方法(set方法无返回值)
	 * @author ZZ
	 * @param instance 实例对象
	 * @param method set方法
	 * @param para 需要set的值
	 */
	public static <T> void invokeSetter(T instance, Method setterMethod, Object para){
		try {
			setterMethod.invoke(instance, para);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 返回方法名和以及值
	 * @author ZZ
	 * @param field 属性
	 * @param valueStr 字符串的值
	 * @return 返回方法名和以及值,如果typeClass不是8种基本数据类型或字符串，则返回<tt>null<tt>
	 */
	public static TwoTuple<String, Object> getSetterMethodNameAndValue(Field field, String valueStr){
		String fieldName = field.getName();
		Class<?> typeClass = field.getType();
		String fName = NameUtils.upperFirstChar(fieldName);
		Object value = null;
		String methodName = MethodNameEnum.SETTER_PREFIX.getName() + fName;
		if(typeClass == Integer.class || typeClass == int.class){
			value = Integer.parseInt(valueStr);
		}else if(typeClass == Byte.class || typeClass == byte.class){
			value = Byte.parseByte(valueStr);
		}else if(typeClass == Short.class || typeClass == short.class){
			value = Short.parseShort(valueStr);
		}else if(typeClass == Float.class || typeClass == float.class){
			value = Float.parseFloat(valueStr);
		}else if(typeClass == Double.class || typeClass == double.class){
			value = Double.parseDouble(valueStr);
		}else if(typeClass == Long.class || typeClass == long.class){
			value = Long.parseLong(valueStr);
		}else if(typeClass == Character.TYPE || typeClass == char.class){
			value = (char)(Integer.parseInt(valueStr));
		}else if(typeClass == Boolean.class || typeClass == boolean.class){
			if(Boolean.TRUE.toString().equalsIgnoreCase(valueStr.trim())){
				value = true;
			}else{
				value = false;
			}
			int isIndex = fieldName.indexOf(MethodNameEnum.IS_PREFIX.getName());
			methodName = MethodNameEnum.SETTER_PREFIX.getName() + fieldName.substring(isIndex + 2);//去掉is
		}else if(typeClass == String.class){
			value = valueStr;
		}
		
		if(value == null){
			return null;
		}
		TwoTuple<String, Object> twoTuple = new TwoTuple<String, Object>(methodName, value);
		return twoTuple;
	}
	
	/**
	 * 返回T的一个实例对象
	 * @author ZZ
	 * @param clazz 类
	 * @return 返回T的一个实例对象
	 */
	public static <T> T newInstance(Class<T> clazz){
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <P, T> T newInstanceForInnerClass(Class<T> clazz, P p){
		Constructor<?> constructor = clazz.getConstructors()[0];
		try {
			return (T)constructor.newInstance(p);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按para类型和方法名返回clazz的一个方法（单个参数的方法）
	 * @author ZZ
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param para 参数
	 * @return 返回clazz的一个方法
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Object para){
		TwoTuple<Class<?>, Class<?>> twoTupleType = getBasicClass(para);
		
		//firstTypeClass  secondTypeClass 只会有一个getMethod成功
		Class<?> firstTypeClass = twoTupleType.getFirst();
		Class<?> secondTypeClass = twoTupleType.getSecond();
		
		Method method = null;
		if(firstTypeClass != null){
			method = getMethod(clazz, methodName, firstTypeClass);
		}
		if(secondTypeClass != null && method == null){//上一个判断没有得到方法时，才进入这个判断
			method = getMethod(clazz, methodName, secondTypeClass);
		}
		return method;
		
	}
	
	/**
	 * 根据paraTypeClass类型和方法名返回clazz的一个方法（单个参数的方法）
	 * @author ZZ
	 * @param clazz 类
	 * @param methodName 方法名
	 * @param paraTypeClass 参数类型
	 * @return 返回clazz的一个方法（单个参数的方法）
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Class<?> paraTypeClass){
		Method method = null;
		try {
			method = clazz.getMethod(methodName, paraTypeClass);
		} catch (Exception e) {
			//TODO 如果不存在这个方法或其他异常，就返回 null空方法
		} 
		return method;
	}
	
	/**
	 * 如果是基本类型则则返回两个类型参数,如果不是基本类型,按para的原类型返回
	 * @author ZZ
	 * @param para 基本类型
	 * @return 返回两个类型参数,如果不是基本类型,按para的原类型返回
	 */
	private static TwoTuple<Class<?>, Class<?>> getBasicClass(Object para){
		Class<?> typeClass = para.getClass();
		Class<?> firstTypeClass = null;
		Class<?> secondTypeClass = null;
		if(typeClass == Integer.class || typeClass == int.class){
			firstTypeClass = Integer.class;
			secondTypeClass = int.class;
		}else if(typeClass == Byte.class || typeClass == byte.class){
			firstTypeClass = Byte.class;
			secondTypeClass = byte.class;
		}else if(typeClass == Short.class || typeClass == short.class){
			firstTypeClass = Short.class;
			secondTypeClass = short.class;
		}else if(typeClass == Float.class || typeClass == float.class){
			firstTypeClass = Float.class;
			secondTypeClass = float.class;
		}else if(typeClass == Double.class || typeClass == double.class){
			firstTypeClass = Double.class;
			secondTypeClass = double.class;
		}else if(typeClass == Long.class || typeClass == long.class){
			firstTypeClass = Long.class;
			secondTypeClass = long.class;
		}else if(typeClass == Character.TYPE || typeClass == char.class){
			firstTypeClass = char.class;
		}else if(typeClass == Boolean.class || typeClass == boolean.class){
			firstTypeClass = Boolean.class;
			secondTypeClass = boolean.class;
		}
		TwoTuple<Class<?>, Class<?>> twoTuple = null;
		if(firstTypeClass == null && secondTypeClass == null){
			twoTuple = new TwoTuple<Class<?>, Class<?>>(para.getClass(), null);
		}else{
			twoTuple = new TwoTuple<Class<?>, Class<?>>(firstTypeClass, secondTypeClass);
		}
		return twoTuple;
	}
	
	/**
	 * 提供一个便捷的toString方法<br>
	 * 遍历所有的属性和属性值，连接成字符串并返回
	 * @author ZZ
	 * @see  {@link Object#toString()}
	 * @param obj 对象
	 * @return 返回String
	 */
	public static String toString(Object obj){
		StringBuilder content = new StringBuilder();
		content.append(obj.getClass().getSimpleName() + "( ");
		
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields != null && fields.length != 0){
			for(Field field : fields){
				String fieldName = field.getName();
				field.setAccessible(true);
				Object value = null;
				try {
					value = field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				content.append(fieldName + ":" + value + " ");
			}
		}
		content.append(")");
		return content.toString();
	}
	
	/**
	 * 将包名转成路径名
	 * @author ZZ
	 * @param packageName 包名
	 * @return 返回路径名
	 */
	public static String packageName2Path(String packageName){
		return packageName.replace(SystemProperty.PACKAGE_SEPARATOR.getValue(), SystemProperty.FILE_SEPARATOR.getValue());
	}
	
	/**
	 * 调用某个类的静态方法
	 * @author ZZ
	 * @param clazz 类
	 * @param staticMethodName 静态方法名
	 * @param paras 参数
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(Class<?> clazz, String staticMethodName, Object... paras) throws Exception {
	    Method method = clazz.getMethod(staticMethodName);  
		return method.invoke(null, paras);
	}
}
