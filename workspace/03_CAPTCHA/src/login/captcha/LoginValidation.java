package login.captcha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 성공/실패 메시지는 경고창으로 작성
		// 응답 처리를 위해서 response의 처리
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// 사용자 입력값 검증
		// 검증 결과에 따라서 이동할 페이지를 다르게 지정
		boolean result = CaptchaValidation.getValidate(request);
		if (result) { 
			// 성공하면 이동 >> redirect (정보를 넘겨주지 않고 그냥 페이지 이동) or PrintWriter out 사용
			// 메시지보여준 후, 이동 방법
			out.println("<script>");
			out.println("alert('검증에 성공했습니다.')");
			out.println("location.href='/03_CAPTCHA/index.jsp'");
			out.println("</script>");
		} else { // 실패했을 때_메세지없이 이동하는 방법
			response.sendRedirect("/03_CAPTCHA/Login");
		}
		
		out.flush(); // 내용 비우기
		out.close(); // out 닫기
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
