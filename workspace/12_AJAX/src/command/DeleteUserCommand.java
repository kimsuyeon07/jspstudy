package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class DeleteUserCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// DTO에 저장할 필요 없다.
		
		int result = MemberDAO.getInstance().deleteUser(no);
		if (result > 0) {
			request.getSession().invalidate();
		}
		
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		
		return null;
	}

}
