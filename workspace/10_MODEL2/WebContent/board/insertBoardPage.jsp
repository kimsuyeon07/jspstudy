<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<% request.setCharacterEncoding("UTF-8"); %>

<!-- header.jsp (상단에 추가) -->
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 작성하기" name="title"/>
</jsp:include>

<!-- CSS & script -->
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.insert_form {
		margin: 0 auto;
		width: 100%;
	}
	#f {
		width: 450px;
		margin: 50px auto;
	}
	#f > input[type="text"], #content {
		width: 100%;
		height: 50px;
		box-sizing: border-box;
		outline: none;
		border: 1px solid lightgray;
		padding-left: 10px;
		margin-bottom: 20px;
	}
	#content {
		height: 450px;
		padding: 15px 10px;
		font-size: 15px;
	}
	#filename {
		margin-bottom: 20px;
		
	}
	
	/* 타이틀 작업 */
	.title {
		padding-bottom: 5px;
	}
</style>

<script>
	$(document).ready(function(){
		const f = $('#f');
		
		const title = $('#title');
		const filename = $('#filename');
		const content = $('#content');
		
		/* 작성하기 */
		const insert_btn = $('#insert_btn');
		insert_btn.click(function(){
			if(title.val() == '') {
				alert('제목은 필수 입니다.');
				title.focus();
				return;
			}
			f.attr('action', '/10_MODEL2/insertBoard.b');
			f.submit();
		})
		
		/* 목록보기 */
		const list_btn = $('#list_btn');
		list_btn.click(function(){
			location.href='/10_MODEL2/selectListBoardPage.b';
		})
		
	})
</script>



<!-- 본문 내용 -->
<div class="insert_form">
	<form method="post" enctype="multipart/form-data" id="f">
		<%-- 작성자 : author --%>
		<p class="title">작성자</p>
		<input type="text" name="author" value="${loginDTO.id}" readonly>  <br>
		<%-- 제목 : titleB --%>
		<p class="title">제목</p>
		<input type="text" name="title" id="title" autofocus> <br>
		<%-- 첨부 : filename --%>
		<p class="title">첨부</p>
		<input type="file" id="filename" name="filename">
		<%-- 내용 : content --%>
		<p class="title">내용</p>
		<textarea name="content" id="content"></textarea> <br>
		
		<input type="hidden" name="ip" value="<%= request.getRemoteAddr()%>">
		
		<%-- 버튼 : button --%>
		<input type="button" value="작성하기" id="insert_btn">
		<input type="reset" value="작성초기화" id="reset_btn">
		<input type="button" value="목록보기" id="list_btn">
	</form>
</div>

<!-- footer.jsp (하단에 추가) -->
<%@ include file="../layout/footer.jsp" %>