package com.xubo.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleDemo01 {

	public static void main(String[] args) throws IOException {
//		demo01();
//		demo02();
//		demo03();
//		SimpleDemo01 sd = new SimpleDemo01();
//		sd.demo04();
//		demo05();
//		demo06();
		demo07();
	}
	// Map集合转JSon对象
	public static void demo01() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		JSONObject jObject = new JSONObject(map);
		System.out.println(jObject);
	}
	
	// javaBean转JSon对象
	public static void demo02() {
		Person person = new Person("zs",23,new Address("fs","hb"));
		JSONObject jObject = new JSONObject(person);
		System.out.println(jObject);
	}

	// String转JSon对象
	public static void demo03() {
		String str = "{\"name\":\"zs\",\"age\":24,\"Address\":{\"workAddress\":\"fs\",\"homeAddress\":\"hb\"}}";
		JSONObject jObject = new JSONObject(str);
		System.out.println(jObject);
	}
	
	// 将一个文件转JSon对象 方式一
	public void demo04() throws IOException {
		InputStream in = super.getClass().getClassLoader().getResourceAsStream("com/xubo/json/per.json");
		byte[] bs = new byte[10];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while((len = in.read(bs)) != -1) {
			String str = new String(bs,0,len);
			sb.append(str);
		}
		String str2 = sb.toString();
		JSONObject jObject = new JSONObject(str2);
		System.out.println(jObject);
	}
	
	// 将一个文件转JSon对象 方式二
	public static void demo05(){
		String str2 = "";
		try {
			str2 = FileUtils.readFileToString(new File("src/com/xubo/json/per.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject(str2);
		System.out.println(jObject);
	}

	// 生成JSon文件
	public static void demo06(){
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			Person p1 = new Person("zs",23,new Address("hb","cs"));
			Person p2 = new Person("ls",25,new Address("hb2","cs2"));
			Person p3 = new Person("ww",27,new Address("hb3","cs3"));
			map.put("zs",p1);
			map.put("ls",p2);
			map.put("ww",p3);
			JSONObject jObject = new JSONObject(map);
			FileWriter fileWriter = new FileWriter("src/com/xubo/json/per2.json");
			jObject.write(fileWriter);
			fileWriter.close();
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}
	

	// String格式的JSON数组转JSon数组
	public static void demo07(){
		String str = "["
					+ "{\"name\":\"zs\",\"age\":23},"
					+ "{\"classname\":\"lq\",\"classno\":\"01\"},"
					+ "{\"shcoolAddress\":\"hb\",\"workAddress\":\"fz\"}"
					+ "]";
		JSONArray array = new JSONArray(str);
		System.out.println(array);
	}
}
