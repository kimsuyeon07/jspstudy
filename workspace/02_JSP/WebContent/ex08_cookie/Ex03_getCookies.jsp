<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 쿠키 읽기
		// 서버가 클라이언트가 보관하고 있는 모든 쿠키를 가져와서 읽는다.
		// 클라이언트의 데이터를 서버가 가져가므로 요청(request)으로 처리한다.
		
		// 1. 전체 쿠키을 읽는다. : 쿠키 '배열'형태로 가져온다!
		Cookie[] cookies = request.getCookies();
		
		// 2. 쿠키를 하나씩 읽는다.
		/* cookies != null && cookies.length != 0  :: cookies객체가 값이 있거나 빈 문자열 때 까지 */
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) { // for 향상문
				out.println("쿠키이름: " + cookie.getName() + "<br>");
				out.println("유효시간: " + cookie.getMaxAge() + "<br>");
				out.println("쿠키   값 : " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br>" );
			}
		}
		
	%>


</body>
</html>