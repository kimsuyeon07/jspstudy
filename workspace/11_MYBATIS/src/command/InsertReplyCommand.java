package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertReplyCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*******1단 댓글 달기*******/ 
		
		/* 파라미터 처리 */ 
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		long groupno = Long.parseLong(request.getParameter("groupno"));
		
		
		/* DB로 보낼 DTO */ 
		BoardDTO replyDTO = new BoardDTO();
		replyDTO.setAuthor(author);
		replyDTO.setTitle(title);
		replyDTO.setContent(content);
		replyDTO.setIp(ip);
		// 댓글 작업 경우, 추가 작업
		replyDTO.setGroupno(groupno); // 댓글은 원글과 같은 그룹이 된다.
		replyDTO.setDepth(1);  	
							// 1 : 댓글의 depth는 1이다. (1단 댓글의 경우, 그냥 "1"로 선언해줘도 된다.)
						  	// 댓글의 depth는 원글의 depth + 1 로 처리한다.
		replyDTO.setGroupord(1);
							// 그룹 내부 순서
							// 초기화로 가지는 값 : "1"
							// 기존 댓글들의 Groupord를 모두 1씩 증가시킨다.
		
		
		/* 기존 댓글들의 Groupord를 모두 1씩 증가시킨다. */
		BoardDAO.getInstance().increseGroupordPerviousReply(groupno);  // Groupno을 전달
		
		/* 댓글의 경우, 게시글의 번호를 필요로 한다. (GROUPNO: 같은 그룹으로 만들기 위해서) */ 
		/* 댓글 삽입하기 */
		int result = BoardDAO.getInstance().insertReply(replyDTO);
		
		
		/* 결과를 처리할 insertResult.jsp(에, 게시글의 번호를 파라미터로 넘겨주면서) 를 만들고 이동한다. */ 
		return new ModelAndView("/11_MYBATIS/board/insertReplyResult.jsp?result="+result, true);  // 반드시, redirect
		// 삽입 후에는 반드시, redirect한다.
		// 결과가 성공인지 실패인지 값을 파라미터로 전달해 주겠다.
	}

}
