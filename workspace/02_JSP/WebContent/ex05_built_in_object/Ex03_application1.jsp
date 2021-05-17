<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		
	
		onload = function(){
			const f = document.forms.insert_form; // <form>의 이름(name)속성으로 대상을 지정할 수 있다.
			// [application2.jsp에서 다시 넘어올 때, 작성되었던 데이터값을 지우는 작업]
			if ('<%=request.getParameter("state")%>' == 'visited') {
				f.writer.value= '';
				f.title.value='';
				f.contents.value='';
			}
			f.addEventListener('submit', function(event){
				if (f.writer.value == '') {  // 작성자와 제목은 "필수"로 입력해라__
					alert('작성자를 입력하세요.');
					event.preventDefault();
					f.writer.focus(); 
					return false;
				} else if (f.title.value == '') {
					alert('제목을 입력하세요.');
					event.preventDefault();
					f.title.focus();
					return false;
				}
			})
		}
	</script>
</head>
<body>


	<h3>게시글 작성하기</h3>
				<!-- application.getContextPath() == request.getContentPath() -->
	<form action="<%=application.getContextPath()%>/ex05/Ex03_application2.jsp"
		  name="insert_form"> 
		  
		<input type="text" name="date" value="<%=new Date(System.currentTimeMillis())%>" placeholder="작성일자"> <br>
							<%-- <%=new Date(System.currentTimeMillis())%> : 오늘 날짜 타임스탬프 값으로 생성 --%>
		<input type="text" name="writer" placeholder="작성자" > <br>
		<input type="text" name="title" placeholder="제목"> <br>
		<textarea name="contents" rows="5" cols="23"></textarea> <br><br>
		<button>작성하기</button>
		<input type="reset" value="작성취소">
		
	</form>
	
	
</body>
</html>