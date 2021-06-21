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
		
		Optional<String> opt = Optional.ofNullable("title");
		String title = opt.orElse("환영합니다.");
		pageContext.setAttribute("title", title);
	%>
	<title>${title}</title>
	<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI=" crossorigin="anonymous"></script>
</head>
<body>
	
	<div class="container">
		<header>
			<a href="/2_10_MODEL2/index.do">
				<img src="<%=request.getContextPath() %>assests/images/naver.png" alt="Logo" style="width:200px;" />
			</a>
		</header>
		
		<section>