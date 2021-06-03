package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.BoardCommand;
import command.DeleteCommand;
import command.FindListCommand;
import command.InsertCommand;
import command.InsertPageCommand;
import command.InsertReplyCommand;
import command.InsertReplyCommand3;
import command.InsertReplyPageCommand;
import command.SelectListCommand1;
import command.SelectListCommand2;
import command.SelectListCommand3;
import common.ModelAndView;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// cmd 뽑아내기 ()
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String cmd = requestURI.substring(contextPath.length() + 1);
		
		// cmd에 따라 해당 클래스 실행
		ModelAndView mav = null;
		BoardCommand command = null;
		switch (cmd) {
		case "selectList1.do":
			command = new SelectListCommand1();
			break;
		case "selectList2.do":
			command = new SelectListCommand2();
			break;
		case "selectList3.do":
			command = new SelectListCommand3();
			break;
		case "insertPage.do":
			command = new InsertPageCommand();
			break;
		case "insertReplyPage.do":
			command = new InsertReplyPageCommand();
			break;
		case "insert.do":
			command = new InsertCommand();
			break;
		case "insertRelpy.do":
			command = new InsertReplyCommand();
			break;
		case "insertRelpy3.do":
			command = new InsertReplyCommand3();
			break;
		case "findList.do":
			command = new FindListCommand();
			break;
		case "delete.do":
			command = new DeleteCommand();
			break;
		
		}
		
		// ModelAndView클래스에 command(BoardCommand_인터페이스:부모클래스)를 전달하고,
		if(command != null) {
			try {
				mav = command.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
				
		}
		 
		if(mav != null) {
			if(mav.isRedirect()) {
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
