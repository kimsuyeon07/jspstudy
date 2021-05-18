package login.captcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;

/**
 * http://
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Servlet은 doGet() 메서드로 실행된다.
	 * __로그인페이지에 캡차이미지를 띄우기 위해서 진행되는 과정
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 네이버에 캡차 키 요청하기
		 * 2. 네이버에 캡차 이미지 요청하기  -------------------
		 * 3. 로그인 페이지(login.jsp)로  이동하기
		 * */
		
		// 1. 네이버에 캡차 키 요청하기
		String key = CaptchaKey.getCaptchaKey();
		CaptchaImage.getCaptchaImage(request, key);
		// response.getWriter().write(key);  // 화면으로 확인하기
		
		/*---------------------------------------------------------------------*/
		// Login.java -> LoginValidation.java [ key전달하기 ]
		// -
		// 1. request 이용
		// Login.java -> login.jsp -> LoginValidation.java
		// reqeust.setAttribute("key", key);
		// 				 <input type="hidden" name="key" value="<%=request.getAttribute("key") %>">
		// 							  reqeust.getParameter("key");
		// - 
		// 2. session 이용 : 브라우저를 닫을 때까지 데이터를 저장해 준다.
		// Login.java -> login.jsp -> LoginValidation.java
		// session.setAttribute("key", key);
		// 				 session.getAtrribute("key");
		//							  session.getAtrribute("key");
		// ↓↓↓
		// session을 구하는 방법
		// 1. request에서 구한다.
		// 2. HttpSession session = request.getSession();
		HttpSession session = request.getSession();
		session.setAttribute("key", key); // LoginValidation.java에서 필요함.
		
		
		
		// 2. 네이버에 캡차 이미지 요청하기  -------------------
		// CaptchaImage.java에게 request를 전달하고,
		// CaptchaImage.java가 그 request에 데이터를 저장했다.
		// 따라서, request에 저장된 데이터를 꺼내서 사용할 수 있다.
	/*
		 * String dir = (String)request.getAttribute("DIR"); String filename =
		 * (String)request.getAttribute("filename"); response.getWriter().write(dir +
		 * "/" + filename);
	 */
		
		// 3. 로그인 페이지(login.jsp)로  이동하기
		// "캡차 이미지가 저장된 디렉터리 + 캡차 이미지 파일명"을 가지고 [ login.jsp ]로 이동한다
		// 즉, request의 정보를 유지한 상태로 이동한다. >>> forward
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
