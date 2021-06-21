package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Person;

public class PersonDAO {

	
	
	
	// singleton
	private static PersonDAO instance;
	private PersonDAO() {		}
	public static PersonDAO getInstance() {
		if (instance == null) {
			instance = new PersonDAO();
		}
		return instance;
	}
	
	/* 0. con : 접속시작 */
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "spring", "1111");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	/* 0. close() : 접속해제 */
	private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) con.close();
			if (ps != null) ps.close();
			if (rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// con, ps, rs, sql
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	/* 1. 사람 목록 */
	public List<Person> selectPersonList() {
		List<Person> list = new ArrayList<Person>();
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, AGE, BIRTHDAT, REGDATE FROM PERSON" ;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Person person = new Person();
				person.setSno(rs.getString(1));
				person.setName(rs.getString(2));
				person.setAge(rs.getInt(3));
				person.setBirthday(rs.getString(4));
				person.setRegdate(rs.getDate(5));
				list.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	/* 2. 한 사람 목록 */
	public Person selectPersonByNo(long no) {
		Person person = null;
		try {
			con = getConnection();
			sql = "SELECT SNO, NAME, AGE, BIRTHDAY, REGDATE FROM PERSON WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				person = new Person();
				person.setSno(rs.getString(1));
				person.setName(rs.getString(2));
				person.setAge(rs.getInt(3));
				person.setBirthday(rs.getString(4));
				person.setRegdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return person;
	}
	
	/* 3. 사람 추가 */
	public int insertPerson(Person person) throws SQLException {
		int count = 0;
		// try문을 사용하지 않고_ throws SQLException으로 java에 알아서 오류를 던지도록 작업
		con = getConnection();
		sql = "INSERT INTO PERSON VALUES (?, ?, ?, ?, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, person.getSno());
		ps.setString(2, person.getName());
		ps.setInt(3, person.getAge());
		ps.setString(4, person.getBirthday());
		count = ps.executeUpdate();
	
		close(con, ps, null);
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
