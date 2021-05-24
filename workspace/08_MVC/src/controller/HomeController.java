package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;
import model.AverageCommand;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("*.do")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 매핑값 알아내기 : 변수 cmd
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1]; // 마지막 인덱스번호
		
		// 3. cmd를 ModelMapper에 넘겨준다
		AverageCommand command = ModelMapper.getInstance().getModel(cmd);
		
		// ModelAndView : 
		ModelAndView mav = null;
		mav = command.execute(request, response);
		if (mav == null) {
			return;
		}
		
		// 응답 view 이동
		if (mav.isRedirect()) { // true이면,
			request.getRequestDispatcher(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
