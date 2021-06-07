package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class SelectMemberByNoCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// DTO생성
		Member member = MemberDAO.getInstance().selectMemberByNo(no);
		
		// JSON으로 만들어 반환시켜준다.
		JSONObject obj = new JSONObject();
		if(member != null) {
			obj.put("no", member.getNo());
			obj.put("id", member.getId());
			obj.put("name", member.getName());
			obj.put("gender", member.getGender());
			obj.put("address", member.getAddress());
			// 결과확인용으로 ("isExist")
			obj.put("isExist", true);
			
		} else {
			obj.put("isExist", false);
		}
		
		// 응답
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		return null;
	}

}
