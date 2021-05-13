<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		label {font-size:14px;}
		select, button {box-sizing:border-box; height:25px;}
		select {width:65px;}
	</style>
</head>
<body>
	
	<%
		String[][] sites = {
				{"구글", "http://google.com"},
				{"네이버", "https://naver.com"},
				{"다음", "https://daum.net"},
				{"네이트", "https://nate.com"}
		};
	%>
	
	<form action="/02_JSP/quiz/Quiz01_2.jsp">
		<label for="site">사이트명 : </label>
		<select id="site" name="site">
			<% for(int i = 0 ; i<sites.length; i++) { %>
				<option value="<%=sites[i][1]%>"><%=sites[i][0] %></option>
			<% }%>
		</select>
		<button>확인</button>
	</form>
	
	
</body>
</html>