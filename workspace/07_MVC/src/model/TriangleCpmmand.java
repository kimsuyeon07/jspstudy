package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class TriangleCpmmand implements ShapeCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int width=0;
		int height=0;
		
		try {
			width = Integer.parseInt(request.getParameter("width"));
			height = Integer.parseInt(request.getParameter("height"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("area", width * height / 2);
		
		return new ModelAndView("views/output.jsp", false);
	}

}
