package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	// index.jsp에서 로그인페이지로 흐름을 넘겨주는 Command
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// login.jsp에서 전송한 파라미터 처리
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// DB로 보낼 DTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		// DAO의 login() 호출
		MemberDTO loginDTO = MemberDAO.getInstance().login(dto);
		// 성공 유무 확인
		// 성공 : session에 loginDTO 저장, MEMBER_LOG 테이블에 기록
		if (loginDTO != null) {
			// session : JAVA파일은 session을 request에서 구해서 쓴다.
			HttpSession session = request.getSession();
			session.setAttribute("loginDTO", loginDTO);
			// DAO의 loginLog() 호출
			MemberDAO.getInstance().loginLog(dto);
		}
		// 어디로, 어떻게
		ModelAndView mav = new ModelAndView("/10_MODEL2/index.do", true);  // true  ::>>  redirect
		
		/*  ▼ ▼  리턴  ▼ ▼  */
		return mav;
	}

}
