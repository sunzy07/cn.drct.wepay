package cn.drct.wepay.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectUtil {
	public static Map<Class<?>, Map<String, Field>> classFieldMap = new HashMap<Class<?>, Map<String, Field>>();

	public synchronized static Map<String, Field> getFieldMap(Class<?> clazz) {
		Map<String, Field> fieldMap = classFieldMap.get(clazz);
		if (fieldMap == null) {
			fieldMap = new HashMap<String, Field>();
			getAllClassField(fieldMap, clazz);
			classFieldMap.put(clazz, fieldMap);
		}
		return fieldMap;
	}

	private static void getAllClassField(Map<String, Field> fieldMap,
			Class<?> clazz) {
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			fieldMap.put(javaNameToCName(field.getName()), field);
		}
		if (clazz.getSuperclass() != null) {
			getAllClassField(fieldMap, clazz.getSuperclass());
		}
	}
	
	public static <T> T toObject(Map<String, String> values,Class<T> clazz){
		T object = null;
		try {
			object = clazz.newInstance();
			Map<String, Field> filedMap = ReflectUtil.getFieldMap(clazz);
			for (Entry<String, String> entry : values.entrySet()) {
				Field field = filedMap.get(entry.getKey());
				if(field!=null){
					try{
						if(field.getType().isAssignableFrom(String.class)){
							field.set(object, entry.getValue());
						}else if(field.getType().isAssignableFrom(int.class)||field.getType().isAssignableFrom(Integer.class)){
							field.set(object, Integer.parseInt(entry.getValue()));
						}else if(field.getType().isAssignableFrom(long.class)||field.getType().isAssignableFrom(Long.class)){
							field.set(object, Long.parseLong(entry.getValue()));
						}else if(field.getType().isAssignableFrom(float.class)||field.getType().isAssignableFrom(Float.class)){
							field.set(object, Float.parseFloat(entry.getValue()));
						}else if(field.getType().isAssignableFrom(double.class)||field.getType().isAssignableFrom(Double.class)){
							field.set(object, Double.parseDouble(entry.getValue()));
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static  Map<String, String> toMap(Object object){
		Map<String, String>  result = new HashMap<String, String>();
		try {
			Map<String, Field> filedMap = ReflectUtil.getFieldMap(object.getClass());
			for (Entry<String, Field> entry : filedMap.entrySet()) {
				Field field = entry.getValue();
				Object value =field.get(object);
				if(value!=null){
					result.put(entry.getKey(), value.toString());
				}
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Class<?> getGenericType(Class<?> clazz,int index) {
		Type genType = null;
		while (clazz != null) {
			genType = clazz.getGenericSuperclass();
			if (genType instanceof ParameterizedType) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		if (clazz == null) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index out of bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}
	
	public static String javaNameToCName(String name){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<name.length();i++){
			if(Character.isUpperCase(name.charAt(i))){
				buffer.append('_');
				buffer.append(Character.toLowerCase(name.charAt(i)));
			}else{
				buffer.append(name.charAt(i));
			}
		}
		return buffer.toString();
	}
}