package model;

import javax.servlet.http.HttpServletRequest;

public class BeerService {

	public String execute(HttpServletRequest request) {
		
		/* 나이를 전달 받아온다. */
		int age = 0;
		// try문을 사용하는 이유 : 전달 받아오는 데이터값이 숫자가 아니거나 전달받지 못했을 때를 위해
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("result", (age < 20 ? "미성년자인데?" : "여기 있습니다~!"));
		
		return "views/output.jsp";
	}
	
	
}
