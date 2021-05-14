<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String chk = request.getParameter("chk");  // 체크를 하지 않으면 null이다.
	
	// 1. 체크를 했다.
	if (chk != null) {
		// 아이디를 쿠키에 보관해 둔다. 
		Cookie cookie = new Cookie("id", id);
		cookie.setMaxAge(60*60*24*15);
		response.addCookie(cookie);
	}
	
	// 2. 체크를 해제했다.
	else {
		// 쿠키에 id가 저장되어있는지 확인 후, id값을 삭제한다.
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				// id값이 있으면 실행
				if (cookie.getName().equals("id")) {
					Cookie ck = new Cookie("id", null);
					ck.setMaxAge(0);
					response.addCookie(ck);
					break;
				}
			}
		}
	}
	
	// login.jsp로 되돌아 가기 : redirect : 현재의 파라미터 값을 가지고 가지 않는다!
	response.sendRedirect("/02_JSP/ex08_cookie/Ex06_login.jsp");
	
	
	
	
%>