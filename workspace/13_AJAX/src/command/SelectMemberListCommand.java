package command;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.MemberController;
import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;
import dto.Paging;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 페이징 작업 : totalRecord, beginRecord, endRecord */ 
		// JSON(obj)에 [ 해당 3가지를 전달해줘야한다. ]
		int totalRecord = MemberDAO.getInstance().getMemberCount();
		// Paging(DTO)에 전달
		Paging paging = new Paging();
		paging.setTotalRecord(totalRecord);
		
		List<Member> list = MemberDAO.getInstance().selectMemberList();
		
		JSONObject obj = new JSONObject();
		
		if(list.size() > 0) { // list안에 데이터가 있으면
			/* "JSONArray"를 사용해서 회원정보를 불러온다! */
			JSONArray arr = new JSONArray();
			// for-each문 적용
			for (Member member : list) {
				JSONObject obj2 = new JSONObject();
				obj2.put("no", member.getNo());
				obj2.put("id", member.getId());
				obj2.put("name", member.getName());
				obj2.put("gender", member.getGender());
				obj2.put("address", member.getAddress());
				arr.add(obj2);
			}
			
			obj.put("list", arr);
			obj.put("isExist", true);
			//System.out.println(obj.toJSONString());
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
