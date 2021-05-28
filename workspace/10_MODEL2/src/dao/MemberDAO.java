package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.MemberDTO;
import oracle.jdbc.driver.DBConversion;

public class MemberDAO {

	private Connection con ;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	/* import javax.sql.DataSource; */
	private static DataSource dataSource; // 커넥션풀 관리 (하는 클래스)
	
	// static 필드의 초기화 : static 블록에서 처리
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");  
			// 톰캣용 (java:comp/env), Resource이름 (jdbc/oracle)
		} catch (NamingException e) {
			 e.printStackTrace();
		}
	}
	
	
	// singleton
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	
	/* 1. 접속 해제 */
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 2. 로그인 : 로그인된 멤버(반환 타입) */
	/* 커넥션(Connection)은 메소드마다 열고 닫는 것이 가장 좋다. */
	public MemberDTO login(MemberDTO dto) {  // dto에 저장된 'id', 'pw'를 가져오기 위해서
		MemberDTO loginDTO = null;
		try {
			con = dataSource.getConnection();  // Connection을 가지고 있는 dataSource로 부터 con을 가지온다.
			/* .xml 파일에서 만들어둔 [ maxTotal="8" ] 중 한개의 커넥션을 가지고 오는 것  */
			sql = "SELECT NO, ID, PW, NAME, EMAIL, REGDATE FROM MEMBER WHERE ID = ? AND PW = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setNString(2, dto.getPw());
			rs = ps.executeQuery();
			if (rs.next()) {
				loginDTO = new MemberDTO();
				loginDTO.setNo(rs.getLong(1));
				loginDTO.setId(rs.getString(2));
				loginDTO.setPw(rs.getString(3)); // =>> 원래는, 비밀번호를 암호화 처리해서 (64비트값으로 통일화)하여 DB에 저장할 것. [아직은 못함]  
				loginDTO.setName(rs.getString(4));
				loginDTO.setEmail(rs.getString(5));
				loginDTO.setRegdate(rs.getDate(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		/* 리턴 */
		return loginDTO;
	}
	
	/* 3. 로그인 로그 남기기 */
	public void loginLog(MemberDTO dto) {  // 어떤 회원이 로그인 했는지 확인
		try {
			con = dataSource.getConnection();  // Connection을 가지고 있는 dataSource로 부터 con을 가지온다.
			sql = "INSERT INTO MEMBER_LOG VALUES (MEMBER_LOG_SEQ.NEXTVAL, ?, SYSDATE, NULL)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
