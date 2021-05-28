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
		const pw2 = $('#pw2');
		const name = $('#name');
		const email = $('#email');

		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		const pw2_message = $('#pw2_message');
		const name_message = $('#name_message');
		const email_message = $('#email_message');

		f.submit(function(event){  // 버튼을 눌렀을 때, 수행한다.
			if (id.val() == '') {
				id_message.text('아이디를 입력하세요.');
				id.focus();
				event.preventDefault(); // submit하지 못하도록 막는다.
				return false;
			}
			else if (pw_message.val() == '') {
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
	.join_form {
		margin: 0 auto;
		width: 100%;
	}
	#f {
		width: 450px;
		margin: 50px auto;
	}
	#f > input:not([type="button"]), #f > button {
		width: 100%;
		height: 50px;
		box-sizing: border-box;
		outline: none;
		
	}
	
	#f > input:not([type="button"]) {
		border: 1px solid lightgray;
		padding-left: 10px;
		margin-top: 5px;
	}
	#f > input:not([type="button"]):focus {
		border: 1px solid black;
	}
	
	#f > button {
		border: 1px solid darkgray;
		background-color: #333;
		color: white;
		font-size: 17px;
		margin-top: 20px;
	}
	
	/* 입력하지 않았을 때 텍스트 표시 */
	#f > .error_message {
		color: red;
		font-size: 14px;
	}
	
	/* title 작업 */
	.title {
		font-size: 13px;
		font-weight: 600; 
	}
	
</style>

<div class="join_form"> 
	<form method="post" id="f">
		<%-- 아이디 --%>
		<span class="title">아이디</span>		<br>
		<input type="text" name="id" id="id" placeholder="ID">	<br>
		<input type="button" value="중복확인" id="id_btn"> 		<br>
		<span id="id_message" class="error_message"></span> 	<br>
		
		<%-- 비밀번호 --%>
		<span class="title">비밀번호</span>									<br>
		<input type="password" name="pw" id="pw" placeholder="PassWord"> 	<br>
		<span id="pw_message" class="error_message"></span>					<br>

		<%-- 비밀번호 확인 --%>
		<span class="title">비밀번호 확인</span>	 						<br>
		<input type="password" id="pw2" placeholder="PassWord Check"> 	<br>
		<span id="pw2_message" class="error_message"></span>			<br>

		<%-- 이름 --%>
		<span class="title">이름</span>										<br>
		<input type="text" id="name" name="name" placeholder="Name"> 	<br>
		<span id="name_message" class="error_message"></span>				<br>

		<%-- 이메일 --%>
		<span class="title">이메일</span>										<br>
		<input type="text" id="email" name="email" placeholder="Email"> 	<br>
		<span id="email_message" class="error_message"></span>				<br>
		
		<%-- 캡차 --%>
		
		<%-- 약관동의 --%>
		
		<button>Join</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>