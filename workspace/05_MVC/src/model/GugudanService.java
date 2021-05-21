package model;

import javax.servlet.http.HttpServletRequest;

public class GugudanService {

	// public 메소드
	// 응답을 받아야 하기 때문에 무조건 'String'
	public String execute(HttpServletRequest request) {
		
		String strDan = request.getParameter("dan");
		
		int dan = 1;
		if (!strDan.isEmpty()) {
			dan = Integer.parseInt(strDan);
		}
		// 사용 이유 : 그냥 String보다 처리속도가 빠르다.
		StringBuilder sb = new StringBuilder();
		for (int n = 1 ; n <= 9 ; n++) {
			sb.append(dan).append("X").append(n).append("=").append(dan*n).append("<br>");
		}
		// StringBuilder은 저장할 때 String으로 변환 해줘야 한다.
		request.setAttribute("gugudan", sb.toString());
		
		return "views/output.jsp";
	}
		
}
