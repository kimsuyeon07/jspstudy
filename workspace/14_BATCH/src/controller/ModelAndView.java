package controller;

public class ModelAndView {

	private String view;
	private boolean isReidrect;
	
	
	// constructor
	public ModelAndView () {}
	public ModelAndView(String view, boolean isReidrect) {
		super();
		this.view = view;
		this.isReidrect = isReidrect;
	}

	// Getter and Setter
	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public boolean isReidrect() {
		return isReidrect;
	}

	public void setReidrect(boolean isReidrect) {
		this.isReidrect = isReidrect;
	}
	
	
	
}
