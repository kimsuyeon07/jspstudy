<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/02_JSP/ex07/receive1.jsp"> <!-- method="get" [디폴트값] -->
			<input type="text" name="name" placeholder="Name">  <br>
			<input type="text" name="age" placeholder="Age">   <br>
			<button>Send</button>
	</form>
	<br><br>
	<form action="/02_JSP/ex07/receive2.jsp"> <!-- method="get" [디폴트값] -->
			<input type="text" name="name" placeholder="Name">  <br>
			<input type="text" name="age" placeholder="Age">   <br>
			<button>Send</button>
	</form>
	
</body>
</html>