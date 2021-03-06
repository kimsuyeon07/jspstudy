package quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quiz03
 */
@WebServlet("/Quiz04")
public class Quiz04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Quiz04() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// ** 파라미터 저장
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");

		// 접속자 ip 주소
		// 1. 기본은 request.getRemoteAddr()로 알 수 있다.
		// String ipAddress = request.getRemoteAddr();
		// 2. 거쳐서 온 IP는 X-Forwarded-For 요청 헤더(request header)에 있다.
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) { // 거치지 않고 직접 왔다면
			ip = request.getRemoteAddr();
		}
		
		// 작성일자
		String date = new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date());
		
		// 생성할 파일명 : 확장자까지 입력
		String filename = date.substring(0, 10) + "_" + writer + ".txt";
		// 파일 경로
		File file = new File("D:\\spring0303\\jspstudy", filename );
		// 파일 생성 스트림 : 속도 향상을 위해서 BufferedWriter를 사용한다.
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		// 파일 생성
		bw.write("작성일자: " + date); bw.newLine(); // 줄바꿈 
		bw.write("작성IP: " + ip);bw.newLine(); // 줄바꿈 
		bw.write("작성자: " + writer);bw.newLine(); // 줄바꿈 
		bw.write("제목: " + title);bw.newLine(); // 줄바꿈 
		bw.write(contents);bw.newLine(); // 줄바꿈 
		 if(bw != null) {
			 bw.close();
		 }
		 // try-catch문을 사용하지 않는 이유? :: doGet()메소드가 알아서 던지고 있어서 "예외처리"를 하지 않아도 된다.
			 
		 
		// Content-type
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		// response.setContentType("text/html; charset=utf-8"); " ↑ 와 동일한 코드 "
		// 출력 스트림 생성
		PrintWriter out = response.getWriter();
		// 응답 처리
		out.println("<script>");
		out.println("alert('" + filename + "파일이 생성되었습니다. ')");
		out.println("history.back()"); 
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
