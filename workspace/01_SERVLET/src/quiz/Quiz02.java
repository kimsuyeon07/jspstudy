package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz01
 */
@WebServlet("/Quiz02")
public class Quiz02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		// 파라미터 저장
		String cmd = request.getParameter("cmd");
		
		Calendar calendar = Calendar.getInstance();
		String message = "";
		
		switch (cmd) {
		case "date" :
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			int date = calendar.get(Calendar.DATE);
			message = year + "년 " + month + "월 " + date + "일"; 
			break;
		case "time" :
			int hour = calendar.get(Calendar.HOUR);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			message = hour + "시 " + minute + "분 " + second + "초";
		}
		
		// Content-type 
		response.setContentType("text/html; charset=utf-8");
		// 출력스트림 생성
		PrintWriter out = response.getWriter();
		// 응답 처리
		out.println("<script>");
		out.println(" alert(' " + message + " ') ");
		// out.println("history.back()"); ↓ 같은 내용
		out.println("history.go(-1)");
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
