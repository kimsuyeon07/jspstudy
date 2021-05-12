<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>이름 : <%=name%> </h3>
	<!-- [forward] 경우, 이동여부를 주소창으로 구분할 수 없다.   -->
	
</body>
</html>