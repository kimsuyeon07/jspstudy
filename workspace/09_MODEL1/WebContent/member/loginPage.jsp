<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login Page</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			
			const f = $('#f');
			const id = $('#id');
			const pw = $('#pw');
			
			f.on('submit', function(event){  // button에 submit이 아니라, <form>에 'submit' 진행_
				if (id.val() == '' || pw.val() == '') {
					alert('아이디와 비밀번호를 모두 입력하세요.');
					event.preventDefault();
					return false;
				}
			}) // f.on('submit', ...) (END)
			
			
		}) // 페이지 로드 이벤트 (END)
	</script>
	
	<style>
		.container {
			margin: 150px auto;
			text-align: center;
		}
		h1 {
			margin-bottom: 20px;
		}
		input {
			width:450px;
			height: 50px;
			outline: none;
			border: 1px solid lightgray;
			margin-bottom: 20px;
			padding-left: 10px;
		}
		button {
			width: 120px;
			height: 50px;
			background-color: black;
			color: white;
			outline: none;
			border: none;
			font-size: 16px;
			cursor: pointer;
			margin-top: 10px;
		}
	</style>
	
</head>
<body>

	<div class="container">
		<form id="f" action="login.jsp" method="post">
			<h1>로그인</h1>
			<input type="text" name="id" id="id" placeholder="ID">		<br>
			<input type="password" name="pw" id="pw" placeholder="PassWord">		<br>
			<button>Login</button>
		</form>
	</div>

</body>
</html>