<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script> src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" </script>
	
</head>
<body>

	<div>
		<p>----------------------------------------------------------------</p>
		<p>1. 회원 정보는 웹 사이트 운영을 위해서만 사용됩니다.</p>
		<p>2. 원치 않으면 정보 제공을 하지 않을 수 있습니다.</p>
		<p>3. 다만 이 경우 정상적인 웹 사이트 이용이 제한됩니다.</p>
		<p>----------------------------------------------------------------</p>
		
		<form action="/02_JSP/quiz/Quiz04_3.jsp">
			위 약관에 동의하십니까? <br><br>
			<input type="radio" id="agree" name="agreement" value="yes" checked> 
			<label for="agree">동의 함 </label>
			<input type="radio" id="noAgree" name="agreement" value="no" > 
			<label for="noAgree">동의 안 함 </label>
			<!-- 이전에 받은 데이터값을 넘겨받아줘야 한다. -->
			<% request.setCharacterEncoding("UTF-8"); %>
			<input type="hidden" name="id" value="<%=request.getParameter("id") %>">
			<input type="hidden" name="pwd" value="<%=request.getParameter("pwd") %>">
			<input type="hidden" name="name" value="<%=request.getParameter("name") %>">
			<br><br>
			
			<input type="submit" value="회원가입">
		</form>
	</div>
	
</body>
</html>