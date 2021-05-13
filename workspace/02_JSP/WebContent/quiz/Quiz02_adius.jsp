<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
 	request.setCharacterEncoding("UTF-8");
 	int age = Integer.parseInt(request.getParameter("age"));
 %>
 
 <script>
 	alert('성인이군요! 당신의 나이는 <%=age%>살이므로 술을 살 수 있어요!');
 	history.back(); // == history.go(-1);
 	// history : 주소창을 보고 이동하기 때문에 [forward를 사용하면 URL의 변화가 없음] 작동하지 않음.
 	/* 
 		< 실질적으로 2번의 경로 이동이 있었다. >
 		forward는 이동 경로(URL)가 변하지 않는 이동이기 때문에 "history"에 잡히지 않는다.
 		따라서, history.go(-2)가 아닌  
 		>> history.go(-1) 내지는 history.back() 을 사용해야 한다.
 	*/
 </script>
 