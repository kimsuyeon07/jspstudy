package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertReplyCommand3 implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*******대댓글 달기*******/ 
		
		/* 파라미터 처리 */ 
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		long groupno = Long.parseLong(request.getParameter("groupno"));
		long no = Long.parseLong(request.getParameter("no"));
		
		
		/* DB로 보낼 DTO */ 
		BoardDTO replyDTO = new BoardDTO();
		replyDTO.setAuthor(author);
		replyDTO.setTitle(title);
		replyDTO.setContent(content);
		replyDTO.setIp(ip);
		// 대댓글 작업 경우, 추가 작업
		// 원글 정보 가져오기 (DAO 작업)
		BoardDTO boardDTO = BoardDAO.getInstance().selectBoard(no);
		
		// 가져온 원글(부모) 정보를 이용해서 replyDTO생성
		replyDTO.setGroupno(boardDTO.getGroupno());
		replyDTO.setGroupord(boardDTO.getGroupord() + 1);
		replyDTO.setDepth(boardDTO.getDepth() + 1);
		
		// 같은 그룹의 기존 댓글들 중에서
		// groupord가 가져온 원글(부모)의 groupord보다 큰 댓글들의
		// groupord를 1씩 증가
		BoardDAO.getInstance().increseGroupordPerviousReply(boardDTO);
		
		
		/* 댓글의 경우, 게시글의 번호를 필요로 한다. (GROUPNO: 같은 그룹으로 만들기 위해서) */ 
		/* 댓글 삽입하기 */
		int result = BoardDAO.getInstance().insertReply(replyDTO);
		
		
		/* 결과를 처리할 insertResult.jsp(에, 게시글의 번호를 파라미터로 넘겨주면서) 를 만들고 이동한다. */ 
		return new ModelAndView("/11_MYBATIS/board/insertReplyResult3.jsp?result="+result, true);  // 반드시, redirect
		// 삽입 후에는 반드시, redirect한다.
		// 결과가 성공인지 실패인지 값을 파라미터로 전달해 주겠다.
	}

}
