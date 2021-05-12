<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function fn(f) {
		
		// f는 document.forms.send_form과 같다.
		
		// 스크립트 코드를 통해서 f의 action을 지정한 뒤 submit을 할 수 있다.
		f.action = "/02_JSP/ex03/Ex02_receive.jsp";  // "submit"을 할 경우는 알아서 파라미터값을 전달하기 때문에 적어주지 않아도 된다.
		f.submit();  // "submit() : submit해라! : input[type="button"]인 경우는 submit을 시켜줘야 한다. "
		
		/* ** <form>으로 전달하는 경우는 [Optional]로 받으면 안된다! ** */
	}
</script>
</head>
<body>
	
	<form name="send_form">
		<input type="text" name="name" placeholder="Name"> <br>
		<input type="text" name="age" placeholder="Age"> <br>
		<input type="button" value="Send >> Ex03_receive.jsp" onclick="fn(this.form)">  
	</form> 	 	<!-- this.form : 해당 객체의 <form>태그 안의 데이터를 전달해준다.  -->
		
</body>
</html>