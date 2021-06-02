package mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// 역할 : SqlSessionFactory를 빌드해서 DAO에게 넘겨준다.
public class DBService {

	
	// Singleton
	private static DBService instance = new DBService();
	/* 추가로 필요한 작업 : 공장을 하나 만들어 준다. */
	private SqlSessionFactory factory;
	
	private DBService() {
		// SqlSessionFactory 빌드
		try {
			String resource = "mybatis/config/sqlmap-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);  // SqlSessionFactoryBuilder : 공장을 짓는 사람
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DBService getInstance() {
		if (instance == null) {
			instance = new DBService();
		}
		return instance;
	}
	
	public SqlSessionFactory getFactory() {
		return factory;
	}
	
	
	/*
	 * DAO >>> DBService.getInstance().getFactory()
	 * 에서 사용할 코드!
	 * */
	
}
