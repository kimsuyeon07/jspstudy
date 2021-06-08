package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.InsertStudentCommand;
import command.SelectStudentListCommand;
import command.StudentCommand;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("*.do")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StudentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// cmd
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length -1];
		
		// StudentCommand
		StudentCommand command = null;
		switch (cmd) {
		case "selectStudentList.do":
			command = new SelectStudentListCommand();
			break;
		case "insertStudent.do":
			command = new InsertStudentCommand();
			break;
		}
		
		// ModelAndView
		ModelAndView mav = null;
		
		if(command != null) {
			try {
				mav = command.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// mav.isReidrect() : true이면 redirect로 이동할 것
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
