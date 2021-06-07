package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class InsertMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 파라미터 처리
		String strMember = request.getParameter("member");
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(strMember);
		
		// 데이터베이스로 보낼 Member로 만들어 주기 [DTO]
		Member member = new Member();
		member.setId(obj.get("id").toString());
		member.setName(obj.get("name").toString());
		member.setGender(obj.get("gender").toString());
		member.setAddress(obj.get("address").toString());
		
		// 중복체크 (하고 싶음 해보기) : "id"
		
		
		// DAO작업
		int result = MemberDAO.getInstance().insertMember(member);
		
		// JSONObject에 결과 값 담기
		JSONObject obj2 = new JSONObject();
		obj2.put("isSuccess", result > 0);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj2);
		out.close();
		
		return null;
	}

}
