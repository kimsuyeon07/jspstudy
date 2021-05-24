<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 1. 파라미터 처리
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	
	/* id >> dao로 전달되고 Member테이블에서 동일한 id가 있는지 체크 */
	

	// 2. MemberDAO 의 isUsableId() 호출
	boolean result = MemberDAO.getInstance().isUsableId(id);

	// 3. result
	// 		가입 가능한 ID이면 true 반환, 이미 가입된 ID이면 false 반환
	if (result) {
		out.println("<script>");
		out.println("alert('사용 가능한 아이디입니다.')");
		out.println("location.href = 'joinPage.jsp?id="+ id +"&chk=true' "); // 사용 가능한 ID와 chk=ture를 joinPage.jsp에 전달한다.
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('이미 사용 중인 아이디입니다.')");
		out.println("location.href = 'joinPage.jsp'");
		out.println("</script>");
	}
		



%>