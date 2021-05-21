package model;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

public class GetTime {

	
	public String excute(HttpServletRequest request) {
		
		LocalTime  time = LocalTime.now();
		String timeTxt = time.getHour() + "시  " + time.getMinute() + "분 " + time.getSecond() + "초";
		// request에 저장하기!
		request.setAttribute("time", timeTxt);
		// return
		return "views/output.jsp";
		
		
		
	}
	
}
