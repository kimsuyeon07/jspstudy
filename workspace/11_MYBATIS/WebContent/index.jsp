<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>index</title>
	<style>
		body {
			margin: 150px auto;
			text-align: center;
		}
		a {
			display: inline-block;
			text-decoration: none;
			padding: 10px 250px;
			border: 1px solid orange;
			color: orange;
			margin-bottom: 20px;
		}
		a:hover {
			background-color: orange;
			border: none;
			color: white;
			font-weight: 500;
		}
		a:nth-child(5) {
			padding: 10px 223px;	
		}
	</style>
</head>
<body>
	
	<a href="/11_MYBATIS/selectList1.do">게시판1</a>
	<br>
	<a href="/11_MYBATIS/selectList2.do">게시판2</a>
	<br>
	<a href="/11_MYBATIS/selectList3.do">대댓글 게시판2</a>
	
</body>
</html>