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
	}

	input[value="게시글 작성하기"] {
		margin-bottom: 10px;
	}	
	
	/* 테이블작업 */
	table {
		width: 100%;
		border-collapse: collapse;
	}
	td {
		border: 1px solid gray;
		height: 40px;
		padding-left: 10px;
	}
	td:nth-child(1) { width: 15%; }
	td:nth-child(2) { width: 30%; }
	td:nth-child(3) { width: 25%; }
	td:nth-child(4) { width: 10%; }
	td:nth-child(5) { width: 20%; }
	
	tfoot {
		text-align: center;
	}

	
</style>

<!-- 본문 내용 -->
<div class = "board_list">
	
	<%-- 로그인을 해야만 게시글을 작성할 수 있다. --%>
	<c:if test="${loginDTO != null}">
		<input type="button" value="게시글 작성하기" onclick="location.href='/10_MODEL2/insertBoardPage.b'">
	</c:if>
	
	<br><hr><br>
	<p>전체 : ${totalRecord} 개 게시물</p>
	<table>
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>최종수정일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.idx}</td>
					<td>${dto.title}</td>
					<td>${dto.author}</td>
					<td>${dto.hit}</td>
					<td>${dto.lastmodified}</td>
				</tr>
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