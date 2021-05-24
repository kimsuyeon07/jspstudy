package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;

public class GradeCommand implements AverageCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int kor =0;
		int eng =0;
		int mat =0;
		try {
			kor = Integer.parseInt(request.getParameter("kor"));
			eng = Integer.parseInt(request.getParameter("eng"));
			mat = Integer.parseInt(request.getParameter("mat"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int avg = (kor + eng + mat) / 3;
		
		
		char grade = ' ';
		if (avg >= 90) {
			grade = 'A';
		} else if ( avg >= 80) {
			grade = 'B';
		} else if ( avg >= 70) {
			grade = 'C';
		} else if ( avg >= 60) {
			grade = 'D';
		} else {
			grade = 'F';
		}
		
		
		
		request.setAttribute("average", avg);
		request.setAttribute("grade", grade);
		
		return new ModelAndView("views/output.jsp", false);
	}

}
