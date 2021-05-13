<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		label {font-size:14px;}
		select, button {box-sizing:border-box; height:30px;}
		select {width:160px;}
	</style>
</head>
<body>
	<form action="/02_JSP/quiz/Quiz02_2.jsp">
		<label for="age">당신의 나이는 ? </label>
		<input type="text" id="age" name="age">
		<button>확인</button>
	</form>
	
</body>
</html>