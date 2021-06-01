package common;

public class Paging {

	// SelectListBoardCommand.java(Class파일)에 필요한 클래스
	
	public static String getPaging(String path, int totalRecord, int recordPerPage, int page) {
		
		/* 전체 페이지 수 계산 */
		int totalPage = totalRecord / recordPerPage;  // 나누어 떨어지지 않을 수도 있다.
		if (totalRecord % recordPerPage != 0) { // 나누어 떨어지지 않는 값이라면
			totalPage++; // totalPage를 증가시켜준다. (나누어떨어지는 값을 가질 때까지..?)
		}
		
		/* 한 블록당 페이지를 5개 배치 */
		int pagePerBlock = 5;
		
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		if (endPage > totalPage) {
			endPage = totalPage;
			// 1 ~ 6 개의 게시글이 있을 때,
			// 1페이지: beginPage(1) , endPage(5)
			// 2페이지: beginPage(6) , endPage(10)
			//	>>> totalPage: 실제 게시글의 수
			//  >>> endPage  : 계산된 각 페이지에서의 마지막 게시글번호
			//  >>>>>>> totalPage(6) < endPage(10) 이면,
			//  >>>>>>> endPage -> "6" 으로 수정   ::  endPage = totalPage
		}
		
		/* 페이징 (이전 1 2 3 4 5 다음) */
		StringBuilder sb = new StringBuilder();
		
		// 이전
		if(beginPage <= pagePerBlock) {
			// "<span class="paging"> 이전 </span>"
			sb.append("이전&nbsp;");
		} else {
			if(path.contains("?")) { // 현재 path에 "?"을 포함하고 있다면
				// <a href = path"/10_MODEL2/selectListBoardPage.b?page=5">이전&nbsp;</a>
				sb.append("<a href=\"" + path + "&page=" + (beginPage - 1) + "\">이전&nbsp;</a>");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) + "\">이전&nbsp;</a>");
			}
		}
			
				
		
		// 1 2 3 4 5
		for(int p = beginPage; p <= endPage; p++) {
			if (p == page) { // 현재 페이지는 링크를 걸지 않는다.
				sb.append(p + "&nbsp;");
			} else {
				if (path.contains("?")) {
					sb.append("<a href=\"" + path + "&page=" + p + "\">" + p + "&nbsp;</a>");
				} else {
					sb.append("<a href=\"" + path + "?page=" + p + "\">" + p + "&nbsp;</a>");
				}
			}
		}
			
				
		
		// 다음
		if(endPage == totalPage) {
			sb.append("다음");
		} else {
			if (path.contains("?")) {
				sb.append("<a href=\"" + path + "&page=" + (endPage + 1) + "\">다음</a>");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (endPage + 1) + "\">다음</a>");
			}
		}
			
				
		/* return 리턴값(sb.toString()) */
		return sb.toString();
		
	}
	
}
