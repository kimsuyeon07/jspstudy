<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>insertPage</title>
	<style>
		.container {
			width: 500px;
			margin: 100px auto;
		}
		label {
			margin-top: 10px;
			display: inline-block;
			width: 100px;
		}
		input[type="text"], textarea {
			width: 100%;
			border: 1px solid darkgray;
			outline: none;
			box-sizing: border-box;
		}
		input[type="text"] {
			margin-top: 5px;
			height: 30px;
		}
		textarea {
			height: 150px;
			margin-bottom: 10px;
			padding: 10px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"></script>
	<script>
		$(function(){
			const f = $('#f');
			const title = $('#title');
			const author = $('#author');
			f.submit(function(evnet){
				if(author.val() == '') {
					alert('비정상적인 접근입니다.');
					history.back();
					return false;
				} else if (title.val() == '') {
					alert('제목은 필수입니다.');
					title.focus();
					event.preventDefault();
				}
			})
		})
	</script>
</head>
<body>

	<div class="container">
		<h3>게시글 작성하기</h3>
		<form action="insertBoard.jsp" method="post" id="f">
			<label>작성자</label><input type="text" name="author" id="author" value="${loginDTO.id}" readonly>  <br> <!--readonly:수정이 안되는   -->
			<label>제목</label><input type="text" name="title" id="title" autofocus>	<br><br> 
			<textarea rows="" cols="" name="content" placeholder="내용을 입력하세요 :)"></textarea>	 <br>
			<button>작성하기</button>
			<input type="reset" value="작성초기화">
			<input type="button" value="게시판으로 이동" onclick="location.href='boardList.jsp'">
			
		</form>
	</div>

</body>
</html>