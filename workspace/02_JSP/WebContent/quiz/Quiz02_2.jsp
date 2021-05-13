<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 예외가 발생하면 이동할 Quiz02_error.jsp 페이지를 등록 --%>
<%@ page errorPage="Quiz02_error.jsp" %>
    
<%
	request.setCharacterEncoding("UTF-8");
	String strAge = request.getParameter("age");
	int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);
	
	if (age < 20) {
		// forward (request를 그대로 전달한다.)
		request.getRequestDispatcher("Quiz02_kid.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("Quiz02_adius.jsp").forward(request, response);
	}
		
	
	
%>