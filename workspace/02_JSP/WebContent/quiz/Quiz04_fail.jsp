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
		int result = request.getParameter("result").isEmpty() ? 0 : Integer.parseInt(request.getParameter("result"));
		int answer = request.getParameter("answer").isEmpty() ? 0 : Integer.parseInt(request.getParameter("answer"));
		int n1 = request.getParameter("n1").isEmpty() ? 0 : Integer.parseInt(request.getParameter("n1"));
		int n2 = request.getParameter("n2").isEmpty() ? 0 : Integer.parseInt(request.getParameter("n2"));
		int sum = n1 + n2;
	%>
	
	<div>
		제출 : <%=n1 %> + <%=n2 %> = <%=result%>
		<br>
		정답 : <%=n1 %> + <%=n2 %> = <%=answer %>;
		<br>
		<a href="/02_JSP/quiz/Quiz04_1.jsp">다시풀기</a>
	</div>
	
	
</body>
</html>