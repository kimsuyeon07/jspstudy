package command.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 첨부파일이 저장될 디렉터리 (생성 여부에 따라 생성까지 진행)
		
		final String DIRECTORY = "archive";
		String realPath = request.getServletContext().getRealPath(DIRECTORY);
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		MultipartRequest multipartRequest = null;
		ModelAndView mav = null;
		try {
			// 업로드가 진행
			multipartRequest = new MultipartRequest(request, 
													realPath, 
													1024 * 1024 * 10, 
													"utf-8",
													new DefaultFileRenamePolicy());
			// 파라미터 처리 : MultiparRequest가 담당
			String author = multipartRequest.getParameter("author");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("content");
			// String ip = request.getRemoteAddr();  
			// multipartRequest로는 ip주로를 알아낼 수 없다. ,  첨부가 없을 때는 이렇게 사용하면 된다.
			String ip = multipartRequest.getParameter("ip");  // multipartRequest로는 ip주로를 알아낼 수 없다.
			String filename = null;  // 첨부파일은 있을 수도 있고 없을 수 도 있기 때문에 우선 "null"처리
					 // 첨부가  없을 경우.
			if(multipartRequest.getFile("filename") == null ) { 
				filename = null;
			} else { // 첨부가 있을 경우. 
				filename = multipartRequest.getFilesystemName("filename"); 
								// getFilesystemName("filename") : 저장될 때 이름
			}
			
			// DB로 보낼 dto
			BoardDTO dto = new BoardDTO();
			dto.setAuthor(author);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setFilename(filename);
			dto.setIp(ip);
			
			// DAO의 insertBoard()메소드 호출
			int result = BoardDAO.getInstace().insertBoard(dto);
			if(result == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글이 저장되지 않았습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				mav = new ModelAndView("/10_MODEL2/selectListBoardPage.b", true);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}

}
