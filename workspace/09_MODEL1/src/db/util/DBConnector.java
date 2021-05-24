package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {

	// singleton
	private static DBConnector instance = new DBConnector();
	
	// DBConnector는 Connection을 생성
	private Connection con;
	private DBConnector() {
		try {
			
			// ojdbc6.jar 메모리 로드
			Class.forName(DBConfig.DRIVER);
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Connection을 가지고 있는 get
	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}
	
	// 생성된 Connection 반환
	public Connection getConnection() {
		return con;
	}

	
	// Connection 닫기
	public void close(PreparedStatement ps, ResultSet rs) {
		try {
			// if (con != null) { con.close(); } 
			// >> singleton_생성자를 만들었기 때문에 한번 닫으면 계속 close()된 con을 받아와서 오류가 난다.
			if (ps != null) { ps.close(); } 
			if (rs != null) { rs.close(); } 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
