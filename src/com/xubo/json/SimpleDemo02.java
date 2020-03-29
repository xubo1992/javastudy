package com.xubo.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import net.sf.json.JSONArray;

public class SimpleDemo02 {
	
	public static void main(String[] args) {
//		demo01();
		demo02();
	}
	
	// map转jsonArray
	public static void demo01() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		JSONArray jArray = new JSONArray();
		jArray = jArray.fromObject(map);
		System.out.println(jArray);
	}

	// jsonArray转map
	public static void demo02() {
		String str = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"lq\",\"classno\":\"01\"},{\"shcoolAddress\":\"hb\",\"workAddress\":\"fz\"}]";
		JSONArray jArray = new JSONArray();
		jArray = jArray.fromObject(str);
		Map<String,Object> map = new HashMap<String, Object>();
		for(int i = 0; i < jArray.size(); i++) {
			Object o = jArray.get(i);
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)o;
			// 获取json中每一个key
			Set<String> keys = json.keySet();
			for(String key : keys) {
				Object value = json.get(key);
				map.put(key, value);
			}
		}
		System.out.println(map);
	}
}
