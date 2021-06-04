package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.MemberCommand;
import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class UpdateUserCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리 : no, pw
		long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		// Member(DTO)에 저장
		Member member = new Member();
		member.setNo(no);
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		int result = MemberDAO.getInstance().updateUser(member);
		/*
		 * if (result > 0) { HttpSession session = request.getSession(); Member
		 * loginUser = (Member)session.getAttribute("loginUser"); loginUser.setPw(pw); }
		 */
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		
		/*비밀번호를 수정하면, session에 담아뒀던 로그인 정보도 수정해줘야 한다.*/
		
		return null;
	}

}
