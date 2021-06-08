package command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 
		 * 페이징 작업 : totalRecord(총 회원 수), beginRecord(시작하는 회원번호), endRecord(끝나는 회원번호)
		 * 		   , recordPerPage(현재 페이지에서 보여주고자 하는 회원 수)
		 * 		   , totalPage(총 페이지 수), beginPage(현재 페이지에서 시작 페이지), endPage(현재 페이지에서 마지막 페이지)
		 * 		   , pagePerBlock([5] : 이전  1 2 3 4 5  다음 : 보여주고자 하는 페이지의 개수) 
		 * 		   , page(현재페이지)
		*/ 
		
		// 파라미터 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// JSON(obj)에 [ 해당 3가지를 전달해줘야한다. ]
		int totalRecord = MemberDAO.getInstance().getMemberCount();
		
		// beginRecord, endRecord 구하기
		int recordPerPage = 2;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = (endRecord < totalRecord) ? endRecord : totalRecord;
		
		// beginRecord~endRecord : Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", beginRecord);
		map.put("endRecord", endRecord);
		
		/*------------------------------------------------------------*/
		
		// beginRecord~endRecord 목록 가져오기 + 전체 회원 호출 (DAO)
		List<Member> list = MemberDAO.getInstance().selectMemberList(map);
		
		
		// totalPage, beginPage, endPage 구하기
		int totalPage = totalRecord / recordPerPage;
		if (totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		int pagePerBlock = 3;
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		endPage = (endPage < totalPage) ? endPage : totalPage;
		
		
		
		/*
		 * 1. 페이지 관련 변수만 저장할 JSON(paging)
		 * 2. JSP로 반환할 결과 JSON(obj) _ paging을 담고 있다.
		*/
		
		JSONObject paging = new JSONObject();
		paging.put("totalRecord", totalRecord);
		paging.put("page", page);
		paging.put("totalPage", totalPage);
		paging.put("pagePerBlock", pagePerBlock);
		paging.put("beginPage", beginPage);
		paging.put("endPage", endPage);
		
		JSONObject obj = new JSONObject();
		obj.put("paging", paging); 
		// (결과 값) obj = { paging:{totalRecord..}, list={}, isSuccess="true"}  
		
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
