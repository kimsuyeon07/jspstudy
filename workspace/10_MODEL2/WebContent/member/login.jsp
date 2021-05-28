<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">

<script>
	$(document).ready(function(){
		
		const f = $('#f');
		const id = $('#id');
		const pw = $('#pw');
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		f.submit(function(event){  // 버튼을 눌렀을 때, 수행한다.
			if (id.val() == '') {
				id_message.text('아이디를 입력하세요.');
				id.focus();
				event.preventDefault(); // submit하지 못하도록 막는다.
				return false;
			}
			else if (pw.val() == '') {
				// else if >> id는 입력이 되어있다고 가정
				id_message.text('');
				pw_message.text('비밀번호를 입력하세요.');
				pw.focus();
				event.preventDefault();
				return false;
			}// if문 (END)
		}) // f.sumit() (END)
		
	}) // 페이지 로드 이벤트 (종료)
</script>

<style>
	.login_form {
		margin: 0 auto;
		width: 100%;
		height: 600px;
	}
	#f {
		width: 450px;
		margin: 100px auto;
	}
	#f > input, #f > button {
		width: 100%;
		height: 50px;
		box-sizing: border-box;
		outline: none;
		
	}
	
	#f > input {
		border: 1px solid lightgray;
		padding-left: 10px;
		margin-top: 10px;
	}
	#f > input:focus {
		border: 1px solid black;
	}
	
	#f > button {
		border: 1px solid darkgray;
		background-color: #333;
		color: white;
		font-size: 17px;
		margin-top: 20px;
	}
	
	#f > span {
		color: red;
		font-size: 14px;
	}
	
</style>

<div class="login_form"> 
	<form action="/10_MODEL2/login.m" method="post" id="f">
		<input type="text" name="id" id="id" placeholder="ID">	<br>
		<span id="id_message"></span>
		<input type="password" name="pw" id="pw" placeholder="PassWord"> 	<br>
		<span id="pw_message"></span>
		<button>Login</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>