<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 현재 날짜를 알려다오. -->
	<% if(request.getAttribute("today") != null) { %>
		<h3>요청한 현재 날짜는 ${today}입니다.</h3> 
	<!-- 지금 시간을 알려다오. -->
	<% } else if(request.getAttribute("time") != null) { %>
		<h3>요청한 현재 시간은 ${time}입니다.</h3> 
	<!-- 로또 번호 6개를 알려다오. -->
	<% } else if(request.getAttribute("lotto") != null) { %>
		<h3>로또 번호 :&ensp; </h3> 	
		<c:forEach var="lottoArr" items="${lotto}">
			${lottoArr} &ensp;
		</c:forEach>
	<!-- 구구단을 보여다오 -->
	<% } else if(request.getParameter("dan") != null) { %>
		<h3>요청한 구구단입니다.</h3>
		${gugudan} <br>
	<!-- 맥주하나 주이소! -->
	<% } else if(request.getParameter("age") != null) { %>
		<h3>${result}</h3>
	<!-- 밥주세요. -->
	<% } else if(request.getParameter("hour") != null) { %>
		<h3>지금 준비된 식사는 ${meal}입니다.</h3>
	<% } %>
	
	
	

</body>
</html>