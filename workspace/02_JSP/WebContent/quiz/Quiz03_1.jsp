<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		label {display:block; font-size:14px;}
	</style>
</head>
<body>
	
	<form action="/02_JSP/quiz/Quiz03_2.jsp">
		<label for="name">설문작성자</label>
		<input type="text" id="name" name="name" placeholder="이름을 입력하세요.">
		<br><br>
		<h3>1. 좋아하는 연예인은 누구인가요?</h3>
		<input type="text" name="starName" placeholder="연예인 이름 입력">
		<button>다음</button>
	</form>
	
</body>
</html>