package com.xubo.reflect;

import java.lang.reflect.Field;

public class PropertyUtil {
	
	/*
	 * 可以给任何对象的任何属性赋值
	 * 参数：Object 赋值的对象	propertyName 赋值的属性		obj2 赋的具体值
	 */
	public static void setProperty(Object obj,String propertyName,Object value) {
		try {
			Class<?> class1 = obj.getClass();
			Field field = class1.getDeclaredField(propertyName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
