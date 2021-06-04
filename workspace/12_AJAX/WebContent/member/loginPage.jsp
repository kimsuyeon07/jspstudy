<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		* {margin:0 auto; padding:0; box-sizing:border-box;}
		body {padding:0 20px;}
		form {
			width: 450px;
			margin-top:150px;
		}
		input, button {
			width: 100%; 
			padding-left:10px; 
			outline:none; 
			margin-bottom:20px;
		}
		input {
			height:50px;
			border: 1px solid lightgray;
		}
		input:focus {
			border: 1px solid orange;
		}	
		button {
			height:50px;
			border: none;
			background-color:orange;
			color:white;
			font-size:16px;
			cursor:pointer;
		}
		a{
			display:block;
			height:50px;
			border: 1px solid orange;
			background-color:white;
			font-size:16px;
			text-align:center;
			text-decoration: none;
			line-height:50px;
			color: orange;
			margin-top:60px;
		}
		a:hover {
			background-color:orange;
			color:white;
		}
	</style>
</head>
<body>

	<form action="/12_AJAX/login.do" method="post" >
		<input type="text" name="id" placeholder="ID"> <br>
		<input type="password" name="pw" placeholder="PASSWORD"> <br>
		<button>Login in</button>
		<a href="/12_AJAX/joinPage.do">Join .GO</a>
	</form>

</body>
</html>