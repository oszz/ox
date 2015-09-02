package org.oszz.ox.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.oszz.ox.common.tuple.TwoTuple;

/**
 * Class的工具类
 * @author ZZ
 *
 */
public class ClassUtils {
	
	/**
	 * 返回class的内部类
	 * @author ZZ
	 * @param clazz
	 * @return 返回class的内部类
	 */
	public static Class<?>[] getInnerClasses(Class<?> clazz){
		return clazz.getDeclaredClasses();
	}
	
	/**
	 * 返回当前class的所有字段属性
	 * @author ZZ
	 * @param clazz 当前class
	 * @return 返回当前class的所有字段属性
	 */
	public static Field[] getFields(Class<?> clazz){
		return clazz.getDeclaredFields();
	}
	
	/**
	 * 返回当前class及其所有父类的字段名称与fieldName相同的属性
	 * @author ZZ
	 * @param clazz 当前class
	 * @param fieldName 字段名称
	 * @return 返回当前class及其所有父类的字段名称与fieldName相同的属性,如果没有找到，则返回<tt>null<tt>
	 */
	public static Field getField(Class<?> clazz, String fieldName){
		Field[] fields = getAllFields(clazz);
		Field field = null;
		for(Field f : fields){
			if(fieldName.equals(f.getName())){
				field = f;
			}
		}
		return field;
	}
	
	
	
	/**
	 * 返回class所有父类机器自身的所有字段属性<br>
	 * 父类会追溯到Object.class，但返回的属性中不包含Object.class的属性
	 * @author ZZ
	 * @param currentClazz 当前类
	 * @return 返回class所有父类的所有字段属性
	 */
	public static Field[] getAllFields(Class<?> currentClazz){
		return getAllFields(currentClazz, Object.class);
	}
	
	/**
	 * 返回class所有父类机器自身的所有字段属性(不包含顶级父类的属性)
	 * @author ZZ
	 * @param currentClazz 当前类
	 * @param maxParentClazz 顶级的父类
	 * @return 返回class所有父类机器自身的所有字段属性(不包含顶级父类的属性)
	 */
	public static Field[] getAllFields(Class<?> currentClazz, Class<?> maxParentClazz){
		List<Field> totalfields = new ArrayList<Field>();
		while(true){
			Field[] fields = getFields(currentClazz);
			totalfields.addAll(0, Arrays.asList(fields));//将父类的字段放在前面
			
			currentClazz = currentClazz.getSuperclass();
			if(currentClazz == maxParentClazz){
				break;
			}
		}
		return totalfields.toArray(new Field[0]);
	}
	
	/**
	 * 调用属性字段的set方法
	 * @author ZZ
	 * @param instance 实例对象
	 * @param field 属性
	 * @param valueStr 属性值
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static <T> void invokeSetterField(T instance, Field field, String valueStr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		TwoTuple<String, Object> smnv = getSetterMethodNameAndValue(field, valueStr);
		String setterMethodName = smnv.getFirst();
		Object value = smnv.getSecond();
		Method setterMethod = getMethod(instance.getClass(), setterMethodName, value);
		invokeMethod(instance, setterMethod, value);
	}
	
	/**
	 * 调用实例对象instance的set方法(set方法无返回值)
	 * @author ZZ
	 * @param instance 实例对象
	 * @param method set方法
	 * @param para 需要set的值
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static <T> void invokeMethod(T instance, Method method, Object para) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		method.invoke(instance, para);
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
		Object value = getValue(field.getType(), valueStr);
		String methodName = getSetterMethodName(fieldName, typeClass);
		
		if(value == null){
			return null;
		}
		TwoTuple<String, Object> twoTuple = new TwoTuple<String, Object>(methodName, value);
		return twoTuple;
	}
	
	/**
	 * 新建并返回T的一个实例对象
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
	
	/**
	 * 创建并返回内部类对象
	 * @author ZZ
	 * @param outClazz 外层的类
	 * @param innerClass 内部类
	 * @return 创建并返回内部类对象
	 */
	@SuppressWarnings("unchecked")
	public static <P, T> T newInstanceForInnerClass(Class<T> outClazz, P innerClass){
		Constructor<?> constructor = outClazz.getConstructors()[0];
		try {
			return (T)constructor.newInstance(innerClass);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
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
//		}else if(typeClass == Character.TYPE || typeClass == char.class){
		}else if(typeClass == Character.TYPE ){
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
//		StringBuilder content = new StringBuilder();
//		content.append(obj.getClass().getSimpleName() + "( ");
		JSONObject json = new JSONObject();
//		Field[] fields = obj.getClass().getDeclaredFields();
		Class<?> clazz = obj.getClass();
		Field[] fields = getAllFields(clazz);
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
//				content.append(fieldName + ":" + value + " - ");
				
				json.put(fieldName, value);
			}
		}
//		content.append(")");
//		return content.toString();
//		return clazz + ":" + json.toString();
		return clazz + ":\n" +json.toString(4, 2);
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
	 * @return 该类静态方法返回的对象
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(Class<?> clazz, String staticMethodName, Object... paras) throws Exception {
	    Method method = clazz.getMethod(staticMethodName);  
		return method.invoke(null, paras);
	}
	
	public static Object getValue(Class<?> typeClass, String valueStr){
//		Class<?> typeClass = field.getType();
		Object value = null;
		if(typeClass == Integer.class || typeClass == int.class){
			int pointIndex = valueStr.indexOf(".");
			if(pointIndex > 0){//说明有小数点
				valueStr = valueStr.substring(0,valueStr.indexOf("."));//去掉小数点
			}
			value = Integer.parseInt(valueStr);
		}else if(typeClass == Byte.class || typeClass == byte.class){
			int pointIndex = valueStr.indexOf(".");
			if(pointIndex > 0){//说明有小数点
				valueStr = valueStr.substring(0,valueStr.indexOf("."));//去掉小数点
			}
			value = Byte.parseByte(valueStr);
		}else if(typeClass == Short.class || typeClass == short.class){
			int pointIndex = valueStr.indexOf(".");
			if(pointIndex > 0){//说明有小数点
				valueStr = valueStr.substring(0,valueStr.indexOf("."));//去掉小数点
			}
			value = Short.parseShort(valueStr);
		}else if(typeClass == Float.class || typeClass == float.class){
			value = Float.parseFloat(valueStr);
		}else if(typeClass == Double.class || typeClass == double.class){
			value = Double.parseDouble(valueStr);
		}else if(typeClass == Long.class || typeClass == long.class){
			int pointIndex = valueStr.indexOf(".");
			if(pointIndex > 0){//说明有小数点
				valueStr = valueStr.substring(0,valueStr.indexOf("."));//去掉小数点
			}
			value = Long.parseLong(valueStr);
//		}else if(typeClass == Character.TYPE || typeClass == char.class){
		}else if(typeClass == Character.TYPE){
			value = (char)(Integer.parseInt(valueStr));
		}else if(typeClass == Boolean.class || typeClass == boolean.class){
			if(Boolean.TRUE.toString().equalsIgnoreCase(valueStr.trim())){
				value = true;
			}else{
				value = false;
			}
		}else if(typeClass == String.class){
			value = valueStr;
		}
		return value;
	}
	
	public static String getSetterMethodName(String fieldName, Class<?> typeClass){
		String fName = NameUtils.upperFirstChar(fieldName);
		String methodName = MethodNameEnum.SETTER_PREFIX.getName() + fName;
		if(typeClass == Boolean.class || typeClass == boolean.class){
			int isIndex = fieldName.indexOf(MethodNameEnum.IS_PREFIX.getName());
			methodName = MethodNameEnum.SETTER_PREFIX.getName() + fieldName.substring(isIndex + 2);//去掉is
		}
		return methodName;
	}
	
	public static Method getSetterMethod(Class<?> clazz, String fieldName){
		String fName = NameUtils.upperFirstChar(fieldName);
		String methodName = MethodNameEnum.SETTER_PREFIX.getName() + fName;
		Method[] allMethods = clazz.getMethods();
		for(Method method : allMethods){
			if(method.getName().equals(methodName)){
				return method;
			}
		}
		return null;
	}
	
	public static Object getMethodNeedValue(Method method, String paraValue){
		Class<?>[] paraTypeClasses = method.getParameterTypes();
		Class<?> paraTypeClass = paraTypeClasses[0];
		return getValue(paraTypeClass, paraValue);
	}
	
	public static String getClassName(Class<?> clazz){
		String name = clazz.getName();
		int lastPointIndex = name.lastIndexOf(".");
		if(lastPointIndex > 0){
			name = name.substring(lastPointIndex + 1);
		}
		return name;
	}
	
}
