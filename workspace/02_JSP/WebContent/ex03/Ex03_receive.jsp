<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	/* ** <form>으로 전달하는 경우는 [Optional]로 받으면 안된다! ** */
	String name = request.getParameter("name");
	if (name.isEmpty()) {
		name = "기본이름";
	}
	String strAge = request.getParameter("age");
	int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge) ; 
	/*
	if (strAge.isEmpty()) {
		age = 0; 
	} else {
		age = Integer.parseInt(strAge);
	}
	*/
%>    
    
    
    
<script>
 	alert('이름은 <%=name%>이고, 나이는 <%=age%>살 이다.');
 	history.back();
</script>