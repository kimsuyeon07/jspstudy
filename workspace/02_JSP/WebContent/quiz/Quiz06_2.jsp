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
	
		String id = request.getParameter("id");	
		String pw = request.getParameter("pw");	
		String name = request.getParameter("name");	
		
		// 이전의 데이터를 쿠키에 저장해두자. : 다음 화면으로 넘길 필요가 없다.
		/* id */
		Cookie cookie1 = new Cookie("id", URLEncoder.encode(id, "UTF-8"));
		cookie1.setMaxAge(60*60);
		/* pw */
		Cookie cookie2 = new Cookie("pw", URLEncoder.encode(pw, "UTF-8"));
		cookie2.setMaxAge(60*60);
		/* name */
		Cookie cookie3 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
		cookie3.setMaxAge(60*60);
		
		// cookie 저장
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
	
	%>
	<h3>연락처를 입력하세요.</h3>
	<form action="/02_JSP/quiz/Quiz06_3.jsp">
		<table>
			<tr>
				<td><label for="addr">주소</label></td>
				<td><input type="text" id="addr" name="addr"></td>
			</tr>
			
			<tr>
				<td><label for="phone">전화번호</label></td>
				<td><input type="text" id="phone" name="phone"></td>
			</tr>
			
			
			<tr>
				<td><label for="email">이메일</label></td>
				<td><input type="text" id="email" name="email"></td>
			</tr>
		</table>
		<br>
		<button>확인</button>
	</form>
	
	
</body>
</html>