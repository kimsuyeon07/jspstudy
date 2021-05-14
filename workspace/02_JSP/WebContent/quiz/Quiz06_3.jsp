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
	
		String addr = request.getParameter("addr");	
		String phone = request.getParameter("phone");	
		String email = request.getParameter("email");
		
		// 쿠키 만들기
		Cookie cookie1 = new Cookie("addr", URLEncoder.encode(addr, "UTF-8"));
		cookie1.setMaxAge(60*60);
		Cookie cookie2 = new Cookie("phone", URLEncoder.encode(phone, "UTF-8"));
		cookie2.setMaxAge(60*60);
		Cookie cookie3 = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
		cookie3.setMaxAge(60*60);
		
		// 쿠키 저장
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		
	
	%>
	
	<h3>추가정보를 입력하세요.</h3>
	<form action="/02_JSP/quiz/Quiz06_4.jsp">
		<table>
			<tr>
				<td> 생년월일 </td>
				<td>
					<input type="text" name="year" size="4">년 
					<input type="text" name="month" size="2">월 
					<input type="text" name="day" size="2">일
				</td>
			</tr>
			
			<tr>
				<td> 결혼여부 </td>
				<td>
					<input type="radio" name="marry" value="yes" checked>미혼
					<input type="radio" name="marry" value="no">기혼
				</td>
			</tr>
			
			
			<tr>
				<td> 직업 </td>
				<td>
					<select>
						<option name="job" value="devl">개발자</option>
						<option name="job" value="artist">예술가</option>
						<option name="job" value="teacher">선생님</option>
					</select>
				</td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="id">
		<input type="hidden" name="pw">
		<input type="hidden" name="name">
		<input type="hidden" name="addr">
		<input type="hidden" name="phone">
		<input type="hidden" name="email">
		<button>확인</button>
	</form>
	

</body>
</html>