package exam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiSearchMovie {


    public static void main(String[] args) throws Exception {
        String clientId = "2SJV4RGU2kNlcZpZGf3C"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "xXrbQf1ObB"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode("코미디", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        /* JSON으로 영화제목 호출 */
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        String filename = "search_result.txt";
        File f = null;
        BufferedWriter bw = null;
        
        // 오늘 날짜 출력
    	Calendar today = Calendar.getInstance();
    	String date = today.get(Calendar.YEAR)+"-"+(today.get(Calendar.MONTH)+1)+"-"+today.get(Calendar.DAY_OF_MONTH) + " " + today.get(Calendar.HOUR)+":"+today.get(Calendar.MINUTE)+":"+today.get(Calendar.SECOND);
    	// System.out.println(date);
        
        try {
        	// 'title'만 호출 준비
        	obj = (JSONObject)parser.parse(responseBody);
        	JSONArray objArr = (JSONArray)obj.get("items");
        	// 파일 생성 및 파일 작성 준비
        	f = new File(filename);
        	bw = new BufferedWriter(new FileWriter(f));
        	
        	for (int i = 0; i<objArr.size(); i++) {
        		JSONObject obj2 = (JSONObject)objArr.get(i);
        		String title = (String)obj2.get("title");
        		
        		// sysout_출력
        		System.out.println(title);
        		// search_result.txt 에 내용 작성
        		bw.write(title + "\n");
        		 
        			
        	}  // for문 (END)
        
        } catch (Exception e) {
        	 e.printStackTrace();
        	 /* 오류 파일 생성 */
             filename = "search_error.txt"; 
             //f = null;
             //BufferedWriter bw = null;
             try {
             	f = new File(filename);
             	bw = new BufferedWriter(new FileWriter(f));
             	bw.write(apiURL + "\n" + date);
             	
             } catch (Exception ee) {
             	ee.printStackTrace();
             } 
             
        } finally {
			if (bw != null) bw.close();
		}
        
        
        
        
        
        
        // System.out.println(responseBody);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
    	HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
            
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){;
    	
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
}