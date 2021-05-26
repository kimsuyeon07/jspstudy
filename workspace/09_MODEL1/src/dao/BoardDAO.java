package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.util.DBConnector;
import dto.BoardDTO;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Singleton
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {
		con = DBConnector.getInstance().getConnection();
	}
	public static BoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	
	/* 1. 게시글 삽입 */ 
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		try {
			sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, SYSDATE)";
			ps =con.prepareStatement(sql);
			ps.setString(1, dto.getAuthor());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getContent());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, null);
		}
		return result;
	}
	
	/* 2. 전체 게시글 반환 */
	public List<BoardDTO> selectAll() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			sql="SELECT IDX, AUTHOR, TITLE, CONTENT, HIT, POSTDATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setIdx(rs.getLong(1));
				dto.setAuthor(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setHit(rs.getInt(5));
				dto.setPostdate(rs.getDate(6));
				list.add(dto);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(ps, rs);
		}
		return list;
	}
		
		
		
	
	
	
	
}
