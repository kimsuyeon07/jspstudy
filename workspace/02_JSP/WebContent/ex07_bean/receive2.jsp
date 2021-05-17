<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% request.setCharacterEncoding("UTF-8"); %>
	
	<%-- 
		디폴트 생성자로 만든 빈
		Person p = new Person(); 
		↓↓
		(==)
	--%>
	<jsp:useBean id="p" class="ex07_bean.Person"></jsp:useBean>
	
	<%--
		setter로 데이터 주입하기
		p.setName(request.getParameter("name"));
		p.setAge(request.getParameter("age"));
	--%>
	
	<%-- p가 가지고 있는 모든 필드 값의 "'*'모든 걸" 알아서 채워라 --%>	 
	<jsp:setProperty property="*" name="p"/>
	
	<h3>이름: <jsp:getProperty property="name" name="p"/></h3>
	<h3>나이: <jsp:getProperty property="age" name="p"/></h3>
	<br>
	<h3>이름: <%=p.getName() %></h3>
	<h3>나이: <%=p.getAge() %></h3>
	
</body>
</html>