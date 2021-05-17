package naver.captcha;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 네이버 캡차 API 예제 - 캡차 이미지 수신
public class Ex02_GetCaptchaImage {

    public static void main(String[] args) {
    	
    	// 캡차 키 발급 코드
    	String clientId = "nbBOJno8uSZiVSZguHxK"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "P5H21seF3g"; //애플리케이션 클라이언트 시크릿값";

        String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        // 네이버가 응답한 JSON데이터 : {"key":"Jxnl1M8TdOl4zGC0"}
        String responseBody = get1(apiURL, requestHeaders);

        // System.out.println(responseBody);
        
        // key값을 빼자.
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
        	obj = (JSONObject)parser.parse(responseBody); 
        } catch (Exception e) {
        	e.printStackTrace();
        }
        String key = (String)obj.get("key");  // * obj타입을 <String>으로 캐스팅 * 
        
        
    	
    	// 캡차 이미지 발급 코드
        // String clientId = "nbBOJno8uSZiVSZguHxK"; //애플리케이션 클라이언트 아이디값";
        // String clientSecret = "P5H21seF3g"; //애플리케이션 클라이언트 시크릿값";

        // String key = "CAPTCHA_KEY"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
        apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

        // 이미지 생성이 성공하면 responseBody == "이미지 캡차가 생성되었습니다."
        responseBody = get2(apiURL,requestHeaders);

        System.out.println(responseBody);
    }

    
    // Ex01_GetCaptchaKey파일에서 가져옴 : get()이 따로 있기 때문에 이름 변경_
    // 네이버개발자센터_캡차 키 발급 요청의 ( get() )
    private static String get1(String apiUrl, Map<String, String> requestHeaders){
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
    
    // Ex01_GetCaptchaKey파일에서 가져옴    
    // 네이버개발자센터_캡차 키 발급 요청의 ( readBody() )
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

    
    // 네이버개발자센터_캡차 이미지 요청(원래 있던 get()을 get2()로 수정)
    private static String get2(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return getImage(con.getInputStream());
            } else { // 에러 발생
                return error(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String getImage(InputStream is){
        int read;
        byte[] bytes = new byte[1024];
        // 랜덤한 이름으로  파일 생성
        String filename = Long.valueOf(new Date().getTime()).toString();
        File f = new File(filename + ".jpg");
        try(OutputStream outputStream = new FileOutputStream(f)){
            f.createNewFile();
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            return "이미지 캡차가 생성되었습니다.";
        } catch (IOException e) {
            throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.",e);
        }
    }

    private static String error(InputStream body) {
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