package common;

public class ModelAndView {

	// 응답View + 이동방식(forward  OR  redirect)
	private String view;
	private boolean isRedirect;  // reidrect이면 true, forward이면 false
	
	// constructor
	public ModelAndView( ) {};
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
