package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.MemberCommand;
import command.member.LoginPageCommand;
import common.ModelAndView;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.m")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기본 처리 (요청과 응답)
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		// ruquest.getRequestURI() == "/10_MODEL2/loginPage.m"
		// request.getRequestURI()/split("/") == {"", "10_MODEL2", "loginPage.m"}
		// split() 결과 배열의 마지막 요소
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length -1]; // arr의 마지막 인덱스 번호 => cmd : loginPage.m
		
		// 요청을 전달하면 그 요청을 처리할 Model(Command)을 반환하는  CommandMapper
		MemberCommand command = MemberCommandMapper.getInstance().getCommand(cmd);
		ModelAndView mav = null;
		if (command != null) { // 전달된 command가 있다면,
			mav = command.execute(request, response); 
			// 실행을 하는 건 ModelAndView에서 진행한다!
		}
		/* Command의 반환이 null인 경우 */
		if (mav == null) {
			// command가 진행을 다 했고 mav가 할 일이 없으면,
			return;  
			/* doGet() 메소드 종료  ▼
			   (Controller의 종료 : Command의 null 반환은 
			   Command가 다 처리하고 이동할 페이지가 없음을 의미) */
		}
		/* 페이지 이동 */
		if (mav.isRedirect()) {
			response.sendRedirect(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
		/*
		 * MemberCommand command : CommandMapper의 부모타입(인터페이스 파일)을 확인
		 * CommandMapper.getInstance() : 까지 기본 경로 : singleton패턴이기 때문에
		 * */
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
