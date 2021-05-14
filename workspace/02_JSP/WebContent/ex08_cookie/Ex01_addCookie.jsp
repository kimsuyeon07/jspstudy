<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie_addCookie</title>
</head>
<body>
	
	<%
		// 쿠키는 서버가 만든다.
		// 자바 영역은 서버 영역이다.
		
		// 1. 쿠키 만들기
		// Cookie : javax.servlet.http (@import : 자동완성만 해준다.)
		/* Cookie 쿠키캑체명 = new Cookie(쿠키이름, 값) */
		Cookie cookie = new Cookie("name", "브레드"); 
		
		// 2. 쿠키의 유효시간 정하기
		// 언제까지 쿠키의 데이터를 보관할 것인가?
	 	/* 쿠키객체.setMaxAge(expiry:초 단위의 시간) */
		cookie.setMaxAge(60*60*24*15); // 15일간 보관하는 쿠키
		
		// 3. 쿠키를 클라이언트에게 전달해서 저장
		// 서버가 클라이언트에게 전달 : 응답(response)
		/* response.addCookie(cookie:쿠키객체) */
		response.addCookie(cookie); 
		
	%>
	
	
	<%--
		-쿠키 및 기타 사이트 데이터
		- 모든 쿠키 및 사이트 데이터 보기
		
	--%>
	
	
	<%--
		쿠키의 이름, 유효시간, 값 확인
		
		1. 이름 : cookie.getName()
		2. 유효시간 : cookie.getMaxAge()
		3. 값 : cookie.getValue()	
	--%>
	
	<h3>쿠키 확인</h3>
	쿠키 이름 : <%=cookie.getName() %> <br>
	유효 시간 : <%=cookie.getMaxAge()%> <br>
	값 : <%=cookie.getValue() %>
	
	
	
	
</body>
</html>