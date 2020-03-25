package com.xubo.reflect;

import java.lang.reflect.Field;

public class PropertyUtil {
	
	/*
	 * ���Ը��κζ�����κ����Ը�ֵ
	 * ������Object ��ֵ�Ķ���	propertyName ��ֵ������		obj2 ���ľ���ֵ
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
