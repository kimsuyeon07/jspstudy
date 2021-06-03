<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		a {
			text-decoration: none;
		}
		a:hover {
			text-decoration: underline;
		}
			
		.container {
			width: 760px;
			margin: 30px auto; 
			padding: 0 20px;
		}
		/* 새 글 작성 CSS */
		#newBoard {
			float: right;
			padding: 10px 20px;
			border: 1px solid lightgray;
			text-align: center;
			background-color: orange;
			color: white;
			text-decoration: none;
		}
		/* TABLE CSS */
		table{
			clear: both;
			width: 100%;
			margin-top: 10px;
			border-collapse: collapse;
		}
		table td {
			height: 40px;
			border: 1px solid lightgray;
			padding: 10px;
		}
		tbody td a {
			margin-left: 5px;
			color: orange;
			font-size: 12px;
		}
		tfoot td {
			text-align:center;
			background-color:#f2f2f2;
		}
		
		/* 검색창 */
		#f { margin-bottom: 30px; }
		select, input, button {
			height: 35px;
			outline: none;
		}
		select, input {
			border: 1px solid lightgray;
		}
		select {
			padding-left: 3px;
			margin-top: 3px;			
			width: 120px;
		}
		input[type="text"] {
			padding-left: 10px;
			width: 250px;
		}
		button, input[type="button"] {
			width: 45px;
			background-color:#f2f2f2;
			border: 1px solid gray;
			cursor: pointer;
		}
		
	</style>
</head>
<body>
	
	<div class="container"> 
		<!-- 새글 작성_ <a>태그 -->
		<a href="/11_MYBATIS/insertPage.do" id="newBoard">새글작성</a>
		
		<br><br><br>
		
		<!-- 검색란 -->
		<form action="/11_MYBATIS/findList.do" id="f"> <!-- method:get *(검색은 "get"으로 처리한다)* --> 
			<select name="column">
				<option value="TITLE">내용</option>
				<option value="AUTHOR">작성자</option>
				<option value="BOTH">내용+작성자</option>
			</select>
			<input type="text" name="query">
			<button>검색</button>
			<input type="button" value="전체" onclick="location.href='/11_MYBATIS/selectList1.do'"> 
		</form>
		<!-- 게시글 목록 -->
		전체 게시글 : ${totalRecord}개 <br>
		<table> 
			<thead>
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>작성자</td>
					<td>최종수정일</td>
					<td>조회수</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list }">
					<tr>
						<td colspan="5">작성된 게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${not empty list}">
					<c:forEach var="dto" items="${list}" varStatus="k">
						<tr>
							<td>${seq - k.index}</td>				<!-- ?no=${dto.no}를 추가해서 해당 게시글의 "no"를 보여준다. -->
							<td>
								<c:if test="${dto.depth == 1}">
									&nbsp;&nbsp;[re]
								</c:if>
								${dto.title}
								<c:if test="${dto.depth == 0}">
									<font size="1"><a href="/11_MYBATIS/insertReplyPage.do?groupno=${dto.groupno}">답글</a></font></td>
								</c:if> 
							<td>${dto.author}</td>
							<td>${dto.lastmodified}</td>
							<td>${dto.hit}</td>
						</tr>
						
						<tr class="insert_reply">
							<!-- 댓글 작성란 -->
						</tr>
						
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">${paging}</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>






