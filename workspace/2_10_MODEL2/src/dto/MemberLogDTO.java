package dto;

public class MemberLogDTO {

	private long no;
	private String id;
	private String login;
	private String logout;
	
	// constructor
	public MemberLogDTO() {}

	
	// Getter and Setter
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	};
	
	

	
}
