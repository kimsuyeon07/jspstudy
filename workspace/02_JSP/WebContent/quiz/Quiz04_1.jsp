<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script> src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" </script>

	<script>

	$(document).ready(function(){
		$('#result').on('blur', function(){
			if ( isNaN($(this).val()) ) {
				alert('정수만 입력할 수 있습니다.');
				$(this).val('');
				$(this).focus();
			}
		}) 
	})
			
	</script>
	
</head>
<body>
	<% 
	 	// 1~9까지의 정수
		int n1 = (int)(Math.random()*9)+1; 
		int n2 = (int)(Math.random()*9)+1; 
		// + - * / %
		String[] opList = {"+","-","*","/","%"};
		String op = opList[(int)(Math.random()*5)+1];
		// 연산
		int answer = 0;
		switch (op) {
		case "+" : answer = n1 + n2; break;
		case "-" : answer = n1 - n2; break;
		case "*" : answer = n1 * n2; break;
		case "/" : answer = n1 / n2; break;
		case "%" : answer = n1 % n2; break;
		}
	%>

	<h3>랜덤 계산기</h3>
	<form action="/02_JSP/quiz/Quiz04_2.jsp">
		<label for="result"> <%=n1 %> <%=op %> <%=n2 %> = &nbsp;</label>
		<input type="text" id="result" name="result" siz="3">
		<input type="hidden" name="n1" value="<%=n1 %>">
		<input type="hidden" name="n2" value="<%=n2 %>">
		<input type="hidden" name="op" value="<%=op %>">
		<input type="hidden" name="answer" value="<%=answer %>">
		<button>제출</button>
		
	</form>

</body>
</html>