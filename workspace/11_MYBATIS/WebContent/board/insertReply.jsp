<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		*{
			margin: 0 auto;
			padding: 0;
			box-sizing: border-box;			
		}
		/* 입력박스 CSS */
		form {
			width: 450px;
			margin-top: 30px;
		}
		div {
			width: 100%;
			border: 1px solid lightgray;
			background-color: white;			
		}
		div input[type="text"], div textarea {
			outline: none;
			border: none;
			padding-left: 10px;
			width: 80%;
		}
		div input[type="text"]{
			height: 40px;
		}
		div textarea {
			width: 100%;
			height: 250px;
			padding-top:10px; 
		}
		/* 입력박스_타이틀 CSS */
		p {
			padding-bottom: 5px;
			margin-top:30px;
			font-size: 15px;
		}
		/* 버튼 CSS */
		button, input[type="reset"] {
			margin-top: 30px;
			margin-right: 10px; 
			width: 120px;
			height: 40px;
			outline: none;
			border:1px solid #aaa;
			background-color: #eee;
			cursor: pointer;
		}
		button:hover, input[type="reset"]:hover {
			background-color: black;
			color: white;
			
		}
	</style>
</head>
<body>
	
	<form action="/11_MYBATIS/insertRelpy.do" method="post">
		<h3>댓글 작성하기</h3>
		<p>작성자</p>
		<div><input type="text" name="author" autofocus></div>
		<p>제목</p>
		<div><input type="text" name="title" required></div>
		<p>내용</p>
		<div><textarea name="content" rows="5" cols="80"></textarea></div>
		
		<%-- 원글의 그룹번호 --%>
		<input type="hidden" name="groupno" value="${param.groupno}">
		 
		<button>저장하기</button>
		<input type="reset" value="작성초기화">
	</form>
	
</body>
</html>