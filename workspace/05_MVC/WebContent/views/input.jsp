<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		onload = function() {
			
			document.getElementById('btn1').onclick = function(){
				location.href = '/05_MVC/DateController'; //Servlet 매핑값 : 'DateController'
			} // 고전 이벤트(.onclick) 종료
			
			document.getElementById('btn2').onclick = function(){
				location.href = '/05_MVC/TimeController';
			}
			
			document.getElementById('btn3').onclick = function(){
				location.href = '/05_MVC/LottoController';
			}
			
		}
	</script>
</head>
<body>

	<%-- VIEW : 언제나 Servlet(Controller)에게 요청한다. --%>
	<input type="button" value="현재 날짜를 알려다오." id="btn1">
	
	<br><br>
	
	<input type="button" value="지금 시간을 알려다오." id="btn2">
	
	<br><br>
	
	<input type="button" value="로또 번호 6개를 알려다오." id="btn3">

	<br><br>
	<br><br>
	
	<!-- 데이터를 보내는 input.jsp -->
	<form action="/05_MVC/GugudanController">
		<input type="number" min="2" max="9" name="dan" placeholder="원하는 구구단 입력" >
		<button>구구단을 보여다오</button>
	</form>
	
	<br><br>
	<br><br>
	
	<h3>a링크로 전달해보기 (파라미터 포함)</h3>
	<a href="/05_MVC/AgeController?age=20">맥주하나 주이소!</a>

	<br><br>
	<br><br>
	
	<!-- action을 [form]태그에 잡아두지 않는 방법 -->
	<form id="f">
		<input type="text" name="hour" placeholder="지금 시간을 입력하세요.">
		<input type="button" value="밥 주세요" id="btn4">
	</form>
	<script>
		const form = document.getElementById('f'); // 제이쿼리 경우_ const form = $('$f');
		/* Click! */
		document.getElementById('btn4').addEventListener('click', function(){
			if (form.hour.value == '') {
				alert('지금 시간을 꼭 입력하세요');
				form.hour.focus();
				return;  // function()을 끝내겠다!
			}
			form.action = '/05_MVC/MealController';
			form.submit();
		})
	</script>
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>