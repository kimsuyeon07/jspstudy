<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	JSP의 4개 영역
	
	1. 데이터를 저장할 수 있는 내장 객체 4개를 의미한다.
	2. 각 내장 객체는 데이터를 유지하는 생명 주기(Life Cycle)가 다르다.
	3. 속성(Attribute)으로 데이터를 저장한다
	4. 종류
		1) pageContext
			(1) 해당 페이지에서만 사용
			(2) 해당 페이지를 벗어나면 소멸
		2) request
			(1) 요청이 발생하면 사용
			(2) 응답되면 소멸
		3) session : 로그인 같은 경우는 session에 보관한다.
			(1) 웹 브라우저에서 사용
			(2) 웹 브라우저를 닫으면 소멸
		4) application
			(1) 웹 애플리케이션에서 사용
			(2) 애플리케이션을 닫으면 소멸
	5. 데이터 저장 메소드
		1) 내장객체.setAttribute("속성명", 데이터);
		2) 모든 데이터의 타입이 Object이다.
	6. 데이터 반환 메소드
		1) 내장객체.getAttribute("속성명");
		2) getAttribute()로 읽어 들인 속성은 캐스팅(형 변환)을 해야 한다.
	7. 4개 영역의 데이터는 EL(Express Language)로 사용한다.	
 --%>    
 
 <%
 	// pageContext에 데이터 저장하기
 	pageContext.setAttribute("name", "브레드");
 	pageContext.setAttribute("age",50);
 	/* "브레드", 50 : Object타입으로 저장된다. :: <object>타입은 데이터 타입을 저장할 수 있다. */
 %>
 
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>	
	
	<h3>이름 : <%=pageContext.getAttribute("name") %></h3>
	<h3>나이 : <%=pageContext.getAttribute("age") %></h3>
	<!-- ("age")+1  :  ERR. 진행할 수 없음 -->
	<!-- ↑ 경우, "캐스팅"후 진행하면 가능하다. -->
	<%
		// 캐스팅 : object 경우, Integer.parseInt(..) [X] 
		// =>>  ** 맨 앞에 (변환 타입)을 붙여준다 **
		String name = (String)pageContext.getAttribute("name");
		int age = (int)pageContext.getAttribute("age");
	%>
	<h3>이름의 길이 : <%=name.length() %></h3>
	<h3>나이에 한 살 더하기 : <%=age + 1  %></h3>
	
	
	
</body>
</html>