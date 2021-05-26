<%@page import="dto.BoardDTO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Board_List</title>
	<style>
		.container {
			width: 700px;
			margin: 100px auto;
			
		}
		.container > a {
			display: inline-block;
			width: 120px;
			height: 25px;
			float: right;
			text-align: center;
			text-decoration: none;
			border-radius: 10px;
			background-color: black;
			color: white;
			font-size: 13px;
			line-height: 24px;
		}
		table {
			border-collapse: collapse; 
		}
		td {
			padding: 5px;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
			text-align: center;
			box-sizing: border-box;
		}
		td:nth-of-type(1) { width: 100px; }
		td:nth-of-type(2) { width: 260px; }
		td:nth-of-type(3) { width: 150px; }
		td:nth-of-type(4) { width: 70px; }
		td:nth-of-type(5) { width: 120px; }
	</style>
</head>
<body>

	<div class="container">
		<a href="insertPage.jsp">새 글 작성하기</a>
		<br><hr><br>
		
		<%
			List<BoardDTO> list = BoardDAO.getInstance().selectAll();
			pageContext.setAttribute("list", list); 
		%>
		
		<table>
			<thead>
				<tr>
					<td>게시글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.idx}</td>
						<td> <a href="view.jsp?idx=${dto.idx}">${dto.title}</a> </td>
						<td>${dto.author}</td>
						<td>${dto.hit}</td>
						<td>${dto.postdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>