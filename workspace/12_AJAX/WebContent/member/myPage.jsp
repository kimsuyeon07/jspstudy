<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyPage_ajax으로 진행하기</title>
	<style>
		*{margin:0 auto; padding:0; text-decoration:none; box-sizing:border-box; font-size:14px;}
		.clear {clear:both;}
		body {padding:0 20px;}
		#my_form { width:450px; margin:50px 0 70px 0;}
		.hi {text-decoration:underline;}
		#logout{
			padding:10px 30px;
			border: 1px solid orange;
			color:orange;
			float:right;
			font-size: 14px;
			font-weight:600;
		}
		#logout:hover {
			background-color:orange;
			color:white;
		}
		.form_box{
			margin-top:60px;
		}
		.form_box span {
			padding-top: 2px; 
			font-size:17px; 
			display:inline-block;
			margin-bottom: 10px;
		}
		label {padding-bottom:5px; }
		input {
			width: 100%;
			height: 50px;
			border: 1px solid lightgray;
			outline: none;
			padding-left: 10px;
			margin-top: 5px;
		}
		input[name="pw1"] { width:64%; }
		input[value="비밀번호 변경하기"] { width:34%; cursor:pointer; padding:0;}
		
		.form_pw_box input {
			margin-bottom: 10px;
		}
		input[value="회원정보수정"] {
			cursor:pointer; 
			background-color: orange;
			color: white;
			border:none;
			font-weight:600;
			font-size:15px;
		}
		
	</style>
	
	<script 
		src="https://code.jquery.com/jquery-3.6.0.min.js"
	 	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>
	<script>
		$(function(){
			
			// DOM 선언
			const name = $('#name');
			const email = $('#email');
			const phone = $('#phone');
			
			
			
			// 이벤트
			$('#update_pw_btn').click(function(){
				// updatePw()함수를 호출하겠다.
				updatePw();
			})
			$('#update_user_btn').click(function(){
				// 회원정보수정_updateUser() 호출
				updateUser();
			})
			
			
			// 함수
			function updatePw() {
				
				if( '${loginUser.pw}' != $('#pw0').val() ) {
					alert('현재 비밀번호가 일치하지 않습니다.');
					return;
				} else if( '${loginUser.pw}' == $('#pw').val() ) {
					alert('현재 비밀번호와 동일한 비밀번호입니다.');
					return;
				} else if( $('#pw').val() == '' ) { // 비밀번호 정규식을 변경
					alert('새 비밀번호를 확인하세요.');
					return;
				} else if( $('#pw').val() != $('#pw1').val() ) {
					alert('새 비밀번호 입력이 일치하지 않습니다.');
					return;
				} // ▲ [END] if문  ▲
				
				$.ajax({
					url:'/12_AJAX/updatePw.do',
					type:'post',
					data: $('#my_form').serialize(),
					dataType: 'json',
					success: function(obj){
						if (obj.result == 1) {
							alert('비밀번호가 변경되었습니다.');
							$('#pw0').val();
							$('#pw').val();
							$('#pw1').val();
						} else {
							alert('비밀번호 변경이 되지 않았습니다.');
						}
					},
					error: function(){
						alert('오류가 발생했습니다.');
					}
				})// ▲ [END] $.ajax(){}  ▲
			} // ▲  [END]updatePw():함수  ▲
			
			
			function updateUser(){
				if( '${loginUser.name}' == name.val() && '${loginUser.email}' == email.val() && '${loginUser.phone}' == phone.val() ) {
					alert('수정사항이 없습니다.');
					return;
				} 
				
				$.ajax({
					url:'/12_AJAX/updateUser.do',
					type: 'post',
					data: $('#my_form').serialize(),
					dataType: 'json',
					success: function(obj){
						if (obj.result == 1) {
							alert('회원정보가 변경되었습니다');
						} else {
							alert('회원정보가 변경되지 않았습니다.');
						}
					},
					error: function(){
						console.log(status + ", " + error);
						alert('오류가 발생했습니다.');
					}
				})
			}
			
			
			
			
		}) // 페이지 로드 (종료)
	</script>
	
</head>
<body>
	
	<form id="my_form" method="post">
		<span class="hi"><b>${loginUser.name}</b>님 반갑습니다. &nbsp; :)</span>
		<a href="/12_AJAX/logout.do" id="logout">LOGOUT</a>
		
		<br><br>
		
		<div class="clear form_box">
			<b>아이디</b><br>
			<span style="color:orange; font-weight:600; font-size:20px;">${loginUser.id}</span> <br><br><br>
			
			<div class="form_pw_box">
				<b>현재 비밀번호</b><br>
				<input type="password" name="pw0" id="pw0"><br>
				<b>새 비밀번호</b><br>
				<input type="password" name="pw" id="pw"><br>
				<b>비밀번호 확인</b><br>
				<input type="password" name="pw1" id="pw1">
				<input type="button" value="비밀번호 변경하기" id="update_pw_btn"><br><br><br>
			</div>
			<b>이름</b><br>
			<input type="text" name="name" id="name" value="${loginUser.name}"><br><br><br>
			
			<b>이메일</b><br>
			<input type="text" name="email" id="email" value="${loginUser.email}"><br><br><br>
			
			<b>전화번호</b><br>
			<input type="text" name="phone" id="phone" value="${loginUser.phone}"><br><br><br>
			
			<b>가입일시</b><br>
			<span>${loginUser.regdate}</span> <br><br><br>
			
			<input type="hidden" name="no" value="${loginUser.no}">
			<input type="button" value="회원정보수정" id="update_user_btn">
	 	</div>
	</form>
	
</body>
</html>