package common;

public class ModelAndView {

	private String view;
	private boolean isRedirect;  // 전송 방법 분류 (redirect / forward)
	
	
	// constructor
	public ModelAndView() {}
	public ModelAndView(String view, boolean isRedirect) {
		super();
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
