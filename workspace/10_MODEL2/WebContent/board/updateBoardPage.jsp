<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% request.setCharacterEncoding("UTF-8"); %>

<!-- header.jsp (상단에 추가) -->
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 수정" name="title"/>
</jsp:include>

<!-- CSS & script -->
<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.update_form {
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
		width: 220px;
	}
	
	/* 타이틀 작업 */
	.title {
		padding-bottom: 5px;
	}
	.file_span { font-size: 13px; }
	
	/* 버튼 CSS */
	.btn {
		padding: 5px 8px;
		border: 1px solid gray;
		outline: none;
		cursor: pointer;
	}
	.btn:hover {
		background-color: black;
		color: white;
	}
	
	
</style>

<script>
	$(document).ready(function(){
		const f = $('#f');
		
		const title = $('#title');
		const content = $('#content');
		const filename = $('#filename');
		
		/* 수정하기 */
		f.sumit(function(event){
			let file = filename.val();
			file = file.subString(file.lastIndexOf('\\') + 1);
			if(title.val() == '${param.title}' && 
			   content.val() == '${param.content}' &&
			   file == '${param.filename}' ) {
			   alert('수정할 내용이 없습니다.');
			   event.preventDefault();
			   return false;
			}
		})
		
		
		/* 목록가기 : <input>에서 onclick()으로 진행함 */
		
		
		
	})
</script>



<!-- 본문 내용 -->
<div class="update_form">
	<form action="updateBoard.b"  method="post" enctype="multipart/form-data" id="f">
		<%-- 작성자 : author --%>
		<p class="title">작성자</p>
		<input type="text"  value="${loginDTO.id}" readonly>  <br>
		<%-- 제목 : titleB --%>
		<p class="title">제목</p>
		<input type="text" name="title" id="title" value="${param.title}"> <br>
		<%-- 첨부 : filename --%>
		<p class="title">첨부</p>
		<input type="file" id="filename" name="filename">
		<!-- 첨부 여부 확인 : 텍스트 형식으로  -->
		<c:if test="${empty param.filename}">
			<span class="file_span">[ 기존 첨부파일 : 없음 ]</span> <br><br>
		</c:if>
		<c:if test="${not empty param.filename}">
			<span class="file_span">[ 기존 첨부파일 : ${param.filename} ]</span> <br><br>
		</c:if>
		<%-- 내용 : content --%>
		<p class="title">내용</p>
		<textarea name="content" id="content">${param.content}</textarea> <br>
		<input type="hidden" name="idx" value="${param.idx}">
		<input type="hidden" name="filename2" value="${param.filename}">
		
		<%-- 버튼 : button --%>
		<button class="btn">수정하기</button>
		<input type="reset" value="수정초기화" id="reset_btn" class="btn">
		<input type="button" value="목록보기" id="list_btn" class="btn" onclick="location.href='/10_MODEL2/selectListBoardPage.b'">
	</form>
</div>

<!-- footer.jsp (하단에 추가) -->
<%@ include file="../layout/footer.jsp" %>