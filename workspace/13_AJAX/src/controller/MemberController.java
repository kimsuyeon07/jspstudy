package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.DeleteMemberCommand;
import command.InsertMemberCommand;
import command.MemberCommand;
import command.SelectMemberByNoCommand;
import command.SelectMemberListCommand;
import command.UpdateMemberCommand;
import controller.ModelAndView;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberController() {
        super();
    }

	/* 해당 부분에서 작성 시작_ */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// cmd 알아내기
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1];
		
		MemberCommand command = null;
		switch(cmd) {
		case "selectMemberList.do":
			command = new SelectMemberListCommand();
			break;
		case "selectMemberByNo.do":
			command = new SelectMemberByNoCommand();
			break;
		case "updateMember.do":
			command = new UpdateMemberCommand();
			break;
		case "insertMember.do":
			command = new InsertMemberCommand();
			break;
		case "deleteMember.do":
			command = new DeleteMemberCommand();
			break;
		}
		
		
		
		ModelAndView mav = null;
		if (command != null) {
			try {
				mav = command.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// ajax로 처리되는 command는 아래 코드가 동작하지 않는다.
		if (mav != null) {
			if (mav.isReidrect()) {
				response.sendRedirect(mav.getView());
			} else {
				request.getRequestDispatcher(mav.getView()).forward(request, response);
			}
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
