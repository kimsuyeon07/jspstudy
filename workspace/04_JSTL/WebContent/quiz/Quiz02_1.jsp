<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		input[type="text"] {
			width: 80px;
			box-sizing: border-box;
			outline: none;
			margin-bottom: 5px;
		}
	</style>
</head>
<body>

	<form action="Quiz02_2.jsp">
		<h3>일반 forEach 연습</h3>
		<p>아래에 크기를 입력하면 폰트 크기를 확인 할 수 있습니다.</p>
		최소 크기
		<input type="number" min="1" max="7" name="minN">
		<br>
		최대 크기
		<input  type="number" min="1" max="7" name="maxN">
		
		<br><br><br>
		
		<h3>향상 forEach 연습</h3>
		<label><input type="checkbox" name="foods" value="불고기">불고기 </label>&nbsp;
		<label><input type="checkbox" name="foods" value="닭갈비">닭갈비 </label> &nbsp;
		<label><input type="checkbox" name="foods" value="순대국">순대국 </label> &nbsp;
		<label><input type="checkbox" name="foods" value="자장면">자장면 </label> &nbsp;
		<label><input type="checkbox" name="foods" value="해장국">해장국 </label>
		
		<br><br>
		
		<button>전송</button>
		<input type="reset" value="취소">
	</form>
	

</body>
</html>