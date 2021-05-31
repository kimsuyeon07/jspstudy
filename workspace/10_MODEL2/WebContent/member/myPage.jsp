<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="마이페이지" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.my_form {
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
		margin-bottom: 20px;
		
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

<script>
	$(document).ready(function(){
		
		const f = $('#f');

		/* ▼ 비밀번호 변경  ▼ */
		const pw_btn = $('#pw_btn');
		pw_btn.click(function(){
			location.href= '/10_MODEL2/updatePwPage.m';
		})
		/* ▲ (END)비밀번호 변경   ▲ */

		/* ▼ 이름, 이메일 변경  ▼ */
		const update_btn = $('#update_btn');
		const name = $('#name');
		const email = $('#email');
		update_btn.click(function(){
			if (name.val() == '${loginDTO.name}' && email.val() == '${loginDTO.email}') {
				alert('변경할 정보가 없습니다.');
				return;
			} 
			f.attr('action', '/10_MODEL2/updateMember.m');
			f.submit();
		})
		/* ▲ (END)이름, 이메일 변경   ▲ */
		
		/* ▼ 회원탈퇴  ▼ */
		const delete_btn = $('#delete_btn');
		delete_btn.click(function(){
			if (confirm('탈퇴하겠습니까?')) {
				location.href='/10_MODEL2/deleteMember.m';
			}
		})
		/* ▲ (END)회원탈퇴   ▲ */
		
		
		
		

		
		
	})
	// 페이지 로드 이벤트 (종료) ▲	
</script>


<div class="my_form"> 
	<form id="f" method="post" >
		<%-- 아이디 --%>
		<span class="title">아이디</span>		<br>
		${loginDTO.id}	<br><br>
		
		<%-- 비밀번호 --%>
		<span class="title">비밀번호 변경</span>	
		<input type="button" value="비밀번호 변경" id="pw_btn"> <br>	
		
		<%-- 이름 --%>
		<span class="title">이름</span>									<br>
		<input type="text" id="name" name="name" value="${loginDTO.name}"> 	<br>
		
		<%-- 이메일 --%>
		<span class="title">이메일</span>										<br>
		<input type="text" id="email" name="email" value="${loginDTO.email}"> 	<br>
		
		<%-- 회원정보 수정_btn --%>
		<input type="button" value="회원정보 수정" id="update_btn"> <br>	
		
		<%-- 회원탈퇴_btn --%>
		<input type="button" value="회원탈퇴" id="delete_btn"> <br>	
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>