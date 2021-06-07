package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class UpdateMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파라미터 처리
		String strMember = request.getParameter("member");
		JSONParser parser = new JSONParser();
		// execute()메서드에서 "throws Exception" 해주기 때문에 [try문 필요 없음.]
		JSONObject obj = (JSONObject)parser.parse(strMember);
		
		// System.out.println(obj.toJSONString()); (확인용)
		
		/* DTO 생성 */
		Member member = new Member();
		member.setNo(Long.parseLong(obj.get("no").toString()));
		member.setId(obj.get("id").toString());
		member.setName(obj.get("name").toString());
		member.setGender(obj.get("gender").toString());
		member.setAddress(obj.get("address").toString());
		
		/* DAO */
		int result = MemberDAO.getInstance().updateMember(member);
		
		/* JSON으로 반환 */
		JSONObject obj2 = new JSONObject();
		obj2.put("isSuccess", result>0); // boolean타입으로 생성하겠다.
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(obj2);
		out.close();
		
		return null;
	}

}
