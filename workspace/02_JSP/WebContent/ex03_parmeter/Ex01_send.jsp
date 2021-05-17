<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="http://localhost:9090/02_JSP/ex03/Ex01_receive.jsp?name=브레드">Ex01_receive로 보내기</a>
	<a href="/02_JSP/ex03/Ex01_receive.jsp?age=50">Ex01_receive로 보내기</a>
	<a href="Ex01_receive.jsp?name=브레드&age=50">Ex01_receive로 보내기</a>

	<a href="./Ex01_receive.jsp?age=50">Ex01_receive로 보내기</a>
	<!-- ↑↑↑ "/ex03/Ex01_receive.jsp?age=50" =>> '/'로 시작하면 Context-Path로  인식.-->
	<!-- 
		↑↑↑ "ex03/Ex01_receive.jsp?age=50" =>> 
		    [ http://localhost:9090/02_JSP/ex03/ex03/Ex01_receive.jsp?age=50 ] 로 인식
		    
		    : 정 사용하고자 하면,
		    "./Ex01_receive.jsp?age=50"
	 -->

</body>
</html>