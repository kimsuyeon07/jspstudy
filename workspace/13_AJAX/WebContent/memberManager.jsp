<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<style>
		* {padding:0; margin:0 auto; box-sizing:border-box; text-decoration:none; outline:none;}
		body {margin: 0 20px;}
		.container {display:flex;}
		.left {width:500px;}
		.right {width:500px;}
		h3 {margin: 50px 0 20px 0;}
		/* left */
		.left input[type="text"] {
			margin-top: 5px;
			padding-left: 5px;
			width:80%;
			height:40px;
			border: 1px solid lightgray;
		}
		.left input[value="여"] {
			margin-left: 5px;
		}
		.left select {
			margin-top: 5px;
			padding-left:5px;
			width: 100px;
			height: 40px;
			border: 1px solid lightgray;
			margin-bottom: 70px;
		}
		.left input[type="text"]:focus, .left select:focus {border:1px solid green;}
		.left input[type="button"] {
			margin-top: 10px;
			margin-right: 10px;
			width:85px;
			height: 40px;
			border-radius: 20px;
			border: 1px solid black;
			background-color: white;
		}
		.left input[type="button"]:hover {
			border: none;
			background-color: black;
			color: white;
			cursor:pointer;
		}
		/* right */
		.right table { border-collapse: collapse; width:100%; }
		.right tr {}
		.right td {
			height: 40px;
			padding: 5px 10px;
			text-align: center;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
		}
		.right td:nth-child(1) {width:17%;}
		.right td:nth-child(2) {width:15%;}
		.right td:nth-child(3) {width:15%;}
		.right td:nth-child(4) {width:13%;}
		.right td:nth-child(5) {width:11%;}
		.right td:nth-child(6) {width:29%;}

		.right input[type="button"] {
			width:50px;
			height: 30px;
			border-radius:20px;
			border: 1px solid #333;
			background-color: white;
			margin: 1px 2px;
		}
		.right input[type="button"]:hover {
			border: none;
			background-color: green;
			color: white;
			cursor:pointer;
		}
		
		
	</style>
	
	<script 
		src="https://code.jquery.com/jquery-3.6.0.min.js"
	 	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>
	<script>
		
		// 페이지 로드 이벤트
		$(function(){
			
			selectMemberList();
			selectMemberByNo();
			updateMember();
			init();
			insertMember();
			deleteMember();
			
		}) // 로드 이벤트 (종료)
		
		
		
		/* 1. 함수 : selectMemberList() */ 
		function selectMemberList(){
			$.ajax({
				url: 'selectMemberList.do',
				type: 'get',
				dataType: 'json',
				success: function(result){
					/*
						result = {
							"list":
								[
									{"no":5,"address":"부산","gender":"여","name":"에이미","id":"user5"},
									{"no":4,"address":"대구","gender":"남","name":"스미스","id":"user4"},
									{"no":3,"address":"대전","gender":"여","name":"제시카","id":"user3"},
									{"no":2,"address":"인천","gender":"여","name":"앨리스","id":"user2"},
									{"no":1,"address":"서울","gender":"남","name":"제임스","id":"user1"}
								],
								"isExist":true
						}
					*/ 
					
					// 전체 회원수
					$('#totalRecord').text('전체 회원: ' + result.paging.totalRecord + '명');
					
					/* 테이블 생성 작업 */
					// 기존의 목록을 화면에서 제거
					$('#memberList').empty();
					
					if (result.isExist) { // 목록이 있다면,
						generateMemberList(result.list);
					} else { // 목록이 없으면,
						$('<tr>') // 2.<tr>을 만들겠다.
						.append( $('<td colspan="6">').text('회원목록이 없습니다.') ) // 3.<tr>안에 <td>를 만들겠다.
								 // ()내부의 괄호 먼저 진행하겠다.
						.appendTo('#memberList'); // 1.'#memberList' 안에서 진행하겠다.
					}
				},
				error: function(xhr, status, error){
					console.log(status + " : " + error);
					alert('회원 목록 로드를 실패했습니다.');
				}
			})
		}
		
		/* 1-1. 함수: generateMemberList(list) */ 
		// 회원 목록을 받아서 테이블을 생성하는 함수
		function generateMemberList(list) {
			$.each(list, function(i, member){ // member : 각각의 하나의 회원정보 : {"no":5,"address":"부산","gender":"여","name":"에이미","id":"user5"}
				$('<tr>')
				.append( $('<td>').text(member.no) )
				.append( $('<td>').text(member.id) )
				.append( $('<td>').text(member.name) )
				.append( $('<td>').text(member.gender) )
				.append( $('<td>').text(member.address) )
				/* 조회에 필요한 해당 Member의 NO의 값을 가지고 올 수 있는 방법  */
				.append( $('<input type="hidden" name="no">').val(member.no))
				// ↑ <input>에 "value"를 따로 추가하는 방법! >>>> .val()
				.append( $('<td>').html('<input type="button" value="조회" id="view_btn"><input type="button" value="삭제" id="delete_btn">') )
				.appendTo('#memberList');
			})
		}
			
			
		/* 2. 함수: 회원정보 가져오기 */ 
		function selectMemberByNo() {
			/* ** 이벤트 처리가 안된다 **  
			      동적으로 만들어진 객체(AJAX으로 만들었다.)
			$('#view_btn').on('click', function(){
				alert('수정하시겠습니까?');
			}) 
			*/
			// 정적으로 만들어진 객체 (**해당 방법으로 작성해야 함.**)
			$('body').on('click', '#view_btn', function(){
				// 회원번호 출력하기  >>>> [ $(this) ] : '#view_btn'(사용자가 누른)
				// ▼ (*동일한 방법*) ▼
				/* var a = $(this).parent().parent().find('input:hidden[name="no"]').val(); */
				var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
				$.ajax({
					url: 'selectMemberByNo.do',
					type: 'get',
					data: 'no='+no,
					success: function(result){
						if(result.isExist){
							// **(동일한 결과)** $('.left input:text[name="id"]').val(reslut.id);
							$('#id').val(result.id);
							$('#name').val(result.name);
							/* checked : 속성은 "attr"으로 작성하지 않고 ["prop"]을 사용해서 진행한다. */
							$('.left input:radio[name="gender"][value="'+ result.gender +'"]').prop('checked', true);
							$('#address').val(result.address);
							/* 1. 이전의 만들어둔 것을 지워주기 ( .remove(); ) 
							   2. 회원번호를 넣어준다. [ input[type="hidden"]으로 생성_ ] */
							$('.left input:hidden[name="no"]').remove();
							$('.left').append( $('<input type="hidden" name="no">').val(result.no) );
						} else {
							alert('해당 회원 정보를 확인할 수 없습니다.');
						}
					}, 
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 오류.');
					}
				})
			})
		}
		
			
		/* 3. 회원 정보 수정 */
		function updateMember(){
			$('#update_btn').click(function(){
				// 서버로 JSON 데이터 보내기
				var obj = {
						"no" : $('input:hidden[name="no"]').val(),
						"id" : $('#id').val(),
						"name" : $('#name').val(),
						"gender" : $('input:radio[name="gender"]:checked').val(),
						"address" : $('#address').val()
				};
				/*
					console.log(JSON.stringify(obj));  
					[ JSON.stringify(JSON객체) ] : json객체를 문자열로 바꿔주는 코드
					▼ ▼ ▼ 
					JSON.stringify(obj)	↓↓ 
					{"no":"5","address":"부산","gender":"여","name":"에이미","id":"user5"}
				*/
				$.ajax({
					url : '/13_AJAX/updateMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json',
					success: function(result){
						if(result.isSuccess) {
							alert('회원 정보가 수정되었습니다.');
							// 목록을 다시 만들어 주기 위한 작업(해당 함수 호출)
							selectMemberList();
						} else {
							alert('변경된 회원 정보가 없습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 수정_ 오류.');
					}
				})
			})
		}
		
		/* 4. 초기화 */
		function init(){
			$('#init_btn').click(function(){
				$('#name').val('');
				$('input:radio[name="gender"]').prop('checked', false);
				$('#address').val('');
			})
		}
		
		/* 5. 회원 등록(추가)하기 */
		function insertMember() {
			$('#insert_btn').click(function(){
				// 자바 스크립트 객체 (JSON객체가 아니다.)
				// → →  자바 스크립트 객체    >>>>  JSON객체로 바뀌는 구간.
				//	    " JSON.stringify(obj)"
				var obj = {
					// "no" : 필요없음!
					"id" : $('#id').val(),
					"name" : $('#name').val(),
					"gender" : $('input:radio[name="gender"]:checked').val(),
					"address" : $('#address').val()
				};
				
				$.ajax({
					url: '/13_AJAX/insertMember.do',
					type: 'post',
					data: 'member='+JSON.stringify(obj),
					dataType: 'json',
					success: function(result){
						if (result.isSuccess) {
							alert('신규회원이 추가되었습니다.');
							selectMemberList();
						} else {
							alert('신규회원 추가에 실패하였습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 등록_ 오류.');
					}
				})
			})
		}
		
		
		/* 6. 회원 삭제 */
		function deleteMember(){
			$('body').on('click', '#delete_btn', function(){
				var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
				if(confirm(no + '번 회원을 삭제할까요?')) {
					$.ajax({
						url: '/13_AJAX/deleteMember.do',
						type: 'get',
						data: 'no=' + no,
						dataType: 'json',
						success: function(result){
							if (result.isSuccess) {
								alert('회원 정보가 삭제되었습니다.');
								selectMemberList();
							} else {
								alert('회원 정보 삭제에 실패하였습니다.');
							}
						}, 
						error: function(xhr, status, error){
							console.log(status + " : " + error);
							alert('회원 등록_ 오류.');
						}
					})
					
				} // [END]if(confirm())
			})
		}
		
		
	</script>
</head>
<body>

	<div class="container">
	
		<%-- 회원 삽입 / 수정/ 보기 --%>
		<div class="left">
			<h3>삽입 / 수정 / 보기</h3>
			아이디 <br>
			<input type="text" name="id" id="id"> <br><br>
			이름 <br>
			<input type="text" name="name" id="name"> <br><br>
			성별 <br>
			<label><input type="radio" name="gender" id="gender" value="남">남</label>
			<label><input type="radio" name="gender" id="gender" value="여">여 </label>  <br><br>
			주소 <br>
			<select name="address" id="address">
				<option value="">지역선택</option>
				<option value="서울">서울</option>
				<option value="경기">경기</option>
				<option value="인천">인천</option>
				<option value="대구">대구</option>
				<option value="대전">대전</option>
				<option value="광주">광주</option>
				<option value="울산">울산</option>
				<option value="부산">부산</option>
			</select>  <br><br>
			<input type="button" value="회원등록" id="insert_btn">
			<input type="button" value="정보 수정" id="update_btn">
			<input type="button" value="초기화" id="init_btn">
		</div>
		
		<%-- 회원 목록/삭제 --%>
		<div class="right">
			<h3>회원 목록 / 삭제</h3>
			<div id="totalRecord"></div>
			<table>
				<thead>
					<tr>
						<td>회원번호</td>
						<td>아이디</td>
						<td>이름</td>
						<td>성별</td>
						<td>주소</td>
						<td>Btn</td>
					</tr>
				</thead>
				<tbody id="memberList"></tbody>
			</table>
			
		</div>
		
	</div>

</body>
</html>