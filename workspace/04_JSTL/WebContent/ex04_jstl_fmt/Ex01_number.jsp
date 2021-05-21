<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 형식 (포멧팅) 라이브러리--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- 변수 선언을 위해서 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- var="n" >> 속성으로 저장  >>  사용을 위해서는 ${n}으로 표현한다. -->
	<c:set var="n" value="123456789.123456789" />
	
	<%-- groupingUsed : 3개씩 묶어준다. 는 의미 --%>
	<h3>쉼표 처리(,) : <fmt:formatNumber value="${n}" groupingUsed="true"></fmt:formatNumber> </h3>
	
	<h3>쉼표 처리(,) : <fmt:formatNumber value="${n}" pattern="#,##0" /> </h3>
	<h3>쉼표 처리(,) : <fmt:formatNumber value="${n}" pattern="#,##0.0" /> </h3>
	<h3>쉼표 처리(,) : <fmt:formatNumber value="${n}" pattern="#,##0.00" /> </h3>
	
	<%-- 소수자리를 표기하는 방법 --%>
	<h3>쉼표 없음 : <fmt:formatNumber value="${n}" pattern="0" /> </h3>
	<h3>쉼표 없음 : <fmt:formatNumber value="${n}" pattern="0.0" /> </h3>
	<h3>쉼표 없음 : <fmt:formatNumber value="${n}" pattern="0.00" /> </h3>

	<%-- 백분율 : type="percent" _지정 : 원래 숫자에 100을 곱해준 값 --%>
	<h3>퍼센트(백분율) : <fmt:formatNumber value="${n}" type="percent" /> </h3>

	<%-- currency :  --%>
	<h3>통화(원화) : <fmt:formatNumber value="${n}" type="currency" /> </h3>
	<h3>통화(타국) : <fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /> </h3>

</body>
</html>