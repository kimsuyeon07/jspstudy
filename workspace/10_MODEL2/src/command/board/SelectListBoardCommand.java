package command.board;

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

public class SelectListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		/* 1. 전체 게시글 개수 구하기 */
		int totalRecord = BoardDAO.getInstace().getTotalBoardCount();
		
		/* 2. 페이지 수 처리 (파라미터로 전달) */
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		/* 3. 한 페이지에 표시할 게시글의 개수 : recordPerPage */
		int recordPerPage = 5;
		
		/* 4.
		 * totalRecord, page, recordPerPage를 통해서 
		 * beginRecord, endRecord를 계산
		*/
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = (endRecord < totalRecord) ? endRecord : totalRecord;
		 
		/* 5. DB로 보낼 Map */ 
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", beginRecord);
		map.put("endRecord", endRecord);
		
		/* 6. DAO의 selectList()메소드를 호출 */ 
		List<BoardDTO> list = BoardDAO.getInstace().selectList(map);
		
		/* 7. 페이징 처리 : 따로 클래스를 생성해서 적용할 것 >>> 관리가 편하기 때문에 */ 
		String paging = Paging.getPaging("/10_MODEL2/selectListBoardPage.b", totalRecord, recordPerPage, page);
		
		/* 8. 응답 View로 전달할 데이터 */ 
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("seq", totalRecord - (page - 1) * recordPerPage);
		
		/* 9.이동할 경로  + 이동방법(false): forward */
		ModelAndView mav = new ModelAndView("/board/listBoard.jsp", false); 
		// forward 이동은 서버내부이동이기 때문에 "/10_MODEL2"는 작성하면 오류가 난다. 

		/* 10. return 리턴 값 (mav) */
		return mav;
	}
	
	
	
	/*-------------------------설명--------------------------*/
	
	/* [ 7. 페이징 처리 ] 를 위한 준비작업
	 * ▼
	 * totalPage : totalRecord, recordPerPage (SelectListBoardCommand)
	 * beginPage : page (SelectListBoardCommand), pagePerBlock(X)
	 * endPage : beginPage, totalPage, pagePerBlock(X)
	 * ▼
	 * SelectListBoardCommand
	 * - totalRecord
	 * - recordPerPage
	 * - page
	 * 위 세 정보를 매개변수로 paging 처리하는 Paging 클래스에 전달
	 * Paging클래스에서 pagePerBlick를 직접 선언
	 * 링크 생성을 위해서 이동 경로를 Paging 클래스에 전달
	 * ▼
	 * >>> Paging클래스에 보내줘야 하는 정보
	 * 	   1) totalRecord
	 * 	   2) recordPerPage
	 * 	   3) page
	 * 	   4) 이동 경로
	 * */
	

}
