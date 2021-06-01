<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% request.setCharacterEncoding("UTF-8"); %>
<!-- header.jsp (상단에 추가) -->
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시판" name="title" />
</jsp:include>
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.board_list {
		margin: 0 auto;
		width: 100%;
		height: 900px;
	}
	
	/* 검색창 CSS */
	#f {}
	select, input, button {
		height: 30px;
		border: 1px solid lightgray;
		outline: none;
		box-sizing: border-box;
	}
	select { width: 70px; margin-top: 3px; padding: 3px; }
	input[type="text"] { width: 220px; }
	button { width: 45px; cursor: pointer; }
	input[value="전체목록보기"] { 
		width: 100px; 
		float: right;
		background-color: #333; 
		color: white;
		border: none;
		cursor: pointer;
	}
	
	/*  */
	.board_list > p {
		clear: both;
		padding-bottom: 10px;
	}

	input[value="게시글 작성하기"] {
		margin: 6px 0;
		width: 120px; 
		float: right;
		cursor: pointer;
	}	
	
	/* 테이블 CSS */
	table {
		width: 100%;
		border-collapse: collapse;
	}
	td {
		border: 1px solid gray;
		height: 40px;
		padding-left: 10px;
	}
	td:nth-child(1) { width: 10%; }
	td:nth-child(2) { width: 45%; }
	td:nth-child(3) { width: 20%; }
	td:nth-child(4) { width: 10%; }
	td:nth-child(5) { width: 15%; }
	
	thead {
		background-color: #E9E9FA;
	}
	
	td > a:hover {
		text-decoration: underline;
		font-weight: 600;
	}
	
	.delete_color {
		background-color: #f2f2f2;
	}
	
	tfoot {
		text-align: center;
		padding: 10px 0;
	}
	tfoot td {
		padding: 16px 0;
	} 
	
</style>

<!-- 본문 내용 -->
<div class = "board_list">
	<%-- 검색 기능 --%>
	<form action="/10_MODEL2/findBoard.b" id="f">
		<!-- BOARD테이블의 칼럼을 'value'로 준다. -->	
		<select name="column"> 
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
			<option value="AUTHOR">작성자</option>
		</select>
		<input type="text" name="query">
		<button>검색</button>
		<!-- 전체목록 -->
		<input type="button" value="전체목록보기" onclick="location.href='/10_MODEL2/selectListBoardPage.b'">
	</form>		
	

	<%-- 로그인을 해야만 게시글을 작성할 수 있다. --%>
	<c:if test="${loginDTO != null}">
		<input type="button" value="게시글 작성하기" onclick="location.href='/10_MODEL2/insertBoardPage.b'">
	</c:if>
	
	<br><br><br>
	
	<p>전체 : <b>${totalRecord}</b>개 게시물</p>
	<table>
		<thead>
			<tr>
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>최종수정일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}" varStatus="k">
				<c:if test="${dto.state == -1}">
					<tr>
						<td>${seq - k.index}</td>
						<td colspan="4" class="delete_color">삭제된 게시글로 <b>비공개</b> 처리되었습니다.</td>
					</tr>
				</c:if>
				<c:if test="${dto.state == 0}">
					<tr>
						<!-- ${totalRecord - ((page - 1) * recordPerPage + k.index)} -->
						<td>${seq - k.index}</td>
						<td><a href="/10_MODEL2/selectOneBoard.b?idx=${dto.idx}">${dto.title}</a></td>
						<td>${dto.author}</td>
						<td>${dto.hit}</td>
						<td>${dto.lastmodified}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
		<tfoot>
			<td colspan="5">
				${paging}
			</td>
		</tfoot>
		
	</table>
	
</div>

<!-- footer.jsp (하단에 추가) -->
<%@ include file="../layout/footer.jsp" %>