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
		/* ▼ 아이디 체크 ▼ */
		function idCheck() {
			let result = false;  // 초기화 : 아이디 실패
			if (id.val() == '') {
				id_message.text('아이디는 필수입니다.');
			} else {
				id_message.text('아이디 통과.');
				// 아이디 성공
				result = true; 
			}
			return result; // submit할 때 반환값을 사용하기 위해. >> submit할 때, 반환 값이 false이면 submit을 할 수 없다.
		}  // (END)idCheck() 
		id.blur(function(){ // 아이디를 입력하고 빠져 나갈 때,
			idCheck(); // 해라.
		}) // (END)id.blur(function()	
		/* ▲ (END)아이디 체크  ▲ */
		
		const pw = $('#pw');
		const pw2 = $('#pw2');
		/* ▼ 비밀번호 체크 ▼ */
		function pwCheck() {
			let result = false;
			if (pw.val() == '') {
				pw_message.text('비밀번호는 필수입니다.');
			} else {
				pw_message.text('비밀번호 통과');
				result = true;
			}
			return result;
		} // (END)pwCheck() 
		pw.blur(function(){
			pwCheck();
		}) // (END)pw.blur(function()
		/* ▲ (END)비밀번호 체크  ▲ */
		// ↓ ↓ ↓ ↓ ↓
		/* ▼ 비밀번호 일치 체크 ▼ */
		function pw2Check() {
			let result = false;
			if(pw2.val() == '') {
				pw2_message.text('비밀번호 확인은 필수입니다.');
			} else if (pw.val() != pw2.val()) {
				pw2_message.text('비밀번호가 일치하지 않습니다.');
			} else {
				pw2_message.text('비밀번호가 일치합니다.');
				result = true;
			}
			return result;
		} // (END)pw2Check() 
		pw2.blur(function(){
			pw2Check();
		}) // (END)pw2.blur(function() 
		/* ▲ (END)비밀번호 일치 체크  ▲ */
		
		
		const name = $('#name');
		
		const email = $('#email');
		/*  이메일 체크 ▼ */
		function emailCheck() {
			let result = false;
			if (email.val() == '') {
				email_message.text('이메일은 필수입니다.');
			} else {
				email_message.text('이메일 통과');
				result = true;
			}
			return result;
		} // (END)emailCheck() 
		email.blur(function(){
			emailCheck();
		}) // (END)email.blur(function()	
		/* ▲ (END)이메일 체크  ▲ */

		/*  */
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		const pw2_message = $('#pw2_message');
		const name_message = $('#name_message');
		const email_message = $('#email_message');

		/* (submit) 버튼을 눌렀을 때,수행한다. */
		f.submit(function(event){  
			if ( !idCheck() || !pwCheck() || !pw2Check() || !emailCheck() ) { // 하나라도 false가 반환되었다면,
				alert('회원가입 정보를 확인 하세요.');
				event.preventDefault();
				return false;
			}// if문 (END)
		}) 
		// ▲ f.submit(function(event) (END) ▲
		
	})
	// 페이지 로드 이벤트 (종료) ▲	
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
		<input type="text" name="id" id="id" placeholder="ID" autofocus>	<br>
		<input type="button" value="중복확인" id="id_btn"> 					<br>
		<span id="id_message" class="error_message"></span> 				<br>
		
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