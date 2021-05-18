package login.captcha;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// 네이버 캡차 API 예제 - 캡차 이미지 수신
public class CaptchaImage {

	// private static HttpServletRequest request; : 필드로 생성 후, 사용하는 방법도 있지만_ 전달하는 방식이 더 좋다.
	
	// 기존의 main()메소드를 
	// 캡차 이미지를 받아 오는 getCaptchaImage() 메소드로 변환
	// 1. 반환타입 : void
	// 2. 메소드명 : getCaptchaImage
	// 3. 매개변수 : 
	//	  1) HttpServletRequest request (realPath를 구하기 위해서 필요)
	//    2) String key (발급 받은 캡차 키)
    public static void getCaptchaImage(HttpServletRequest request, String key) {
    	
    	
    	String clientId = "nbBOJno8uSZiVSZguHxK"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "P5H21seF3g"; //애플리케이션 클라이언트 시크릿값";

        // String key = "CAPTCHA_KEY"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key; 
        											// 매개변수로 전달 받은 "key"값을 가져온다.

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        /* 수정 사항 : (매개변수 : request 추가) */
        String responseBody = get(request, apiURL,requestHeaders);

        // System.out.println(responseBody); 개발자 확인용. 주석처리
    }

    // getImage()의 결과를 "return"
    /* 수정 사항 : (매개변수 : HttpServletRequest request 추가) */
    private static String get(HttpServletRequest request, String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            	
            	/* 수정 사항 : (매개변수 : request 추가) */
                return getImage(request, con.getInputStream());
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
    
    /* 수정 사항 : (매개변수 : HttpServletRequest request 추가) */
    private static String getImage(HttpServletRequest request, InputStream is){
        int read;
        byte[] bytes = new byte[1024];
        
        // 타임스탬프값으로 파일명_ 파일 생성  + (.jpg)
        String filename = Long.valueOf(new Date().getTime()).toString() + ".jpg";
        
        // 캡차 이미지를 저장할 디렉터리(파일 명)
        final String DIR = "captcha_storage";
        
        // realPath를 알아내려면 request가 팔요하다.
        // captcha_storage 디렉터리의 실제 경로를 알아낸다. : request.getServletContext().getRealPath( 파일 명 );
        String realPath = request.getServletContext().getRealPath(DIR);
        
        // realPath에 디렉터리가 생성되지 않았다면 생성하는 코드
        File dir = new File(realPath);
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        
        /* 수정사항_ 경로지정 ++ */
        // 1. File("디렉터리경로/파일");
        // 2. File("디렉터리경로", "파일");
        File f = new File(realPath, filename);  // ".jpg"삭제 : filename에서 같이 저장했기 때문에
        try(OutputStream outputStream = new FileOutputStream(f)){
            f.createNewFile();
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            /* 
             	Login.java로부터 받은 request를 사용하기 때문에
             	여기에서 request에 데이터를 저장하면 Login.java도 저장된 데이터를 사용할 수 있다.
             */
            
            /* 캡차 이미지 다운로드 성공한 시점 */
            // 캡차 이미지 경로 + 파이령을 request에 저장한다.
            request.setAttribute("DIR", DIR); // Login.java가 사용할 수 있도록 저장
            request.setAttribute("filename", filename); // Login.java가 사용할 수 있도록 저장
            
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