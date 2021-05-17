<%@page import="ex01_file.BoardDTO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		final String DIR = "archive";
		String realPath = request.getServletContext().getRealPath(DIR);
		MultipartRequest multipart = new MultipartRequest(
				request,
				realPath,
				1024 * 1024 * 50,
				"UTF-8",
				new DefaultFileRenamePolicy() 
				);
		
		String title = multipart.getParameter("title");
		String writer = multipart.getParameter("writer");
		String filename = multipart.getFilesystemName("filename"); 
					   // multipart.getFilesystemName( " " ) : 서버에 저장된 파일명을 찾아준다.
					   
		// DB로 보내는 작업을 대비해서 빈으로 생성해 두기
		BoardDTO dto = new BoardDTO();
 		dto.setTitle(title);
 		dto.setWriter(writer);
 		dto.setFilename(filename);
					   
	%>
	
	<%-- 제목, 작성자, 첨부된 이미지 --%>
	
	
	<ul>
		<li>제목 : <%=dto.getTitle() %></li>
		<li>작성자 : <%=dto.getWriter() %></li>
		<% 
			switch ( dto.getFilename().substring(dto.getFilename().lastIndexOf(".")+1).toLowerCase() ) {
				case "jpg": case "jpeg": case "png": case "tif": case "bmp":
					out.println(" <li> 첨부된 이미지 : <img src= \"../" + DIR + "/" + dto.getFilename() + "\"> </li> ");
					                                          // ** 띄어쓰기 주의 **
				break; 
			 	default:  
			 		out.println("<li>첨부 파일명 : " + dto.getFilename() + "</li>");
			} %>
	</ul>
	
	
</body>
</html>