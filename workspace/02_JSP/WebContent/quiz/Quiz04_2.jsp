<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		request.setCharacterEncoding("UTF-8");
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");
		String op = request.getParameter("op");
		String result = request.getParameter("result");
		String answer = request.getParameter("answer");
		
		String message = "";
		if (answer.equals(result)) {
			message += n1 + op + n2 + "=" + result + "<br>" + "정답입니다."; 
		} else {
			message += "제출 : " + n1 + op + n2 + "=" + result + "<br>" ;
			message += "정답 : " + n1 + op + n2 + "=" + answer ;
		}
		/* if (answer == result) {
			request.getRequestDispatcher("Quiz04_success.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Quiz04_fail.jsp").forward(request, response);
		} */
	%>
	
	<div>
		<%=message %>
		<br>
		<a href="/02_JSP/quiz/Quiz04_1.jsp">다시풀기</a>
		
	</div>
	

</body>
</html>