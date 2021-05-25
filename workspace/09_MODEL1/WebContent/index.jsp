<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Index</title>
	<style>
		* { margin: 0 auto; text-align:center; }
		h1 { margin: 50px 0; }
		a {
			text-decoration: none;
			color: black;
		}
		.container {
			width: 500px;
			margin: 0 auto;
			text-align: center;
			display: flex;
			justify-content: space-between;
		}
		.btn {
			width: 150px;
			height: 50px;
			font-size: 14px;
		}
		.btn > a {
			display: block;
			width: 100%;
			height: 100%;
			line-height: 50px;
		}
	</style>
</head>
<body>

	<h1>회원 관리</h1>
	<div class="container">
		<div class="btn">
			<c:if test="${loginDTO eq null}">
				<a href="/09_MODEL1/member/joinPage.jsp">회원가입</a>
			</c:if>
			<c:if test="${loginDTO ne null}">
				<a href="/09_MODEL1/board/boardList.jsp">게시판</a>
			</c:if>
		</div>
		
		<div class="btn">
			<c:if test="${loginDTO eq null}">
				<a href="/09_MODEL1/member/loginPage.jsp">로그인</a>
			</c:if>
			<c:if test="${loginDTO ne null }" >
				<!--loginDTO.name : loginDTO.getName() 으로 알아서 빼온다  -->
				<b>${loginDTO.name}님</b> 반갑습니다   :)		<br>
				<a href="/09_MODEL1/member/logout.jsp">로그아웃</a>		<br>
				<a href="/09_MODEL1/member/myPage.jsp">개인정보보기</a>
			</c:if>		
			<c:if test="${loginDTO.id eq 'admin'}">
				<a href="/09_MODEL1/member/memberList.jsp">회원목록보기</a>
			</c:if>		
		</div>
	</div>

</body>
</html>