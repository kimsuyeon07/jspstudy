
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script> src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" </script>
	<style>
		.container {
			margin: 300px auto;
			width: 300px;
		}
		.blind { /* label을 숨긴다. */ display: none; }
	</style>
	
</head>
<body>
	<%
		// 쿠키명이 id인 쿠키를 찾는다.
		String id = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("id")){
					// id의 값을 담은 함수를 만든다.
					id = cookie.getValue();
					System.out.println(id);
					break;	
				}
			}
		}
	%>
	
	<script>
		/* 아이디 기억하기 : <script>에서 진행한다. */
		$(document).ready(function(){
			// id 값이 존재하면 실행한다.
			if (<%=id %> != null ) { 
				$('#id').val(<%=id%>);
				$('#chk').attr('checked', true);
			}
		})
	</script>
	
	<!-- ---------------------------------------------------------------- -->
	
	<div class="container">
		<form action="/02_JSP/ex08_cookie/Ex06_idCheck.jsp" method="post">
			<label for="id" class="blind">아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디"> 
			<br>
			<label for="pw" class="blind">비밀번호</label>
			<input type="password" name="pw" id="pw" placeholder="●●●●"> 
			<br>
			<button>로그인</button>  
			<br><br>
			<input type="checkbox" name="chk" id="chk">
				<label for="chk">아이디 기억하기</label>
		</form>
	</div>
	
</body>
</html>