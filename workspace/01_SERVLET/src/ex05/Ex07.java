package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex07
 */
@WebServlet("/Ex07")
public class Ex07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex07() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 폼의 요소는 무조건 전달된다. 즉, null이 아니다.
		System.out.println(request.getParameter("name") == null);
		System.out.println(request.getParameter("name").isEmpty()); // isEmpty() : 빈 문자열 체크 : 공백 체크
		
		// 1. 요청 처리
		// 1) 인코딩
		request.setCharacterEncoding("utf-8");
		// 2) 파라미터 저장
		String name = request.getParameter("name");
		// ↓ <form>태그로 사용할 경우는 따로 작업할 필요는 없음. : 빈 문자열을 경우는 공백으로 전달되기 때문에 : 에러가 나지는 않음
		if (name.isEmpty()) {
			name = "기본이름";
		}
		String strAge = request.getParameter("age");
		int age = 0;
		if (!strAge.isEmpty()) {
			age = Integer.parseInt(strAge);
		}
		
		// 2. 응답 처리
		// 1) content-type
		response.setContentType("text/html; charset=utf-8");
		// 2) 출력 스트림 생성
		PrintWriter out = response.getWriter();
		// 3) 응답 생성
		out.println("<script>");
		out.println("alert('이름: " + name +  ", 나이: "  + age + "살');");
		out.println("history.back();");
		out.println("</script>");
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
