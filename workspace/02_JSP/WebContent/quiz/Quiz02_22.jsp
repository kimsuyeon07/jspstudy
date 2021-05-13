<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String strAge = request.getParameter("age");
	int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div>
		<% if (age < 20) { %>
		<h2>미성년자</h2>
		<p>당신의 나이는 <%=age %>살 이므로 주류를 살 수 없습니다.</p>
		<% } else { %>
		<h2>성인</h2>
		<p>당신의 나이는 <%=age %>살 이므로 주류를 살 수 있습니다.</p>
		<% } %>
		<br>
		<a onclick="history.back()">처음으로 돌아가기</a>
	</div>
	
	
	
</body>
</html>