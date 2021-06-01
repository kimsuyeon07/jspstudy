<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<% request.setCharacterEncoding("UTF-8"); %>
<!-- header.jsp (상단에 추가) -->
<jsp:include page="../layout/header.jsp">
	<jsp:param value="${dto.idx}번 게시글" name="title" />
</jsp:include>
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.board_view {
		width: 100%;
		display: flex;
		justify-content: space-between;
	}
	.board_content {
		width: 40%;
	}
	.board_content > p {
		font-size: 14px;
		font-weight: 600;
		padding-bottom: 5px; 
	}
	
	.board_img {
		width: 55%;
	}
	.board_img > img {
		width: 100%;
	}
	
	/* 버튼 */
	.btn {
		display: flex;
		margin-right: 10px;
	}
	.btn input[value="게시글 삭제"] { margin: 0 10px; }
	.btn input, .btn button {
		padding: 5px 8px;
		border: 1px solid gray;
		outline: none;
		cursor: pointer;
	}
	.btn input:hover, .btn button:hover {
		background-color: black;
		color: white;
	}
	
	
</style>

<script>
	// 페이지 로드 이벤트
	$(document).ready(function(){
		<%-- 삭제 버튼 --%>
		const delete_btn = $('#delete_btn');
		delete_btn.click(function(){
			if (confirm('삭제할까요?')) {
				location.href='/10_MODEL2/deleteBoard.b?idx=${dto.idx}';
			}
		})
		
		<%-- 버튼 --%>
		
		
	})
</script>

<!-- 본문 내용 -->
<div class="board_view">
	<div class="board_content">
		<p class="title">작성자</p>
		${dto.author} 				<br><br>
		<p class="title">작성자IP</p>
		${dto.ip} 					<br><br>
		<p class="title">최종수정일</p>
		${dto.lastmodified} 		<br><br>
		<p class="title">조회수</p>
		${dto.hit} 					<br><br>
		<p class="title">제목</p>
		${dto.title} 				<br><br>
		<p class="title">내용</p>
		<pre>${dto.content} </pre>	<br><br>
		<div class="btn">
			<c:if test="${loginDTO.id == dto.author }"> <%-- 작성자만 볼 수 있다. --%>
				<form action="/10_MODEL2/updateBoardPage.b">
					<!-- request로 데이터를 저장했기 때문에 다른 페이지로 데이터를 가지고 갈 수없어서 이렇게 작성 >> session으로 한다면 할 필요 없다. -->
					<input type="hidden" name="idx" value="${dto.idx}">
					<input type="hidden" name="title" value="${dto.title}">
					<input type="hidden" name="content" value="${dto.content}">
					<input type="hidden" name="filename" value="${dto.filename}">
					<button>수정하기</button>
				</form>
				<input type="button" value="게시글 삭제" id="delete_btn">
			</c:if>
			<input type="button" value="목록보기" onclick="location.href='${referer}'">
		</div>
	</div>
	<div class="board_img">
		<c:if test="${dto.filename != null}">
			<img src="/10_MODEL2/archive/${dto.filename}" alt="Board-img">
		</c:if>
	</div>
</div>



<!-- footer.jsp (하단에 추가) -->
<%@ include file="../layout/footer.jsp" %>