<%@page import="java.net.URLEncoder"%>
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
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("id");	
		String month = request.getParameter("pw");	
		String day = request.getParameter("name");	
		String marry = request.getParameter("addr");	
		String job = request.getParameter("phone");	
		String brith = year + "년 " + month + "월 " + day + "일";
		
		
		// 쿠키 생성
		Cookie cookie1 = new Cookie("brith", URLEncoder.encode(brith, "UTF-8"));
		cookie1.setMaxAge(60*60);
		Cookie cookie2 = new Cookie("marry", URLEncoder.encode(marry, "UTF-8"));
		cookie2.setMaxAge(60*60);
		Cookie cookie3 = new Cookie("job", URLEncoder.encode(job, "UTF-8"));
		cookie3.setMaxAge(60*60);
		
		// 쿠키 저장
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
		
	
	
	%>
	
	
</body>
</html>