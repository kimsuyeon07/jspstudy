package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.ShapeCommand;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("*.do")  // .do로 끝나는 모든 jsp파일을 처리한다,
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

		// 1. 기본 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 요청 확인 :  /07_MVC/circle.do  =>> "circle.do"만 사용하고자
		String[] arr = request.getRequestURI().split("/");  // "/"로 문자열을 배열로 나누겠다. <**ㄴ배열 선언 필수**>
		String cmd = arr[arr.length - 1];  // arr의 마지막 요소구하기 : (arr.length - 1)
		
		// 3. 요청에 따른  Model의 선택
		// 1) 클래스명 : ModelMapper
		// 2) 매개변수 : String cmd (어떤요청인지 전달)
		// 3) 반환타입 : Model의 인터페이스 (Model을 반환)
		/* ▼ ▼ ▼ */
		// Model의 기본
		// 1. 인터페이스를 하나 생성한다.
		// 2. 해당 인터페이스를 구현한다.
		//    -> 모든 Model의 타입은 인터페이스이다.
		ShapeCommand command = ModelMapper.getInstance().getModel(cmd);
		
		
		// 4. Model 실행
		// String path = command.execute(request, response);  :: 응답 View만 반환하는 경우
		ModelAndView mav = null;
		mav = command.execute(request, response);
		if (mav == null) {
			return;  // 이동을 하지 않는다. 화면이 바뀌지 않는다. 여기지점에서 멈춘다. 
		}
		
		// 5. 응답 View 이동 : forward : request에 데이터를 저장했기 때문에 전달을 위해서
		// request.getRequestDispatcher(path).forward(request, response);
		if (mav.isRedirect()) {
			// redirect라면,
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
