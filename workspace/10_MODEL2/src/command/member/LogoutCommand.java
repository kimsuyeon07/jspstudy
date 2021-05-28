package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LogoutCommand implements MemberCommand {

	// index.jsp에서 로그인페이지로 흐름을 넘겨주는 Command
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// session의 loginDTO의 로그아웃 기록을 위해 id 추출
		
		/* 
		 * session : JAVA파일은 session을 request에서 구해서 쓴다.
		 * request의 sesstion을 빼는 방법
		*/
		HttpSession session = request.getSession();
		
		String id = ((MemberDTO)session.getAttribute("loginDTO")).getId();
		
		// DAO의 logoutLog() 호출
		MemberDAO.getInstance().logoutLog(id);
		
		// session의 초기화 : 로그인에 필요한 id, pw를 삭제하겠다.
		session.invalidate();
	
		/*  ▼ ▼  리턴  ▼ ▼  */
		return new ModelAndView("/10_MODEL2/index.do", true);
	}

}
