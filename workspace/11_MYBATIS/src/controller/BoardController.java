package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.BoardCommand;
import command.InsertCommand;
import command.InsertPageCommand;
import command.SelectListCommand;
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
		
		ModelAndView mav = null;
		BoardCommand command = null;
		switch (cmd) {
		case "selectList.do":
			command = new SelectListCommand();
			break;
		case "insertPage.do":
			command = new InsertPageCommand();
			break;
		case "insert.do":
			command = new InsertCommand();
			break;
		}
		
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
