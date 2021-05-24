package command;

public class ModelAndView {

	private String view;
	private boolean isRedirect;
	
	// constructor
	public ModelAndView() {};
	public ModelAndView(String view, boolean isRedirect) {
		this.view = view;
		this.isRedirect = isRedirect;
	}

	// Getter and Setter
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
}
