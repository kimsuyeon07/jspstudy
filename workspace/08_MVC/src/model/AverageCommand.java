package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ModelAndView;

public interface AverageCommand {
	
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response);
	
}
