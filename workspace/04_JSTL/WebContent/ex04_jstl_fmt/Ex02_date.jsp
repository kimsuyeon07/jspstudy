<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- value >> 클래스생성도 가능! --%>
	<c:set var="now" value="<%=new Date() %>" />
	
	<!-- 디폴트(기본값) : 2021. 5. 21 -->
	<h3>디폴트(기본값) : <fmt:formatDate value="${now}" /> </h3>
	
	<%-- SimpleDateFormat 클래스의 날짜 패턴을 그대로 사용한다. --%>
	<!-- pattern = " yyyy(년도) 년          MM(개월) 월          dd(날짜) 일         E(요일) 요일 "  -->
	<h3>날짜 : <fmt:formatDate value="${now}" pattern="yyyy년  MM월 dd일 E요일" /> </h3>
	<!-- pattern = " a(오전/오후_구분) h(12시간제_표기법_소문자):mm:ss " -->
	<h3>12시간제 : <fmt:formatDate value="${now}" pattern="a h:mm:ss" /> </h3>
	<!-- pattern = " H(24시간제_표기법_대문자):mm:ss" -->
	<h3>24시간제 : <fmt:formatDate value="${now}" pattern="H:mm:ss" /> </h3>
	
	
</body>
</html>