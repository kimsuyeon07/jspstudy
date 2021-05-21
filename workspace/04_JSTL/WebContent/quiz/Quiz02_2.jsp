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

	<% request.setCharacterEncoding("UTF-8"); %>
	
	<!-- form으로 넘어오는 데이터값은 파라미터로 넘어온다! param.minN | param.maxN -->
	<h3>일반 forEach 연습</h3>
	<c:forEach var="i" begin="${param.minN}" end="${param.maxN}"  step="1">
		<font size="${i}">폰트 사이즈 : ${i} <br></font>
	</c:forEach> 
	
	
	<br><br>
	
	
	<!-- paramVaules.foods : 배열로 출력 -->
	<h3>향상 forEach 연습</h3>
	<ul>
		<!-- forEach : items >> (*배열*) >> paramValues.foods -->
		<!-- forEach : varStatus >> 배열의 순번,인덱스번호를 알 수 있다! -->
		<c:forEach var="check_food" items="${paramValues.foods}" varStatus="vs">
			<li>${vs.count}번째 음식 : ${check_food} </li>
		</c:forEach>
	</ul>
	
	

</body>
</html>