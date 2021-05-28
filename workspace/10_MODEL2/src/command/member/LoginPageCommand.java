package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class LoginPageCommand implements MemberCommand {

	// index.jsp에서 로그인페이지로 흐름을 넘겨주는 Command
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 로그인을 할 수 있는 login.jsp로 이동
		/* DB를 갔다가 오지 않는다. [ 단순페이지 이동 ] */
		
		/* 이동할 경로를 저장하고 이동방식을 저장하는 클래스: ModelAndView를 반환해서 진행한다. */
		// String view :이동할 .jsp파일명   ,  boolean isRedirect : (redirct: true,  forward: false)
		// ▼ ▼ ▼
		// return new ModelAndView(view, isRedirect);
		return new ModelAndView("/10_MODEL2/member/login.jsp", true);
		
	}

}
