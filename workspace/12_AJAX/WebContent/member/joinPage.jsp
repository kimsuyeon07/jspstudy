<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Join</title>
	<style>
		* {
			padding:0;
			margin:0 auto;
			box-sizing:border-box;
		}
		body {padding:0 20px; margin: 50px auto;}
		#join_form {width: 450px;}
		label {
			display:block;
			font-weight:700;
			padding: 0 0 5px 0;
			margin-top:30px;
			font-size:14px;
		}
		input {
			padding: 5px 10px;
			width: 100%;
			height: 50px;
			border: 1px solid lightgray;
			outline: none;
		}
		input[value="가입하기"] {
			margin-top: 50px;
			cursor:pointer;
		}
		
		/* <form>태그 내부의 message CSS */
		.msg {
			/* color:red; */ 
			font-size: 15px;
			padding-top:5px;
			padding-left:5px;
		}
		
		
		
	</style>
	
	<script 
		src="https://code.jquery.com/jquery-3.6.0.min.js"
	 	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>
	<script>
		// 페이지 로드
		$(document).ready(function(){
			
			// DOM 선언
			const join_form = $('#join_form');
			const id = $('#id');
			const id_msg = $('#id_msg');
			const join_btn = $('#join_btn');
			
			// 결과
			a = idCheck();
			
			// 이벤트
			id.blur(function(){   idCheck();   })
			join_btn.click(function(){   join();  })
			
			
			// 함수
			function idCheck() {
				// 반환 값
				let result = false;
				
				// 아이디 정규식 : 4 ~ 32자, 소문자, 숫자, 특수문자(_, -), 시작은 소문자
				const regID = /^[a-z][a-z0-9_-]{3,31}$/;
				
				if( regID.test(id.val()) ) { //입력된 ID의 정규식 검사
					$.ajax({  // <form>으로 생각하고_보면 쉬움
						url:'/12_AJAX/idCheck.do',  // 서버로 보내는 요청 경로
						type: 'post',				// 서버로 보내고자하는 방식
						data: "id="+id.val(),		// 서버로 보내고자하는 데이터(파라미터) : (보낼때는 파라미터로, 받아올때는 JSON으로 받아오겠다.)
						dataType:'json',			// 서버가 보내준 응답 데이터의 타입
						success: function(obj) { // 서버의 응답 데이터는 function()의 매개변수로 전달된다.
							// obj : {"result":true} 또는 {"result":false}
							// obj.result == obj["result"] == true 또는 false
							if (obj.result) {
								$(id_msg).css('color','darkseagreen').css('font-weight','600');
								id_msg.text('멋진 아이디네요~');
								// 유일한 성공 조건
								result = true;  
								/////////////////////
							} else {
								$(id_msg).css('color','red').css('font-weight','500');
								id_msg.text('이미 가입된 아이디입니다.');
							}
						}, 
						error: function(){
							alert('오류가 발생했습니다.');
						}
					})  // ▲ [END] $.ajax(){}  ▲
						
				} else {
					$(id_msg).css('color','red').css('font-weight','500');
					id_msg.text('4~32자, 소문자/숫자/특수문자(_ ,-), 소문자 시작');
				} // ▲ [END] if문  ▲
					
				return result;
						
			} // ▲ [END] function idCheck(){} ▲
			
			
			function join() {
				$.ajax({
					url: '/12_AJAX/join.do',
					type: 'post',
					data: join_form.serialize(), //join_form의 모든 파라미터를 보낸다. [ serialize : 직렬화 ]
					dataType: 'json',
					success: function(obj){
						if(obj.result == 1) {
							alert('회원 가입되었습니다.');
							location.href='/12_AJAX/loginPage.do';
						} else {
							alert('회원 가입이 실패했습니다.');
						}
					},
					error: function(){
						alert('오류가 발생했습니다.');
					}
				})
			}
			
			
			
		}) // 페이지 로드 (종료)
		
	</script>
	
</head>
<body>

	
	<!-- <form action="/12_AJAX/join.do" id="join_form" method="post"> -->
	<form id="join_form" method="post">
		
		<h1>회원가입</h1>
		
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" placeholder="ID">
		<div id="id_msg" class="msg"></div>

		<label for="pw">비밀번호</label>
		<input type="password" name="pw" id="pw" placeholder="PASSWORD">
		<div id="pw_msg" class="msg"></div>
		
		<!-- <label for="rePw">비밀번호 확인</label>
		<input type="password" name="rePw" id="rePw" placeholder="PASSWORD CHECK">
		<div id="rePw_msg" class="msg"></div> -->
		
		<label for="name">이름</label>
		<input type="text" name="name" id="name" placeholder="NAME">
		<div id="name_msg" class="msg"></div>
		
		<label for="email">이메일</label>
		<input type="text" name="email" id="email" placeholder="E-mail">
		<div id="email_msg" class="msg"></div>
		
		<label for="phone">전화번호</label>
		<input type="text" name="phone" id="phone" placeholder="PHONE">
		<div id="phone_msg" class="msg"></div>
		
		<input type="button" value="가입하기" id="join_btn">
		
	</form>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>