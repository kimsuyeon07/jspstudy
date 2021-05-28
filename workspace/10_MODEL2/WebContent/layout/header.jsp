<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%
		request.setCharacterEncoding("UTF-8");
		// Optional에 저장된 데이터가 없다면[ Optional.ofNullable( .. ) ] (..)부분으로 저장객체를 만들어 주겠다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
		// Optional의 객체 opt에 저장 값이 없다면 [ opt.orElse(" 내용 작성 ")] : 이 내용으로 보여주겠다.ㄴ
		String title = opt.orElse("환영합니다.");
		pageContext.setAttribute("title", title);
	%>
	<title>${title}</title>
	<link rel="stylesheet" href="assets/css/layout.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<header>
			<a href="/10_MODEL2/index.do">
				<img src="<%=request.getContextPath() %>/assets/images/daum.png" alt="Logo" style="width:200px;">
			</a>
			<nav>
				<ul>
					<%-- 로그인하지 않았을 때, 보여지는 NAV --%>
					<c:if test="${loginDTO == null}">
						<li><a href="/10_MODEL2/JoinPage.m">회원가입</a></li>
						<li><a href="/10_MODEL2/loginPage.m">로그인</a></li>
						<li><a href="#">마이페이지</a></li>
					</c:if>
					<%-- 로그인되어 있을 때, 보여지는 NAV --%>
					<c:if test="${loginDTO != null}">
						<li><a href="/10_MODEL2/logoutPage.m">로그아웃</a></li>
						<li><a href="#">마이페이지</a></li>
					</c:if>
				</ul>
			</nav>
		</header>
		
		<hr>
		
		<section>