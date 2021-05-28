package dto;

import java.util.Date;

public class MemberDTO {

	private long no;
	private String id;
	private String pw;
	private String name;
	private String email;
	private Date regdate;

	// constructor
	public MemberDTO(){}
	public MemberDTO(String id, String name, String email, Date regdate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.regdate = regdate;
	}
	
	

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

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	


	
	
	
}