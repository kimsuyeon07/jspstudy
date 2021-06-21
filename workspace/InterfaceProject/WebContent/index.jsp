<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"> </script>
	<script>
		$(document).ready(function(){
			fn_insert();
		
		}) // 페이지 로더 이벤트 (종료)
		
		// 함수
		function fn_insert(){
			$('#insert_btn').click(function(){
				var regSNO = /^[0-9]{6}$/;
				if ( !regSNO.test($('#sno').val()) ) {
					alert('주민등록번호는 6자리 숫자입니다.');
					return; // .click(function(){ .. })을 마치겠다!
				}
				$.ajax({
					url: 'insertPerson.do',
					type: 'post',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(obj) {
						//console.log(obj);  >>  1 : 성공  , 0 : 실패
						if (obj.count > 0) {
							alert('등록되었습니다.');
						} else {
							alert('등록에 실패하였습니다.')
						}
						
					},
					error: function(xhr, textStatus, errorThrown) {
						//console.log(textStatus);
						//console.log(errorThrown);
						//alert('ERROR');
						//↓ xhr.status : 상태를 정의하는 정수 값, 임의로 결정
						//console.log(xhr.status);
						//↓ xhr/responseText: 응답된 텍스트, 예외 메시지가 전달
						//console.log(xhr.responseText);
						if (xhr.status == 3001 || xhr.status == 3002 || xhr.status == 3003) {
							alert(xhr.responseText);
						}
					}
				}); // [END]ajax
			}); // [END]click
		} //[END]fn_insert()
	</script>
	<style>
		*{box-sizing: border-box; outline:none; margin:0; border-collapse:collapse;}
		body {width:100%; margin: 0 auto;}
		.container { position:relative; margin:50px auto; width:800px;}
		.insert_form {float:left; width:330px;}
		.list_form {float:right; width:450px;}
		
		/* left */
		#f input {width:100%; height: 45px; margin-bottom:10px; border:1px solid lightgray;}
		#f input[type="text"] {padding-left:10px;}
		#f input[type="text"]:focus {border:1px solid black;}
		#f input[type="button"]:hover {background-color:black; color:white; cursor:pointer;}
		
		/* right */
		.list_form table {width:100%; text-align:center;}
		.list_form td {border:1px solid #e5e5e5; height:45px;}
		.list_form td:nth-child(1) { width:30%; }
		.list_form td:nth-child(2) { width:20%; }
		.list_form td:nth-child(3) { width:20%; }
		.list_form td:nth-child(4) { width:30%; }
		.list_form thead tr {font-weight:600;}
		
	</style>
</head>
<body>

	<div class="container">
		
		<!-- left -->
		<div class="insert_form">
			<form id="f">
				<input type="text" id="sno" name="sno" placeholder="주민등록번호(6자리)">
				<input type="text" name="name" id="name" placeholder="이름">
				<input type="text" name="age" id="age" placeholder="나이">
				<input type="text" name="birthday" id="birthday" placeholder="생년월일">
				<input type="button" value="등록하기" id="insert_btn">
			</form>
		</div>
		
		<!-- right -->
		<div class="list_form">
			<table>
				<thead>
					<tr>
						<td>주민등록번호</td>
						<td>이름</td>
						<td>나이</td>
						<td>생년월일</td>
					</tr>
				</thead>
				<tbody id="person_list">
					
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>