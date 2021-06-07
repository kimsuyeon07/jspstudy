package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class LogoutCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그아웃 : session의 초기화  : 안에 담고 있는 로그인유저를 지운다.
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginUser") != null) {
			session.invalidate();  
		}
			
		return new ModelAndView("/12_AJAX/index.jsp", true);
	}
	
}
		
		
