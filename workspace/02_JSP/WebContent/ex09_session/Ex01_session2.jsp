<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String name = session.getAttribute("name") == null ? "기본이름" : (String)session.getAttribute("name"); // "name"이라는 속성을 저장하는 함수_
	/* String name = (String)session.getAttribute("name"); */
	// session.getAttribute("name") : < Object = 객체 타입 > :: 캐스팅 해준다!
	
	int age = session.getAttribute("age") == null ? 0 : (int)session.getAttribute("age");
	/* int age = (int)session.getAttribute("age"); */
	
	out.println("이름 : " + name + "<br>");
	out.println("나이 : " + age + "<br>");
	out.println("<a href=\"Ex01_session3.jsp\">세션 삭제하기</a> ");
%>