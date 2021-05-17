package json_simple;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Ex03_toJSONString {

	public static void main(String[] args) {
		
		/*
		JSONObject obj = new JSONObject();  // 빈 공간의 오브젝트 생성
		
		obj.put("name", "브레드");  // JSONObject는 HashMap과 사용법 (동일)
		obj.put("age", 50);
		
		String requestData = obj.toJSONString();
		System.out.println(requestData);
		*/
		
		JSONObject obj1 = new JSONObject();  // 빈 공간의 오브젝트 생성
		obj1.put("name", "브레드");  // JSONObject는 HashMap과 사용법 (동일)
		obj1.put("age", 50);

		JSONObject obj2 = new JSONObject();  // 빈 공간의 오브젝트 생성
		obj2.put("name", "브레드");  // JSONObject는 HashMap과 사용법 (동일)
		obj2.put("age", 50);
		
		JSONArray list = new JSONArray();
		list.add(obj1);
		list.add(obj2);
		
		String requestData = list.toJSONString();
		System.out.println(requestData);
		
		

	}
}
