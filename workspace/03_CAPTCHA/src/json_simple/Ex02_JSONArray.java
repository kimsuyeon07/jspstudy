package json_simple;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Ex02_JSONArray {

	public static void main(String[] args) {
	 
		// JSON : { } => JSONObject (단일 데이터)일 때, 이 방법으로 처리한다.
		// JSON : [{}, {}] -> JSONArray
		
		// JSONArray는 List 인터페이스를 구현한다.
		// 따라서, 같은 인터페이스를 구현한 ArrayList 클래스와 사용법이 같다.
		
		// [{"name": :브레드", "age": 50}, {"name": " 초코", "age": 30}] 데이터 삽입
		String responseData = "[ {\"name\": :브레드\", \"age\": 50}, {\"name\": \" 초코\", \"age\": 30} ]";
		
		JSONParser parser = new JSONParser();
		
		// JSONArray list = (JSONArray)parser.parse(responseData); >> * 예외처리 필요 *
		JSONArray list = null;
		try {
			list = (JSONArray)parser.parse(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// list의 각 요소는 get() 메소드로 추출한다.
		System.out.println("첫 번째 요소 : " + list.get(0));  // list.get(index:인덱스먼호)
		System.out.println("두 번째 요소 : " + list.get(1));  // list.get(index:인덱스먼호)
		
		// 배열의 데이터를 출력하는 방법 : for문
		for (int i=0; i<list.size(); i++) {
			JSONObject obj = (JSONObject)list.get(i);
			System.out.println((i + 1) + "번째 요소 이름 : " + obj.get("name"));
			System.out.println((i + 1) + "번째 요소 이름 : " + obj.get("age"));
		}
 		
		

		
		
		
		
		
		
		
		
		
		
	}
}
