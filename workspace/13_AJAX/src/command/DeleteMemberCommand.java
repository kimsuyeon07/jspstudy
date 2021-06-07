package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;

public class DeleteMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// DTO에 보내줄 필요 없음
		
		// DAO실행
		int result = MemberDAO.getInstance().deleteMember(no);
		
		// JSONObject에 결과값 전달
		JSONObject obj = new JSONObject();
		obj.put("isSuccess", result>0);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
				
		
		return null;
	}

}
