package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.util.DBConnector;
import dto.MemberDTO;

public class MemberDAO {

	// field
	private Connection con = null;
	private  PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;

	
	// singleton
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		con = DBConnector.getInstance().getConnection();
	}
	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	
	/* 1. 회원가입 */ 
	public int save(MemberDTO dto) {  // join.jsp가 전달한 dto
		
		// db 접속
		int result = 0;
		try {
			sql = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, ?,?,?,?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getEmail());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close( ps, null);
		}
		return result;
	}
	
	/* 2. 아이디 중복 체크 */
	public boolean isUsableId(String id) {  // idCheck.jsp가 전달한 id
		boolean result = false;
		try {
			sql = "SELECT ID FROM MEMBER WHERE ID = ?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			result = !rs.next();
			/*
			if (!rs.next()) { // 검색결과 rs가 없으면 중복이 없으므로 가입이 가능하다.
				result = true;
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close( ps, rs);
		}
		return result;
	}
	
	
}
