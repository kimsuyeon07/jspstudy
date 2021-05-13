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
	<h3>신규 회원 정보를 입력하세요.</h3>
	<form action="/02_JSP/quiz/Quiz05_2.jsp">
	
		<table>
			<tr>
				<td><label for="id">아이디 &nbsp;</label></td>
				<td><input type="text" id="id" name="id" placeholder="ID"></td>
			</tr>
			<tr>
				<td><label for="pwd">비밀번호 &nbsp;</label></td>
				<td><input type="password" id="pwd" name="pwd" placeholder="●●●●"></td>
			</tr>
			<tr>
				<td><label for="name">이름 &nbsp;</label></td>
				<td><input type="text" id="name" name="name" placeholder="Name"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center; padding-top:15px;"><button>회원가입</button></td>
			</tr>
		</table>
	
		
	</form>
	

</body>
</html>