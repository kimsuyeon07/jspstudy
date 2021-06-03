package command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import common.Paging;
import dao.BoardDAO;
import dto.BoardDTO;

public class FindListCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		/* List는 Map이 필요하다. */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", "%" + query + "%");
		
		// 검색된 게시글의 개수
		int totalRecord = BoardDAO.getInstance().getFindRecordCount(map);
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int recordPerPage = 5;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		if (endRecord > totalRecord) {
			endRecord = totalRecord;
		}
		
		
		map.put("beginRecord", beginRecord);
		map.put("endRecord", endRecord);
		
		List<BoardDTO> list = BoardDAO.getInstance().findList(map);
		
		/* Paging 페이징 작업 */
		String paging = Paging.getPaging("/11_MYBATIS/findList.do?column="+column + "&query="+query, totalRecord, recordPerPage, page);
		// 검색결과는 "언제나" 이동경로에 전달받아온 파라미터값[ column, query ]이 필요하다. (그래야 이동할 위치를 찾을 수 있다.)
		
		/* request를 가지고 이동하는 "forward"이기 때문에 */
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("seq", totalRecord - (page - 1) * recordPerPage);
		
		
		
		return new ModelAndView("/board/selectList1.jsp", false);  //  포워드
	}

}
