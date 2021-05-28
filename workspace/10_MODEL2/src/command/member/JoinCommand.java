package command.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class JoinCommand implements MemberCommand {

	// index.jsp에서 로그인페이지로 흐름을 넘겨주는 Command
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// join.jsp에서 보낸 id, pw, name, email
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// DB로 보낼 DTO
		MemberDTO dto = new MemberDTO(id, pw, name, email);
		// dto.setId(id); >> MemberDTO의 생성자로 삽입 완료.
		// dto.setPw(pw);
		// dto.setName(name);
		// dto.setEmail(email);
		
		// DAO의 join() 호출
		int result = MemberDAO.getInstance().join(dto);
		
		try { 
			// 응답을 만들 PrintWriter
			PrintWriter out = response.getWriter();
			if (result > 0) {  // 가입 성공 시, ( result = 1 ) 반환
				out.println("<script>"); // Servlet 코드
				out.println("alert('가입되었습니다.')");
				out.println("location.href='/10_MODEL2/loginPage.m'"); // 로그인페이지로 넘기겠다.
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원가입이 실패했습니다.')");
				out.println("location.href='/10_MODEL2/JoinPage.m'"); // 로그인페이지로 넘기겠다.
				out.println("</script>");				
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		/*  ▼ ▼  리턴  ▼ ▼  */
		return null;  // ModelAndView가 생성될 필요가 없기 때문에. [ Command가 다 처리했기 때문에 ]
	}

}
