<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="비밀번호 변경" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">

<script>
	$(document).ready(function(){
		
		const f = $('#f');
		const pw0 = $('#pw0');
		const pw = $('#pw');
		const pw1 = $('#pw1');

		const pw0_message = $('#pw0_message');
		const pw_message = $('#pw_message');
		const pw1_message = $('#pw1_message');

		/* ▼ 비밀번호 체크 ▼ */
		f.submit(function(event){
				pw0_message.text('');  // 공백 처리
				pw_message.text('');
				pw1_message.text('');
				
			if (pw0.val() != '${loginDTO.pw}') {
				pw0_message.text('현재 비밀번호가 일치하지 않습니다.');
				pw0.focus();
				event.preventDefault();
				return false;
			} else if(pw.val() == '') { // 공백검사 확인이 아니라 정규식 검사로 수정
				pw_message.text('새 비빌번호를 입력하세요.');
				pw.focus();
				event.preventDefault();
				return false;
			} else if(pw1.val() != pw.val()) {
				pw1_message.text('비밀번호 입력을 확인하세요.');
				event.preventDefault();
				return false;
			} 
		})
		
		/* ▲ (END)비밀번호 체크  ▲ */

		
		
	})
	// 페이지 로드 이벤트 (종료) ▲	
</script>

<style>
	.pw_form {
		margin: 0 auto;
		width: 100%;
	}
	#f {
		width: 450px;
		margin: 50px auto;
	}
	#f > input, #f > button {
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
		cursor: pointer;
	}
	
	/* 입력하지 않았을 때 텍스트 표시 */
	#f > .message {
		color: red;
		font-size: 12px;
		display: inline-block;
		padding-bottom: 15px;
	}
	
	/* title 작업 */
	.title {
		font-size: 13px;
		font-weight: 600; 
	}
	
</style>

<div class="pw_form"> 
	<form action="/10_MODEL2/updatePw.m" method="post" id="f">
		
		<%-- 비밀번호 --%>
		<span class="title">현재 비밀번호</span>	 			<br>
		<input type="password" name="pw0" id="pw0"> 	<br>
		<span class="message" id="pw0_message"></span> 	<br>
		<%-- 새 비밀번호 --%>
		<span class="title">새 비밀번호</span>	 			<br>
		<input type="password" name="pw" id="pw"> 		<br>  <!-- 새롭게 변경할 비밀번호 정보_  -->
		<span class="message" id="pw_message"></span>	<br>
		<%-- 비밀번호 확인 --%>
		<span class="title">비밀번호 확인</span>	 			<br>
		<input type="password" name="pw1" id="pw1"> 	<br>
		<span class="message" id="pw1_message"></span>	<br>
		
		<%-- 비밀번호 변경_ Btn --%>
		<button> 비밀번호 변경 </button>
		
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>

