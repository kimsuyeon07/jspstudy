<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>개인정보를 입력하세요.</h3>
	<form action="/02_JSP/quiz/Quiz06_2.jsp">
		<table>
			<tr>
				<td><label for="id">아이디</label></td>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			
			<tr>
				<td><label for="pw">비밀번호</label></td>
				<td><input type="password" id="pw" name="pw"></td>
			</tr>
			
			
			<tr>
				<td><label for="name">이름</label></td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
		</table>
		<br>
		<button>확인</button>
	</form>
	
</body>
</html>